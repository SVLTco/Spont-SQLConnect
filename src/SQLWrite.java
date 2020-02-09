import com.sun.org.apache.bcel.internal.ExceptionConst;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import static java.lang.System.exit;

public class SQLWrite {

    public static SpontUser addUser(SQLConnection database, int userID, String username, String password, float lat, float lon, int pingingID) {
        Connection conn = database.getConnection();
        Statement s = database.getStatement();

        SpontUser user = new SpontUser(userID, username);

        try {
            ResultSet r = s.executeQuery("SELECT * FROM user_data");
            String exist, id;
            while (r.next()) {
                id = r.getString(1);
                exist = r.getString(2);
                if (exist.equals(username) || id.equals(userID)) {
                    System.err.println("User Already Exists! Try another Username and ID");
                    exit(1);
                }
            }

            s.executeUpdate("INSERT INTO user_data(userID, username, password, noOfGroups, lat, lon, pingingID) values (\"" + userID + "\", \"" + username + "\", \"" + password + "\", \"" + 0 + "\", \"" + lat + "\", \"" + lon + "\", \"" + pingingID + "\");");
            s.executeUpdate("CREATE TABLE Friend_" + userID + " (userID VARCHAR(255));");

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }

    public static boolean createGroup(SQLConnection database, SpontUser user, String groupName, int userIDs[] ) {
        try {
            database.getStatement().executeUpdate("CREATE TABLE Group_" + user.getUserID() + "_" + (user.getGroups().size() + 1) + " (userID VARCHAR(255));");

            for (int i = 0; i < userIDs.length; i++) {
                database.getStatement().executeUpdate("INSERT INTO Group_" + user.getUserID() + "_" + (user.getGroups().size() + 1) + " (userID) values (" + userIDs[i] + ");");
            }
            user.addGroup(new SQLGroup(user.getUserID(), groupName));

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean addFriend(SQLConnection database, SpontUser user, int userID) {
        try {
            database.getStatement().executeUpdate("INSERT INTO Friends_" + user.getUserID() + " (userID) values (userID)");
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        SQLConnection db = new SQLConnection("35.243.223.126", "root", "A7eqjnaejqqsgOII");

        SpontUser clientside1 = addUser(db, 1, "test1", "pw", (float) 40.00, (float) 4.034043030, 0);
        SpontUser clientside2 = addUser(db, 2, "test2", "pw", (float) 40.00, (float) 4.034043030, 0);
        SpontUser clientside3 = addUser(db, 3, "test3", "pw", (float) 40.00, (float) 4.034043030, 0);
        SpontUser clientside4 = addUser(db, 4, "test4", "pw", (float) 40.00, (float) 4.034043030, 0);

        int users[] = new int[]{1, 2, 3};
        System.out.println(createGroup(db, clientside4, "SALT", users));

        db.closeConnection();
    }
}

/**
 *INSERT INTO cities (city, country) values ("San Francisco", "USA");
 * INSERT INTO cities (city, country) values ("Beijing", "China");
 *
 CREATE TABLE user-data (
   userID VARCHAR(255),
   username VARCHAR(255),
   password VARCHAR(255),
   noOfGroups VARCHAR(255),
   lat DECIMAL VARCHAR(255),
   lon DECIMAL VARCHAR(255)
   pingingID VARCHAR(255)
  );
 */