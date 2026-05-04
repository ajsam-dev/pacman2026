package dk.sdu.imada.oop26;

import dk.sdu.imada.oop26.view_layer.ViewHandler;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

  public void start(Stage stage) {
    ViewHandler handler = new ViewHandler(stage);

    handler.openStartScreen();

  }

  public static void main(String[] args) {
    System.out.println("Test at launch() bliver kørt!");
    launch();
  }

}
