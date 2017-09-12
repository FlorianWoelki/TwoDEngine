package test;

import com.florianwoelki.twodengine.GameContainer;
import com.florianwoelki.twodengine.components.objects.CollideComponent;
import com.florianwoelki.twodengine.components.objects.GameObject;
import com.florianwoelki.twodengine.gfx.Renderer;

/**
 * Created by Florian Woelki on 22.11.16.
 *
 * This class represents the ball for the game.
 * It handles all the ball logic.
 */
public class Ball extends GameObject {

    private boolean left = true;
    private float speedY = 0;

    /**
     * The constructor sets all initial values for
     * the ball.
     * It also adds the collision component physics for
     * the ball.
     *
     * @param x Int coordinate on screen
     * @param y Int coordinate on screen
     */
    public Ball(int x, int y) {
        setTag("ball");

        this.x = x;
        this.y = y;
        width = 16;
        height = 16;
        // Add collision physics to the ball
        addComponent(new CollideComponent());
    }

    /**
     * See {@link GameObject#update(GameContainer, float)}
     * This method updates the enemy and it handles the logic
     * where the ball should move.
     * It also updates the components for the game.
     *
     * @param gc GameContainer for getting values from the game
     * @param dt Float delta for good fps on every computer
     */
    @Override
    public void update(GameContainer gc, float dt) {
        if(left) {
            x -= dt * 155;
        } else {
            x += dt * 155;
        }

        y += speedY;

        if(y < 0) {
            y = 0;
            speedY *= -1;
        }

        if(y + height > gc.getHeight()) {
            y = gc.getHeight() - height;
            speedY *= -1;
        }

        updateComponents(gc, dt);
    }

    /**
     * See {@link GameObject#render(GameContainer, Renderer)}
     * This method renders the ball as a rectangle with the given
     * x, y, width and height values.
     * It also renders the rectangle in the color 0xff00ff00.
     *
     * @param gc GameContainer for getting values from the game
     * @param renderer Renderer for rendering the ball
     */
    @Override
    public void render(GameContainer gc, Renderer renderer) {
        renderer.drawRect((int) x, (int) y, (int) width, (int) height, 0xff00ff00);
    }

    /**
     * See {@link GameObject#componentEvent(String, GameObject)}
     * This method checks if the collision happens between the ball
     * and the player or the ball and the enemy.
     * It handles the logic of the collision.
     *
     * @param name String of the event
     * @param object GameObject which is affected
     */
    @Override
    public void componentEvent(String name, GameObject object) {
        if(name.equalsIgnoreCase("collide")) {
            if(object.getX() < x) {
                left = false;
            } else {
                left = true;
            }

            speedY = -((object.getY() + object.getHeight() / 2) - (y + height / 2)) / (object.getHeight() / 2);
        }
    }

    /**
     * See {@link GameObject#dispose()}
     * This method does nothing for the ball class.
     */
    @Override
    public void dispose() {
    }

}
