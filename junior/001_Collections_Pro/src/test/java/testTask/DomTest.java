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
        ibm.addOrder(new Order(1, "IBM", "Create", "Buy", 3, 30));
        ibm.addOrder(new Order(2, "IBM", "Create", "Buy", 6, 10));
        ibm.addOrder(new Order(3, "IBM", "Create", "Sale", 5, 15));
        ibm.addOrder(new Order(3, "IBM", "Delete"));
//        ibm.addOrder(new Order(2, "IBM", "Delete"));
        System.out.println(ibm);
    }

}