package checkout;

import cart.CartService;
import org.example.Book;
import org.example.Cart;
import org.example.CartItem;
import org.example.Order;
import org.example.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutServiceTest {

    private CheckoutService checkoutService;
    private CartService cartService;
    private Cart cart;
    private Cart cart1;
    private Book cleanCode;
    private Book ddd;
    private User user;
    private User user1;

    @BeforeEach
    void setUp() {
        checkoutService = new CheckoutService();
        cartService = new CartService();
        user = new User(1, "Alice");
        user1 = new User(2, "Bob");

        cart = cartService.createCart(user);
        cart1 = cartService.createCart(user1);

        cleanCode = new Book("Clean Code", 10);
        ddd = new Book("Domain-Driven Design", 4);

        // add vào cart
        cartService.addToCart(cart1, ddd, 3);
        cartService.addToCart(cart, cleanCode, 3);
        cartService.addToCart(cart, ddd, 2);
    }


    @Test
    void checkoutMoreThanInCart_ShouldThrowException() {
        Map<Book, Integer> checkoutItems = new HashMap<>();
        checkoutItems.put(cleanCode, 5); // giỏ chỉ có 3

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> checkoutService.checkout(user, cart, checkoutItems));

        assertTrue(ex.getMessage().contains("Checkout quantity > quantity in cart"));
    }

    @Test
    void checkoutExactQuantity_ShouldRemoveItemFromCart() {
        Map<Book, Integer> checkoutItems = new HashMap<>();
        checkoutItems.put(ddd, 2);

        Order order = checkoutService.checkout(user, cart, checkoutItems);

        assertEquals(2, ddd.getStockQuantity());
        int dddQuantityInCart = cart.getItems().stream()
            .filter(i -> i.getBook().equals(ddd))
            .map(CartItem::getQuantity)
            .findFirst()
            .orElse(0);  //
        assertEquals(0, dddQuantityInCart);
    }

    @Test
    void twoUsersCheckout_SecondUserOutOfStock_ShouldThrowException() {
        Map<Book, Integer> checkoutItemsUser1 = new HashMap<>();
        checkoutItemsUser1.put(ddd, 3);

        Map<Book, Integer> checkoutItemsUser2 = new HashMap<>();
        checkoutItemsUser2.put(ddd, 2);

        Order order1 = checkoutService.checkout(user1, cart1, checkoutItemsUser1);
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> checkoutService.checkout(user, cart, checkoutItemsUser2));

        assertTrue(ex.getMessage().contains("Not enough stock"));
    }
}
