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
    private List<Order> sale;
    private List<Order> buy;
    private List<Order> list;

    public Orders(String company) {
        this.company = company;
        sale = new ArrayList<>();
        buy = new ArrayList<>();
        list = new ArrayList<>();
    }

    public void addOrder(Order order) {
        if (order.getType().equals("Delete")) {
            deleteOrder(order);
            return;
        }
        List<Order> toCheck;
        List<Order> toAdd;
        if (order.getAction().equals("Buy")) {
            sale.sort(new Comparator<Order>() {
                @Override
                public int compare(Order o1, Order o2) {
                    return o1.getPrice().compareTo(o2.getPrice());
                }
            });
            toCheck = sale;
            toAdd = buy;
        } else {
            buy.sort(new Comparator<Order>() {
                @Override
                public int compare(Order o1, Order o2) {
                    return -o1.getPrice().compareTo(o2.getPrice());
                }
            });
            toCheck = buy;
            toAdd = sale;
        }
        if (checkList(order, toCheck).getVolume() != 0) {
            toAdd.add(order);
            list.add(order);
        }
    }

    public Order checkList(Order order, List<Order> list) {
        LinkedList<Order> toDel = new LinkedList<>();
        int index = 0;
        while (order.getVolume() > 0 && index < list.size()) {
            Order order1 = list.get(index++);
            order1.comparePrice(order);
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
