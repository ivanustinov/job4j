package waitnotifynotifyall;

/**
 * //TODO work comments.
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
            try {
                while (true) {
                    queue.offer(a++);
                    queue.notify();
                    queue.wait();
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        }
    }
}