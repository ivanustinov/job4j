package testTask;

import java.util.HashMap;
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
    private HashMap<Integer, Order> map;
    private TreeSet<Order> buy;
    private TreeSet<Order> sale;

    public Dom(String company) {
        this.company = company;
        buy = new TreeSet<>();
        sale = new TreeSet<>();
        map = new HashMap<>();
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
        boolean result = false;
        Order toDel = map.remove(order.getId());
        if (toDel.getAction().equals("Buy")) {
            result = deleteSumm(buy, toDel) > 0 ? true : buy.remove(toDel);
        } else {
            result = deleteSumm(sale, toDel) > 0 ? true : sale.remove(toDel);
        }
        return result;
    }

    public boolean checkSails(Order tobuy) {
        TreeSet<Order> sales = new TreeSet<>(sale);
        boolean result = false;
        for (Order saler : sales) {
            tobuy = tobuy.getPrice() >= saler.getPrice() ? getVolumeAfterTrade(tobuy, saler, sale) : tobuy;
            if (tobuy == null) {
                return false;
            }
        }
        map.put(tobuy.getId(), tobuy);
        if (checkSumm(buy, tobuy)) {
            result = buy.add(tobuy);
        }
        return result;
    }

    public boolean checkBuy(Order saler) {
        TreeSet<Order> buys = new TreeSet<>(buy);
        boolean result = false;
        for (Order buyse : buys) {
            saler = saler.getPrice() <= buyse.getPrice() ? getVolumeAfterTrade(saler, buyse, buy) : saler;
            if (saler == null) {
                return false;
            }
        }
        map.put(saler.getId(), saler);
        if (checkSumm(sale, saler)) {
            result = sale.add(saler);
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

    public int deleteSumm(TreeSet<Order> tree, Order order) {
        int result = 1;
        for (Order order1 : tree) {
            if (order1.getPrice() == order.getPrice()) {
                result = order1.getVolume() - order.getVolume() > 0 ? result : -1;
                order1.setVolume(order1.getVolume() - order.getVolume());
                break;
            }
        }
        return result;
    }

    public Order getVolumeAfterTrade(Order order, Order orderInTable, TreeSet<Order> tree) {
        int volumeAfterTrade = order.getVolume() - orderInTable.getVolume();
        if (volumeAfterTrade >= 0) {
            deleteOrder(orderInTable);
            if (volumeAfterTrade == 0) {
                order = null;
            } else {
                order.setVolume(volumeAfterTrade);
            }
        } else {
            orderInTable.setVolume(-volumeAfterTrade);
            tree.add(orderInTable);
            map.put(orderInTable.getId(), orderInTable);
            order = null;
        }
        return order;
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
                builder.append("         ").append(sale);
            }
        }
        return builder.toString();
    }
}