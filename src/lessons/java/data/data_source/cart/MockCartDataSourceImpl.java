package lessons.java.data.data_source.cart;

import lessons.java.data.models.Cart;
import lessons.java.data.models.CartItem;
import lessons.java.data.models.Product;

import java.util.List;

public class MockCartDataSourceImpl extends CartDataSource {

    private final Cart cart = new Cart();

    @Override
    public void addToCart(Product product, int count) {
        cart.addItem(new CartItem(product, count));
    }

    @Override
    public Cart getCart() {
        return cart;
    }

    @Override
    public void clearCart() {
        cart.clearItems();
    }

    @Override
    public boolean removeCartItem(String cartIemId) {
        return cart.removeItemById(cartIemId);
    }
}