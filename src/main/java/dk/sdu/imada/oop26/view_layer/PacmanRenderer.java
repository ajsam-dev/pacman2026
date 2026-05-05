package dk.sdu.imada.oop26.view_layer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PacmanRenderer {
  private GraphicsContext grafCon;

  public PacmanRenderer(PacmanView pacmanView) {
    grafCon = pacmanView.getCanvas().getGraphicsContext2D();
  }

  public void drawRedSquare() {
    grafCon.setFill(Color.RED);
    grafCon.fillRect(50, 50, 200, 200);
  }

}
