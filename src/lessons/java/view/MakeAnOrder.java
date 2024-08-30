package lessons.java.view;

import lessons.java.common.AppView;
import lessons.java.data.models.CartItem;
import lessons.java.data.models.Order;
import lessons.java.data.service.ShopService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MakeAnOrder extends AppView {
    public MakeAnOrder(ShopService shopService) {
        super("Оформление заказа", new ArrayList<>());
        this.shopService = shopService;
    }

    final ShopService shopService;

    @Override
    public void action() {
        List<CartItem> cartItems = shopService.getCart().getItems();

        if (cartItems.isEmpty()) {
            System.err.println("\nСначала добавьте товар в корзину");
        } else {
            Scanner in = new Scanner(System.in);

            System.out.println("Введите своё имя");
            String name = in.nextLine();

            System.out.println("Введите свой телефон");
            String phone = in.nextLine();

            System.out.println("Введите свой адрес");
            String address = in.nextLine();

            System.out.println("Введите способ платежа");
            String paymentType = in.nextLine();

            System.out.println("Введите время доставки");
            String deliveryTime = in.nextLine();
            Order order = new Order(name, phone, address, paymentType, deliveryTime, cartItems);
            final boolean res = shopService.createOrder(order);

            if (res) {
                System.out.println("\nЗаказ оформлен\n");
                saveOrder(order);
            } else {
                System.err.println("\nНе удалось оформить заказ\n");
            }
        }
    }

    void saveOrder(Order order) {
        shopService.saveOrder(order);
    }
}