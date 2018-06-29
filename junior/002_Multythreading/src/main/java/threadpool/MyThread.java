package threadpool;

import java.util.concurrent.BlockingQueue;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 30.05.2018
 */
public class MyThread extends Thread {
    private final BlockingQueue<Runnable> tasks;
    private boolean isStopped = false;

    public MyThread(BlockingQueue<Runnable> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void run() {
        while (!isStopped()) {
            try {
                Runnable runnable = tasks.take();
                runnable.run();
            } catch (Exception e) {
                interrupt();
            }
        }
    }

    public synchronized void doStop() {
        isStopped = true;
        interrupt(); //break pool thread out of dequeue() call.
    }

    public synchronized boolean isStopped() {
        return isStopped;
    }
}
