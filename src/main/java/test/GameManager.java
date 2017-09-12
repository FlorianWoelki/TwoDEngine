package test;

import com.florianwoelki.twodengine.AbstractGame;
import com.florianwoelki.twodengine.GameContainer;
import com.florianwoelki.twodengine.gfx.Renderer;

/**
 * Created by Florian Woelki on 21.11.16.
 *
 * This class represents the game manager.
 * And this class handles all the manging part of
 * the game. Such as, updating the current state and
 * rendering it.
 */
public class GameManager extends AbstractGame {

    /**
     * The constructor pushes just the play state to
     * the actual state.
     */
    public GameManager() {
        push(new PlayState());
    }

    /**
     * See {@link AbstractGame#update(GameContainer, float)}
     * This method updates the current state.
     *
     * @param gc GameContainer for managing the state
     * @param dt Float delta for good fps on every computer
     */
    @Override
    public void update(GameContainer gc, float dt) {
        peek().update(gc, dt);
    }

    /**
     * See {@link AbstractGame#render(GameContainer, Renderer)}
     * This method renders the current state.
     *
     * @param gc GameContainer for managing the state
     * @param renderer Renderer for rendering the current state
     */
    @Override
    public void render(GameContainer gc, Renderer renderer) {
        peek().render(gc, renderer);
    }

    public static void main(String[] args) {
        // Sets all initial values for the game
        GameContainer gc = new GameContainer(new GameManager());
        gc.setWidth(320);
        gc.setHeight(240);
        gc.setScale(3);
        gc.setClearScreen(true);
        // Disable lightning
        gc.setDynamicLights(false);
        gc.setEnableLighting(false);
        gc.start();
    }

}
