package testTask;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 10.05.2018
 */
public class OrdersTest {
    private OrdersApp market = new OrdersApp();
    private final static String KODAK = "Kodak";
    private final static String IBM = "IBM";

    public void createMarketKodak() {
        //Add sail orders.
        market.putOrder(new Order(1, KODAK, "Create", "Sale", 15, 8));
        market.putOrder(new Order(2, KODAK, "Create", "Sale", 12, 11));
        market.putOrder(new Order(3, KODAK, "Create", "Sale", 11, 22));
        //Add Buy orders.
        market.putOrder(new Order(4, KODAK, "Create", "Buy", 4, 13));
        market.putOrder(new Order(5, KODAK, "Create", "Buy", 7, 17));
        //Try to rewrite the Order
        market.putOrder(new Order(5, KODAK, "Create", "Buy", 8, 12));
    }
    public void createMarketIBM() {
        //Add sail orders.
        market.putOrder(new Order(1, IBM, "Create", "Sale", 12, 8));
        market.putOrder(new Order(2, IBM, "Create", "Sale", 12, 11));
        market.putOrder(new Order(3, IBM, "Create", "Sale", 11, 22));
        //Add Buy orders.
        market.putOrder(new Order(4, IBM, "Create", "Buy", 7, 13));
        market.putOrder(new Order(5, IBM, "Create", "Buy", 7, 17));
        //Try to rewrite the Order
        market.putOrder(new Order(5, IBM, "Create", "Buy", 8, 17));
    }

    @Test
    public void addOrderMethodTest() {
        createMarketKodak();
        Orders kodak = market.getFirms().get(KODAK);
        assertThat(kodak.getOrder(1), is(new Order(1, 15, 8)));
        assertThat(kodak.getOrder(2), is(new Order(2, 12, 11)));
        assertThat(kodak.getOrder(4), is(new Order(4, 4, 13)));
        assertThat(kodak.getOrder(5), is(new Order(5, 7, 17)));
        System.out.println(kodak);
    }
    @Test
    public void removeOrderMethodTest() {
        createMarketKodak();
        market.putOrder(new Order(4, KODAK, "Delete", "Buy", 10, 13));
        market.putOrder(new Order(5, KODAK, "Delete", "Buy", 10, 13));
        Orders kodak = market.getFirms().get(KODAK);
        Order order = null;
        assertThat(kodak.getOrder(4), is(order));
        assertThat(kodak.getOrder(5), is(order));
        System.out.println(kodak);
    }

    @Test
    public void whenMustBuyOrSaleTheBestPriceWeHave() {
        createMarketKodak();

        Orders kodak = market.getFirms().get(KODAK);
        market.putOrder(new Order(6, KODAK, "Create", "Buy", 30, 11));
        Order order = null;
        //Order with id 3 - the best suggestion. price - 11, volume - 22 before trading.
        assertThat(kodak.getOrder(3), is(new Order(3, 11, 11)));
        //No order with id 6 in the list because we had done it at the time it appeared.
        assertThat(kodak.getOrder(6), is(order));
        //Trying to sail
        market.putOrder(new Order(7, KODAK, "Create", "Sail", 4, 28));
        // No order with id 7 in the list because we had done it at the time it appeared.
        assertThat(kodak.getOrder(7), is(order));
        //Order with id 5 - the best suggestion. price - 7, but volume - 17. We want 28. Next suggestion:
        // id - 4, price - 4, volume - 13.
        assertThat(kodak.getOrder(5), is(order));
        assertThat(kodak.getOrder(4), is(new Order(4, 4, 2)));
        System.out.println(market);
    }

    @Test
    public void testFoldingOrders() {
        createMarketKodak();
        createMarketIBM();
        System.out.println(market);
    }

}