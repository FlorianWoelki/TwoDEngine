package test;

import com.florianwoelki.twodengine.AbstractGame;
import com.florianwoelki.twodengine.GameContainer;
import com.florianwoelki.twodengine.gfx.Renderer;

/**
 * Created by Florian Woelki on 21.11.16.
 */
public class GameManager extends AbstractGame {

    public GameManager() {

    }

    @Override
    public void update( GameContainer gc, float dt ) {

    }

    @Override
    public void render( GameContainer gc, Renderer renderer ) {
    }

    public static void main( String[] args ) {
        GameContainer gc = new GameContainer( new GameManager() );
        gc.setWidth( 320 );
        gc.setHeight( 240 );
        gc.setScale( 3 );
        gc.setDynamicLights( false );
        gc.setEnableLighting( false );
        gc.start();
    }

}
