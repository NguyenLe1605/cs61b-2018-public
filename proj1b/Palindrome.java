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
}
