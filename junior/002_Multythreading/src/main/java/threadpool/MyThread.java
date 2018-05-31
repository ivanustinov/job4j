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
        while (true) {
            synchronized (tasks) {
                while (tasks.size() == 0) {
                    try {
                        System.out.println(Thread.currentThread().getId() + " is waiting the task");
                        tasks.wait();
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }
            tasks.poll().run();
        }
    }
}
