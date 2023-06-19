package diary.bean;

import diary.dataaccess.DiaryDAO;
import diary.util.ConnectionManagerUtil;
import diary.vo.DiaryVO;
import java.sql.Connection;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * {@inheritDoc}
 * Create new Diary.
 * called by {@link diary.servlet.MainServlet MainServlet}.
 * 
 * @author iceman
 * @version 1.0
 */
public class NewDiaryBean implements ExecuteInterface {

    /**
     * {@inheritDoc}
     * 
     * From web app, get user diary data and store {@link DiaryVO DiaryVO}.
     * 
     * @return String "success" or "failure"
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        // get Connection.
        ConnectionManagerUtil managerUtil = new ConnectionManagerUtil();
        Connection connection = managerUtil.getConnection();
        HttpSession session = request.getSession(false);

        // set diary data from web session.
        String loginID = (String) session.getAttribute("loginID");
        String dateTime = request.getParameter("dateTime");
        String sentence = request.getParameter("sentence");

        // Use dao to insert into DB.
        DiaryDAO diaryDAO = new DiaryDAO(connection);
        DiaryVO diaryVO = new DiaryVO(loginID, dateTime, sentence);
        int statusCode = diaryDAO.insert(diaryVO);
        if (statusCode == -99) {
            ResourceBundle rb = ResourceBundle.getBundle("message");
            request.setAttribute("errorMessage", rb.getString("newDiary.error.message"));
            managerUtil.closeConnection();
            return "failure";
        }

        managerUtil.commit();
        managerUtil.closeConnection();
        return "success";

    }
}
