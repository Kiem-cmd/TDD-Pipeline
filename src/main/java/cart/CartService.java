package cart;
import org.example.Cart;
import org.example.CartItem;
import org.example.User;
import org.example.Book;

public class CartService {

    public Cart createCart(User user) {
        return new Cart(user);
    }

    public void addToCart(Cart cart, Book book, int quantity) {
        int available = book.getStockQuantity();
        if (available <= 0) {
            throw new RuntimeException("Sản phẩm " + book.getTitle() + " đã hết hàng!");
        }
        int finalQuantity = Math.min(quantity, available);
        CartItem item = new CartItem(book, finalQuantity);
        cart.getItems().add(item);
    }
}
