package testTask;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 07.05.2018
 */
public class DomTest {
    Dom ibm = new Dom("IBM");

    @Test
    public void createDom() {
        ibm.addOrder(new Order(1, "IBM", "Create", "Buy", 6, 30));
        ibm.addOrder(new Order(2, "IBM", "Create", "Buy", 6, 10));
        ibm.addOrder(new Order(3, "IBM", "Create", "Sale", 10, 40));
        ibm.addOrder(new Order(4, "IBM", "Create", "Sale", 6, 40));
        ibm.addOrder(new Order(5, "IBM", "Create", "Buy", 8, 30));
        ibm.addOrder(new Order(6, "IBM", "Create", "Buy", 9, 20));
        ibm.addOrder(new Order(7, "IBM", "Create", "Buy", 5, 40));
        ibm.addOrder(new Order(8, "IBM", "Create", "Sale", 11, 70));
        ibm.addOrder(new Order(9, "IBM", "Create", "Sale", 12, 60));
        ibm.addOrder(new Order(10, "IBM", "Create", "Sale", 13, 80));
        ibm.addOrder(new Order(11, "IBM", "Create", "Sale", 14, 30));
        ibm.addOrder(new Order(11, "IBM", "Create", "Sale", 15, 40));
        ibm.addOrder(new Order(10, "Delete"));
        System.out.println(ibm);
    }

}