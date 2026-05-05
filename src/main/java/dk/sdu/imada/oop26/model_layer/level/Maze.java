package dk.sdu.imada.oop26.model_layer.level;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Maze {

    public static final int TILE_SIZE = 24;  // pixels per tile

    private final TileType[][] tiles;  // [row][col]
    private final int rows;
    private final int cols;

    public Maze(TileType[][] tiles) {
        this.tiles = tiles;
        this.rows = tiles.length;
        this.cols = tiles[0].length;
    }

    public static Maze load(String resourcePath) {
        try (InputStream is = Maze.class.getResourceAsStream(resourcePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            int rows = lines.size();
            int cols = lines.stream().mapToInt(String::length).max().orElse(0);

            TileType[][] tiles = new TileType[rows][cols];
            for (int r = 0; r < rows; r++) {
                String row = lines.get(r);
                for (int c = 0; c < cols; c++) {
                    char ch = c < row.length() ? row.charAt(c) : ' ';
                    tiles[r][c] = TileType.fromChar(ch);
                }
            }

            return new Maze(tiles);
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException("Kunne ikke læse bane: " + resourcePath, e);
        }
    }

    public TileType get(int col, int row) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return TileType.WALL;  // out-of-bounds = solid
        }
        return tiles[row][col];
    }

    public void set(int col, int row, TileType type) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            tiles[row][col] = type;
        }
    }

    public int getCols() { return cols; }
    public int getRows() { return rows; }

    public int getPixelWidth() { return cols * TILE_SIZE; }
    public int getPixelHeight() { return rows * TILE_SIZE; }
}
