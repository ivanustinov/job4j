package testtask;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 17.05.2018
 */
public class FileRead {

    public String readFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        String a;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while ((a = reader.readLine()) != null) {
                builder.append(a);
            }
        } catch (IOException y) {
            y.printStackTrace();
        }
        return builder.toString();
    }
}