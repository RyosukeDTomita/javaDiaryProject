package diary.servlet;

import diary.bean.ExecuteInterface;
import java.io.IOException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Called by jsp.
 * forwarding jsp.
 * 
 * @author iceman
 * @version 1.0
 */
public class MainServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ResourceBundle rb = ResourceBundle.getBundle("action");
        String forwardPage = rb.getString("TopPage");

        HttpSession session = request.getSession(false); // session not exist, return null.
        if (session != null) {
            session.invalidate(); // destroy session.
        }

        // forward to login.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPage);
        dispatcher.forward(request, response);
        return;
    }

    /**
     * @param request  {@code HttpServletRequest}
     * @param response {@code HttpServletResponse}
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ResourceBundle rb = ResourceBundle.getBundle("action");
        HttpSession session = request.getSession(false); // Session not exist, then return null.
        String action = request.getParameter("action"); // get html hidden parameter.

        try {
            StringTokenizer tkn = new StringTokenizer(action, "."); // separating action by "."
            String pageName = tkn.nextToken();

            String noAuthPage = "false";
            try {
                noAuthPage = rb.getString(pageName + ".noAuthPage");
            } catch (MissingResourceException e) {
                e.printStackTrace();
                System.out.println("noAuthPage is not found in action.property.");
            }

            // Authentication page.
            if ("false".equals(noAuthPage)) {
                // no session, then forward to login.jsp.
                if (session == null || session.getAttribute("loginID") == null
                        || "".equals(session.getAttribute("loginID"))) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher(rb.getString("TopPage"));
                    dispatcher.forward(request, response);
                    return;
                }
            }

            String className = rb.getString(action); // action.properties .submit value.
            Class<?> clazz = Class.forName(className); // return "className" Class Object.
            // newInstance() is deprecaetd.
            ExecuteInterface usi = (ExecuteInterface) clazz.newInstance();

            String ret = usi.execute(request, response); // return "success" or "failure".

            try {
                String forwardPage = rb.getString(action + "." + ret);
                RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPage);
                dispatcher.forward(request, response);
                return;
            } catch (MissingResourceException e) {
                e.printStackTrace();
                System.out.println("No forwarding in jsp action.property");
            }

        } catch (Exception e) {
            e.printStackTrace();

            if (session != null) {
                session.invalidate();
            }
            // forward to error.jsp.
            RequestDispatcher dispatcher = request.getRequestDispatcher(rb.getString("system.error.top"));
            dispatcher.forward(request, response);
            return;
        }

    }

}