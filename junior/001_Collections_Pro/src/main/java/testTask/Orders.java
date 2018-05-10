package testTask;

import java.util.*;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 09.05.2018
 */
public class Orders {
    private String company;
    private Set<Order> sale;
    private Set<Order> buy;
    private Set<Order> list;

    public Orders(String company) {
        this.company = company;
        sale = new HashSet<>();
        buy = new HashSet<>();
        list = new HashSet<>();
    }

    public void addOrder(Order order) {
        if (order.getType().equals("Delete")) {
            deleteOrder(order);
            return;
        }
        Set<Order> toCheck;
        Set<Order> toAdd;
        if (order.getAction().equals("Buy")) {
            toCheck = sale;
            toAdd = buy;
        } else {
            toCheck = buy;
            toAdd = sale;
        }
        checkList(order, toCheck);
        if (order.getVolume() != 0) {
            toAdd.add(order);
            list.add(order);
        }
    }

    public Order checkList(Order order, Set<Order> list) {
        LinkedList<Order> toDel = new LinkedList<>();
        for (Order order1 : list) {
            order.comparePrice(order1);
            if (order1.getVolume() == 0) {
                toDel.add(order1);
            }
        }
        for (Order order1 : toDel) {
            deleteOrder(order1);
        }
        return order;
    }

    public void deleteOrder(Order order) {
        if (order.getAction().equals("Buy")) {
            buy.remove(order);
        } else sale.remove(order);
        list.remove(order);
    }

    public TreeMap<Integer, Order> toPrint() {
        TreeMap<Integer, Order> tree = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -o1.compareTo(o2);
            }
        });
        for (Order order : list) {
            int key = order.getPrice();
            Order order1 = tree.get(key);
            if (order1 != null) {
                order1.setVolume(order1.getVolume() + order.getVolume());
                tree.put(key, order1);
            } else {
                tree.put(key, order);
            }
        }
        return tree;
    }

    @Override
    public String toString() {
        Map<Integer, Order> tree = toPrint();
        StringBuffer buffer = new StringBuffer();
        buffer.append("Покупка Цена Продажа" + "\n");
        for (Integer integer : tree.keySet()) {
            if (tree.get(integer).getAction().equals("Buy")) {
                buffer.append(tree.get(integer).getVolume()).append("      ").append(integer + "\n");
            } else {
                buffer.append("          " + integer).append(" ").append(tree.get(integer).getVolume() + "\n");
            }
        }
        return buffer.toString();
    }
}
