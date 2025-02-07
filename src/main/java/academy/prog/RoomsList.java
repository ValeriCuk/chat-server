package academy.prog;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class RoomsList {

    private static final RoomsList roomsList = new RoomsList();
    private final Map<String, HashSet<String>> roomMembers = new HashMap<>();

    public static RoomsList getInstance() {
        return roomsList;
    }

    public void addRoom(String room, String member) {
        roomMembers.putIfAbsent(room, new HashSet<>());
        roomMembers.get(room).add(member);
    }

    public void leaveRoom(String room, String member) {
        if (roomMembers.containsKey(room)) {
            roomMembers.get(room).remove(member);
            if (roomMembers.get(room).isEmpty()) {
                roomMembers.remove(room);
            }
        }
    }

    public boolean isUserInRoom(String room, String user){
        return roomMembers.containsKey(room) && roomMembers.get(room).contains(user);
    }
}