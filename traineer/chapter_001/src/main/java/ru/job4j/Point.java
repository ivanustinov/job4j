package ru.job4j;
/**
 * Программа для вычисления расстояний между точками.
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 0.1
 */
public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double distanceTo(Point a) {
        double result = Math.sqrt(
                Math.pow(x - a.x, 2) + Math.pow(y - a.y, 2)
        );
        return result;
    }

}
