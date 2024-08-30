package lessons.java.data.service;

import lessons.java.data.data_source.cart.CartDataSource;
import lessons.java.data.data_source.catalog.CatalogDataSource;
import lessons.java.data.data_source.make_an_order.MakeAnOrderDataSource;
import lessons.java.data.data_source.show_order.ShowOrderDataSource;
import lessons.java.data.models.*;

import java.util.ArrayList;
import java.util.Comparator;

public class ShopService {
    final CatalogDataSource catalogDataSource;
    final CartDataSource cartDataSource;
    final MakeAnOrderDataSource makeAnOrderDataSource;
    final ShowOrderDataSource showOrderDataSource;

    public ShopService(CatalogDataSource catalogDataSource, CartDataSource cartDataSource, MakeAnOrderDataSource makeAnOrderDataSource, ShowOrderDataSource showOrderDataSource) {
        this.catalogDataSource = catalogDataSource;
        this.cartDataSource = cartDataSource;
        this.makeAnOrderDataSource = makeAnOrderDataSource;
        this.showOrderDataSource = showOrderDataSource;
    }

    public ArrayList<Product> getCatalog(int page, int limit, Comparator<Product> comparator) {
        return catalogDataSource.getCatalog(page, limit, comparator);
    }

    public boolean addToCart(String productId, int count) {
        Product product = catalogDataSource.productById(productId);
        if (product != null) {
            cartDataSource.addToCart(product, count);
            return true;
        }
        return false;
    }

    public Order showOrder() {
        return showOrderDataSource.showOrder();
    }

    public void saveOrder(Order order) {
        showOrderDataSource.setOrder(order);
    }

    public Cart getCart() {
        return cartDataSource.getCart();
    }

    public void clearCart() {
        cartDataSource.clearCart();
    }

    public boolean removeCartItem(String cartItemId) {
        return cartDataSource.removeCartItem(cartItemId);
    }

    public boolean createOrder(Order order) {
        return makeAnOrderDataSource.createOrder(order);
    }
}