package diary.dataaccess;

import diary.util.ConnectionManagerUtil;
import diary.vo.DiaryVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * DataBase access class related with Diary contents.
 * Used by {@link diary.bean.UserDiaryListBean DiaryListBean},
 * {@link diary.bean.NewDiaryBean CreateDiaryBean}.
 * 
 * @author iceman
 * @version 1.0
 */
public class DiaryDAO {

    ConnectionManagerUtil connectionManager = new ConnectionManagerUtil();
    private Connection connection;

    public DiaryDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Insert DiaryVO into SQL Data Base.
     *
     * @param dvo {@code DiaryVO}
     * @return int
     * 
     */
    public int insert(DiaryVO dvo) {
        String INSERT_SQL = "INSERT INTO diarycontent (dt, id, sentence, checks) VALUES(?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {
            System.out.println("INSERT " + dvo);
            statement.setString(1, dvo.getDateTime());
            statement.setString(2, dvo.getLoginID());
            statement.setString(3, dvo.getSentence());
            statement.setString(4, "NULL"); // WEP
            int count = statement.executeUpdate();
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            return -99; // error
        }

    }

    /**
     * Select {@code userID}'s all diary data and store {@code diaryList}.
     * This list is consist of {@link DiaryVO DiaryVO}.
     * 
     * @return List
     */
    public List<DiaryVO> selectUserDiary(String userID) {
        String SELECT_SQL = "SELECT dt, id, sentence, checks FROM diarycontent WHERE id=?";

        List<DiaryVO> diaryList = new ArrayList<>(); // = ArrayList<DiaryVO>() (Diamond Operator).
        try (PreparedStatement statement = connection.prepareStatement(SELECT_SQL)) {
            statement.setString(1, userID);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    DiaryVO diaryVO = new DiaryVO();
                    diaryVO.setDateTime(resultSet.getString("dt").replace(" 00:00:00", ""));
                    diaryVO.setLoginID(resultSet.getString("id"));
                    diaryVO.setSentence(resultSet.getString("sentence"));
                    diaryList.add(diaryVO);
                }
                // System.out.println(diaryList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLException.");
        }
        Collections.reverse(diaryList);
        return diaryList;
    }

    /**
     * Used in {@code drop()}.
     * From DB, get diarycontent NUMBER.
     * 
     * @param dvo
     * @return int
     */
    private int getDeletingDiaryNumber(DiaryVO dvo) {
        String SELECT_SQL = "SELECT * FROM diarycontent WHERE id=? AND dt=?";

        try (PreparedStatement statement = connection.prepareStatement(SELECT_SQL)) {
            statement.setString(1, dvo.getLoginID());
            statement.setString(2, dvo.getDateTime());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    DiaryVO delCandidateDvo = new DiaryVO();
                    delCandidateDvo.setDateTime(resultSet.getString("dt").replace(" 00:00:00", ""));
                    delCandidateDvo.setLoginID(resultSet.getString("id"));
                    delCandidateDvo.setSentence(resultSet.getString("sentence"));
                    if (delCandidateDvo.equals(dvo)) {
                        return resultSet.getInt("NUMBER");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLException.");
        }
        return -99; // No deleting target.
    }

    /**
     * Delete diary data.
     * 
     * @param dvo
     * @return
     */
    public int drop(DiaryVO dvo) {
        String DELETE_SQL = "DELETE FROM diarycontent WHERE NUMBER=?";
        Integer dropDiaryNumber = getDeletingDiaryNumber(dvo);
        try (PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {
            System.out.println("DELETE " + dvo);
            statement.setString(1, dropDiaryNumber.toString());
            int count = statement.executeUpdate();
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            return -99; // error
        }
    }
}
