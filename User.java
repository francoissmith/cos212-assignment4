import java.util.ArrayList;

public class User {
    String userName;
    int userID;
    ArrayList<Relationship> friends = new ArrayList<>(); 
    int saturationDeg;
    int uncoloredDeg;
    int color;
    int distance;

    @Override
    public String toString() {
        return userName + "[" + userID + "]";
    }
// *************************************************************************************************** User() : constructor
    public User(String userName, int userID){
        this.userName = userName;
        this.userID = userID;
        this.saturationDeg = 0;
        this.uncoloredDeg = 0;
        this.color = 0;
        this.distance = 0;
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
// *************************************************************************************************** getDegree()
    public int getDegree() {
        int deg = 0;

        for (int i = 0; i < friends.size(); i++) {
            deg++;
        }

        return deg;
    }

// ***************************************************************************************************
    public ArrayList<User>getUncoloredFriends(ArrayList<User> uncoloredFriends) {

        for (Relationship friend : friends) {
            if (friend.friendB.color == 0) {
                uncoloredFriends.add(friend.friendB);
            }
        }

        return uncoloredFriends;
    }
}
