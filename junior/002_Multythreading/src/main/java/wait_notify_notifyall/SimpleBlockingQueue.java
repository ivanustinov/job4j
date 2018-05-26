package wait_notify_notifyall;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 23.05.2018
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    int capacity;

    public SimpleBlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public void offer(T value) {
        String val = String.valueOf(value);
        queue.offer(value);
        System.out.println("Value " + val + " added");
    }

    public T poll() {
        T val = queue.poll();
        System.out.println("Value " + val + " have got");
        return val;
    }

    public int getSize() {
        return queue.size();
    }

}