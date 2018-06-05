package bomberman;

import java.util.concurrent.locks.ReentrantLock;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 31.05.2018
 */
public class Board implements Runnable {
    private final Cell[][] land = new Cell[5][5];
    private static final int[] X = new int[]{-1, 0, 1, 0};
    private static final int[] Y = new int[]{0, 1, 0, -1};

    public Board() {
        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[i].length; j++) {
                land[i][j] = new Cell(i, j);
            }
        }
    }

    public boolean checkStep(int x, int y, int stepX, int stepY) {
        x += stepX;
        y += stepY;
        if ((x < land.length && x >= 0) && (y < land.length && y >= 0)) {
            return true;
        } else {
            return false;
        }
    }

    public Cell getNextCell(ReentrantLock cell) {
        int i = (int) (Math.random() * 4);
        Cell myCell = (Cell) cell;
        int x = myCell.getX();
        int y = myCell.getY();
        if (checkStep(x, y, X[i], Y[i])) {
            x += X[i];
            y += Y[i];
            return land[x][y];
        } else {
            return getNextCell(cell);
        }
    }

    public Cell create() {
        int x = (int) (Math.random() * 5);
        int y = (int) (Math.random() * 5);
        return land[x][y];
    }

    public boolean move(Cell sourse, Cell dist) {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println(sourse);
            long a = System.currentTimeMillis();
            while (!dist.tryLock()) {
                if (System.currentTimeMillis() - a > 500) {
                    dist = getNextCell(sourse);
                }
            }
            sourse.unlock();
            move(dist, getNextCell(dist));
        }
        return Thread.currentThread().isInterrupted();
    }

    @Override
    public void run() {
        Cell firstCell = create();
        while (!firstCell.tryLock()) {
            firstCell = getNextCell(firstCell);
        }
        move(firstCell, getNextCell(firstCell));
    }

    public static void main(String[] args) {
        Board board = new Board();
        Thread move = new Thread(board);
        move.start();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        move.interrupt();
    }


}
