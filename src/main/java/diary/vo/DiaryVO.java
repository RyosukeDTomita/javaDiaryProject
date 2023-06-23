package diary.vo;

import java.io.Serializable;

import diary.util.CalendarUtil;

import java.util.Calendar;
import java.util.Comparator;
import static java.util.Comparator.comparing;

/**
 * Store the diary data.
 * DiaryVO()
 * DiaryVO(loginID, dateTime(yyyy-mm-dd), sentence)
 * 
 * @author iceman
 * @version 1.0
 */
public class DiaryVO implements Serializable, Comparable<DiaryVO> {
    private static final long serialVersionUID = 1L;
    private String loginID;
    private String dateTime;
    private String sentence;
    private Calendar dateTimeCalendar;
    private CheckListVO checkListVO;

    /**
     * DiaryVO Constructor().
     */
    public DiaryVO() {
    }

    /**
     * DiaryVO Constructor(loginID, dateTime, sentence).
     *
     * @param loginID  loginID
     * @param dateTime diary created date time.
     * @param sentence diary full text.
     */
    public DiaryVO(String loginID, String dateTime, String sentence) {
        this.loginID = loginID;
        this.dateTime = dateTime;
        this.dateTimeCalendar = CalendarUtil.strToCalendar(dateTime);
        this.sentence = sentence;
    }

    /**
     * 
     * DiaryVO Constructor(loginID, dateTime, sentence, checkListVO).
     * 
     * @param loginID     {@code String}
     * @param dateTime    {@code String}
     * @param sentence    {@code String}
     * @param checkListVO {@code CheckListVO}
     */
    public DiaryVO(String loginID, String dateTime, String sentence, CheckListVO checkListVO) {
        this.loginID = loginID;
        this.dateTime = dateTime;
        this.dateTimeCalendar = CalendarUtil.strToCalendar(dateTime);
        this.sentence = sentence;
        this.checkListVO = checkListVO;
    }

    /**
     * get user loginID.
     *
     * @return String
     */
    public String getLoginID() {
        return loginID;
    }

    /**
     * set user loginID.
     *
     * @param loginID
     */
    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    /**
     * get Diary dateTime.
     *
     * @return String
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * set Diary dateTime.
     *
     * @param dateTime
     */
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
        this.dateTimeCalendar = CalendarUtil.strToCalendar(dateTime);
    }

    /**
     * @param dateTimeCalendar
     */
    /**
     * public void setDateTimeCalendar(String dateTime) {
     * this.dateTime = dateTime;
     * }
     */

    /**
     * @return Calendar
     */
    public Calendar getDateTimeCalendar() {
        return dateTimeCalendar;
    }

    /**
     * get Diary sentence.
     *
     * @return String
     */
    public String getSentence() {
        return sentence;
    }

    /**
     * set Diary sentence.
     *
     * @param sentence
     */
    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public void setCheckListVO(CheckListVO checkListVO) {
        this.checkListVO = checkListVO;
    }

    public CheckListVO getCheckListVO() {
        return this.checkListVO;
    }

    /**
     * {@inheritDoc}
     * 
     * @return String
     *         DiaryVO(loginID/dateTime/sentence)
     */
    @Override
    public String toString() {
        final String FORMAT = "DiaryVO(loginID=%s/dateTime=%s/sentence=%s)";
        return String.format(FORMAT, loginID, dateTime, sentence);
    }

    /**
     * Comparator Construction Method
     */
    private static final Comparator<DiaryVO> COMPARATOR = comparing((DiaryVO dvo) -> dvo.dateTimeCalendar)
            .thenComparing(dvo -> dvo.loginID);

    /**
     * {@inheritDoc}
     * 
     * @param dvo {@code DiaryVO}
     * @return result
     *         1, 0, -1
     */
    @Override
    public int compareTo(DiaryVO dvo) {
        return Integer.signum(COMPARATOR.compare(this, dvo));
        // int result = dateTimeCalendar.compareTo(dvo.dateTimeCalendar); //
        // if (result == 0) {
        // result = loginID.compareTo(dvo.loginID);
        // }
        // return Integer.signum(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof DiaryVO)) {
            return false;
        }
        DiaryVO dvo = (DiaryVO) o;
        return dvo.loginID.equals(loginID) && dvo.dateTime.equals(dateTime) && dvo.sentence.equals(sentence);
    }

    /**
     * {@inheritDoc}
     * return Objects.hash(loginID, dateTime, sentence); fix bad performance one.
     */
    @Override
    public int hashCode() {
        int result = loginID.hashCode();
        result = 31 * result + dateTime.hashCode();
        result = 31 * result + sentence.hashCode();
        return result;

    }

    /**
     * {@inheritDoc}
     * Clone not supported
     */
    @Override
    protected final Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

}
