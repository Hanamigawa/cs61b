/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {
    // off by 4 has most counts: 115
    public static void main(String[] args) {
//        offByNPalins(0);

//        for (int i = 0; i<30; i++){         // this section for finding which N has most Palins (ans: 4, with 115 palins)
//            int countPalins = countPalinForN(i);
//            System.out.println("Off by " + i + " has counts of " + countPalins);
//            if (countPalins <= 4) offByNPalins(i);
//        }

        for (int i = 0; i<30; i++){         // this section for finding which N has which longest word
            int lenOfWord = longestWord(i);
            System.out.println("Off by " + i + " has length of " + lenOfWord);
        }
        //ans: 4 vingarer; 5 interjoin; 11 gladiolar
    }

    private static void offByNPalins(int n){    // print all off-by-N palins
        int minLength = 4;
        In in = new In("../library-sp18/data/words.txt");
        Palindrome palindrome = new Palindrome();

        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && palindrome.isPalindrome(word, new OffByN(n))) {
                System.out.println(word);
            }
        }
    }

    private static int countPalinForN(int n){
        int minLength = 4;
        In in = new In("../library-sp18/data/words.txt");
        Palindrome palindrome = new Palindrome();
        int count = 0;

        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && palindrome.isPalindrome(word, new OffByN(n))) {
                count += 1;
            }
        }

        return count;
    }

    private static int longestWord(int n){
        int minLength = 4;
        In in = new In("../library-sp18/data/words.txt");
        Palindrome palindrome = new Palindrome();
        int wordLen = 0;
        String longest = "";
        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && palindrome.isPalindrome(word, new OffByN(n))) {
                if (word.length() >= wordLen){
                    wordLen = word.length();
                    longest = word;
                }
            }
        }
        System.out.println(longest);
        return wordLen;
    }
}
