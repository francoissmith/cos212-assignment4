import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        //StudentExample();
        // User a = new User("a", 1);
        // User b = new User("b", 2);
        // User c = new User("c", 3);
        // User d = new User("d", 4);
        // User e = new User("e",5);
        // User f = new User("f", 6);
        // User g = new User("g", 7);
        // User h = new User("h", 8);

        GraphDB g1 = new GraphDB();
        g1.addUser("a", 1);
        g1.addUser("b", 2);
        g1.addUser("c", 3);
        g1.addUser("d", 4);
        g1.addUser("e", 5);
        g1.addUser("f", 6);
        g1.addUser("g", 7);
        g1.addUser("h", 8);
        // System.out.println(a.toString());

        // System.out.println(g1.getUser("b"));
        // System.out.println(g1.getUser(3));

        // a.addFriend(e, 10);
        // a.addFriend(f, 10);
        // a.addFriend(g, 10);
        // b.addFriend(e, 11);
        // b.addFriend(h, 11);
        // b.addFriend(c, 11);
        // c.addFriend(g, 12);
        // c.addFriend(b, 12);
        // d.addFriend(f, 13);
        // d.addFriend(g, 13);
        // e.addFriend(a, 14);
        // e.addFriend(b, 14);
        // f.addFriend(h, 15);
        // f.addFriend(g, 15);
        // g.addFriend(h, 16);




        // for(int i = 0; i < a.getFriends().length; i++){
        //     System.out.println(a.getFriends()[i]);
        //     System.out.println(b.getFriends()[i]);
        // }

        g1.addFriendship(1, 5, 1);
        g1.addFriendship(1, 6, 2);
        g1.addFriendship(1, 7, 3);
        g1.addFriendship(2, 3, 4);
        g1.addFriendship(2, 5, 5);
        g1.addFriendship(2, 8, 6);
        g1.addFriendship(3, 7, 7);
        g1.addFriendship(4, 6, 8);
        g1.addFriendship(4, 7, 9);
        g1.addFriendship(6, 7, 10);
        g1.addFriendship(6, 8, 11);
        g1.addFriendship(7, 8, 12);
        // for(int i = 0; i < g1.getUser("a").getFriends().length; i++){
        //     System.out.println(g1.getUser("a").getFriends()[i]);
        // }

        User[][] result = g1.clusterUsers();

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + "  ");
            }
            System.out.println();
        }

        System.out.println();
        for (int i = 1; i <= g1.getAllUsers().length; i++) {
            System.out.println(g1.getUser(i) + ":");
            for (Relationship relationship : g1.getUser(i).getFriends()) {
                System.out.print(" "+relationship.friendA.toString() + "->" + relationship.friendB);
            }
            System.out.println();
            // System.out.println();
        }
        return;
    }
}

/*
Expected Output:
D[3]-(8.0)->E[4]
A[0]-(2.0)->B[1]
F[5]-(1.0)->G[6]
B[1]-(6.0)->E[4]
C[2]-(3.0)->G[6]
B[1]-(9.0)->F[5]
A[0];F[5];
B[1];C[2];D[3];
E[4];G[6];
G[6]
F[5]
E[4]
b[1];g[6];
c[2];e[4];f[5];
a[0];d[3];h[7];
*/
