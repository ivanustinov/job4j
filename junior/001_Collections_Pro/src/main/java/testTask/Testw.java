package testTask;

import java.util.*;

/**
 *
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 08.05.2018
 */
public class Testw {
    public static void main(String[] args) {
        Testw t = new Testw();
        t.HashSetExample();
    }


    public class En implements Comparable<En> {
        Integer anInt;
        Integer price;

        public En(int anInt, int price) {
            this.anInt = anInt;
            this.price = price;
        }


        @Override
        public int compareTo(En o) {
            int id = this.anInt.compareTo(o.anInt);
            int amount = price.compareTo(o.price);
            return amount != 0 ? amount : id;
        }


        @Override
        public int hashCode() {
            return anInt.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            En e = (En) obj;
            return anInt == e.anInt;
        }

        @Override
        public String toString() {
            return anInt + " " + price;
        }
    }

    // @see https://docs.oracle.com/javase/8/docs/api/java/util/HashSet.html
    public void HashSetExample() {
        TreeSet<En> buy = new TreeSet<>();
        buy.add(new En(1, 1));
        buy.add(new En(2, 2));
        buy.add(new En(3, 3));
        buy.add(new En(4, 4));
        buy.add(new En(3, 1));
        System.out.println(buy);
    }
}
