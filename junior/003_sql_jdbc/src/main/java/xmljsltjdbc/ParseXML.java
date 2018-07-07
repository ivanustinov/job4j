package xmljsltjdbc;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 06.07.2018
 */
public class ParseXML {
    private DefaultHandler handler;
    SAXParserFactory factory;
    private SAXParser saxParser;

    public ParseXML() {
        factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);
        try {
            saxParser = factory.newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
        handler = new DefaultHandler() {
            int sum = 0;

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                if (qName.equals("entry")) {
                    sum += Integer.parseInt(attributes.getValue(0));
                }
            }

            @Override
            public void endDocument() throws SAXException {
                System.out.println(sum);
            }
        };
    }

    public void parse(String file) {
        try {
            saxParser.parse(file, handler);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
