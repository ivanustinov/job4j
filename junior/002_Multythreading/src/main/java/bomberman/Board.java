package bomberman;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 31.05.2018
 */
public class Board implements Runnable {
    private final Cell[][] land;
    private int size;
    private int monsterNumber;
    private static final int[] X = new int[]{-1, 0, 1, 0};
    private static final int[] Y = new int[]{0, 1, 0, -1};
    private final Queue<Thread> monsters = new LinkedList<>();

    public Board() {
        while (size < 5) {
            size = (int) (Math.random() * 10);
        }
        System.out.println("Size of the Board is " + size);
        while (monsterNumber < 1) {
            monsterNumber = (int) (Math.random() * 5);
        }
        System.out.println("Number of monsters is " + monsterNumber);
        land = new Cell[size][size];
        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[i].length; j++) {
                land[i][j] = new Cell(i, j);
            }
        }
        int blocks = (int) (0.25 * size * size);
        System.out.println("The number of blocks is " + blocks);
        for (int i = 0; i < blocks; i++) {
            int x = (int) (Math.random() * size);
            int y = (int) (Math.random() * size);
            Cell block = land[x][y];
            while (!block.tryLock()) {
                block = getNextCell(block);
            }
        }
        for (int i = 0; i < monsterNumber; i++) {
            Thread monster = new Thread(this);
            monster.setName("Monster" + i);
            monsters.add(monster);
            monster.start();
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
        int x = (int) (Math.random() * size);
        int y = (int) (Math.random() * size);
        return land[x][y];
    }

    public boolean move(Cell sourse, Cell dist) {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println(Thread.currentThread().getName() + " Cell: " + sourse);
            long a = System.currentTimeMillis();
            while (!dist.tryLock()) {
                if ((System.currentTimeMillis() - a) > 500) {
                    System.out.println("Invalid Cell " + dist);
                    dist = getNextCell(sourse);
                }
            }
            sourse.unlock();
            move(dist, getNextCell(dist));
        }
        return Thread.currentThread().isInterrupted();
    }

    public void interrupt() {
        for (Thread monster : monsters) {
            monster.interrupt();
        }
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
        Thread bomberman = new Thread(board);
        bomberman.setName("BomberMan");
        bomberman.start();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bomberman.interrupt();
        board.interrupt();
    }
}
