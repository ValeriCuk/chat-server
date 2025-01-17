package academy.prog;

import java.util.HashSet;
import java.util.Set;

public class UsersList {
    private static final UsersList usersList = new UsersList();
    private final Set<String> onlineUsers = new HashSet<>();

    public static UsersList getInstance() {
        return usersList;
    }

    public void userLoggedIn(String user) {
        onlineUsers.add(user);
    }

    public void userLoggedOut(String user) {
        onlineUsers.remove(user);
    }

    public Set<String> getOnlineUsers() {
        return onlineUsers;
    }

    public boolean isUserOnline(String user) {
        return onlineUsers.contains(user);
    }
}
