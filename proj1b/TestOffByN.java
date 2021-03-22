import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    @Test
    public void testEqualChars(){
        {
            CharacterComparator obn = new OffByN(1);
            assertTrue(obn.equalChars('&', '%'));
            assertTrue(obn.equalChars('b', 'a'));
            assertFalse(obn.equalChars('A', 'C'));
        }
        {
            CharacterComparator obn = new OffByN(0);
            assertTrue(obn.equalChars('&', (char) 38));
            assertTrue(obn.equalChars('a', 'a'));
            assertFalse(obn.equalChars('A', 'E'));
        }
    }
}
