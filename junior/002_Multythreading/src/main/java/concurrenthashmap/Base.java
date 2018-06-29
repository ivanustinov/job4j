package concurrenthashmap;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * //TODO work comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 29.05.2018
 */
public class Base {
    private int id;
    private int data = 10;
    public AtomicInteger version = new AtomicInteger(1);

    public Base(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void changeData(int data) {
        //Тут можно сделать цикл
        this.data = data;
        int ver = version.get();
        version.compareAndSet(ver, ver + 1);
    }

}
