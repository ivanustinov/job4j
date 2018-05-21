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

    public void createThreads() {
        new Thread(() -> {
            for (int i = 0; i != 1000000; i++) {
                a++;
            }
            System.out.println("Done");
        }).start();
        new Thread(() -> {
            for (int i = 0; i != 1000000; i++) {
                a++;
            }
            System.out.println("Done");
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a);
    }

    public static void main(String[] args) {
        new ProblemsInThreads().createThreads();
    }
}
