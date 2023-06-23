package diary.bean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;

import org.apache.struts.mock.MockHttpSession;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;

import diary.annotation.Fast;
import diary.util.ConnectionManagerUtil;
import diary.vo.DiaryVO;
import diary.dataaccess.DiaryDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * NewDiaryBean() Test.
 * See {@link diary.dataaccess.DiaryDAO}.
 * 
 * @author iceman
 * @version 1.0
 */
@Disabled
public class NewDiaryBeanTest {
    /**
     * Instantiating test class.
     */
    public class InstantiateTest {
        /**
         * test create {@code NewDiaryBean} instance.
         */
        @Fast
        @Test
        public void defaultConstructorTest() {
            String className = "diary.bean.NewDiaryBean";
            try {
                Class<?> clazz = Class.forName(className); // return "className" Class Object.
                // newInstance() is deprecaetd.
                ExecuteInterface usi = (ExecuteInterface) clazz.getDeclaredConstructor().newInstance();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    static ExecuteInterface usi;

    /**
     * Before test, create {@code NewDiaryBean} instance and insert test data in
     * SQL.
     */
    @BeforeAll
    public static void setUpClass() {
        String className = "diary.bean.NewDiaryBean";
        try {
            Class<?> clazz = Class.forName(className); // return "className" Class Object.
            // newInstance() is deprecate.
            usi = (ExecuteInterface) clazz.getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * After test, delete test data inserted by {@code setUpClass}.
     */
    @AfterAll
    public static void tearDownClass() {
        ConnectionManagerUtil managerUtil = new ConnectionManagerUtil();
        Connection connection = managerUtil.getConnection();
        DiaryDAO diaryDAO = new DiaryDAO(connection);
        DiaryVO dvo = new DiaryVO("laughing man", "1999-12-31",
                "僕は耳と目を閉じ，口を噤んだ人間になろうと考えた。");
        diaryDAO.drop(dvo);
        managerUtil.commit();
        managerUtil.closeConnection();
    }

    /**
     * executeSuccessTest()
     * This test expect {@code success} pattern.
     * {@code request}, {@code response} are stub.
     */
    @Fast
    @Test
    public void executeSuccessTest() {
        // set up. create request, response stub.
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("dateTime")).thenReturn("1999-12-31");
        when(request.getParameter("sentence")).thenReturn("僕は耳と目を閉じ，口を噤んだ人間になろうと考えた。");
        MockHttpSession mockSession = new MockHttpSession();
        mockSession.setAttribute("loginID", "laughing man");
        when(request.getSession(false)).thenReturn(mockSession);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // test
        assertEquals("success", usi.execute(request, response));
    }

}