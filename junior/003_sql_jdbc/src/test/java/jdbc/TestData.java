package jdbc;

import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 13.07.2018
 */
public class TestData {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yy");

    @Test
    public void createLocalData() {
        String date = "13 июн 18";
        String may = "13 май 18";
        System.out.println(LocalDate.parse(date, formatter));
        assertThat(date, is(LocalDate.parse(date, formatter).toString()));
        assertThat(date, is(LocalDate.parse(may, formatter).toString()));
    }
}
