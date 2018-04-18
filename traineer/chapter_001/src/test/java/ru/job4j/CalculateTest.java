package ru.job4j;


import org.junit.Test;
import ru.job4j.Calculate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Ivan Ustinov (ivanustinov1985@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class CalculateTest {
    /**
     * Test add.
     */
    @Test
    public void testSummMethod() {
        Calculate calculate = new Calculate();
        int first = 5, second = 2, expect = 7;
        int result = calculate.summ(first, second);
        assertThat(result, is(expect));
    }
}