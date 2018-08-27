package appjsp.entities;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 22.08.2018
 */
public enum UsersRoles {
    GUEST(0), USER(1), ADMIN(2);
    private int acsessLevel;

    UsersRoles(int acsessLevel) {
        this.acsessLevel = acsessLevel;
    }

    public int getAcsessLevel() {
        return acsessLevel;
    }
}
