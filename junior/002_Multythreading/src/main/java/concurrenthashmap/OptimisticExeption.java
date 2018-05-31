package concurrenthashmap;

/**
 * //TODO work comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 30.05.2018
 */
public class OptimisticExeption extends RuntimeException {
    public OptimisticExeption() {
        System.out.println("Invalid data");
    }
}
