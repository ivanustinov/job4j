package testTask;

import java.util.HashMap;
import java.util.HashSet;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 11.05.2018
 */
public class OrdersApp {
    private HashMap<String, Orders> firms;

    public OrdersApp() {
        this.firms = new HashMap<>();
    }

    public void putOrder(Order order) {
        String key = order.getBook();
        if (firms.get(key) == null) {
            Orders firm = new Orders(key);
            firm.addOrder(order);
            firms.put(key, firm);
        } else {
            firms.get(key).addOrder(order);
        }
    }

    /**
     * Gets firms
     *
     * @return value of firms
     */

    public HashMap<String, Orders> getFirms() {
        return firms;
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
