package threadpool;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
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
    private final List<MyThread> threads = new LinkedList<>();
    private final BlockingQueue<Runnable> tasks = new LinkedBlockingQueue<>();
    private boolean isStopped = false;


    public ThreadPool() {
        System.out.println("Cores " + size);
        for (int i = 0; i < size; i++) {
            threads.add(new MyThread(tasks));
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }


    public synchronized void work(Work work) throws Exception {
        if (isStopped) {
            throw new IllegalStateException("ThreadPool is stopped");
        }
        tasks.offer(work);
    }

    public void shutdown() {
        this.isStopped = true;
        for (MyThread thread : threads) {
            thread.doStop();
        }
    }


    public static void main(String[] args) throws Exception {
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
