package test;

import com.florianwoelki.twodengine.GameContainer;
import com.florianwoelki.twodengine.components.objects.Collider;
import com.florianwoelki.twodengine.components.objects.GameObject;
import com.florianwoelki.twodengine.gfx.Renderer;

/**
 * Created by Florian Woelki on 22.11.16.
 */
public class Enemy extends GameObject {

    private GameObject target;

    public Enemy( int x, int y ) {
        this.x = x;
        this.y = y;
        width = 16;
        height = 64;
        addComponent( new Collider() );
    }

    @Override
    public void update( GameContainer gc, float dt ) {
        if ( target == null ) {
            target = gc.getGame().peek().getManager().findObject( "ball" );
        }

        if ( target.getY() + target.getHeight() / 2 > y - 2 ) {
            y += dt * 125;

            if ( y < 0 ) {
                y = 0;
            }
        }

        if ( target.getY() + target.getHeight() / 2 < y + 2 ) {
            y -= dt * 125;

            if ( y + height > gc.getHeight() ) {
                y = gc.getHeight() - height;
            }
        }

        updateComponents( gc, dt );
    }

    @Override
    public void render( GameContainer gc, Renderer renderer ) {
        renderer.drawRect( (int) x, (int) y, (int) width, (int) height, 0xffff0000 );
    }

    @Override
    public void componentEvent( String name, GameObject object ) {

    }

    @Override
    public void dispose() {

    }

}
