package dk.sdu.imada.oop26.view_layer;

import javafx.scene.Group;
import javafx.scene.layout.Pane;

public class PacmanView {

  public Pane getPacmanScreenUi(){
    Pane pane = new Pane();
    pane.setStyle("-fx-background-color: black;");
    return pane;
  }

public Group getPacmanScreenUiGroup(){
    Group a_group = new Group();
    a_group.setStyle("-fx-background-color: black;");
    return a_group;
  }
}
