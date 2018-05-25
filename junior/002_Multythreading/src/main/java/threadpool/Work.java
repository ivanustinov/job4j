package threadpool;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 25.05.2018
 */
public class Work implements Runnable {
    ThreadPool pool;

    public Work(ThreadPool pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        pool.getThread();
        try {
            System.out.println(Thread.currentThread().getId() + " starts working");
            Thread.sleep(2000); //do some work
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getId() + "  has done the job");
        pool.putThread();
    }
}
