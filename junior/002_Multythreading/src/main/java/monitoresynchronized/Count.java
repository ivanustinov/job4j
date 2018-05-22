package monitoresynchronized;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 21.05.2018
 */
@ThreadSafe
public class Count {
    @GuardedBy("this")
    private int value;

    public synchronized void increment() {
        for (int i = 0; i < 200000; i++) {
            this.value++;
        }
    }

    public int get() {
        return this.value;
    }
}