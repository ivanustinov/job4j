package jmm;

/**
 * //TODO work comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 26.05.2018
 */
public class CreateThreads {

    private ProblemsInThreads problemsInThreads;

    public CreateThreads(ProblemsInThreads problemsInThreads) {
        this.problemsInThreads = problemsInThreads;
    }

    public void createThreads() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (problemsInThreads) {
                    for (int i = 0; i != 20000; i++) {
                        problemsInThreads.increase();
                    }
                }
                System.out.println("Done increase " + problemsInThreads.getA());
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (problemsInThreads) {
                    for (int i = 0; i != 20000; i++) {
                        problemsInThreads.low();
                    }
                }
                System.out.println("Done low " + problemsInThreads.getA());
            }
        }).start();
    }
}
