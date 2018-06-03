package bomberman;

import java.util.concurrent.locks.ReentrantLock;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 01.06.2018
 */
public class Cell extends ReentrantLock {
    private int x;
    private int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return x + " " + y;
    }

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }
}
