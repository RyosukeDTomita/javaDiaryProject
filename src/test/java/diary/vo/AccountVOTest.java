package diary.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import diary.annotation.Fast;

/**
 * AccountVO Test.
 * 
 * @author iceman
 * @version 1.0
 */
public class AccountVOTest {

    /**
     * Instantiating test class.
     */
    public class InstantiateTest {
        /**
         * default constructor instantiate test.
         */
        @Fast
        @Test
        public void defaultConstructorTest() {
            AccountVO avo = new AccountVO();
            assertNull(avo.getLoginID());
            assertNull(avo.getPassword());
        }

        /**
         * Constructor with parameter arguments(id, password) test.
         */
        @Fast
        @Test
        public void argumentsConstructorTest() {
            AccountVO avo = new AccountVO("iceman", "p@ssw0rd");
            assertEquals("iceman", avo.getLoginID());
            assertEquals("p@ssw0rd", avo.getPassword());
        }
    }

    static AccountVO avo;
    static AccountVO avoClone;
    static AccountVO avoLoginIDChanged;
    static AccountVO avoPasswordChanged;

    /**
     * Create 4 instances for test method.
     */
    @BeforeAll
    public static void setUpClass() {
        avo = new AccountVO("iceman", "p@ssw0rd");
        avoClone = new AccountVO("iceman", "p@ssw0rd");
        avoLoginIDChanged = new AccountVO("superman", "p@ssw0rd");

        avoPasswordChanged = new AccountVO("iceman", "ap@ssw0rd");
    }

    /**
     * toString() test.
     */
    @Fast
    @Test
    public void toStringTest() {
        assertEquals("AccountVO(loginID=iceman/password=p@ssw0rd)", avo.toString());
    }

    /**
     * compareTo() test.
     */
    @Fast
    @Test
    public void compareToTest() {
        assertEquals(0, avo.compareTo(avoClone));
        assertEquals(-1, avo.compareTo(avoLoginIDChanged));
        assertEquals(1, avo.compareTo(avoPasswordChanged));
    }

    /**
     * equals() test.
     */
    @Fast
    @Test
    public void equalsTest() {
        assertTrue(avo.equals(avoClone));
        assertFalse(avo.equals(avoLoginIDChanged));
        assertFalse(avo.equals(avoPasswordChanged));
    }

    /**
     * hashCode test.
     */
    @Fast
    @Test
    public void hashCodeTest() {
        assertEquals(avo.hashCode(), avoClone.hashCode());
        assertNotEquals(avo.hashCode(), avoLoginIDChanged.hashCode());
        assertNotEquals(avo.hashCode(), avoPasswordChanged.hashCode());

    }

    /**
     * clone() is not implemented.
     * Check CloneNotSupportedException throw when clone() is called.
     */
    @Fast
    @Test
    public void cloneTest() {
        assertThrows(CloneNotSupportedException.class, () -> avo.clone());
    }

}
