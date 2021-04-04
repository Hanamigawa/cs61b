package byog.lab6;
import byog.lab6.MemoryGame.*;
import edu.princeton.cs.introcs.StdDraw;
import org.junit.Test;

import java.awt.*;

public class TestMemoryGame {

    @Test
    public void testGenerateRandomString() {
        MemoryGame game = new MemoryGame(50, 50, 3);
        String str = game.generateRandomString(4);
        System.out.println(str);
        game.drawFrame(str);
        StdDraw.clear();
        game.flashSequence(str);
        StdDraw.pause(1000);
    }

    @Test
    public void testStdDraw() {
//        StdDraw.setCanvasSize(50 * 16, 50 * 16);
//        Font font = new Font("Monaco", Font.BOLD, 30);
//        StdDraw.setFont(font);
//        StdDraw.setXscale(0, 50);
//        StdDraw.setYscale(0, 50);
//        StdDraw.clear(Color.BLACK);
//        StdDraw.enableDoubleBuffering();
//        StdDraw.show();
        MemoryGame game = new MemoryGame(50, 50, 3);
        game.drawFrame("Start!");
        StdDraw.setPenColor(Color.ORANGE);
        StdDraw.pause(2000);
//        while (StdDraw.hasNextKeyTyped()) {
//            StdDraw.clear(Color.black);
//            char c = StdDraw.nextKeyTyped();
//            System.out.println(c);
//            game.drawFrame("" + c);
//            StdDraw.pause(2000);
//        }
        String str2 = game.solicitNCharsInput(10);
        System.out.println(str2);
    }

    @Test
    public void testStartGame(){
        MemoryGame game = new MemoryGame(40, 40, 1);
        game.startGame();
    }
}
