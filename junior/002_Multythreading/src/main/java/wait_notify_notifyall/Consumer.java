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
                    Integer a = queue.poll();
                    while (a != null) {
                        a = queue.poll();
                    }
                    queue.notify();
                    queue.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
