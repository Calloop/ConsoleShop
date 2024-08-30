package lessons.java.data.models;

import java.util.List;

public class Order {
    final public String name;
    final public String phone;
    final public String address;
    final public String paymentType;
    final public String deliveryTime;
    final public List<CartItem> cart;

    public Order(String name, String phone, String address, String paymentType, String deliveryTime, List<CartItem> cart) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.paymentType = paymentType;
        this.deliveryTime = deliveryTime;
        this.cart = cart;
    }

    public void printDetails() {
        System.out.println("Имя: " + name);
        System.out.println("Телефон: " + phone);
        System.out.println("Адрес: " + address);
        System.out.println("Тип платежа: " + paymentType);
        System.out.println("Время доставки: " + deliveryTime);

        System.out.println("  Корзина: ");

        for (CartItem cartItem : cart) {
            System.out.println("Товар: " + cartItem.product.title + ", ID " + cartItem.product.id);
            System.out.println("Количество: " + cartItem.count);
        }
    }
}