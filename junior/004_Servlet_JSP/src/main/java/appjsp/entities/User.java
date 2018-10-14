package appjsp.entities;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 03.08.2018
 */
public class User {
    private String id = UUID.randomUUID().toString();
    private UsersRoles role;
    private String login;
    private String password;
    private LocalDateTime createDate;

    public User(UsersRoles role, String login, String password) {
        this.role = role;
        this.login = login;
        this.password = password;
        this.createDate = LocalDateTime.now();
    }

    public User(String id, UsersRoles role, String login, String password) {
        this.id = id;
        this.role = role;
        this.login = login;
        this.password = password;
        this.createDate = LocalDateTime.now();
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

    public String getId() {
        return id;
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
        return String.format("id: %s; login: %s; role: %s", getId(), login, role);
    }
}
