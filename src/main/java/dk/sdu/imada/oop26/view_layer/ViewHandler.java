package dk.sdu.imada.oop26.view_layer;

import java.net.URL;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ViewHandler {
  private Stage stage;
  private StartpageView startpageView;
  private PacmanView pacmanView;

  public ViewHandler(Stage stage) {
    this.stage = stage;
    this.startpageView = new StartpageView(this);
    this.pacmanView = new PacmanView();
  }

  public void openStartScreen() {
    BorderPane region = startpageView.getUi();
    Scene scene = new Scene(region, 800, 500);
    stage.setTitle("StartPage");
    stage.setScene(scene);
    stage.show();
  }

  public void openPacmanScreen(){
    Pane pane = pacmanView.getPacmanScreenUi();

    // ,Color.BLACK
    Scene scene = new Scene(pane, 800, 500);
    stage.setTitle("Pac-man Game");
    stage.setScene(scene);
    stage.show();
  }

  public void getResourcePath() {
    String resourcePath = "/images/pacman_thumbnail.png";
    URL url = getClass().getResource(resourcePath);
    System.out.println(getClass().getResource("images/pacman_thumbnail.png"));
    System.out.println(getClass().getResource("images/pacman_thumbnail.png"));
  }

}
