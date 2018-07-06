package bomberman;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 31.05.2018
 */
public class Board implements Runnable {
    private Cell[][] land;
    private int size;
    private int monsterNumber;
    private int blocks;
    private static final int[] X = new int[]{-1, 0, 1, 0};
    private static final int[] Y = new int[]{0, 1, 0, -1};
    private ExecutorService monsters;
    private static final Logger LOGGER =
            Logger.getLogger(Board.class.getName());


    public void init() {
        while (size < 5) {
            size = (int) (Math.random() * 10);
        }
        land = new Cell[size][size];
        LOGGER.info("Size of the Board is " + size + "x" + size);
        while (monsterNumber < 1) {
            monsterNumber = (int) (Math.random() * 5);
        }
        monsters = Executors.newFixedThreadPool(monsterNumber + 1);
        LOGGER.info("Number of monsters is " + monsterNumber);
        blocks = (int) (0.25 * size * size);
        LOGGER.info("The number of blocks is " + blocks);
        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[i].length; j++) {
                land[i][j] = new Cell(i, j);
            }
        }
        for (int i = 0; i < blocks; i++) {
            int x = (int) (Math.random() * size);
            int y = (int) (Math.random() * size);
            Cell block = land[x][y];
            while (!block.tryLock()) {
                block = getNextCell(block);
            }
        }
        for (int i = 0; i < monsterNumber; i++) {
            monsters.execute(this);
        }
        Thread bomberman = new Thread(this);
        bomberman.setName("BomberMan");
        monsters.execute(bomberman);
    }

    public boolean checkStep(int x, int y, int stepX, int stepY) {
        x += stepX;
        y += stepY;
        return (x < land.length && x >= 0) && (y < land.length && y >= 0);
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
        int x = (int) (Math.random() * size);
        int y = (int) (Math.random() * size);
        return land[x][y];
    }

    public void move(Cell sourse, Cell dist) {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            LOGGER.info(Thread.currentThread().getName() + " Cell: " + sourse);
            long a = System.currentTimeMillis();
            while (!dist.tryLock()) {
                if ((System.currentTimeMillis() - a) > 500) {
                    LOGGER.info("Invalid Cell " + dist);
                    dist = getNextCell(sourse);
                }
            }
            sourse.unlock();
            sourse = dist;
            dist = getNextCell(sourse);
        }
    }

    public void stop() {
        monsters.shutdownNow();
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
        board.init();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        board.stop();
    }
}
