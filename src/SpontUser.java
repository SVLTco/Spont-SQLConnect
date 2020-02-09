import java.util.ArrayList;

public class SpontUser {

    private int userID;

    private String username;

    private ArrayList<SQLGroup> groups;

    public SpontUser(int userID, String username){
        this.userID = userID;
        this.username = username;
        this.groups = new ArrayList<>();
    }

    public int getUserID() {
        return userID;
    }

    public ArrayList<SQLGroup> getGroups() {
        return groups;
    }

    public String getUsername() {
        return username;
    }

    public void addGroup(SQLGroup group){
        groups.add(group);
    }
}
