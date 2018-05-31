package threadpool;

/**
 * //TODO work comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 25.05.2018
 */
public class Work implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getId() + " is starting to work");
            Thread.sleep(1000); //do some work
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getId() + "  has done the job");
    }
}
