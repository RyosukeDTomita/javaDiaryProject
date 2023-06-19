package diary.dataaccess;

import diary.util.ConnectionManagerUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DataBase access related with login.
 * Used by {@link diary.bean.LoginSubmitBean LoginSubmitBean}.
 * 
 * @author iceman
 * @version 1.0
 */

public class LoginDAO {
    ConnectionManagerUtil connetionmanager = new ConnectionManagerUtil();
    private Connection connection;

    /**
     * constructor.
     */
    public LoginDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * run SQL syntax to check user input id, password is exists.
     *
     * @param id
     *                 User ID
     * @param password
     *                 User password.
     * @return boolean
     * @throws SQLException
     *                      When argument(id, password) is not valid data.
     */
    public boolean checkLogin(String id, String password) throws SQLException {
        String sql = "SELECT id FROM account WHERE id = ? AND password = ?";

        // set user input id, password.
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, id);
        statement.setString(2, password);

        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                    System.out.println("Connection close successfully.");
                } catch (SQLException e) {
                    throw new RuntimeException("Failed to close statement.", e);
                }
            }
        }
    }
}