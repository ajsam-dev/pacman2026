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

  public PacmanView() {
    root_group = new Group();
    canvas = new Canvas(400, 300);
    root_group.getChildren().add(canvas);
    pacmanRenderer = new PacmanRenderer(this);
    executeRender();
  }

  public Group getPacmanScreenUi() {
    return root_group;
  }

  public void executeRender(){
    pacmanRenderer.drawRedSquare();
  }

  public Canvas getCanvas() {
    return canvas;
  }
}
