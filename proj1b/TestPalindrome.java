import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        {
            String[] trues = {"", "a", "aba", "abba"};
            String[] falses = {"ab", "horse", "aaba"};
            for (String x : trues) {
                assertTrue(x, palindrome.isPalindrome(x));
            }
            for (String x : falses) {
                assertFalse(x, palindrome.isPalindrome(x));
            }
        }

        {//test offByOne palindrome
            CharacterComparator cc = new OffByOne();
            String[] trues = {"flake", "%&", "FBAE", "", "a"};
            String[] falses = {"abe", "horse", "aaea", "aba", "abba"};
            for (String x : trues) {
                assertTrue("OBO: " + x, palindrome.isPalindrome(x, cc));
            }
            for (String x : falses) {
                assertFalse("OBO: " + x, palindrome.isPalindrome(x, cc));
            }
        }
        {//test offByN palindrome with N=2
            CharacterComparator cc = new OffByN(2);
            String[] trues = {"acac", "", "a"};
            String[] falses = {"abe", "horse", "aaea", "aba", "abba"};
            for (String x : trues) {
                assertTrue("OBO: " + x, palindrome.isPalindrome(x, cc));
            }
            for (String x : falses) {
                assertFalse("OBO: " + x, palindrome.isPalindrome(x, cc));
            }
        }
        {//test offByOne palindrome with N=0
            CharacterComparator cc = new OffByN(0);
            String[] trues = { "aba", "EBBE", "", "a"};
            String[] falses = {"abe", "horse", "aaea"};
            for (String x : trues) {
                assertTrue("OBO: " + x, palindrome.isPalindrome(x, cc));
            }
            for (String x : falses) {
                assertFalse("OBO: " + x, palindrome.isPalindrome(x, cc));
            }
        }
    }
}
