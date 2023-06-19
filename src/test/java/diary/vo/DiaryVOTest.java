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
import diary.exception.DiaryException;

/**
 * {@link DiaryVO DiaryVO} Test.
 * 
 * @author iceman
 * @version 1.0
 */
public class DiaryVOTest {

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
            DiaryVO dvo = new DiaryVO();
            assertNull(dvo.getLoginID());
            assertNull(dvo.getDateTime());
            assertNull(dvo.getSentence());
            assertThrows(DiaryException.class, () -> dvo.setDateTime("1111/22/33"));
        }

        /**
         * Constructor with parameter arguments(loginID, dateTime, sentence) test.
         */
        @Fast
        @Test
        public void argumentsConstructorTest() {
            DiaryVO dvo = new DiaryVO("laughing man", "1999-12-31", "僕は耳と目を閉じ，口を噤んだ人間になろうと考えた。");
            assertEquals(dvo.getLoginID(), "laughing man");
            assertEquals(dvo.getDateTime(), "1999-12-31");
            assertEquals(dvo.getSentence(), "僕は耳と目を閉じ，口を噤んだ人間になろうと考えた。");
        }
    }

    static DiaryVO dvo;
    static DiaryVO dvoClone;
    static DiaryVO dvoTomorrow;
    static DiaryVO dvoLoginIDChanged;

    /**
     * Create 4 instances for test method.
     */
    @BeforeAll
    public static void setUpClass() {
        dvo = new DiaryVO("laughing man", "1999-12-31", "僕は耳と目を閉じ，口を噤んだ人間になろうと考えた。");
        dvoClone = new DiaryVO("laughing man", "1999-12-31", "僕は耳と目を閉じ，口を噤んだ人間になろうと考えた。");
        dvoTomorrow = new DiaryVO("laughing man", "2000-01-01", "僕は耳と目を閉じ，口を噤んだ人間になろうと考えた。");
        dvoLoginIDChanged = new DiaryVO("iceman", "1999-12-31", "僕は耳と目を閉じ，口を噤んだ人間になろうと考えた。");

    }

    /**
     * toString() test.
     */
    @Fast
    @Test
    public void toStringTest() {
        assertEquals("DiaryVO(loginID=laughing man/dateTime=1999-12-31/sentence=僕は耳と目を閉じ，口を噤んだ人間になろうと考えた。)",
                dvo.toString());
    }

    /**
     * compareTo() test.
     */
    @Fast
    @Test
    public void compareToTest() {
        assertEquals(0, dvo.compareTo(dvoClone));
        assertEquals(-1, dvo.compareTo(dvoTomorrow));
        assertEquals(1, dvo.compareTo(dvoLoginIDChanged));
    }

    /**
     * equals() test.
     */
    @Fast
    @Test
    public void equalsTest() {
        assertTrue(dvo.equals(dvoClone));
        assertFalse(dvo.equals(dvoTomorrow));
        assertFalse(dvo.equals(dvoLoginIDChanged));
    }

    /**
     * hashCode test.
     */
    @Fast
    @Test
    public void hashCodeTest() {
        assertEquals(dvo.hashCode(), dvoClone.hashCode());
        assertNotEquals(dvo.hashCode(), dvoTomorrow.hashCode());
        assertNotEquals(dvo.hashCode(), dvoLoginIDChanged.hashCode());

    }

    /**
     * clone() is not implemented.
     * Check CloneNotSupportedException throw when clone() is called.
     */
    @Fast
    @Test
    public void cloneTest() {
        assertThrows(CloneNotSupportedException.class, () -> dvo.clone());
    }
}
