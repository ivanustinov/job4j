package wait_notify_notifyall;

import org.junit.Test;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 23.05.2018
 */

public class SimpleBlockingQueueTest {
    @Test
    public void testThreads() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(10);
        Thread producer = new Thread(new Producer(queue));
        Thread consumer = new Thread(new Consumer(queue));
        producer.start();
        consumer.start();
//        assertThat(queue.getSize(), is(1));
        Thread.sleep(1000);
        producer.interrupt();
        consumer.interrupt();
    }
}