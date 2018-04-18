package tasks;

/**
 * Counter2
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 */
public class Counter {
    public int add(int first, int finish) {
        int summ = 0;
        for (int i = first; i <= finish; i++) {
            if (i % 2 == 0) {
                summ += i;
            }
        }
        return summ;
    }
}