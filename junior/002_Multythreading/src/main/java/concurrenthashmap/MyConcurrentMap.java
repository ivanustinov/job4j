package concurrenthashmap;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 29.05.2018
 */
public class MyConcurrentMap {
    private ConcurrentHashMap<Integer, Base> map;
    private int key;

    public MyConcurrentMap() {
        this.map = new ConcurrentHashMap<>();
    }

    public void add(Base model) {
        int key = model.getId();
        if (map.computeIfPresent(key, (Integer key1, Base oldBase) -> {
            if (model.getVersion() - oldBase.getVersion() > 1) {
                throw new OptimisticExeption();
            } else {
                return model;
            }
        }) == null) {
            map.put(key, model);
        }
    }

    public void update(Base model) {
        int key = model.getId();
        map.computeIfPresent(key, (Integer key1, Base oldBase) -> {
            if (model.getVersion() - oldBase.getVersion() > 1) {
                throw new OptimisticExeption();
            } else {
                return model;
            }
        });
    }

    public void remove(Base model) {
        int key = model.getId();
        if (map.computeIfPresent(key, (Integer key1, Base oldBase) -> {
            if (model.getVersion() - oldBase.getVersion() > 1) {
                throw new OptimisticExeption();
            } else {
                return model;
            }
        }) != null) {
            map.remove(key, model);
        }
    }
}
