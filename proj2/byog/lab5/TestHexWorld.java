package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import org.junit.Test;
import byog.lab5.HexWorld.*;

import java.util.Random;

import static byog.lab5.HexWorld.addHexagon;

public class TestHexWorld {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);

    private static TERenderer ter = new TERenderer();;
    private static TETile[][] world = new TETile[WIDTH][HEIGHT];

    @Test
    public static void testAddHexagon(int side) {
        Position p = new Position(5, 0);
        addHexagon(world, p, side, Tileset.FLOWER);
    }

    public static void newWorld(){
        ter.initialize(WIDTH, HEIGHT);
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    public static void main(String[] args){
        newWorld();
        testAddHexagon(3);
        ter.renderFrame(world);
    }
}
