package xml_jslt_jdbc;

import java.io.File;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 02.07.2018
 */
public class Main {
    public static void main(String[] args) {
        try (StoreSQL store = new StoreSQL(new Config())) {
            store.connect();
            store.createTable();
            store.generate(20);
            StoreXML xml = new StoreXML(new File("file"));
            xml.save(store.selectTable());
            ConverterXSLT.initFile();
            ConverterXSLT.convert(new File("file"), new File("dest"), new File("scheme.xsl"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
