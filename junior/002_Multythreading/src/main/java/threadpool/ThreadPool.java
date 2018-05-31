package threadpool;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * //TODO work comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 24.05.2018
 */
public class ThreadPool {
    private int size = Runtime.getRuntime().availableProcessors();
    private final List<Thread> threads = new LinkedList<>();
    private final Queue<Runnable> tasks = new LinkedBlockingQueue<>();

    public ThreadPool() {
        System.out.println("Cores " + size);
        while (size > 0) {
            threads.add(new MyThread(tasks));
            size--;
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }

    public void work(Work work) {
        tasks.offer(work);
    }

    public void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }


    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool();
        for (int i = 0; i < 6; i++) {
            pool.work(new Work());
        }
        try {
            Thread.sleep(2200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pool.shutdown();
    }
}
