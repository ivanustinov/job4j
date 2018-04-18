package ru.job4j;
/**
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 0.1
 */
public class Converter {
    /**
     * Конвертируем рубли в евро.
     * @param value рубли.
     * @return Евро.
     */
    public int rubleToEuro(int value) {
        return  value / 70;
    }
    /**
     * Конвертируем евро в рубли.
     * @param value евро.
     * @return рубли.
     */
    public int euroToRuble(int value) {
        return  value * 70;
    }

    /**
     * Конвертируем рубли в доллары.
     * @param value рубли.
     * @return доллары
     */
    public int rubleToDollar(int value) {
        return value / 60;
    }
    /**
     * Конвертируем доллары в рубли.
     * @param value доллары.
     * @return рубли
     */
    public int dollarToRuble(int value) {
        return value * 60;
    }
}
