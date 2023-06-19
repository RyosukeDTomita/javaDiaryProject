package diary.bean;

import diary.dataaccess.DiaryDAO;
import diary.util.ConnectionManagerUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import diary.vo.DiaryVO;

/**
 * {@inheritDoc}
 * fetch diary content.
 * called by {@link diary.servlet.MainServlet MainServlet}.
 * 
 * @author iceman
 * @version 1.0
 */
public class UserDiaryListBean implements ExecuteInterface {

    /**
     * {@inheritDoc}
     * call from newdiary.jsp
     * 
     * @param request
     * @param response
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        String loginID = (String) session.getAttribute("loginID");
        System.out.println("=====");
        System.out.println(loginID);
        List<DiaryVO> diaryVOList = selectUserDiary(loginID);
        if (diaryVOList.size() == 0) {
            ResourceBundle rb = ResourceBundle.getBundle("message");
            session.setAttribute("errorMessage", rb.getString("userDiary.error.message"));
            return "failure";
        }
        session.setAttribute("diaryVOList", diaryVOList);
        return "success";
    }

    /**
     * 
     * @param loginID
     * @return List<DiaryVO>
     */
    public List<DiaryVO> selectUserDiary(String loginID) {

        // get Connection.
        ConnectionManagerUtil managerUtil = new ConnectionManagerUtil();
        Connection connection = managerUtil.getConnection();

        DiaryDAO diaryDAO = new DiaryDAO(connection);
        List<DiaryVO> diaryVOList = diaryDAO.selectUserDiary(loginID);

        managerUtil.closeConnection();
        return diaryVOList;
    }
}
