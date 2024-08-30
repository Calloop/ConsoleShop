package lessons.java.view;

import lessons.java.common.AppView;
import lessons.java.data.models.Cart;
import lessons.java.data.models.CartItem;
import lessons.java.data.service.ShopService;

import java.util.ArrayList;
import java.util.List;

public class CartView extends AppView {
    final ShopService shopService;

    public CartView(ShopService shopService, ArrayList<AppView> children) {
        super("Корзина", children);
        this.shopService = shopService;
    }

    @Override
    public void action() {
        Cart cart = shopService.getCart();
        List<CartItem> cartItems = cart.getItems();

        if (cartItems.isEmpty()) {
            System.err.println("\nДобавьте товар в корзину");
        } else {

            System.out.println("\nТовары в корзине (" + cartItems.size() + "):");

            for (CartItem cartItem : cartItems) {
                System.out.println(cartItem.product.id + " " + cartItem.product.title + " " + cartItem.count);
            }
            System.out.println();
        }
    }
}