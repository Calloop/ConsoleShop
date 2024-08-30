package lessons.java.data.data_source.show_order;

import lessons.java.data.models.Order;

public abstract class ShowOrderDataSource {
    public abstract Order showOrder();
    public abstract void setOrder(Order order);
}
