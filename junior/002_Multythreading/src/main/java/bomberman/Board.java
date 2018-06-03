package bomberman;

import java.util.concurrent.locks.ReentrantLock;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 31.05.2018
 */
public class Board {
    private final static ReentrantLock[][] LAND = new Cell[5][5];
    private static int[] t = new int[]{-1, 0, 1, 0};
    private static int[] r = new int[]{0, 1, 0, -1};

    static {
        for (int i = 0; i < LAND.length; i++) {
            for (int j = 0; j < LAND[i].length; j++) {
                LAND[i][j] = new Cell(i, j);
            }
        }
    }

    public static boolean checkStep(int x, int y, int stepX, int stepY) {
        x += stepX;
        y += stepY;
        if ((x < LAND.length && x >= 0) && (y < LAND.length && y >= 0)) {
            return true;
        } else {
            return false;
        }
    }

    public static ReentrantLock getNextCell(ReentrantLock cell) {
        int i = (int) (Math.random() * 4);
        Cell myCell = (Cell) cell;
        int x = myCell.getX();
        int y = myCell.getY();
        if (Board.checkStep(x, y, t[i], r[i])) {
            x += t[i];
            y += r[i];
            return LAND[x][y];
        } else {
            return getNextCell(cell);
        }
    }

    public static ReentrantLock create() {
        int x = (int) (Math.random() * 5);
        int y = (int) (Math.random() * 5);
        return LAND[x][y];
    }

    public static boolean move(ReentrantLock sourse, ReentrantLock dist) {
        while (Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println(sourse);
            sourse.unlock();
            while (!dist.tryLock()) {
                dist = getNextCell(sourse);
            }
            move(dist, getNextCell(dist));
        }
        return Thread.currentThread().isInterrupted();
    }

    public static void main(String[] args) {
        Thread move = new Thread() {
            @Override
            public void run() {
                ReentrantLock firstCell = Board.create();
                while (!firstCell.tryLock()) {
                    firstCell = getNextCell(firstCell);
                }
                Board.move(firstCell, getNextCell(firstCell));
            }
        };
        move.start();
    }
}
