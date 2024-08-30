package lessons.java.view;

import lessons.java.common.AppView;
import lessons.java.data.comparators.AppComparator;
import lessons.java.data.comparators.PriceComparator;
import lessons.java.data.models.Product;
import lessons.java.data.service.ShopService;

import java.util.ArrayList;

public class CatalogView extends AppView {

    final ShopService shopService;

    public CatalogView(ShopService shopService, ArrayList<AppView> children, ArrayList<AppComparator<Product>> comparators) {
        super("Каталог", children);
        this.shopService = shopService;
        availableComparators.addAll(comparators);

        if (!availableComparators.isEmpty()) {
            selectedComparator = availableComparators.get(0);
        }
    }

    @Override
    public void action() {
        ArrayList<Product> catalog = shopService.getCatalog(nowPage, pageLimit, selectedComparator.comparator);
        hasNextPage = catalog.size() == pageLimit;

        System.out.println("\nТовары в каталоге:");

        for (Product product : catalog) {
            System.out.println(product.id + " " + product.title + " " + product.price);
        }
    }
}