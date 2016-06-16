package nl.fontys.sm.superduperwaffle.db.models.experimental;

import nl.fontys.sm.superduperwaffle.db.models.User;

/**
 * Created by Antonio M on 16-6-2016.
 */
final public class userLogged {

    private static User user;

    static public void setUser(User user1) {
        user = user1;
    }
}
