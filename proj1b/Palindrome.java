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

    private boolean isPalindromeHelper(Deque<Character> deque) {
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

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null) {
            return false;
        }

        int len = word.length();
        for (int i = 0; i < len / 2; i++) {
            if (!cc.equalChars(word.charAt(i), word.charAt(len - 1 - i))) {
                return false;
            }
        }

        return true;
    }
}
