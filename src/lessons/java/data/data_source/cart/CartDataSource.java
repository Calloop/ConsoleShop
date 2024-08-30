package lessons.java.data.data_source.cart;

import lessons.java.data.models.Cart;
import lessons.java.data.models.Product;

public abstract class CartDataSource {
    public abstract void addToCart(Product product, int count);
    public abstract Cart getCart();
    public abstract void clearCart();
    public abstract boolean removeCartItem(String cartIemId);
}