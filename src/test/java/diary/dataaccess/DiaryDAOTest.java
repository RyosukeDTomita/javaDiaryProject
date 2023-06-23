package diary.dataaccess;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;

import diary.annotation.Fast;
import diary.annotation.Slow;
import diary.util.ConnectionManagerUtil;
import diary.vo.DiaryVO;

/**
 * {@link DiaryDAO DiaryDAO} Test.
 * 
 * @author iceman
 * @version 1.0
 */
@Disabled
public class DiaryDAOTest {

    static DiaryDAO diaryDAO;
    static DiaryVO dvo;
    static DiaryVO dvo2;
    static DiaryVO dvo3;

    /**
     * set up method.
     */
    @BeforeAll
    public static void setUpClass() {
        ConnectionManagerUtil managerUtil = new ConnectionManagerUtil();
        Connection connection = managerUtil.getConnection();
        diaryDAO = new DiaryDAO(connection);
        dvo = new DiaryVO("laughing man", "1999-12-31", "僕は耳と目を閉じ，口を噤んだ人間になろうと考えた。");
        dvo2 = new DiaryVO("Bismarck", "1815-01-12", "愚者は経験から学び，賢者は歴史に学ぶ");
        dvo3 = new DiaryVO("laughing man", "2022-12-24", "Merry Christmas!");
    }

    /**
     * insert() test.
     */
    @Test
    @Fast
    public void insertTest() {
        assertNotEquals(0, diaryDAO.insert(dvo));
        assertNotEquals(0, diaryDAO.insert(dvo2));
        assertNotEquals(0, diaryDAO.insert(dvo3));
    }

    /**
     * selectAllDiary() test.
     */
    @Test
    @Fast
    public void selectMyDiaryTest() {
        // set up
        List<DiaryVO> preparedDiaryList = new ArrayList<>(); // actual data.
        preparedDiaryList.add(dvo3);
        preparedDiaryList.add(dvo);
        diaryDAO.insert(dvo);
        diaryDAO.insert(dvo2);
        diaryDAO.insert(dvo3);
        // test
        List<DiaryVO> diaryList = diaryDAO.selectUserDiary("laughing man");
        assertEquals(preparedDiaryList.toString(), diaryList.toString());

    }

    /**
     * getDeletingDiaryNumber() is private method.
     * use reflection api.
     */
    @Test
    @Fast
    public void getDeletingDiaryNumberTest()
            throws IllegalAccessException, IllegalArgumentException,
            InvocationTargetException,
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,
            NoSuchMethodException {
        // setup
        DiaryVO dvoTmp = new DiaryVO("droptest", "2022-12-25", "今日はクリスマスです。");
        DiaryVO dvoTmpDummy = new DiaryVO("droptest", "2022-12-25", "ダミー。");
        diaryDAO.insert(dvoTmp);

        // use reflection api, get private method.
        Method testMethod = DiaryDAO.class.getDeclaredMethod("getDeletingDiaryNumber", DiaryVO.class);
        testMethod.setAccessible(true);

        // test
        int actual = (int) testMethod.invoke(diaryDAO, dvoTmp);
        assertNotEquals(-99, actual);
        int actualDummy = (int) testMethod.invoke(diaryDAO, dvoTmpDummy);
        assertEquals(-99, actualDummy);

        // tear down
        diaryDAO.drop(dvoTmp);

    }

    /**
     * drop() test.
     */
    @Test
    @Fast
    public void dropTest() {
        // setup
        DiaryVO dvoTmp = new DiaryVO("droptest", "2022-12-25", "今日はクリスマスです。");
        DiaryVO dvoTmpDummy = new DiaryVO("droptest", "2022-12-25", "ダミー。");
        diaryDAO.insert(dvoTmp);

        // test
        assertEquals(0, diaryDAO.drop(dvoTmpDummy)); // not drop.
        assertNotEquals(0, diaryDAO.drop(dvoTmp));
    }

    /**
     * drop diary data.
     * This method test drop().
     */
    @AfterEach
    public void tearDown() {
        diaryDAO.drop(dvo);
        diaryDAO.drop(dvo2);
        diaryDAO.drop(dvo3);
    }
}
