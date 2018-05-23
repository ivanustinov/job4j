package jmm;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 21.05.2018
 */
public class ProblemsInThreads {
    private volatile int a = 0;

    public void increase() {
        synchronized (this) {
            a++;
        }
    }

    public void createThreads() {
        new Thread(() -> {
            for (int i = 0; i != 20000; i++) {
                increase();
            }
            System.out.println("Done " + a);
        }).start();
        new Thread(() -> {
            for (int i = 0; i != 20000; i++) {
                increase();
            }
            System.out.println("Done " + a);
        }).start();
    }

    public static void main(String[] args) {
        new ProblemsInThreads().createThreads();
    }
}
