package test;

import com.florianwoelki.twodengine.GameContainer;
import com.florianwoelki.twodengine.components.objects.CollideComponent;
import com.florianwoelki.twodengine.components.objects.GameObject;
import com.florianwoelki.twodengine.gfx.Renderer;

import java.awt.event.KeyEvent;

/**
 * Created by Florian Woelki on 22.11.16.
 *
 * This class represents the player or the paddle for
 * the game.
 * It handles all the player logic.
 */
public class Player extends GameObject {

    /**
     * The constructor sets all initial values for
     * the player.
     * It also adds the collision component physics for
     * the player.
     *
     * @param x Int coordinate on screen
     * @param y Int coordinate on screen
     */
    public Player(int x, int y) {
        setTag("player"); // Sets the actual name of the class
        this.x = x;
        this.y = y;
        width = 16;
        height = 64;
        // Add collision physics to the player
        addComponent(new CollideComponent());
    }

    /**
     * See {@link GameObject#update(GameContainer, float)}
     * This method updates the player and it handles the logic
     * of the keyboard input for the player.
     * It also updates the components for the game.
     *
     * @param gc GameContainer for getting values from the game
     * @param dt Float delta for good fps on every computer
     */
    @Override
    public void update(GameContainer gc, float dt) {
        // If the key 'up' is pressed => move player
        if(gc.getInput().isKey(KeyEvent.VK_UP)) {
            y -= 125 * dt;

            if(y < 0) {
                y = 0;
            }
        }

        // If the key 'down' is pressed => move player
        if(gc.getInput().isKey(KeyEvent.VK_DOWN)) {
            y += 125 * dt;

            if(y + height > gc.getHeight()) {
                y = gc.getHeight() - height;
            }
        }

        updateComponents(gc, dt);
    }

    /**
     * See {@link GameObject#render(GameContainer, Renderer)}
     * This method renders the player as a rectangle with the given
     * x, y, width and height values.
     * It also renders the rectangle in the color 0xffffffff.
     *
     * @param gc GameContainer for getting values from the game
     * @param renderer Renderer for rendering the player
     */
    @Override
    public void render(GameContainer gc, Renderer renderer) {
        renderer.drawRect((int) x, (int) y, (int) width, (int) height, 0xffffffff);
    }

    /**
     * See {@link GameObject#componentEvent(String, GameObject)}
     * This method does nothing for the player class.
     *
     * @param name String of the event
     * @param object GameObject which is affected
     */
    @Override
    public void componentEvent(String name, GameObject object) {
    }

    /**
     * See {@link GameObject#dispose()}
     * This method does nothing for the player class.
     */
    @Override
    public void dispose() {
    }

}
