package generics;

/**
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 22.04.2018
 */
public class RoleStore extends AbstractStore<Role> {

    /**
     * Constructor.
     *
     * @param size - the size of massive.
     */
    public RoleStore(int size) {
        super(size);
    }
}
