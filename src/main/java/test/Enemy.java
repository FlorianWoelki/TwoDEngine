package test;

import com.florianwoelki.twodengine.GameContainer;
import com.florianwoelki.twodengine.components.objects.CollideComponent;
import com.florianwoelki.twodengine.components.objects.GameObject;
import com.florianwoelki.twodengine.gfx.Renderer;

/**
 * Created by Florian Woelki on 22.11.16.
 *
 * This class represents the enemy or the enemy paddle for
 * the game.
 * It handles all the enemy logic.
 */
public class Enemy extends GameObject {

    private GameObject target;

    /**
     * The constructor sets all initial values for
     * the enemy.
     * It also adds the collision component physics for
     * the enemy.
     *
     * @param x Int coordinate on screen
     * @param y Int coordinate on screen
     */
    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
        width = 16;
        height = 64;
        // Add collision physics to the enemy
        addComponent(new CollideComponent());
    }

    /**
     * See {@link GameObject#update(GameContainer, float)}
     * This method updates the enemy and it handles the logic
     * where the enemy should move.
     * It also updates the components for the game.
     *
     * @param gc GameContainer for getting values from the game
     * @param dt Float delta for good fps on every computer
     */
    @Override
    public void update(GameContainer gc, float dt) {
        if(target == null) {
            target = gc.getGame().peek().getManager().findObject("ball");
        }

        // Moving upwards
        if(target.getY() + target.getHeight() / 2 > y - 2) {
            y += dt * 125;

            if(y < 0) {
                y = 0;
            }
        }

        // Moving downwards
        if(target.getY() + target.getHeight() / 2 < y + 2) {
            y -= dt * 125;

            if(y + height > gc.getHeight()) {
                y = gc.getHeight() - height;
            }
        }

        updateComponents(gc, dt);
    }

    /**
     * See {@link GameObject#render(GameContainer, Renderer)}
     * This method renders the enemy as a rectangle with the given
     * x, y, width and height values.
     * It also renders the rectangle in the color 0xffff0000.
     *
     * @param gc GameContainer for getting values from the game
     * @param renderer Renderer for rendering the enemy
     */
    @Override
    public void render(GameContainer gc, Renderer renderer) {
        renderer.drawRect((int) x, (int) y, (int) width, (int) height, 0xffff0000);
    }

    /**
     * See {@link GameObject#componentEvent(String, GameObject)}
     * This method does nothing for the enemy class.
     *
     * @param name String of the event
     * @param object GameObject which is affected
     */
    @Override
    public void componentEvent(String name, GameObject object) {
    }

    /**
     * See {@link GameObject#dispose()}
     * This method does nothing for the enemy class.
     */
    @Override
    public void dispose() {
    }

}
