package nl.fontys.sm.superduperwaffle.db.models;

/**
 * Created by Antonio M on 17-6-2016.
 */
public class ContainerLoggedUser {
    private static ContainerLoggedUser instance = null;

    private User user;

    protected ContainerLoggedUser() {}

    public static ContainerLoggedUser getInstance() {
        if(instance == null) {
            instance = new ContainerLoggedUser();
        }
        return instance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
