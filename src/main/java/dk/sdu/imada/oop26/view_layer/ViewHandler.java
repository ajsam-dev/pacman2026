package dk.sdu.imada.oop26.view_layer;

import dk.sdu.imada.oop26.controller_layer.PacmanController;
import dk.sdu.imada.oop26.controller_layer.PacmanLoop;
import dk.sdu.imada.oop26.controller_layer.StartPageController;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * ViewHandler — orchestrates scene-transitions between start screen and game.
 *
 * Ansvar:
 *  - Opretter alle controllers og views ved init
 *  - Håndterer scene-skift via openStartScreen() / openPacmanScreen()
 *  - Starter og stopper PacmanLoop ved scene-skift
 *
 * Regel: ViewHandler er IKKE en god class der ved alt om alt.
 * Den ved kun "hvilken skærm er aktiv" og hvordan man skifter.
 * Game-state hører i Model (GameWorld), ikke her.
 */
public class ViewHandler {

    private final Stage stage;

    private final StartpageView startpageView;
    private final StartPageController startPageController;

    private final PacmanView pacmanView;
    private final PacmanController pacmanController;
    private final PacmanLoop pacmanLoop;

    public ViewHandler(Stage stage) {
        this.stage = stage;

        // Start screen
        this.startpageView = new StartpageView(this);
        this.startPageController = new StartPageController(this, startpageView);

        // Pac-Man game screen
        this.pacmanView = new PacmanView();
        this.pacmanController = new PacmanController(this, pacmanView);
        this.pacmanLoop = new PacmanLoop(pacmanView, pacmanController);
    }

    public void openStartScreen() {
        pacmanLoop.stop();

        BorderPane region = startpageView.getUi();
        Scene scene = new Scene(region, 800, 500, Color.BLACK);
        stage.setTitle("Welcome-screen");
        stage.setScene(scene);
        stage.show();
    }

    public void openPacmanScreen() {
        StackPane root = pacmanView.getPacmanScreenUi();

        // Scene-størrelse matcher banen
        double width = pacmanView.getCanvas().getWidth();
        double height = pacmanView.getCanvas().getHeight() + 20; // lidt margen til HUD

        Scene scene = new Scene(root, width, height, Color.BLACK);
        stage.setTitle("Pac-man Game");
        stage.setScene(scene);
        stage.show();

        // Start game loop NÅR scenen er aktiv
        pacmanLoop.start();
    }

    // Getters til controllere der skal navigere
    public Stage getStage() { return stage; }
}
