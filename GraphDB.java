import java.util.ArrayList;

public class GraphDB {
    private ArrayList<User> users = new ArrayList<>();
// *************************************************************************************************** addUser()
    public User addUser(String userName, int ID) {
        for (User user : users) {
            if (user.userID == ID) {
                return user;
            }
        }

        User newUser = new User(userName, ID);
        users.add(newUser);

        return newUser;
    }
// *************************************************************************************************** getUser() : ID
    public User getUser(int userID) {
        for (User user : users) {
            if (user.userID == userID) {
                return user;
            }
        }
        return null;
    }
// *************************************************************************************************** getUser() : String
    public User getUser(String userName) {
        for (User user : users) {
            if (user.userName.equals(userName)) {
                return user;
            }
        }

        return null;
    }
// ***************************************************************************************************
    public Relationship addFriendship(int frienteeID, int friendedID, double relationshipValue) {
        return null;
    }
// ***************************************************************************************************
    public User[][] clusterUsers() {
        return null;
    }
// ***************************************************************************************************
    public Relationship[] minSpanningTree() {
        return null;
    }
// ***************************************************************************************************
    public User[] getUsersAtDistance(User fromUser, int distance) {
        return null;
    }
// ***************************************************************************************************

}
