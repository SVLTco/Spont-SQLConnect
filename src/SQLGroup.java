public class SQLGroup {

    private int groupID;

    private String groupName;

    public SQLGroup(int groupID, String groupName){
            this.groupID = groupID;
            this.groupName = groupName;
    }

    public int getGroupID() {
        return groupID;
    }

    public String getGroupName() {
        return groupName;
    }
}
