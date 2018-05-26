package wait_notify_notifyall;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 23.05.2018
 */
public class Consumer implements Runnable {
    SimpleBlockingQueue<Integer> queue;

    public Consumer(SimpleBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        synchronized (queue) {
            try {
                while (true) {
                    queue.poll();
                    Thread.sleep(100);
                    queue.notify();
                    queue.wait();
                }
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        }
    }
}
