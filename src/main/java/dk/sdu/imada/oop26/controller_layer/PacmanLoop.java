package dk.sdu.imada.oop26.controller_layer;

import dk.sdu.imada.oop26.view_layer.PacmanView;
import javafx.animation.AnimationTimer;

public class PacmanLoop extends AnimationTimer {

    private final PacmanView view;
    private final PacmanController controller;

    private long lastTime = 0;

    // Fixed timestep — gør fysik deterministisk uafhængigt af framerate
    private static final double FIXED_DT = 1.0 / 60.0;
    private double accumulator = 0;

    public PacmanLoop(PacmanView view, PacmanController controller) {
        this.view = view;
        this.controller = controller;
    }

    @Override
    public void handle(long now) {
        // Første frame: bare gem tiden og spring update over
        if (lastTime == 0) {
            lastTime = now;
            view.render();
            return;
        }

        // Beregn rigtige delta time (nano → sekunder)
        double frameTime = (now - lastTime) / 1_000_000_000.0;
        lastTime = now;

        // Cap mod "spiral of death" hvis pc'en pludselig hænger
        if (frameTime > 0.25) frameTime = 0.25;

        // Fixed timestep accumulator — kør update flere gange hvis vi sakker bagud
        accumulator += frameTime;
        while (accumulator >= FIXED_DT) {
            // Når PacmanController.update(dt) er klar, fjern kommentaren her:
            // controller.update(FIXED_DT);
            accumulator -= FIXED_DT;
        }

        // Render én gang per frame uanset hvor mange update-tikker der kørte
        view.render();
    }
}
