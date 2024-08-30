package lessons.java.view;

import lessons.java.common.AppView;
import lessons.java.data.models.Cart;
import lessons.java.data.models.CartItem;
import lessons.java.data.service.ShopService;

import java.util.ArrayList;
import java.util.Scanner;

public class RemoveCartItemView extends AppView {
    public RemoveCartItemView(ShopService shopService) {
        super("Удалить товар из корзины", new ArrayList<>());
        this.shopService = shopService;
    }

    final ShopService shopService;

    @Override
    public void action() {
        Cart cart = shopService.getCart();
        System.out.println("\nТовары в корзине: ");
        for (CartItem cartItem : cart.getItems()) {
            System.out.println(cartItem.product.id + " " + cartItem.product.title + " " + cartItem.count);
        }
        System.out.println();

        Scanner in = new Scanner(System.in);

        boolean itemRemoved = false;
        while (!itemRemoved) {
            System.out.println("Введите id товара: ");
            String cartItemId = in.nextLine();

            boolean res = shopService.removeCartItem(cartItemId);

            if (res) {
                System.out.println("\nТовар " + cartItemId + " удален из корзины\n");
                itemRemoved = true;
            } else {
                System.err.println("\nТовара нет в корзине. Введите правильный ID");
            }
        }
    }
}