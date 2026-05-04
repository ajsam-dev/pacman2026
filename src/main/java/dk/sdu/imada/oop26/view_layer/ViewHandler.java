package dk.sdu.imada.oop26.view_layer;

import dk.sdu.imada.oop26.controller_layer.PacmanController;
import dk.sdu.imada.oop26.controller_layer.PacmanViewLoop;
import dk.sdu.imada.oop26.controller_layer.StartPageController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ViewHandler {
  private Stage stage;
  private StartpageView startpageView;
  private StartPageController startPageController;
  private PacmanView pacmanView;
  private PacmanController pacmanController;
  private PacmanViewLoop pacmanViewLoop;

  public ViewHandler(Stage stage) {
    this.stage = stage;

    this.startpageView = new StartpageView(this);
    this.startPageController = new StartPageController(this, startpageView);

    this.pacmanView = new PacmanView();
    this.pacmanController = new PacmanController(this, pacmanView);

    this.pacmanViewLoop = new PacmanViewLoop(pacmanView, pacmanController);
  }

  public void openStartScreen() {
    BorderPane region = startpageView.getUi();
    Scene scene = new Scene(region, 800, 500);
    stage.setTitle("Welcome-screen");
    stage.setScene(scene);
    stage.show();
  }

  public void openPacmanScreen(){
    pacmanViewLoop.start();
    Group root_group = pacmanView.getPacmanScreenUi();
    Scene scene = new Scene(root_group, 800, 500, Color.BLACK);

    stage.setTitle("Pac-man Game");
    stage.setScene(scene);
    stage.show();
  }

  // public void getResourcePath() {
  //   String resourcePath = "/images/pacman_thumbnail.png";
  //   URL url = getClass().getResource(resourcePath);
  //   System.out.println(getClass().getResource("images/pacman_thumbnail.png"));
  //   System.out.println(getClass().getResource("images/pacman_thumbnail.png"));
  // }

}
