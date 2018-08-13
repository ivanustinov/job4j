package xmljsltjdbc;

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
        try (StoreSQL store = new StoreSQL(new Config("/sqlite.properties"))) {
            store.connect();
            store.createTable();
            store.generate(30);
            StoreXML xml = new StoreXML(new File("file"));
            xml.save(store.selectTable());
            new ConverterXSLT().convert("file", "dest", "scheme.xsl");
            ParseXML parseXML = new ParseXML();
            parseXML.parse("dest");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
