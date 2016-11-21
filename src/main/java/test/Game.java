package test;

import com.florianwoelki.twodengine.AbstractGame;
import com.florianwoelki.twodengine.GameContainer;
import com.florianwoelki.twodengine.gfx.Renderer;

/**
 * Created by Florian Woelki on 21.11.16.
 */
public class Game extends AbstractGame {

    public static void main( String[] args ) {
        GameContainer gc = new GameContainer( new Game() );
        gc.setWidth( 140 );
        gc.setHeight( 120 );
        gc.setScale( 5 );
        gc.start();
    }

    @Override
    public void update( GameContainer gc, float dt ) {
    }

    @Override
    public void render( GameContainer gc, Renderer renderer ) {
    }

}
