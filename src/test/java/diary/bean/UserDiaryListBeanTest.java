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
 * LoginSubmitBean Test.
 * check() method test are skipped.
 * See {@link diary.dataaccess.DiaryDAO}.
 * 
 * @author iceman
 * @version 1.0
 */
@Disabled
public class UserDiaryListBeanTest {
    /**
     * Instantiating test class.
     */
    public class InstantiateTest {
        /**
         * test create {@code UserDiaryListBean} instance.
         */
        @Fast
        @Test
        public void defaultConstructorTest() {
            String className = "diary.bean.UserDiaryListBean";
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
     * Before test, create {@code UserDiaryListBean} and insert test data in SQL.
     */
    @BeforeAll
    public static void setUpClass() {
        String className = "diary.bean.UserDiaryListBean";
        try {
            Class<?> clazz = Class.forName(className); // return "className" Class Object.
            // newInstance() is deprecate.
            usi = (ExecuteInterface) clazz.getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // add test data.
        ConnectionManagerUtil managerUtil = new ConnectionManagerUtil();
        Connection connection = managerUtil.getConnection();
        DiaryDAO diaryDAO = new DiaryDAO(connection);
        DiaryVO dvo = new DiaryVO("laughing man", "1999-12-31", "僕は耳と目を閉じ，口を噤んだ人間になろうと考えた。");
        diaryDAO.insert(dvo);
        managerUtil.commit();
        managerUtil.closeConnection();
    }

    /**
     * After test, delete test data inserted by {@code setUpClass}.
     */
    @AfterAll
    public static void tearDownClass() {
        ConnectionManagerUtil managerUtil = new ConnectionManagerUtil();
        Connection connection = managerUtil.getConnection();
        DiaryDAO diaryDAO = new DiaryDAO(connection);
        DiaryVO dvo = new DiaryVO("laughing man", "1999-12-31", "僕は耳と目を閉じ，口を噤んだ人間になろうと考えた。");
        diaryDAO.drop(dvo);
        managerUtil.commit();
        managerUtil.closeConnection();
    }

    /**
     * This test expect {@code success} pattern.
     * {@code request}, {@code response} are stub.
     */
    @Fast
    @Test
    public void executeSuccessTest() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        MockHttpSession mockSession = new MockHttpSession();
        mockSession.setAttribute("loginID", "laughing man");
        when(request.getSession(false)).thenReturn(mockSession);

        assertEquals("success", usi.execute(request, response));
    }

}