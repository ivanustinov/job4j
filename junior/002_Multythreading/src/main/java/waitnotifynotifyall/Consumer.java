package waitnotifynotifyall;

/**
 * //TODO work comments.
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
                    queue.wait();
                    queue.poll();
                    queue.notify();
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        }
    }
}
