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
// *************************************************************************************************** addFriendship()
    public Relationship addFriendship(int frienteeID, int friendedID, double relationshipValue) {
        User userA, userB;
        userA = userB = null;

        for (User user : users) {
            if (user.userID == frienteeID) {
                userA = user;
            }

            if (user.userID == friendedID) {
                userB = user;
            }
        }

        if (userA != null && userB != null) {
            Relationship newFriendship = userA.addFriend(userB, relationshipValue);

            return newFriendship;
        }
        return null;
    }
// *************************************************************************************************** clusterUsers()
    public User[][] clusterUsers() {
        return null;
    }
// *************************************************************************************************** minSpanningTree()
    public Relationship[] minSpanningTree() {
        return null;
    }
// *************************************************************************************************** getUsersAtDistance()
    public User[] getUsersAtDistance(User fromUser, int distance) {
        return null;
    }
// ***************************************************************************************************

    public User[] getAllUsers() {
        return users.toArray(new User[0]);
    }
}
