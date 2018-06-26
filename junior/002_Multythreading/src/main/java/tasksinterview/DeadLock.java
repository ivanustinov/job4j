package tasksinterview;

import java.util.concurrent.CountDownLatch;

/**
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 14.06.2018
 */
public class DeadLock extends Thread {
    private Object first;
    private Object second;
    private CountDownLatch lock;


    public DeadLock(Object first, Object second, CountDownLatch lock) {
        this.first = first;
        this.second = second;
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (first) {
            try {
                lock.await();
            } catch (InterruptedException e) {
                throw new RuntimeException("Interrupted");
            }
            synchronized (second) {
                System.out.println("Finished");
            }
        }
    }

    public static void main(String[] args) {
        Object one = new Object();
        Object two = new Object();
        CountDownLatch lock = new CountDownLatch(2);
        new DeadLock(one, two, lock).start();
        new DeadLock(two, one, lock).start();
    }
}
