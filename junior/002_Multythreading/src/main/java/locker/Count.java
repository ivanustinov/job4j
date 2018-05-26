package locker;

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

    public void increment() {
        this.value++;
    }


    @Override
    public String toString() {
        return String.valueOf(value);
    }

}