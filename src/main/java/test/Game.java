package test;

import com.florianwoelki.twodengine.AbstractGame;
import com.florianwoelki.twodengine.GameContainer;
import com.florianwoelki.twodengine.gfx.Renderer;
import com.florianwoelki.twodengine.input.Input;

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
        if ( Input.isKey( KeyEvent.VK_W ) ) {
            y--;
        } else if ( Input.isKey( KeyEvent.VK_S ) ) {
            y++;
        } else if ( Input.isKey( KeyEvent.VK_A ) ) {
            x--;
        } else if ( Input.isKey( KeyEvent.VK_D ) ) {
            x++;
        }
    }

    @Override
    public void render( GameContainer gc, Renderer renderer ) {
        renderer.drawString( "Testing", 0xffffffff, x, y );
    }

}
