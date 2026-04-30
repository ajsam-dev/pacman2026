package project.pacman.view_layer;

import java.net.URL;
import java.util.logging.Handler;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

  public URL getUrl() {
    String resourcePath = "/images/pacman_thumbnail.png";
    URL url = getClass().getResource(resourcePath);
    return url;
  }

  public void start(Stage stage) {
    ViewHandler handler = new ViewHandler(stage);
    handler.getResourcePath();;
    handler.openStartScreen();

  }

  public static void main(String[] args) {
    System.out.println("Test at launch() bliver kørt!");
    launch();
  }


}
