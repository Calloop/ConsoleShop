package lessons.java.common;

import lessons.java.data.comparators.AppComparator;
import lessons.java.data.models.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AppView {
    public String title;
    public final ArrayList<AppView> children;
    public final int pageLimit;
    public int nowPage;
    public boolean hasNextPage;
    public final ArrayList<AppComparator<Product>> availableComparators;
    public AppComparator<Product> selectedComparator;

    protected AppView(String title, ArrayList<AppView> children) {
        this.title = title;
        this.children = children;
        availableComparators = new ArrayList<>();
        nowPage = 0;
        pageLimit = 5;
        hasNextPage = false;
    }

    public List<String> getComparatorOptions() {
        if (availableComparators.isEmpty()) {
            return Collections.emptyList();
        }
        return availableComparators.stream()
                .map(AppComparator::getDescription)
                .collect(Collectors.toList());
    }

    public void action() {
    }
}