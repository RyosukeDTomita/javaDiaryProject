package diary.vo;

import java.io.Serializable;
import java.util.Comparator;
import static java.util.Comparator.comparing;

/**
 * Account is implementation of Serializable, stored user login information.
 * 
 * @author iceman
 * @version 1.0
 *
 */
public class AccountVO implements Serializable, Comparable<AccountVO> {
    private String loginID;
    private String password;
    private static final long serialVersionUID = 1L;

    public AccountVO() {
    }

    /**
     * Constructor
     * 
     * @param loginID  User ID
     * @param password User password
     */
    public AccountVO(String loginID, String password) {
        this.loginID = loginID;
        this.password = password;
    }

    /**
     * @return loginID Login ID
     */
    public String getLoginID() {
        return loginID;
    }

    /**
     * @param loginID login ID
     */
    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    /**
     * @return password login password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password login password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * {@inheritDoc}
     * 
     * @return "AccountVO(loginID/password)";
     */
    @Override
    public String toString() {
        final String FORMAT = "AccountVO(loginID=%s/password=%s)";
        return String.format(FORMAT, loginID, password);
    }

    /**
     * Comparator Construction Method
     */
    private static final Comparator<AccountVO> COMPARATOR = comparing((AccountVO avo) -> avo.loginID)
            .thenComparing(avo -> avo.password);

    /**
     * {@inheritDoc}
     * 
     * WEP
     * 
     * @param avo {@code AccountVO}
     * @return result to use signum, return -1, 0, 1
     */
    @Override
    public int compareTo(AccountVO avo) {
        return Integer.signum(COMPARATOR.compare(this, avo));
        // int result = loginID.compareTo(avo.loginID);
        // if (result == 0) {
        // result = password.compareTo(avo.password);
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
        if (!(o instanceof AccountVO)) {
            return false;
        }
        AccountVO avo = (AccountVO) o;
        return avo.loginID == loginID && avo.password == password;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = loginID.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }

    /**
     * {@inheritDoc}
     * WIP
     */

    @Override
    protected final Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
