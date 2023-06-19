package diary.exception;

/**
 * diary package's Exception.
 * 
 * @author iceman
 * @version 1.0
 */
public class DiaryException extends RuntimeException {
    /**
     * Constructor
     */
    public DiaryException() {
        super();
    }

    /**
     * Constructor
     *
     * @param msg
     */
    public DiaryException(String msg) {
        super(msg);
    }

    /**
     * Constructor
     * 
     * @param e Exception
     */
    public DiaryException(Exception e) {
        super();
    }

    /**
     * Constructor
     * 
     * @param msg
     * @param e   Exception
     */
    public DiaryException(String msg, Exception e) {
        super(msg, e);
    }

}
