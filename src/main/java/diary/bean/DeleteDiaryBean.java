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
 * delete selected Diary.
 * called by {@link diary.servlet.MainServlet MainServlet}.
 * 
 * @author iceman
 * @version 1.0
 */
public class DeleteDiaryBean implements ExecuteInterface {
    /**
     * {@inheritDoc}
     * 
     * Delete selected diary.
     * 
     * @return String "success" or "failure"
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        // get Connection.
        ConnectionManagerUtil managerUtil = new ConnectionManagerUtil();
        Connection connection = managerUtil.getConnection();
        HttpSession session = request.getSession(false);

        // set diary data.
        String loginID = (String) session.getAttribute("loginID");
        String dateTime = request.getParameter("dateTime");
        String sentence = request.getParameter("sentence");

        // dao
        DiaryDAO diaryDAO = new DiaryDAO(connection);
        DiaryVO diaryVO = new DiaryVO(loginID, dateTime, sentence);
        int statusCode = diaryDAO.insert(diaryVO);

        if (statusCode == -99) {
            ResourceBundle rb = ResourceBundle.getBundle("message");
            request.setAttribute("errorMessage", rb.getString("deleteDiary.error.message"));
            return "failure";
        }
        managerUtil.commit();
        managerUtil.closeConnection();
        return "success";

    }
}
