/**
 * SQLConnection
 *
 * A class for defining and easily connecting to a Serverside
 * SQL Instance for use with Spont: The spontanious
 *
 * @author Matthew Tighe
 */

import java.sql.*;
import java.util.Properties;


public class SQLConnection {
    /**
    * Name of Default DB to connect to;
    */
    private String DB = "spont_users";

    /**
     * Base Connection point for server access;
     */
    private Connection conn = null;

    /**
     * The base statement of the connection;
     */
    private Statement s = null;

    /**
     * SQLConnection Instance
     *
     * The representation of a full-seal connection to a SQL Database Server Hosted
     * off location to the local machine. Create a new Instance and pass to SQLWrite and SQLRead
     * functions for full usage
     *
     * @param hostname ip/domain in form com.test or ipv4 form (NO PORT: DEFAULT IS 3306)
     * @param username the username for the database
     * @param password the password for the database
     *
     * @object SQLConnection a connection to the database called "DB"(var.) on a server hosted at
     *                       host.
     */
    public SQLConnection(String hostname, String username, String password){
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Properties connectionProps = new Properties();
            connectionProps.put("user", username);
            connectionProps.put("password", password);

            this.conn = DriverManager.getConnection("jdbc:" + "mysql" + "://" + hostname + ":" + "3306" + "/" + "", connectionProps);

            this.s = conn.createStatement();

            s.executeUpdate("USE spont_users;");

            System.out.printf("Base connection point initialized successfully at: " + this + "\n");

        } catch (Exception msee){
            msee.printStackTrace();
        }
    }

    /**
     * Closes the connection local variable and the
     * statement local variable
     *
     * @return true - if both closes work
     *         false - if either close fails
     */
    public boolean closeConnection(){
        if(this.conn != null){
            try {
                this.s.close();
                this.conn.close();
            } catch (Exception e) {
                System.err.println(e.getMessage());
                return false;
            }
        }
        return true;
    }

    /**
     * Get Statement
     * @return the statement associated with this SQL instance

     */
    public Statement getStatement(){
        return this.s;
    }

    /**
     * Get Connection
     * @return the socket connection associated with this SQL instance

     */
    public Connection getConnection(){
        return this.conn;
    }

    /**
     * Debug printing
     * @return Form: SQLConnection: root@23.236.57.155 (e.g.)
     */
    @Override
    public String toString(){
        return "SQLConnection: " + this.conn;
    }

}
