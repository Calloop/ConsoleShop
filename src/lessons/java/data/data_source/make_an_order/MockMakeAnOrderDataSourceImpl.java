package lessons.java.data.data_source.make_an_order;

import lessons.java.data.models.Order;

public class MockMakeAnOrderDataSourceImpl extends MakeAnOrderDataSource {

    @Override
    public boolean createOrder(Order order) {
        return order != null;
    }
}