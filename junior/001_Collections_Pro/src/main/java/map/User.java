package map;

import java.util.Calendar;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 29.04.2018
 */
public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && name.equals(user.name)
                && birthday.equals(user.birthday);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + name.hashCode();
        result = 31 * result + children;
        result = 31 * result + birthday.hashCode();
        return result;
    }

    @Override
    public String toString() {
        String brth = birthday.get(Calendar.YEAR) + " " + birthday.get(Calendar.MONTH) + " " + birthday.get(Calendar.DATE);
        return name + " " + children + " " + brth + " " + hashCode();
    }
}
