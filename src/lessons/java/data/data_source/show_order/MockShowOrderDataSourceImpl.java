package lessons.java.data.data_source.show_order;

import lessons.java.data.models.Order;

public class MockShowOrderDataSourceImpl extends ShowOrderDataSource {

    public Order order;

    @Override
    public Order showOrder() {
        return order;
    }

    @Override
    public void setOrder(Order order) {
        this.order = order;
    }
}
