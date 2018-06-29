package concurrenthashmap;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 29.05.2018
 */
public class MyConcurrentMap {
    private ConcurrentHashMap<Integer, Base> map;

    public MyConcurrentMap() {
        this.map = new ConcurrentHashMap<>();
    }

    public void add(Base model) {
        int key = model.getId();
        Base b = map.computeIfPresent(key, (k, oldBase) -> {
            int oldVersion = model.version.get() - 1;
            int currentVersion = model.version.get();
            if (!oldBase.version.compareAndSet(oldVersion, currentVersion)) {
                throw new OptimisticExeption();
            }
            oldBase = model;
            return oldBase;
        });
        if (b == null) {
            map.put(key, model);
        }
    }

    public void update(Base model) {
        int key = model.getId();
        map.computeIfPresent(key, (k, oldBase) -> {
            int oldVersion = model.version.get() - 1;
            if (!oldBase.version.compareAndSet(oldVersion, oldVersion + 1)) {
                throw new OptimisticExeption();
            }
            oldBase = model;
            return oldBase;
        });
    }

    public void delete(Base model) {
        int key = model.getId();
        Base b = map.computeIfPresent(key, (k, oldBase) -> {
            int oldVersion = oldBase.version.get();
            if (!oldBase.version.compareAndSet(oldVersion, oldVersion + 1)) {
                throw new OptimisticExeption();
            }
            map.remove(key);
            return oldBase;
        });
    }
}
