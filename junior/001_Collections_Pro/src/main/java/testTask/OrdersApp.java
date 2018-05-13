package testTask;

import java.util.HashMap;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 11.05.2018
 */
public class OrdersApp {
    private HashMap<String, Orders> firms;
    private HashMap<Integer, Order> orders;

    public OrdersApp() {
        this.firms = new HashMap<>();
        this.orders = new HashMap<>();
    }

    public void putOrder(Order order) {
        int key = order.getId();
        String firm = order.getBook();
        if (orders.get(key) != null) {
            if (order.getAction().equals("Delete")) {
                orders.remove(order.getId());
                putOrderInFirm(order);
            } else {
                System.out.println("Wrong Id");
                return;
            }
        } else {
            putOrderInFirm(order);
            Order orderToPut = firms.get(firm).getOrder(key);
            if (orderToPut != null) {
                orders.put(order.getId(), orderToPut);
            }
        }
    }

    public void putOrderInFirm(Order order) {
        String key = order.getBook();
        if (firms.get(key) == null && !order.getAction().equals("Delete")) {
            Orders firm = new Orders(key);
            firm.addOrder(order);
            firms.put(key, firm);
        } else {
            firms.get(key).addOrder(order);
        }
    }

    public Order getOrder(Integer id) {
        return orders.get(id);
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        for (Orders orders : firms.values()) {
            buffer.append(orders.toString());
        }
        return buffer.toString();
    }
}
