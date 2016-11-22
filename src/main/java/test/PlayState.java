package test;

import com.florianwoelki.twodengine.GameContainer;
import com.florianwoelki.twodengine.components.State;
import com.florianwoelki.twodengine.components.objects.ObjectManager;
import com.florianwoelki.twodengine.gfx.Renderer;

/**
 * Created by Florian Woelki on 22.11.16.
 */
public class PlayState extends State {

    private ObjectManager manager;

    public PlayState() {
        manager = new ObjectManager();
        manager.addObject( new Player( 0, 0 ) );
        manager.addObject( new Ball( 156, 116 ) );
        manager.addObject( new Enemy( 304, 0 ) );
    }

    @Override
    public void update( GameContainer gc, float dt ) {
        manager.updateObjects( gc, dt );
    }

    @Override
    public void render( GameContainer gc, Renderer renderer ) {
        manager.renderObjects( gc, renderer );
    }

    @Override
    public void dispose() {
        manager.disposeObjects();
    }

    public ObjectManager getManager() {
        return manager;
    }

}
