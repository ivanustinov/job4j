package jmm;

/**
 * //TODO work comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 21.05.2018
 */
public class ProblemsInThreads {
    private int a = 0;

    public void increase() {
        synchronized (this) {
            a++;
        }
    }
    public void low() {
        a--;
    }

    public void createThreads() {
        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40000; i++) {
                    increase();
                }
                System.out.println("Done encrease " + a);
            }
        });
        Thread two = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40000; i++) {
                    low();
                }
                System.out.println("Done low " + a);
            }
        });
        two.start();
        one.start();
    }

    public int getA() {
        return a;

    }

    public static void main(String[] args) {
        new ProblemsInThreads().createThreads();
    }
}
