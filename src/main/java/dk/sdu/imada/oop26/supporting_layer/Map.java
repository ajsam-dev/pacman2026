package dk.sdu.imada.oop26.supporting_layer;

public class Map {
  private final int rows;
  private final int colums;
  private final int tsize;
  private final int[] tiles;

  public Map() {
    rows = 8;
    colums = 8;
    tsize = rows * colums;
    tiles = new int[tsize];
  }

  public int getTile(int row, int col) {
    return tiles[row * colums + col];
  }

  public int getAmountOfRows() {
    return rows;
  }

  public int getAmountOfColumns() {
    return colums;
  }

  public int getMapSize() {
    return tsize;
  }
}
