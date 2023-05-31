public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        if (word == null) {
            return null;
        }

        Deque<Character> deque = new ArrayDeque<>();
        int len = word.length();
        for (int i = 0; i < len; i++) {
            deque.addLast(word.charAt(i));
        }

        return deque;
    }

    public boolean isPalindromeHelper(Deque<Character> deque) {
        if (deque.size() <= 1) {
            return true;
        }

        if (deque.removeFirst() != deque.removeLast()) {
            return false;
        }

        return isPalindromeHelper(deque);
    }

    public boolean isPalindrome(String word) {
        if (word == null) {
            return false;
        }

        Deque<Character> deque = wordToDeque(word);
        return isPalindromeHelper(deque);
    }
}
