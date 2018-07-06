package xmljsltjdbc;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.List;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 02.07.2018
 */
public class StoreXML {
    private final File file;


    public StoreXML(File file) {
        this.file = file;
    }

    public void save(List<Field> list) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Entries entries = new Entries(list);
            jaxbMarshaller.marshal(entries, file);
            jaxbMarshaller.marshal(entries, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @XmlRootElement
    public static class Entries {
        private List<Field> entry;

        public Entries() {
        }

        public Entries(List<Field> values) {
            this.entry = values;
        }

        public List<Field> getEntry() {
            return entry;
        }

        public void setEntry(List<Field> entry) {
            this.entry = entry;
        }
    }

    public static class Field {
        private int field;

        public Field() {
        }

        public Field(int value) {
            this.field = value;
        }

        public int getField() {
            return field;
        }

        public void setField(int field) {
            this.field = field;
        }
    }

}
