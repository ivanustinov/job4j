package jmm;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 21.05.2018
 */
public class ProblemsInThreads {
    private int a = 0;

    public void increase() {
        a++;
    }

    public void low() {
        a--;
    }
    public void createThreads() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (this) {
                    for (int i = 0; i != 20000; i++) {
                        increase();
                    }
                }
                System.out.println("Done increase " + a);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (this) {
                    for (int i = 0; i != 20000; i++) {
                        low();
                    }
                }
                System.out.println("Done low " + a);
            }
        }).start();
    }

    public int getA() {
        return a;

    }

    public static void main(String[] args) {
        new ProblemsInThreads().createThreads();
    }
}
