package testTask;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 07.05.2018
 */
public class Dom {
    private String company;
    private TreeSet<Order> buy;
    private TreeSet<Order> sale;

    public Dom(String company) {
        this.company = company;
        buy = new TreeSet<>(new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                Integer first = o1.getPrice();
                Integer second = o2.getPrice();
                return -first.compareTo(second);
            }
        });
        sale = new TreeSet<>(new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                Integer first = o1.getPrice();
                Integer second = o2.getPrice();
                return -first.compareTo(second);
            }
        });
    }

    public boolean addOrder(Order order) {
        Boolean result;
        if (order.getType().equals("Delete")) {
            result = deleteOrder(order);
        } else {
            result = order.getAction().equals("Buy") ? checkSails(order) : checkBuy(order);
        }
        return result;
    }

    public boolean deleteOrder(Order order) {
        return buy.remove(order) ? true : sale.remove(order);
    }

    public boolean checkSails(Order tobuy) {
        TreeSet<Order> sales = new TreeSet<>(sale);
        boolean result = false;
        for (Order saler : sales) {
            tobuy = tobuy.getPrice() >= saler.getPrice() ? getVolumeAfterTrade(tobuy, saler) : tobuy;
            if (tobuy == null) {
                return false;
            }
        }
        if (checkSumm(buy, tobuy)) {
            result =  buy.add(tobuy);
        }
        return result;
    }

    public boolean checkSumm(TreeSet<Order> tree, Order order) {
        boolean result = true;
        for (Order order1 : tree) {
            if (order1.getPrice() == order.getPrice()) {
                order1.setVolume(order1.getVolume() + order.getVolume());
                result = false;
                break;
            }
        }
        return result;
    }

    public Order getVolumeAfterTrade(Order order, Order orderInTable) {
        int volumeAfterTrade = order.getVolume() - orderInTable.getVolume();
        if (volumeAfterTrade >= 0) {
            deleteOrder(orderInTable);
            if (volumeAfterTrade == 0) {
                order = null;
            }
        } else {
            orderInTable.setVolume(-volumeAfterTrade);
            order = null;
        }
        return order;
    }

    public boolean checkBuy(Order saler) {
        TreeSet<Order> buys = new TreeSet<>(buy);
        boolean result = false;
        for (Order buy : buys) {
            saler = saler.getPrice() <= buy.getPrice() ? getVolumeAfterTrade(saler, buy) : saler;
            if (saler == null) {
                return false;
            }
        }
        if (checkSumm(sale, saler)) {
            result = sale.add(saler);
        }
        return result;
    }

    @Override
    public String toString() {
        Iterator<Order> it = buy.iterator();
        Iterator<Order> it2 = sale.iterator();
        StringBuffer builder = new StringBuffer();
        builder.append("Акции компани " + company + "\n" + "Покупка Цена Продажа" + "\n");
        while (it.hasNext() || it2.hasNext()) {
            if (it.hasNext()) {
                builder.append(it.next()).append(" ");
                if (it2.hasNext()) {
                    Order order = it2.next();
                    builder.append(order.getPrice() + "  " + order.getVolume() + "\n");
                } else {
                    builder.append("\n");
                }
            } else {
                Order order = it2.next();
                String sale = order.getPrice() + "  " + order.getVolume() + "\n";
                builder.append("          ").append(sale);
            }
        }
        return builder.toString();
    }
}