package diary.dataaccess;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import diary.annotation.Fast;
import diary.util.ConnectionManagerUtil;

/**
 * {@link LoginDAD LoginDAO} Test.
 * 
 * @author iceman
 * @version 1.0
 */
public class LoginDAOTest {
    static LoginDAO loginDAO;

    @BeforeAll
    public static void setUpClass() {
        ConnectionManagerUtil managerUtil = new ConnectionManagerUtil();
        Connection connection = managerUtil.getConnection();
        loginDAO = new LoginDAO(connection);
    }

    @Test
    @Fast
    public void checkLoginTest() {
        try {
            // Collect User login.
            boolean loginTrue = loginDAO.checkLogin("hoge", "password");
            assertTrue(loginTrue);

            // incorrect password.
            boolean loginFalse1 = loginDAO.checkLogin("hoge", "p@ssword");
            assertFalse(loginFalse1);

            // incorrect user id.
            boolean loginFalse2 = loginDAO.checkLogin("anon", "password");
            assertFalse(loginFalse2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
