package threadpool;

import java.util.Queue;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 30.05.2018
 */
public class MyThread extends Thread {
    private final Queue<Runnable> tasks;

    public MyThread(Queue<Runnable> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            while (tasks.size() == 0) {
                if (Thread.currentThread().isInterrupted()) {
                    return;
                }
            }
            tasks.poll().run();
        }
    }
}
