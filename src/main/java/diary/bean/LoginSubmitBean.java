package diary.bean;

import diary.dataaccess.LoginDAO;
import diary.util.ConnectionManagerUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * {@inheritDoc}
 * Login process.
 * called by {@link diary.servlet.MainServlet MainServlet}.
 * 
 * @author iceman
 * @version 1.0
 */
public class LoginSubmitBean implements ExecuteInterface {

    /**
     * {@inheritDoc}
     * call from {@link diary.servlet.MainServlet MainServlet}
     * 
     * @param request  {@code HttpServletRequest}
     * @param response {@code HttpServletResponse}
     * @return String "success" or "failure".
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String loginID = request.getParameter("loginID");
        String password = request.getParameter("password");

        boolean auth = false;
        try {
            // auth = check(loginID, password, request, response);
            // can not use try with resource with boolean.
            auth = check(loginID, password); // can not use try with resource with boolean.
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Authentication error.");
        }
        if (auth) {
            HttpSession session = request.getSession(true); // No session, then create new one.
            session.setAttribute("loginID", loginID);
            return "success";
        } else {
            ResourceBundle rb = ResourceBundle.getBundle("message");
            request.setAttribute("errorMessage", rb.getString("login.error.message"));
            return "failure";
        }

    }

    /**
     * call {@link diary.dataaccess.LoginDAO LoginDAO}.
     * 
     * @param loginID  {@code String}
     * @param password {@code String}
     * @return auth {@code boolean}
     * @throws SQLException
     *                      When {@code loginDAO.checkLogin()}
     */
    public boolean check(String loginID, String password) throws SQLException {
        // get Connection.
        ConnectionManagerUtil managerUtil = new ConnectionManagerUtil();
        Connection connection = managerUtil.getConnection();
        LoginDAO loginDAO = new LoginDAO(connection);
        try {
            boolean auth = loginDAO.checkLogin(loginID, password);
            return auth;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
