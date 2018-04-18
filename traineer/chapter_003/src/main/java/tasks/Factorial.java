package tasks;

/**
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 0.2
 */
public class Factorial {
    public int calculateFactorial(int n) {
        int number = 1;
        for (int i = 1; i <= n; i++) {
            number *= i;
        }
        return number;
    }
}