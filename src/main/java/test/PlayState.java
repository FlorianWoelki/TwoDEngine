package test;

import com.florianwoelki.twodengine.GameContainer;
import com.florianwoelki.twodengine.components.State;
import com.florianwoelki.twodengine.components.objects.ObjectManager;
import com.florianwoelki.twodengine.gfx.Renderer;
import lombok.Getter;

/**
 * Created by Florian Woelki on 22.11.16.
 *
 * This class represents the PlayState vor Pong.
 * It handles the logic of the game.
 */
public class PlayState extends State {

    @Getter
    private ObjectManager manager;

    /**
     * This constructor setups all the entities in the game.
     */
    public PlayState() {
        manager = new ObjectManager();
        manager.addObject(new Player(0, 0));
        manager.addObject(new Ball(156, 116));
        manager.addObject(new Enemy(304, 0));
    }

    /**
     * See {@link State#update(GameContainer, float)}
     * This method updates all the entities in the game.
     *
     * @param gc GameContainer for managing objects in the game
     * @param dt Float delta for good fps on every computer
     */
    @Override
    public void update(GameContainer gc, float dt) {
        manager.updateObjects(gc, dt);
    }

    /**
     * See {@link State#render(GameContainer, Renderer)}
     * This method renders all the entities in the game.
     *
     * @param gc GameContainer for managing objects in the game
     * @param renderer Renderer for rendering images or objects
     */
    @Override
    public void render(GameContainer gc, Renderer renderer) {
        manager.renderObjects(gc, renderer);
    }

    /**
     * See {@link State#dispose()}
     * This method will dispose all the entities in the game.
     */
    @Override
    public void dispose() {
        manager.disposeObjects();
    }

}
