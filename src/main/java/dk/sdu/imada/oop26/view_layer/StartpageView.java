package dk.sdu.imada.oop26.view_layer;

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

public class StartpageView {
  private ViewHandler handler;
  private Label welcome_label = new Label("Welcome To Pac-Man");
  private Button enter_button = new Button("Enter Game");

  public StartpageView(ViewHandler handler) {
    this.handler = handler;
  }

  public BorderPane getUi() {
    BorderPane root = new BorderPane();
    root.setPadding(new Insets(20, 20, 20, 20));

    Background background = getBackgroundObject();

    root.setBackground(background);

    // Label in center
    BorderPane.setAlignment(welcome_label, Pos.CENTER);
    welcome_label.setStyle("-fx-font-size: 40; -fx-text-fill: white;");
    root.setTop(welcome_label);

    // Button down center and action
    BorderPane.setAlignment(enter_button, Pos.BOTTOM_CENTER);
    root.setBottom(enter_button);

    return root;
  }

  private Background getBackgroundObject() {

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

    return new Background(backgroundImage);
  }

  public Button getEnterButton() {
    return enter_button;
  }
}
