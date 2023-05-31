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
    public void testCorrectIsPalindrome() {
        assertFalse(palindrome.isPalindrome("cat"));
        assertFalse(palindrome.isPalindrome("dog"));
        assertTrue(palindrome.isPalindrome("404"));
        assertTrue(palindrome.isPalindrome("tat"));
        assertTrue(palindrome.isPalindrome("noon"));
        assertTrue(palindrome.isPalindrome("racecar"));
        assertTrue(palindrome.isPalindrome("aaaaa"));
        assertTrue(palindrome.isPalindrome("abba"));
        assertFalse(palindrome.isPalindrome("baba"));
    }

    @Test
    public void testIsPalindromeWithNullOrShortString() {
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome(" "));
        assertFalse(palindrome.isPalindrome(null));
    }

    @Test
    public void testIsPalinedromeWithSpecialCharacter() {
        assertTrue(palindrome.isPalindrome("?#?"));
        assertTrue(palindrome.isPalindrome(" # "));
        assertFalse(palindrome.isPalindrome(" *"));
        assertFalse(palindrome.isPalindrome("#?#?"));
    }

    @Test
    public void testIsPalindromeComparatorWithNullOrShortString() {
        OffByOne obo = new OffByOne();
        assertTrue(palindrome.isPalindrome("", obo));
        assertTrue(palindrome.isPalindrome("a", obo));
        assertTrue(palindrome.isPalindrome(" ", obo));
        assertFalse(palindrome.isPalindrome(null, obo));
    }

    @Test
    public void testIsPalindromeWithComparator() {
        OffByOne obo = new OffByOne();
        assertTrue(palindrome.isPalindrome("abab", obo));
        assertFalse(palindrome.isPalindrome("abba", obo));
        assertFalse(palindrome.isPalindrome("bale", obo));
        assertTrue(palindrome.isPalindrome("flake", obo));
        assertFalse(palindrome.isPalindrome("blake", obo));
        assertFalse(palindrome.isPalindrome("aaaa", obo));
    }
}
