package lessons.java.data.data_source.make_an_order;

import lessons.java.data.models.Order;

public abstract class MakeAnOrderDataSource {
    public abstract boolean createOrder(Order order);
}