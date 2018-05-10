package testTask;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 07.05.2018
 */
public class Order implements Comparable {
    private Integer id;
    private String book;
    private String type;
    private String action;
    private Integer price;
    private int volume;

    /**
     * Constructor.
     *
     * @param id     the unique number of order.
     * @param book   the type of share.
     * @param type   has two values: to create or delete order.
     * @param action Has two values: to buy or sale shares.
     * @param price  price of one share.
     * @param volume the number of shares.
     */
    public Order(int id, String book, String type, String action, int price, int volume) {
        this.id = id;
        this.book = book;
        this.type = type;
        this.action = action;
        this.price = price;
        this.volume = volume;
    }


    public Order(Integer id, String book, String type, String action) {
        this.id = id;
        this.type = type;
        this.book = book;
        this.action = action;
    }


    public void comparePrice(Order order) {
        if ((action.equals("Buy") && price >= order.getPrice()) || action.equals("Sale") && price <= order.getPrice()) {
            int v = volume - order.getVolume();
            this.setVolume(v);
            order.setVolume(-v);
        }

    }

    public void setVolume(int volume) {
        if (volume < 0) {
            this.volume = 0;
        } else {
            this.volume = volume;
        }
    }

    /**
     * Gets id
     *
     * @return value of id
     */

    public Integer getId() {
        return id;
    }

    /**
     * Gets book
     *
     * @return value of book
     */

    public String getBook() {
        return book;
    }

    /**
     * Gets type
     *
     * @return value of type
     */

    public String getType() {
        return type;
    }

    /**
     * Gets action
     *
     * @return value of action
     */

    public String getAction() {
        return action;
    }

    /**
     * Gets price
     *
     * @return value of price
     */

    public Integer getPrice() {
        return price;
    }

    /**
     * Gets volume
     *
     * @return value of volume
     */

    public int getVolume() {
        return volume;
    }


    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Order order = (Order) obj;
        return this.hashCode() == order.hashCode();
    }

    @Override
    public int compareTo(Object o) {
        Order order = (Order) o;
        return -price.compareTo(order.getPrice());
    }
}
