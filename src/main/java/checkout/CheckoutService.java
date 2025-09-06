package checkout;

import org.example.Book;
import org.example.Cart;
import org.example.CartItem;
import org.example.Order;
import org.example.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CheckoutService {

    private long orderIdSequence = 1;

    // checkout 1 phần giỏ hàng
    public Order checkout(User user, Cart cart, Map<Book, Integer> checkoutItems) {

        List<CartItem> orderedItems = new ArrayList<>();

        for (Map.Entry<Book, Integer> entry : checkoutItems.entrySet()) {
            Book book = entry.getKey();
            int quantityToCheckout = entry.getValue();

            CartItem itemInCart = cart.getItems().stream()
                    .filter(i -> i.getBook().equals(book))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Book not found in cart"));

            if (quantityToCheckout > itemInCart.getQuantity()) {
                throw new RuntimeException("Checkout quantity > quantity in cart for book: " + book.getTitle());
            }
            if (quantityToCheckout > book.getStockQuantity()) {
                throw new RuntimeException("Not enough stock for book: " + book.getTitle());
            }

            // giảm tồn kho
            book.setStockQuantity(book.getStockQuantity() - quantityToCheckout);

            // tạo CartItem để gắn vào Order
            orderedItems.add(new CartItem(book, quantityToCheckout));

            // cập nhật lại giỏ
            itemInCart.setQuantity(itemInCart.getQuantity() - quantityToCheckout);
            if (itemInCart.getQuantity() == 0) {
                cart.getItems().remove(itemInCart);
            }
        }

        // tạo order
        Order order = new Order(user, orderedItems);
        order.setId(orderIdSequence++);

        return order;
    }
}
