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
        if (a >= 9) {
            queue.notifyAll();
            return;
        }
        synchronized (queue) {
            try {
                System.out.print("Thread produser: " + "\n");
                while (queue.ifElse()) {
                    System.out.print(a + " ");
                    queue.offer(a++);
                    Thread.sleep(100);
                }
                System.out.println();
                queue.notifyAll();
                queue.wait();
                run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}