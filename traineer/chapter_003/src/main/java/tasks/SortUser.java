package tasks;

import java.util.*;

public class SortUser {


    public static class User implements Comparable {
        Integer age;
        String name;

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public int compareTo(Object o) {
            return age.compareTo(((User) o).age);
        }

        @Override
        public String toString() {
            return "User{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public List<User> sortNameLength(List<User> r) {
        r.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return Integer.compare(o1.name.length(), o2.name.length());
            }
        });
        return r;
    }

    public List<User> sortByAllFields(List<User> r) {
        r.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int result = o1.name.compareTo(o2.name);
                if (result == 0)
                    result = o1.age.compareTo(o2.age);
                return result;
            }
        });
        return r;
    }

    public Set<User> sort(List<User> f) {
        Set<User> s = new TreeSet<>(f);
        return s;
    }

    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        list.add(new User(10, "Nikolai"));
        list.add(new User(8, "Nikolai"));
        list.add(new User(15, "Ivan"));
        list.add(new User(10, "Ivan"));
//        System.out.println(new SortUser().sort(list));
        System.out.println(new SortUser().sortByAllFields(list));
    }
}
