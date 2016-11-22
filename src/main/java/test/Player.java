package test;

import com.florianwoelki.twodengine.GameContainer;
import com.florianwoelki.twodengine.components.objects.Collider;
import com.florianwoelki.twodengine.components.objects.GameObject;
import com.florianwoelki.twodengine.gfx.Renderer;

import java.awt.event.KeyEvent;

/**
 * Created by Florian Woelki on 22.11.16.
 */
public class Player extends GameObject {

    public Player( int x, int y ) {
        setTag( "player" );
        this.x = x;
        this.y = y;
        width = 16;
        height = 64;
        addComponent( new Collider() );
    }

    @Override
    public void update( GameContainer gc, float dt ) {
        if ( gc.getInput().isKey( KeyEvent.VK_UP ) ) {
            y -= 75 * dt;

            if ( y < 0 ) {
                y = 0;
            }
        }

        if ( gc.getInput().isKey( KeyEvent.VK_DOWN ) ) {
            y += 75 * dt;

            if ( y + height > gc.getHeight() ) {
                y = gc.getHeight() - height;
            }
        }

        updateComponents( gc, dt );
    }

    @Override
    public void render( GameContainer gc, Renderer renderer ) {
        renderer.drawRect( (int) x, (int) y, (int) width, (int) height, 0xffffffff );
    }

    @Override
    public void componentEvent( String name, GameObject object ) {

    }

    @Override
    public void dispose() {

    }

}
