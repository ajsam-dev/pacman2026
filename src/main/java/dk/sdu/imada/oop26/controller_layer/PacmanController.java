package dk.sdu.imada.oop26.controller_layer;

import dk.sdu.imada.oop26.view_layer.PacmanView;
import dk.sdu.imada.oop26.view_layer.ViewHandler;

/**
 * PacmanController — STUB / PLACEHOLDER.
 *
 * Det her er din opgave at implementere. Den skal:
 *  - Holde reference til Model (GameWorld eller hvad du kalder den)
 *  - Holde reference til View (read-only)
 *  - Holde reference til InputState
 *  - Have update(dt) der kalder world.update(dt, input)
 *  - Evt. håndtere pause/resume, game-over-logik
 *
 * ViewHandler skal stadig kunne oprette den med (ViewHandler, PacmanView)-signaturen
 * som du har i din eksisterende kode, så jeg lader constructoren være sådan.
 */
public class PacmanController {

    private final ViewHandler viewHandler;
    private final PacmanView view;

    public PacmanController(ViewHandler viewHandler, PacmanView view) {
        this.viewHandler = viewHandler;
        this.view = view;
    }

    /**
     * Kaldes hver fixed timestep fra PacmanLoop.
     * Implementer din spil-logik her.
     */
    public void update(double dt) {
        // TODO: Implementer
        // world.update(dt, inputState);
    }
}
