package lessons.java.view;

import lessons.java.common.AppView;
import lessons.java.data.models.Order;
import lessons.java.data.service.ShopService;

import java.util.ArrayList;

public class ShowOrderView extends AppView {
    public ShowOrderView(ShopService shopService) {
        super("Посмотреть заказ", new ArrayList<>());
        this.shopService = shopService;
    }

    final ShopService shopService;

    @Override
    public void action() {
        Order order = shopService.showOrder();

        if (order == null) {
            System.err.println("\nЗаказ еще не оформлен");
        } else {
            System.out.println("\nИнформация о заказе: ");
            order.printDetails();
        }
    }
}