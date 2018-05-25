package threadpool;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 24.05.2018
 */
public class ThreadPool {
    private int cores;

    public ThreadPool(int cores) {
        this.cores = cores;
    }

    public synchronized void getThread() {
        while (cores < 1) {
            try {
                System.out.println(Thread.currentThread().getId() + " is waiting");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        cores--;
    }

    public synchronized void putThread() {
        cores++;
        notify();
    }


    public void add(Work work) {
        new Thread(work).start();
    }


    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool(4);
        for (int i = 0; i < 6; i++) {
            Work work = new Work(pool);
            pool.add(work);
        }
    }
}
