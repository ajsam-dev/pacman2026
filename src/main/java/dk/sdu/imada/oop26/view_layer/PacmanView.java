package dk.sdu.imada.oop26.view_layer;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PacmanView {
  private Group root_group;
  private Canvas canvas;
  private GraphicsContext grafcon;
  private PacmanRenderer pacmanRenderer;

  public PacmanView(){
    pacmanRenderer = new PacmanRenderer(this);
  }

  public Group getPacmanScreenUi(){
    root_group = new Group();
    canvas = new Canvas(400,300);
    grafcon = canvas.getGraphicsContext2D();
    root_group.getChildren().add(canvas);
    grafcon.setFill(Color.RED);
    grafcon.fillRect(0, 0, 100, 100);

    
    return root_group;
  }

  public Canvas getCanvas(){
    return canvas;
  }
}
