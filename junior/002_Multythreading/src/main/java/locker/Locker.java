package locker;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 25.05.2018
 */
public class Locker {
    private boolean isLocked = false;
    private String currentThread;

    public synchronized void lock() {
        while (isLocked) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println((currentThread = Thread.currentThread().getName()) + " have got the log.");
        isLocked = true;
    }

    public synchronized void unlock() {
        if (currentThread.equals(Thread.currentThread().getName())) {
            isLocked = false;
            System.out.println(Thread.currentThread().getName() + " have returned the log.");
            notify();
        }
    }
}
