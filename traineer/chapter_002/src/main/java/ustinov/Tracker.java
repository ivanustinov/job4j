package ustinov;

import java.util.Random;

public class Tracker {
    protected final Item[] items = new Item[100];
    protected int position;
    private static final Random RD = new Random();

    public Item add(Item item) {
        item.setId(geteratedId());
        items[position++] = item;
        return item;
    }

    public Item findByID(String id) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    public Item findByName(String name) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getName().equals(name)) {
                result = item;
                break;
            }
        }
        return result;
    }


    public void replaceItemById(String id, Item item) {
        for (int i = 0; i < position; i++) {
            if (items[i].getId() == id) {
                items[i] = item;
            }
        }
    }

    public void deleteItemById(String id) {
        for (int i = 0; i < position; i++) {
            ;
            if (items[i].getId() == id) {
                System.arraycopy(items, i + 1, items, i, items.length - 1 - i);
            }
        }
    }

    public void editItem(Item item) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && items[i].getId().equals(item.getId())) {
                items[i] = item;
            }
        }
    }

    public String geteratedId() {
        String id = String.valueOf(System.currentTimeMillis() + RD.nextInt());
        return id;
    }


    public Item[] getAll() {
        Item[] ite = new Item[position];
        for (int i = 0; i < ite.length; i++) {
            ite[i] = items[i];
        }
        return ite;
    }
}
