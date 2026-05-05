package dk.sdu.imada.oop26.model_layer.level;

public enum TileType {
  EMPTY,
  WALL,
  DOT,
  POWER_PELLET,
  GHOST_SPAWN,
  PLAYER_SPAWN,
  GATE;

  public boolean isSolid() {
    return this == WALL;
  }

  public boolean isCollectible() {
    return this == DOT || this == POWER_PELLET;
  }

  public static TileType fromChar(char c) {
    switch (c) {
      case '#':
        return WALL;
      case '.':
        return DOT;
      case 'o':
        return POWER_PELLET;
      case 'G':
        return GHOST_SPAWN;
      case 'P':
        return PLAYER_SPAWN;
      case '-':
        return GATE;

      default:
        throw new IllegalArgumentException("Unknown tile: " + c);
    }
  }
}
