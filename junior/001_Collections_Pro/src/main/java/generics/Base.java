package generics;

/**
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 22.04.2018
 */

public abstract class Base {
    private final String id;

    /**
     * Constructor.
     *
     * @param id
     */
    protected Base(final String id) {
        this.id = id;
    }

    /**
     * Getter
     *
     * @return Strng id.
     */
    public String getId() {
        return id;
    }
}
