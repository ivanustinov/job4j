package testTask;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 10.05.2018
 */
public class OrdersTest {
    Orders orders = new Orders("IBM");
    @Test
    public void addOrdersTest() {
        orders.addOrder(new Order(1, "IBM", "Create", "Buy",15, 10));
        orders.addOrder(new Order(2, "IBM", "Create", "Buy",15, 15));
        orders.addOrder(new Order(1, "IBM", "Delete", "Buy"));
        orders.addOrder(new Order(3, "IBM", "Create", "Sale", 10, 40));
        orders.addOrder(new Order(4, "IBM", "Create", "Sale", 10, 17));
        orders.addOrder(new Order(5, "IBM", "Create", "Buy", 2, 4));
        orders.addOrder(new Order(6, "IBM", "Create", "Sale", 6, 7));
        System.out.println(orders);
//        orders.addOrder(new Order(1, "IBM", "Create", "Byu",15, 10));
//        orders.addOrder(new Order(1, "IBM", "Create", "Byu",15, 10));
    }

}