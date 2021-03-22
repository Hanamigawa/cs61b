public class Palindrome {
    public Deque<Character> wordToDeque(String word){
//        String letter = Character.toString(text.charAt(0))
        Deque<Character> charDeque = new LinkedListDeque<>();
        for(int i = 0; i < word.length(); i++){
            charDeque.addLast(word.charAt(i));
        }
        return charDeque;
    }

    public boolean isPalindrome(String word){
        Deque d = wordToDeque(word);
//        while (d.size() > 1) {
//            if ( d.removeFirst() == d.removeLast()){
//                continue;
//            }
//            return false;
//        }
//        return true;
        return isPalindrome(d);
    }

    private boolean isPalindrome(Deque<Character> d){
        if (d.size() <= 1) return true;
        return (d.removeFirst() == d.removeLast() && isPalindrome(d));
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> d = wordToDeque(word);
        while (d.size() > 1) {
            if ( cc.equalChars(d.removeFirst(),d.removeLast())){
                continue;
            }
            return false;
        }
        return true;
    }
}
