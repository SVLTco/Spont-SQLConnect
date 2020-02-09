import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class SQLWrite {

    public static SpontUser addUser(SQLConnection database, int userID, String username, String password, float lat, float lon, int pingingID) {
        Connection conn = database.getConnection();
        Statement s = database.getStatement();

        SpontUser user = new SpontUser(userID, username);

        try {
            s.executeUpdate("INSERT INTO user_data(userID, username, password, noOfGroups, lat, lon, pingingID) values (\"" + userID + "\", \"" + username + "\", \"" + password + "\", \"" + 0 + "\", \"" + lat + "\", \"" + lon + "\", \"" + pingingID + "\");");
            s.executeUpdate("CREATE TABLE Friend_" + userID + " (userID VARCHAR(255));");

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return user;
    }

    /**
    public static boolean createGroup(SpontUser user, String groupName, int userIDs[] ){
        for(int)

    }*/
    public static void main(String[] args) {
        SQLConnection db = new SQLConnection("35.243.223.126", "root", "A7eqjnaejqqsgOII");

        addUser(db, 1, "test", "pw", (float) 40.00, (float) 4.034043030, 0);

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