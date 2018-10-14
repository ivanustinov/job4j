package appjsp.entities;

import java.util.UUID;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 12.10.2018
 */
public class IndexUser {
    private String id = UUID.randomUUID().toString();
    private String firstName;
    private String serName;
    private String email;


    public IndexUser(String name, String serName, String email) {
        this.firstName = name;
        this.serName = serName;
        this.email = email;
    }

    public IndexUser() {
    }

    public String getId() {
        return id;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getSerName() {
        return serName;
    }


    public String getEmail() {
        return email;
    }
}
