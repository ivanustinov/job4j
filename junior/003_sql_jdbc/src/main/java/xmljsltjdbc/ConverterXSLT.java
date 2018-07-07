package xmljsltjdbc;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 02.07.2018
 */
public class ConverterXSLT {

    public void convert(String sourse, String dest, String scheme) {
        String xsl = "";
        String xml = "";
        try (FileInputStream sours = new FileInputStream(sourse);
             FileInputStream des = new FileInputStream(scheme)) {
            byte[] str = new byte[sours.available()];
            sours.read(str);
            xml = new String(str);
            byte[] str2 = new byte[des.available()];
            des.read(str2);
            xsl = new String(str2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        TransformerFactory factory = TransformerFactory.newInstance();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dest))) {
            Transformer transformer = factory.newTransformer(
                    new StreamSource(new ByteArrayInputStream(xsl.getBytes())));
            transformer.transform(new StreamSource(
                            new ByteArrayInputStream(xml.getBytes())),
                    new StreamResult(writer));
        } catch (TransformerException | IOException e) {
            e.printStackTrace();
        }
    }
}
