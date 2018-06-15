package tasksinterview;

/**
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 14.06.2018
 */
public class DeadLock {
    private Object first = new Object();
    private Object second = new Object();
    private boolean isStarted;
    private boolean isStartedSecond;

    public void firstThread() {
        while (!isStarted) {
        }
        synchronized (first) {
            System.out.println("First thread locks first object");
            isStartedSecond = true;
            System.out.println("First thread deadlock");
            synchronized (second) {
            }
        }
    }

    public void secondThread() {
        synchronized (second) {
            System.out.println("Second thread locks second object");
            isStarted = true;
            while (!isStartedSecond) {
            }
            System.out.println("Second thread deadlock");
            synchronized (first) {
            }
        }
    }

    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();
        Thread one = new Thread(() -> {
            deadLock.firstThread();
        });
        Thread two = new Thread(() -> {
            deadLock.secondThread();
        });
        one.start();
        two.start();
    }
}
