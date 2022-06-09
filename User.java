import java.util.ArrayList;

public class User {
    String userName;
    int userID;
    ArrayList<Relationship> friends = new ArrayList<>(); 

    @Override
    public String toString() {
        return userName + "[" + userID + "]";
    }
// *************************************************************************************************** User() : constructor
    public User(String userName, int userID){
        this.userName = userName;
        this.userID = userID;
    }
// *************************************************************************************************** getFriends()
    public Relationship[] getFriends(){
        return friends.toArray(new Relationship[0]);
    }
// *************************************************************************************************** addFriend()
    public Relationship addFriend(User friend, double friendshipValue){
        if (friend != null) {
            if (this.hasFriend(friend)) {
                for (Relationship relationship : friends) {
                    if (relationship.friendB.equals(friend)) {
                        return relationship;
                    }
                }
            }

            Relationship newRelationship = new Relationship(this, friend, friendshipValue);

            addFriend(newRelationship);
            friend.addFriend(this, friendshipValue);

            return newRelationship;
        }
        return null;
    }
// *************************************************************************************************** addFriend() : helper
    public void addFriend(Relationship relationship){
        this.friends.add(relationship);
    }
// *************************************************************************************************** hasFriend()
    public boolean hasFriend(User friend) {
        for (Relationship relationship : friends) {
            if (relationship.friendB.equals(friend)) {
                return true;
            }
        }

        return false;
    }
}
