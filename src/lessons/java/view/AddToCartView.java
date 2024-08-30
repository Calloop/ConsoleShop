package lessons.java.view;

import lessons.java.common.AppView;
import lessons.java.data.service.ShopService;

import java.util.ArrayList;
import java.util.Scanner;

public class AddToCartView extends AppView {
    public AddToCartView(ShopService shopService) {
        super("Добавить товар", new ArrayList<>());
        this.shopService = shopService;
    }

    final ShopService shopService;

    @Override
    public void action() {
        Scanner in = new Scanner(System.in);

        System.out.println("Введите id продукта");
        String productId = in.nextLine();

        System.out.println("Введите количество");
        int count = in.nextInt();

        boolean res = shopService.addToCart(productId, count);

        System.out.println(res ? "\nТовар добавлен" : "\nОшибка при добавлении товара");
    }
}