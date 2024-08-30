package lessons.java.view;

import lessons.java.common.AppView;
import lessons.java.data.service.ShopService;

import java.util.ArrayList;
import java.util.Scanner;

public class ClearCartView extends AppView {
    public ClearCartView(ShopService shopService) {
        super("Очистить корзину", new ArrayList<>());
        this.shopService = shopService;
    }

    final ShopService shopService;

    @Override
    public void action() {
        System.out.println("1 - Подтвердить очистку");
        System.out.println("2 - назад");

        Scanner in = new Scanner(System.in);
        int count = in.nextInt();

        if (count != 2) {
            if (count == 1) {
                shopService.clearCart();
            } else {
                System.out.println("Неверный ввод. Повторите попытку.");
            }
        }
    }
}