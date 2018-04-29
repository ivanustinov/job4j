package set;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 29.04.2018
 */
public class SimpleHashSetTest {

    @Test
    public void testAddAndREmoveMethod() {
        SimpleHashSet<Element> hashSet = new SimpleHashSet<>(7);
        hashSet.add(new Element(0));
        hashSet.add(new Element(1));
        hashSet.add(new Element(2));
        hashSet.add(new Element(6));
        assertThat(hashSet.contains(new Element(1)), is(true));
        assertThat(hashSet.remove(new Element(1)), is(true));
        assertThat(hashSet.contains(new Element(1)), is(false));
    }
    @Test
    public void testIncreaseCapasityMethod() {
        SimpleHashSet<Element> hashSet = new SimpleHashSet<>(7);
        hashSet.add(new Element(0));
        hashSet.add(new Element(1));
        hashSet.add(new Element(2));
        hashSet.add(new Element(6));
        hashSet.add(new Element(7));
        assertThat(hashSet.getContainer().length, is(10));
        System.out.println(Arrays.toString(hashSet.getContainer()));
    }


}