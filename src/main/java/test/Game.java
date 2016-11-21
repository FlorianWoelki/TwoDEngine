package test;

import com.florianwoelki.twodengine.AbstractGame;
import com.florianwoelki.twodengine.GameContainer;
import com.florianwoelki.twodengine.gfx.Renderer;

import java.awt.event.KeyEvent;

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

    int x = 0;
    int y = 0;

    @Override
    public void update( GameContainer gc, float dt ) {
        if ( gc.getInput().isKey( KeyEvent.VK_UP ) ) {
            y--;
        } else if ( gc.getInput().isKey( KeyEvent.VK_DOWN ) ) {
            y++;
        } else if ( gc.getInput().isKey( KeyEvent.VK_LEFT ) ) {
            x--;
        } else if ( gc.getInput().isKey( KeyEvent.VK_RIGHT ) ) {
            x++;
        }
    }

    @Override
    public void render( GameContainer gc, Renderer renderer ) {
        renderer.drawString( "Testing", 0xffffffff, x, y );
    }

}
