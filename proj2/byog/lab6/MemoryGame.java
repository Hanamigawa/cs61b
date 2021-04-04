package byog.lab6;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        int seed = Integer.parseInt(args[0]);
        MemoryGame game = new MemoryGame(40, 40, seed);
        game.startGame();
    }

    public MemoryGame(int width, int height, int seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        //TODO: Initialize random number generator
        rand = new Random(seed);
    }

    public String generateRandomString(int n) {
        //TODO: Generate random string of letters of length n
        char[] chars = new char[n];
        for (int i = 0; i < n; i++) {
            chars[i] = CHARACTERS[rand.nextInt(26)];
        }
        return new String(chars);
    }

    public void drawFrame(String s) {
        //TODO: Take the string and display it in the center of the screen
        StdDraw.setFont(new Font("Arial", Font.BOLD, 32));
        StdDraw.setPenColor(Color.ORANGE);
        StdDraw.text(width / 2, height / 2, s);
        StdDraw.show();
        //TODO: If game is not over, display relevant game information at the top of the screen

    }

    public void flashSequence(String letters) {
        //TODO: Display each character in letters, making sure to blank the screen between letters
        for (char c : letters.toCharArray()) {
            drawFrame(c+"");
            StdDraw.pause(1000);
            StdDraw.clear(Color.black);
            StdDraw.show();
            StdDraw.pause(500);
        }
    }

    public String solicitNCharsInput(int n) {
        //TODO: Read n letters of player input
        char[] chars = new char[n];
        int idx = 0;
        while (idx < n) {
            if (StdDraw.hasNextKeyTyped()){
                StdDraw.clear(Color.black);
                char c = StdDraw.nextKeyTyped();
                chars[idx++] = c;
                String str = new String(chars);
                drawFrame(str);
            }
        }
        return new String(chars);
    }

    private void pause(float sec) {
        StdDraw.pause((int) sec*1000);
        StdDraw.clear(Color.black);
    }

    public void startGame() {
        //TODO: Set any relevant variables before the game starts
        int round = 0;
        //TODO: Establish Game loop
        while (true) {
            round += 1;
            drawFrame("Round " + round);
            pause(1);
            String theStr = generateRandomString(round);
            flashSequence(theStr);
            drawFrame("Start typing! ");
            String ansStr = solicitNCharsInput(round);
            pause(1);
            if(ansStr.contentEquals(theStr)) {
                drawFrame("Correct!");
                pause(1);
            } else {
                drawFrame("Wrong! Expected: " + theStr + ", but got " + ansStr);
                pause(3);
                break;
            }
        }

    }

}
