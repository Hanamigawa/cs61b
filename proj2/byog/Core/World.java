package byog.Core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.lab5.HexWorld;

public class World {
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    public static final int ROOMLIMIT = 10;
    public static final int HALLWAYLIMIT = 24;

    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);


    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Room {
        Position p1;
        Position p2;

        public Room(Position p1, Position p2) {
            this.p1 = p1;
            this.p2 = p2;
        }
    }

    static abstract class Hallway extends Room{
        int length;
        public Hallway(Position p1, int length) {
            super(p1, null);
            this.length = length;
        }
        public abstract boolean isX();
        public abstract boolean isY();
    }

    static class XHallway extends Hallway {
        public XHallway(Position p1, int length) {
            super(p1, length);
            p2 = new Position(p1.x + length, p1.y + 2);
        }

        @Override
        public boolean isX() {
            return true;
        }

        @Override
        public boolean isY() {
            return false;
        }
    }

    static class YHallway extends Hallway {
        public YHallway(Position p1, int length) {
            super(p1, length);
            p2 = new Position(p1.x + 2, p1.y + length);
        }

        @Override
        public boolean isX() {
            return false;
        }

        @Override
        public boolean isY() {
            return true;
        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        newBlankWorld(world);
        List<Room> roomHallways= new ArrayList<>();
        for (int i = 0; i < 20; i ++) {
            roomHallways.add(randomHallway());
            roomHallways.add(randomHallway());
            roomHallways.add(randomRoom());
        }
        for (Room x : roomHallways) {
            addRoom(world, x);
        }
        for (Room x : roomHallways) {
            if (isIsolated(world, x)) blackOutRoom(world, x);
        }
        ter.renderFrame(world);
    }

    private static void newBlankWorld(TETile[][] world) {
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    private static void addRoom(TETile[][] world, Room room) {  //assume s > 4
        for (int i = room.p1.x; i <= room.p2.x; i++) {
            for (int j = room.p1.y; j <= room.p2.y; j++) {
                if (i == room.p1.x || i == room.p2.x || j == room.p1.y || j == room.p2.y) {
                    if (world[i][j] == Tileset.NOTHING) world[i][j] = Tileset.WALL;
                } else {
                    world[i][j] = Tileset.FLOOR;
                }
            }
        }
    }

    private static Room randomRoom() {
        int x = RANDOM.nextInt(WIDTH - ROOMLIMIT);
        int y = RANDOM.nextInt(HEIGHT - ROOMLIMIT);
        Position p1 = new Position(x, y);
        x += RANDOM.nextInt(ROOMLIMIT - 4) + 4;
        y += RANDOM.nextInt(ROOMLIMIT - 4) + 4;
        Position p2 = new Position(x, y);
        return new Room(p1, p2);
    }

    private static void addHallway(TETile[][] world, Hallway hallway) {
//        Position p1 = hallway.p1;
//        Position p2 = hallway.isX() ?
//                new Position(p1.x + hallway.length, p1.y + 2)
//                : new Position(p1.x + 2, p1.y + hallway.length);
//        addRoom(world, new Room(p1, p2));
        addRoom(world, hallway);
    }

    private static Hallway randomHallway(){
        int length = RANDOM.nextInt(HALLWAYLIMIT - 3) + 3;
        if (RANDOM.nextBoolean()) {
            Position p = new Position(RANDOM.nextInt(WIDTH - length), RANDOM.nextInt(HEIGHT - 3));
            return new XHallway(p, length);
        } else {
            Position p = new Position(RANDOM.nextInt(WIDTH - 3), RANDOM.nextInt(HEIGHT - length));
            return new YHallway(p, length);
        }
    }

    private static boolean isIsolated(TETile[][] world, Room room){
        for (int i = room.p1.x; i <= room.p2.x; i++) {
            if(world[i][room.p1.y] != Tileset.WALL || world[i][room.p2.y] != Tileset.WALL) return false;
        }
        for (int j = room.p1.y; j <= room.p2.y; j++) {
            if(world[room.p1.x][j] != Tileset.WALL || world[room.p2.x][j] != Tileset.WALL) return false;
        }
        return true;
    }

    private static void blackOutRoom(TETile[][] world, Room room) {  //assume s > 4
        for (int i = room.p1.x; i <= room.p2.x; i++) {
            for (int j = room.p1.y; j <= room.p2.y; j++) {
                    world[i][j] = Tileset.NOTHING;
                }
            }
    }
}
