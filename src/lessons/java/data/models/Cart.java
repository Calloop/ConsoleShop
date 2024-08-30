package lessons.java.data.models;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<CartItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void addItem(CartItem item) {
        items.add(item);
    }

    public void clearItems() {
        items.clear();
    }

    public boolean removeItemById(String cartItemId) {
        for (int i = 0; i < items.size(); i++) {
            CartItem item = items.get(i);
            if (item.product.id.equals(cartItemId)) {
                items.remove(i);
                return true;
            }
        }

        return false;
    }

    public List<CartItem> getItems() {
        return items;
    }
}