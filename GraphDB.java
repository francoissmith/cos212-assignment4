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
        ArrayList<User> uncolored = new ArrayList<>();
        uncolored = getUncolored(uncolored);
        User v;
        int j = 0;

        for (User user : users) {
            user.saturationDeg = 0;
            user.uncoloredDeg = user.getDegree();
        }

        while (uncolored.size() > 0) {
            ArrayList<User> uncoloredFriends = new ArrayList<>();
            v = processNext(uncolored);
            j = getColor(v);
            uncoloredFriends = v.getUncoloredFriends(uncoloredFriends);

            for (User u : uncoloredFriends) {
                if (!adjToColor(u, j)) {
                    u.saturationDeg++;
                }
                u.uncoloredDeg--;
            }

            v.color = j;
            uncolored.remove(v);
        }

        ArrayList<Integer> listColors = new ArrayList<>();
        listColors = getAllColors(listColors);

        ArrayList<ArrayList<User>> temp = new ArrayList<>();
        
        for (int i = 0; i < listColors.size(); i++) {
            temp.add(new ArrayList<>());
        }   

        for (User user : users) {
            temp.get(user.color-1).add(user);
        }

        User[][] result = temp.stream().map(el -> el.toArray(new User[0])).toArray(User[][]::new);

        return result;
    }
// *************************************************************************************************** minSpanningTree()
    public Relationship[] minSpanningTree() {
        ArrayList<Relationship> tree = new ArrayList<>();
        ArrayList<Relationship> edges = new ArrayList<>();

        tree.clear(); 
        for (User user : users) {
            for (Relationship relationship : user.friends) {
                edges.add(relationship);

            }
        }

        for (int i = 0; i < edges.size(); i++) {
            tree.add(edges.get(i));
            Relationship cycle = unionCycle(tree);
            if (cycle != null) {
                tree.remove(cycle);
            }
        }

        return tree.toArray(new Relationship[0]);
    }
// *************************************************************************************************** getUsersAtDistance()
    public User[] getUsersAtDistance(User fromUser, int distance) {
        ArrayList<User> usersAtDistance = new ArrayList<>();
        if (distance == 1) {
            for (Relationship friend : fromUser.getFriends()) {
                usersAtDistance.add(friend.friendB);
            }
        } else if ( distance > 1){
            ArrayList<User> dList = new ArrayList<>();
            for (User user : users) {
                user.distance = 0;
                dList.add(user);
            }

            // while(dList.size() > 0) {
            //     User curr = dList.get(0);

            //     curr.distance = minEdges(fromUser, curr);
            //     System.out.println(curr.userName +"("+ curr.distance+") ");
            //     dList.remove(curr);
            // }


        }
        
        return usersAtDistance.toArray(new User[0]);
    }
// *************************************************************************************************** getAllUsers()
    public User[] getAllUsers() {
        return users.toArray(new User[0]);
    }

// *************************************************************************************************** getUncolored()
    public ArrayList<User> getUncolored(ArrayList<User> uncolored) {
        for (User user : users) {
            if (user.color == 0) {
                uncolored.add(user);
            }
        }

        return uncolored;
    }

// *************************************************************************************************** processNext()
    public User processNext(ArrayList<User> uncolored) {
        ArrayList<User> highestSaturation = new ArrayList<>();
        int highest = 0;
        for (User user : uncolored) {
            if (user.saturationDeg > highest) {
                highest = user.saturationDeg;
            }
        }

        for (User user : uncolored) {
            if (user.saturationDeg == highest) {
                highestSaturation.add(user);
            }
        }

        User v = highestSaturation.get(0);
        for (User user : highestSaturation) {
            if (user.uncoloredDeg > v.uncoloredDeg) {
                v = user;
            }
        }
        return v;
    }

// *************************************************************************************************** getColor()
    public int getColor(User user) {
        int j = 1;
        
        for (int i = 0; i < user.getFriends().length; i++) {
            if (user.getFriends()[i].friendB.color == j) {
                j++;
                i = -1;
            }
        }
        return j;
    }

// *************************************************************************************************** adjToColor()
    public boolean adjToColor(User u, int j) {
        for (Relationship rel : u.getFriends()) {
            if (rel.friendB.color == j) {
                return true;
            }
        }
        return false;
    }

// *************************************************************************************************** listColors()
    public ArrayList<Integer> getAllColors(ArrayList<Integer> listColors) {
        boolean found;
        for (User user : users) {
            found = false;
            for (Integer color : listColors) {
                if (user.color == color) {
                    found = true;
                }
            }

            if (!found) {
                listColors.add(user.color);
            }
        }

        return listColors;
}

// *************************************************************************************************** unionCycle()
    ArrayList<Integer> root = new ArrayList<>();
    ArrayList<Integer> next = new ArrayList<>();
    public Relationship unionCycle(ArrayList<Relationship> tree) {

        for (int i = 0; i < users.size()-1; i++) {
            root.set(i, i);
            next.set(i, i);
        }

        for (Relationship edge : tree) {
            union(edge);
        }

        return null;
    }

// *************************************************************************************************** unionCycle()
    public void union(Relationship edge) {
        if (root.get(edge.friendA.userID) == root.get(edge.friendB.userID)) {
            return;
        }
    }

// *************************************************************************************************** minEdges()
    int min;
    int count;
    public int minEdges(User src, User des){
        min = Integer.MAX_VALUE;
        count = 0;

        minEdgeUtil(src, des);

        return min;
    }

    public void minEdgeUtil(User src, User des) {
        src.distance = count;

        if (src.equals(des)) {
            if (min > count) {
                min = count;
            }
        } else {
            for (Relationship friend : src.getFriends()) {
                User temp = friend.friendB;

                if (temp.distance == 0) {
                    count++;
                    minEdgeUtil(temp, des);
                }
            }
        }

        src.distance = 0;
        count--;
    }


}
