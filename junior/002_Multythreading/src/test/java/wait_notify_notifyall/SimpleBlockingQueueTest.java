package wait_notify_notifyall;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

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
        Thread.sleep(50);
        assertThat(queue.getSize(), is(10));
        consumer.start();
        producer.interrupt();
        consumer.interrupt();
    }
}