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
                Integer a = queue.poll();
                while (true) {
                    while (a != null) {
                        a = queue.poll();
                    }
                    queue.notify();
                    queue.wait();
                    if ((a = queue.poll()) != null) {
                        continue;
                    } else return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
