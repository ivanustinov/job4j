package appjsp.entities;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 03.08.2018
 */
public class User {
    private int id;
    private UsersRoles role;
    private String login;
    private String password;
    private int acsessLevel;
    private LocalDateTime createDate;

    public User(int id, UsersRoles role, String login, String password) {
        this.id = id;
        this.role = role;
        this.login = login;
        this.password = password;
        this.createDate = LocalDateTime.now();
        this.acsessLevel = role.getAcsessLevel();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(UsersRoles role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public UsersRoles getRole() {
        return role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getAcsessLevel() {
        return acsessLevel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return Objects.equals(login, user.login)
                && Objects.equals(role, user.role)
                && Objects.equals(password, user.password);
    }


    @Override
    public int hashCode() {
        return Objects.hash(login, createDate);
    }

    @Override
    public String toString() {
        return String.format("id: %s\nrole: %s\nlogin: %s\n", id, role, login);
    }

    public static void main(String[] args) {
        System.out.println(new User(1, UsersRoles.ADMIN, "Petr", "rtre"));
    }
}
