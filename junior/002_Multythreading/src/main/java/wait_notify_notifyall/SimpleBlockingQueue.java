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
        queue.offer(value);
    }

    public T poll() {
        return queue.poll();
    }

    public boolean ifElse() {
        return queue.size() < capacity;
    }

    public int getSize() {
        return queue.size();
    }

    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(10);
        new Thread(new Producer(queue)).start();
        new Thread(new Consumer(queue)).start();

    }

}