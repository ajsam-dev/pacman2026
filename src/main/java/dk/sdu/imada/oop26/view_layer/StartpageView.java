package project.pacman.view_layer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;

public class StartpageView {
  private ViewHandler handler;

  public StartpageView(ViewHandler handler) {
    this.handler = handler;
  }

  public BorderPane getUi() {
    // initialisere et Borderpane pga. sin faste placeringer!
    BorderPane root = new BorderPane();
    root.setPadding(new Insets(20, 20, 20, 20));

    // Background to image
    String pacman_thumbnail1 = "/images/pacman-thumbnail.png";
    String pacman_thumbnail2 = "/images/pacman-thumbnail2.png";

    Image pacman_thumbnail = new Image(getClass().getResourceAsStream(pacman_thumbnail2));
    try {
      if (pacman_thumbnail.isError()) {
        System.err.println("Billede indlæst med fejl!");
      }
    } catch (Exception e) {
      System.err.println("Kunne ikke indlæse billede: " + e.getMessage());
    }

    BackgroundSize bsize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true);

    BackgroundImage backgroundImage = new BackgroundImage(pacman_thumbnail, BackgroundRepeat.NO_REPEAT,
        BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bsize);

    Background background = new Background(backgroundImage);

    root.setBackground(background);

    // Label in center
    Label welcome_label = new Label("Welcome To Pac-Man");
    BorderPane.setAlignment(welcome_label, Pos.CENTER);
    welcome_label.setStyle("-fx-font-size: 40; -fx-text-fill: white;");
    root.setTop(welcome_label);

    // Button down center and action
    Button enter_button = new Button("Enter Game");
    BorderPane.setAlignment(enter_button, Pos.BOTTOM_CENTER);
    root.setBottom(enter_button);
    enter_button.setOnAction(event -> {
      handler.openPacmanScreen();
    });

    return root;
  }
}
