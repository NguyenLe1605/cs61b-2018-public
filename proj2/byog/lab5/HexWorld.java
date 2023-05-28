package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */

public class HexWorld {

    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;
    public static void main (String[] args) {
        TERenderer teRenderer = new TERenderer();
        teRenderer.initialize(WIDTH, HEIGHT);

        TETile[][] tiles = new TETile[WIDTH][HEIGHT];
        initTile(tiles, WIDTH, HEIGHT);
        addHexagon(tiles, Tileset.FLOWER, 3, new Position(5,5));
        teRenderer.renderFrame(tiles);
    }

    public static int xHexOffset(int s, int i) {
        int effectiveI = i;
        if (i >= s) {
            effectiveI = 2 * s - i - 1;
        }
        return -effectiveI;
    }

    public static int hexRowWidth(int s, int i ) {
        int effectiveI = i;
        if (i >= s) {
            effectiveI = 2 * s - effectiveI -  1;
        }
        return s + 2 * effectiveI;
    }

    public static void addRow(TETile[][] tiles, TETile tile, Position p, int width) {
        for (int xi = 0; xi < width; xi += 1) {
            tiles[p.x + xi][p.y] = tile;
        }
    }

    public static void addHexagon(TETile[][] tiles, TETile tile, int sideLength, Position p) {
        for (int yi = 0; yi < 2 * sideLength; yi += 1) {
            int thisRowY = p.y + yi;
            int xRowStart = p.x + xHexOffset(sideLength, yi);

            Position newStart = new Position(xRowStart, thisRowY);
            int rowWidth = hexRowWidth(sideLength, yi);
            addRow(tiles, tile, newStart, rowWidth);
        }
    }

    public static void initTile(TETile[][] tiles, int width, int height) {
        for (int i = 0; i < height; i += 1) {
            for (int j = 0; j < width; j += 1) {
                tiles[i][j] = Tileset.NOTHING;
            }
        }
    }
}


