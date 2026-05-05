package dk.sdu.imada.oop26.view_layer;

import dk.sdu.imada.oop26.model_layer.level.Maze;
import dk.sdu.imada.oop26.model_layer.level.TileType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

/**
 * PacmanRenderer — stateless rendering-logik.
 * Får en GraphicsContext og noget at tegne, og tegner det.
 * Ejer ingenting selv. Holder ingen reference til Model.
 *
 * Alle metoder tager GraphicsContext + data ind som parametre.
 * Det gør den nem at teste og genbruge.
 */
public class PacmanRenderer {

  // Klassiske Pac-Man-farver
  private static final Color WALL_COLOR = Color.web("#2121DE"); // signaturblå
  private static final Color WALL_INNER = Color.web("#0000A0");
  private static final Color DOT_COLOR = Color.web("#FFB897");
  private static final Color BACKGROUND = Color.BLACK;
  private static final Color PACMAN_COLOR = Color.web("#FFFF00");
  private static final Color GATE_COLOR = Color.web("#FFB8AE");

  // Ghost-farver (klassiske)
  private static final Color BLINKY = Color.web("#FF0000"); // rød
  private static final Color PINKY = Color.web("#FFB8FF"); // pink
  private static final Color INKY = Color.web("#00FFFF"); // cyan
  private static final Color CLYDE = Color.web("#FFB852"); // orange

  private static final Color HUD_TEXT = Color.WHITE;

  /**
   * Clear canvas. Kald først hver frame.
   */
  public void clear(GraphicsContext gc, double width, double height) {
    gc.setFill(BACKGROUND);
    gc.fillRect(0, 0, width, height);
  }

  /**
   * Tegn hele banen — vægge, dots, power pellets, gates.
   */
  public void renderMaze(GraphicsContext gc, Maze maze) {
    int size = Maze.TILE_SIZE;

    for (int r = 0; r < maze.getRows(); r++) {
      for (int c = 0; c < maze.getCols(); c++) {
        TileType type = maze.get(c, r);
        double x = c * size;
        double y = r * size;

        switch (type) {
          case WALL:
            drawWall(gc, x, y, size);
          case DOT:
            drawDot(gc, x, y, size);
          case POWER_PELLET:
            drawPowerPellet(gc, x, y, size);
          case GATE:
            drawGate(gc, x, y, size);
          case GHOST_SPAWN:
            break;
          case PLAYER_SPAWN:
            break;
          case EMPTY:
            break;
        }
      }
    }
  }

  private void drawWall(GraphicsContext gc, double x, double y, int size) {
    gc.setFill(WALL_COLOR);
    gc.fillRect(x, y, size, size);
    // Indre nuance for at give dybde
    gc.setFill(WALL_INNER);
    gc.fillRect(x + 2, y + 2, size - 4, size - 4);
    gc.setFill(WALL_COLOR);
    gc.fillRect(x + 4, y + 4, size - 8, size - 8);
  }

  private void drawDot(GraphicsContext gc, double x, double y, int size) {
    gc.setFill(DOT_COLOR);
    double d = size / 5.0;
    gc.fillOval(x + (size - d) / 2, y + (size - d) / 2, d, d);
  }

  private void drawPowerPellet(GraphicsContext gc, double x, double y, int size) {
    gc.setFill(DOT_COLOR);
    double d = size * 0.7;
    gc.fillOval(x + (size - d) / 2, y + (size - d) / 2, d, d);
  }

  private void drawGate(GraphicsContext gc, double x, double y, int size) {
    gc.setFill(GATE_COLOR);
    gc.fillRect(x, y + size / 2.0 - 2, size, 4);
  }

  /**
   * Tegn Pac-Man som en gul cirkel med "mund".
   * mouthAngle = 0 betyder lukket mund, 45 betyder åben.
   * facing: 0=højre, 90=ned, 180=venstre, 270=op (grader).
   */
  public void renderPacman(GraphicsContext gc, double x, double y, double facing, double mouthAngle) {
    int size = Maze.TILE_SIZE;
    gc.setFill(PACMAN_COLOR);

    // fillArc tegner et cirkelsegment.
    // Vi tegner hele cirklen MINUS et kileformet udsnit til munden.
    double startAngle = facing + mouthAngle;
    double extent = 360 - 2 * mouthAngle;

    // JavaFX's fillArc bruger standard matematiske vinkler (0 = højre, mod uret).
    // Pac-Man-koordinater: 0=højre, 90=ned (med uret), så vi negerer.
    gc.fillArc(x, y, size, size, -startAngle, -extent, javafx.scene.shape.ArcType.ROUND);
  }

  /**
   * Tegn en ghost med specifik farve.
   * ghostIndex: 0=Blinky, 1=Pinky, 2=Inky, 3=Clyde
   */
  public void renderGhost(GraphicsContext gc, double x, double y, int ghostIndex) {
    // Color color = switch (ghostIndex % 4) {
    // case 0 -> BLINKY;
    // case 1 -> PINKY;
    // case 2 -> INKY;
    // default -> CLYDE;
    // };

    Color color;

    switch (ghostIndex % 4) {
      case 0:
        color = BLINKY;
        break;

      case 1:
        color = PINKY;
        break;

      case 2:
        color = INKY;
        break;

      default:
        color = CLYDE;
        break;
    }

    renderGhostBody(gc, x, y, color);
  }

  private void renderGhostBody(GraphicsContext gc, double x, double y, Color color) {
    int size = Maze.TILE_SIZE;
    gc.setFill(color);

    // Hoved (halvcirkel øverst)
    gc.fillArc(x, y, size, size, 0, 180, javafx.scene.shape.ArcType.ROUND);

    // Krop (rektangel)
    gc.fillRect(x, y + size / 2.0, size, size / 2.0 - 4);

    // Bølgede fødder (tre små trekanter)
    double footW = size / 3.0;
    double footY = y + size - 4;
    for (int i = 0; i < 3; i++) {
      double fx = x + i * footW;
      gc.fillPolygon(
          new double[] { fx, fx + footW / 2, fx + footW },
          new double[] { footY, y + size, footY },
          3);
    }

    // Øjne (hvide cirkler)
    gc.setFill(Color.WHITE);
    double eyeSize = size / 4.0;
    double eyeY = y + size / 4.0;
    gc.fillOval(x + size / 5.0, eyeY, eyeSize, eyeSize);
    gc.fillOval(x + size - size / 5.0 - eyeSize, eyeY, eyeSize, eyeSize);

    // Pupiller (sorte)
    gc.setFill(Color.BLACK);
    double pupilSize = eyeSize / 2.0;
    gc.fillOval(x + size / 5.0 + pupilSize / 2, eyeY + pupilSize / 2, pupilSize, pupilSize);
    gc.fillOval(x + size - size / 5.0 - eyeSize + pupilSize / 2, eyeY + pupilSize / 2, pupilSize, pupilSize);
  }

  /**
   * Tegn HUD: score + lives.
   * Bruger skærmkoordinater (ingen camera-transform).
   */
  public void renderHud(GraphicsContext gc, int score, int lives, double canvasWidth, double canvasHeight) {
    gc.setFill(HUD_TEXT);
    gc.setFont(Font.font("Monospaced", FontWeight.BOLD, 16));
    gc.setTextAlign(TextAlignment.LEFT);

    gc.fillText("SCORE: " + score, 10, 20);
    gc.fillText("LIVES: " + lives, canvasWidth - 100, 20);
  }
}
