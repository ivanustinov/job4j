package threads;

/**
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 17.05.2018
 */

import javafx.scene.shape.Rectangle;

public class RectangleMove implements Runnable {
    private final Rectangle rect;

    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {
        int x = 1;
        int y = 1;
        while (!Thread.currentThread().isInterrupted()) {
            rect.setX(rect.getX() + x);
            rect.setY(rect.getY() + y);
            if (rect.getX() == 300 || rect.getX() == 0) {
                x *= -1;
            }
            if (rect.getY() == 300 || rect.getY() == 0) {
                y *= -1;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
