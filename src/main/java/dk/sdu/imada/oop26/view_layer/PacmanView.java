package dk.sdu.imada.oop26.view_layer;

import dk.sdu.imada.oop26.model_layer.level.Maze;
import dk.sdu.imada.oop26.model_layer.level.TileType;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * PacmanView — ejer Canvas + GraphicsContext og koordinerer rendering.
 *
 * Ansvar:
 *  - Oprette og holde Canvas-noden (JavaFX-ressource)
 *  - Eksponere root-noden til ViewHandler så den kan smækkes i en Scene
 *  - Holde reference til Maze (banen der skal tegnes)
 *  - Holde reference til Renderer (det værktøj der tegner)
 *  - I render(): clear canvas, tegn maze, tegn entities, tegn HUD
 *
 * Bemærk: View er KUN write-til-canvas. Den ændrer ikke spillets state.
 * Currently bruger den hardcoded statiske positioner for player og ghosts —
 * når du har Player og Ghost i model-laget, læser den derfra i stedet.
 */
public class PacmanView {

    private final Canvas canvas;
    private final GraphicsContext gc;
    private final StackPane root;
    private final PacmanRenderer renderer;

    private Maze maze;

    // STATISKE positioner — placeholders indtil Model-laget er klar.
    // Når du har Player og Ghosts i model, skal disse erstattes af
    // referencer til de objekter, og render() læser .getX() / .getY() fra dem.
    private double playerX, playerY;
    private double[] ghostX = new double[4];
    private double[] ghostY = new double[4];

    private int score = 0;
    private int lives = 3;

    public PacmanView() {
        // this.maze = Maze.load("/levels/level1.txt");
        this.maze = Maze.load("/other/level1.txt");

        double width = maze.getPixelWidth();
        double height = maze.getPixelHeight();

        this.canvas = new Canvas(width, height);
        this.gc = canvas.getGraphicsContext2D();

        this.root = new StackPane(canvas);
        this.root.setStyle("-fx-background-color: black;");

        this.renderer = new PacmanRenderer();

        initStaticPositions();
    }

    /**
     * Sætter placeholder-positioner for player og ghosts ud fra
     * spawn-tiles i banen. Disse er statiske indtil din Model overtager.
     */
    private void initStaticPositions() {
        int size = Maze.TILE_SIZE;
        // Find PLAYER_SPAWN i mazen
        for (int r = 0; r < maze.getRows(); r++) {
            for (int c = 0; c < maze.getCols(); c++) {
                if (maze.get(c, r) == TileType.PLAYER_SPAWN) {
                    playerX = c * size;
                    playerY = r * size;
                }
            }
        }

        // Find GHOST_SPAWN-tiles og placér 4 ghosts der
        int ghostsPlaced = 0;
        for (int r = 0; r < maze.getRows() && ghostsPlaced < 4; r++) {
            for (int c = 0; c < maze.getCols() && ghostsPlaced < 4; c++) {
                if (maze.get(c, r) == TileType.GHOST_SPAWN) {
                    // Placer ghosts hver anden tile for at sprede dem
                    if ((c + r) % 2 == 0) {
                        ghostX[ghostsPlaced] = c * size;
                        ghostY[ghostsPlaced] = r * size;
                        ghostsPlaced++;
                    }
                }
            }
        }
    }

    /**
     * Hovedrender-metode — kaldes hver frame fra PacmanLoop.
     */
    public void render() {
        // 1. Clear
        renderer.clear(gc, canvas.getWidth(), canvas.getHeight());

        // 2. Tegn banen (vægge, dots, power pellets)
        renderer.renderMaze(gc, maze);

        // 3. Tegn ghosts
        for (int i = 0; i < 4; i++) {
            renderer.renderGhost(gc, ghostX[i], ghostY[i], i);
        }

        // 4. Tegn Pac-Man
        // facing=0 (højre), mouthAngle=30 (ret åben mund)
        renderer.renderPacman(gc, playerX, playerY, 0, 30);

        // 5. HUD ovenpå
        renderer.renderHud(gc, score, lives, canvas.getWidth(), canvas.getHeight());
    }

    /**
     * Returnerer rod-noden til Scene-opbygning i ViewHandler.
     * Dette er broen mellem dit eget rendering-system og JavaFX scene graph.
     */
    public StackPane getPacmanScreenUi() {
        return root;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Maze getMaze() {
        return maze;
    }

    // Setters til når Model-laget tager over.
    // Indtil da kan du eksperimentere ved at sætte positioner manuelt.
    public void setPlayerPosition(double x, double y) {
        this.playerX = x;
        this.playerY = y;
    }

    public void setGhostPosition(int index, double x, double y) {
        if (index >= 0 && index < 4) {
            this.ghostX[index] = x;
            this.ghostY[index] = y;
        }
    }

    public void setScore(int score) { this.score = score; }
    public void setLives(int lives) { this.lives = lives; }
}
