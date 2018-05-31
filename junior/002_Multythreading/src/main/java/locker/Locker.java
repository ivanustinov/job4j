package locker;

/**
 * //TODO work comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 25.05.2018
 */
public class Locker {
    private Thread currentThread = null;

    public synchronized void lock() {
        while (currentThread != null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        currentThread = Thread.currentThread();
        System.out.println((Thread.currentThread().getName()) + " has got the log.");
    }

    public synchronized void unlock() {
        if (currentThread == Thread.currentThread()) {
            currentThread = null;
            System.out.println(Thread.currentThread().getName() + " has returned the log.");
            notify();
        }
    }
}
