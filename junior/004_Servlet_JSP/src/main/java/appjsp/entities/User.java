package appjsp.entities;

import appjsp.entities.enums.Cities;
import appjsp.entities.enums.Countries;
import appjsp.entities.enums.UsersRoles;

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
    private Countries country;
    private Cities city;
    private LocalDateTime createDate;

    public User(UsersRoles role, Countries country, Cities city, String login, String password) {
        this.role = role;
        this.country = country;
        this.city = city;
        this.login = login;
        this.password = password;
        this.createDate = LocalDateTime.now();
    }

    public User(String id, UsersRoles role, Countries country, Cities city, String login, String password) {
        this.id = id;
        this.role = role;
        this.country = country;
        this.city = city;
        this.login = login;
        this.password = password;
        this.createDate = LocalDateTime.now();
    }

    public User() {
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

    public Countries getCountry() {
        return country;
    }

    public Cities getCity() {
        return city;
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
