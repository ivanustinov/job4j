package wait_notify_notifyall;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 23.05.2018
 */
public class Producer implements Runnable {
    private int a = 0;
    private SimpleBlockingQueue<Integer> queue;

    public Producer(SimpleBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        synchronized (queue) {
            if (a >= 9) {
                queue.notifyAll();
                return;
            }
            try {
                while (queue.ifElse()) {
                    queue.offer(a++);
                }
                queue.notifyAll();
                queue.wait();
                run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}