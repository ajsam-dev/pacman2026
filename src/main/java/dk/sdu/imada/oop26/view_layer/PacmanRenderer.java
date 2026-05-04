package dk.sdu.imada.oop26.view_layer;

import javafx.scene.canvas.GraphicsContext;

public class PacmanRenderer {
  private GraphicsContext grafCon;

  public PacmanRenderer(PacmanView pacmanView) {
    grafCon = pacmanView.getCanvas().getGraphicsContext2D(); 
  }

}
