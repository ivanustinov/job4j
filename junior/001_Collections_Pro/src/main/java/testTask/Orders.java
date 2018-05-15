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
    private final Set<Order> sale = new TreeSet<>();
    private final Set<Order> buy = new TreeSet<>();
    private final Map<Integer, Order> mapToReturnOrder = new HashMap<>();
    private final static String DELETE = "Delete";
    private final static String BUY = "Buy";

    public Orders(String company) {
        this.company = company;
    }

    public void addOrder(Order order) {
        if (DELETE.equals(order.getType())) {
            deleteOrder(order);
        }
        Set<Order> toCheck = sale;
        Set<Order> toAdd = buy;
        if (!BUY.equals(order.getAction())) {
            toCheck = buy;
            toAdd = sale;
        }
        if (checkAndRemoveOrdersInList(order, toCheck).getVolume() != 0) {
            mapToReturnOrder.put(order.getId(), order);
            toAdd.add(order);
        }
    }

    public Order checkAndRemoveOrdersInList(Order order, Set<Order> list) {
        LinkedList<Order> toDel = new LinkedList<>();
        for (Order order1 : list) {
            order1.substractVolumes(order);
            if (order.getVolume() == 0) {
                break;
            }
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
        if (BUY.equals(order.getAction())) {
            buy.remove(order);
        } else {
            sale.remove(order);
        }
        mapToReturnOrder.remove(order.getId());
    }

    public Order getOrder(Integer id) {
        return mapToReturnOrder.get(id);
    }

    public Map<Integer, Order> toPrint(Map<Integer, Order> map) {
        Map<Integer, Order> treeToPrint = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -o1.compareTo(o2);
            }
        });
        for (Order order : map.values()) {
            int key = order.getPrice();
            String buyOrSale = order.getAction();
            Order orderToPrint = treeToPrint.get(key);
            if (orderToPrint != null) {
                orderToPrint.increaseVolume(order.getVolume());
            } else {
                orderToPrint = new Order(buyOrSale, order.getVolume());
                treeToPrint.put(key, orderToPrint);
            }
        }
        return treeToPrint;
    }

    @Override
    public String toString() {
        Map<Integer, Order> tree = toPrint(mapToReturnOrder);
        if (tree.size() == 0) {
            return "";
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append("Акции компании " + company + "\n" + "Покупка Цена Продажа" + "\n");
        for (Integer integer : tree.keySet()) {
            Order orderToPrint = tree.get(integer);
            if (orderToPrint.getAction().equals(BUY)) {
                buffer.append(orderToPrint.getVolume()).append("      " + integer + "\n");
            } else {
                buffer.append("          " + integer).append(" " + orderToPrint.getVolume() + "\n");
            }
        }
        return buffer.toString();
    }
}
