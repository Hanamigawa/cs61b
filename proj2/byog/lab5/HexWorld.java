package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 27;
    private static final int HEIGHT = 30;

//    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random();

    private static TERenderer ter = new TERenderer();
    private static TETile[][] world = new TETile[WIDTH][HEIGHT];

    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args){
        List<Position> tesList = new ArrayList<>();
        {
            tesList.add(new Position(12, 0));
            tesList.add(new Position(12, 24));
            for (int i = 1; i < 8; i++) {
                if (i % 2 == 1) {
                    tesList.add(new Position(7, i * 3));
                    tesList.add(new Position(17, i * 3));
                } else {
                    tesList.add(new Position(2, i * 3));
                    tesList.add(new Position(12, i * 3));
                    tesList.add(new Position(22, i * 3));
                }
            }
        }  // fill in locations for hexes on tesList
        newWorld();
        drawTessel(tesList);
        ter.renderFrame(world);
    }

    private static void newWorld(){
        ter.initialize(WIDTH, HEIGHT);
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        for (int h = 0; h < s; h++) {
            for (int i = -h; i < s + h; i++) {
                world[p.x + i][p.y + h] = t;
            }
        }
        for (int h = 0; h < s; h++) {
            int newH = 2 * s - h - 1;
            for (int i = -h; i < s + h; i++) {
                world[p.x + i][p.y + newH] = t;
            }
        }
    }

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(10);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.GRASS;
            case 3: return Tileset.FLOOR;
            case 4: return Tileset.MOUNTAIN;
            case 5: return Tileset.TREE;
            case 6: return Tileset.SAND;
            case 7: return Tileset.WATER;
            case 8: return Tileset.LOCKED_DOOR;
            case 9: return Tileset.UNLOCKED_DOOR;
            default: return Tileset.NOTHING;
        }
    }

    private static void addBigHex(int side, Position p) {
        TETile t = randomTile();
        addHexagon(world, p, side, t);
    }

    private static void drawTessel(List<Position> tesselData) {
        for (Position pos : tesselData) {
            addBigHex(3, pos);
        }
    }

//    private static Position[] from2Dto1D(Position[][] mat){
//        int count = 0;
//        for (int i = 0; i < mat.length; i++){
//            count += mat[i].length;
//        }
//        Position[] line = new Position[count];
//        int idx = 0;
//        for (int i = 0; i < mat.length; i++) {
//            for (int j = 0; j < mat[i].length; j++){
//                line[idx] = mat[i][j];
//            }
//        }
//        return line;
//    }
}