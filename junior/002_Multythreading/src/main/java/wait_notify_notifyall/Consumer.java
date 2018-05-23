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
                    System.out.print("Thread consumer: " + "\n");
                    while (a != null) {
                        System.out.print(a + " ");
                        Thread.sleep(100);
                        a = queue.poll();
                    }
                    System.out.println();
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
