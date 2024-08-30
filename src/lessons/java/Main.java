package lessons.java;

import lessons.java.common.AppView;
import lessons.java.common.PageLoop;
import lessons.java.controller.UserController;
import lessons.java.data.comparators.AppComparator;
import lessons.java.data.comparators.PriceComparator;
import lessons.java.data.data_source.cart.CartDataSource;
import lessons.java.data.data_source.cart.MockCartDataSourceImpl;
import lessons.java.data.data_source.catalog.CatalogDataSource;
import lessons.java.data.data_source.catalog.MockCatalogDataSourceImpl;
import lessons.java.data.data_source.make_an_order.MockMakeAnOrderDataSourceImpl;
import lessons.java.data.data_source.make_an_order.MakeAnOrderDataSource;
import lessons.java.data.data_source.show_order.MockShowOrderDataSourceImpl;
import lessons.java.data.data_source.show_order.ShowOrderDataSource;
import lessons.java.data.data_source.user.FileUserRepository;
import lessons.java.data.models.Product;
import lessons.java.data.service.ShopService;
import lessons.java.data.service.UserService;
import lessons.java.view.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        FileUserRepository userRepository = new FileUserRepository("src/resources/userData/users.txt");
        ShopService shopService = getShopService(userRepository);

        ArrayList<AppView> mainChildren = getAppViews(shopService);
        AppView mainView = new MainView(mainChildren);

        new PageLoop(mainView).run();
    }

    private static ShopService getShopService(FileUserRepository userRepository) {
        UserService userService = new UserService(userRepository);
        UserController userController = new UserController(userService);

        userController.menu();

        CartDataSource cartDataSource = new MockCartDataSourceImpl();
        CatalogDataSource catalogDataSource = new MockCatalogDataSourceImpl();
        MakeAnOrderDataSource makeAnOrderDataSource = new MockMakeAnOrderDataSourceImpl();
        ShowOrderDataSource showOrderDataSource = new MockShowOrderDataSourceImpl();
        return new ShopService(catalogDataSource, cartDataSource, makeAnOrderDataSource, showOrderDataSource);
    }

    private static ArrayList<AppView> getAppViews(ShopService shopService) {
        AppView addToCartView = new AddToCartView(shopService);
        AppView clearCartView = new ClearCartView(shopService);
        AppView removeCartItemView = new RemoveCartItemView(shopService);


        ArrayList<AppView> catalogChildren = new ArrayList<>();
        catalogChildren.add(addToCartView);

        ArrayList<AppComparator<Product>> catalogComparators = new ArrayList<>();
        catalogComparators.add(new AppComparator<>(new PriceComparator(true), "По возрастанию цены"));
        catalogComparators.add(new AppComparator<>(new PriceComparator(false), "По убыванию цены"));

        AppView catalogView = new CatalogView(shopService, catalogChildren, catalogComparators);

        ArrayList<AppView> cartChildren = new ArrayList<>();
        cartChildren.add(removeCartItemView);
        cartChildren.add(clearCartView);
        AppView cartView = new CartView(shopService, cartChildren);

        AppView makeAnOrderView = new MakeAnOrder(shopService);

        AppView showOrderView = new ShowOrderView(shopService);

        ArrayList<AppView> mainChildren = new ArrayList<>();
        mainChildren.add(catalogView);
        mainChildren.add(cartView);
        mainChildren.add(makeAnOrderView);
        mainChildren.add(showOrderView);
        return mainChildren;
    }
}