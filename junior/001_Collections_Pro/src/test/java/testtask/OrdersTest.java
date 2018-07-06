package testtask;

import org.junit.Test;
import testtask.market.Order;
import testtask.market.OrdersApp;

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

    @Test
    public void createMarketKodak() {
        //Add sail orders.
        market.putOrder(new Order(1, KODAK, "Create", "Sale", 15, 8));
        market.putOrder(new Order(2, KODAK, "Create", "Sale", 12, 11));
        market.putOrder(new Order(3, KODAK, "Create", "Sale", 11, 22));
        //Add Buy orders.
        market.putOrder(new Order(4, KODAK, "Create", "Buy", 7, 13));
        market.putOrder(new Order(5, KODAK, "Create", "Buy", 7, 17));
        market.putOrder(new Order(6, KODAK, "Create", "Buy", 5, 17));
        market.putOrder(new Order(7, KODAK, "Create", "Buy", 5, 17));
        //Try to change the Order
        market.putOrder(new Order(1, KODAK, "Create", "Buy", 8, 12));
        System.out.println(market.getOrder(1, KODAK));
    }

    public void createMarketIBM() {
        //Add sail orders.
        market.putOrder(new Order(1, IBM, "Create", "Sale", 12, 8));
        market.putOrder(new Order(2, IBM, "Create", "Sale", 12, 11));
        market.putOrder(new Order(3, IBM, "Create", "Sale", 11, 22));
        //Add Buy orders.
        market.putOrder(new Order(4, IBM, "Create", "Buy", 7, 13));
        market.putOrder(new Order(5, IBM, "Create", "Buy", 7, 17));
        //Try to change the Order
        market.putOrder(new Order(5, IBM, "Create", "Buy", 8, 17));
    }

    @Test
    public void removeOrderMethodTest() {
        market.putOrder(new Order(4, KODAK, "Create", "Buy", 7, 13));
        market.putOrder(new Order(5, KODAK, "Create", "Buy", 7, 17));
        market.putOrder(new Order(4, KODAK, "Delete", "Buy", 7, 13));
        market.putOrder(new Order(5, KODAK, "Delete", "Buy", 7, 17));
        Order order = null;
        assertThat(market.getOrder(4, KODAK), is(order));
        assertThat(market.getOrder(5, KODAK), is(order));
    }

    @Test
    public void mustSaleTheBiggestPriceWeHave() {
        market.putOrder(new Order(4, KODAK, "Create", "Buy", 7, 13));
        market.putOrder(new Order(5, KODAK, "Create", "Buy", 6, 17));
        Order order = null;
        market.putOrder(new Order(7, KODAK, "Create", "Sale", 4, 15));
        // No order with id 7 in the items because we had done it at the time it appeared.
        assertThat(market.getOrder(7, KODAK), is(order));
        assertThat(market.getOrder(5, KODAK), is(new Order(5, 6, 15)));
    }

    @Test
    public void mustBuyTheLowestPriceWeHaveAtFirst() {
        market.putOrder(new Order(4, KODAK, "Create", "Sale", 7, 13));
        market.putOrder(new Order(5, KODAK, "Create", "Sale", 6, 17));
        market.putOrder(new Order(7, KODAK, "Create", "Buy", 10, 20));
        Order order = null;
        // No order with id 7 in the items because we had done it at the time it appeared.
        assertThat(market.getOrder(7, KODAK), is(order));
        assertThat(market.getOrder(4, KODAK), is(new Order(4, 7, 10)));
    }

    @Test
    public void testFoldingOrders() {
        createMarketKodak();
        createMarketIBM();
        System.out.println(market);
    }
}