package diary.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * ConnectionManagerUtil.
 * Used by DAO.
 * 
 * @author iceman
 * @version 1.0
 */
public class ConnectionManagerUtil {
    private static String driverName;
    private static String host;
    private static String user;
    private static String password;
    private static String dbName;
    private static String port;
    private static Connection connection;

    /*
     * Initial setting for Data Base.
     * fetch db info and load db driver.
     * static {} run when constructor is called.
     */
    static {
        // from "system.properties", fetch DB information.
        ResourceBundle rb = ResourceBundle.getBundle("system");
        driverName = rb.getString("db.driver");
        user = rb.getString("db.user");
        dbName = rb.getString("db.dbName");
        password = rb.getString("db.pass");
        host = rb.getString("db.host");
        port = rb.getString("db.port");

        // fix "No suitable driver found for jdbc error".
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("can not load data base driver.");
        }
    }

    /**
     * Create connection to Data Base.
     *
     * @return Connection
     *         sql connection.
     */
    public Connection getConnection() {
        String dbPath = String.format("jdbc:mysql://%s:%s/%s", host, port, dbName);
        try {
            connection = DriverManager.getConnection(dbPath, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("data base connection error.", e);
        }

        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to set auto commit=off.", e);
        }
        return connection;

    }

    /**
     * commit change db.
     */
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to commit.");
        }
    }

    /**
     * roll back db.
     */
    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to rollback.");
        }
    }

    /**
     * close Connection.
     */
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to close connection.", e);
        }
    }

}
