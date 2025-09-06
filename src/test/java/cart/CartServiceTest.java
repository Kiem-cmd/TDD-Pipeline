package cart;

import org.example.Book;
import org.example.Cart;
import org.example.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CartServiceTest {

    private CartService cartService;
    private User user;
    private Cart cart;

    @BeforeEach
    void setUp() {
        cartService = new CartService();
        user = new User(1, "Alice");
        cart = cartService.createCart(user);
    }
    @Test
    void addToCart_WhenOutOfStock_ShouldThrowException() {
        Book book = new Book("Domain-Driven Design", 0); // hết hàng
        Cart cart = cartService.createCart(user);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> cartService.addToCart(cart, book, 2));

        assertTrue(ex.getMessage().contains("đã hết hàng"));
    }
    @Test
    void addToCart_WhenEnoughStock_ShouldReduceStockAndAddItem() {
        Book book = new Book("Clean Code", 100);

        cartService.addToCart(cart, book, 3);

        assertEquals(3, cart.getItems().getFirst().getQuantity());
        assertEquals(100, book.getStockQuantity());
    }

    @Test
    void addToCart_WhenNotEnoughStock_ShouldAddMaxStock() {
        Book book = new Book("Domain-Driven Design", 3);
        cartService.addToCart(cart, book, 5);
        assertEquals(3, cart.getItems().getFirst().getQuantity());
    }

}