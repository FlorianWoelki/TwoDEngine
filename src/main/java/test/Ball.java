package test;

import com.florianwoelki.twodengine.GameContainer;
import com.florianwoelki.twodengine.components.objects.Collider;
import com.florianwoelki.twodengine.components.objects.GameObject;
import com.florianwoelki.twodengine.gfx.Renderer;

/**
 * Created by Florian Woelki on 22.11.16.
 */
public class Ball extends GameObject {

    private boolean left = true;
    private float speedY = 0;

    public Ball( int x, int y ) {
        setTag( "ball" );

        this.x = x;
        this.y = y;
        width = 16;
        height = 16;
        addComponent( new Collider() );
    }

    @Override
    public void update( GameContainer gc, float dt ) {
        if ( left ) {
            x -= dt * 50;
        } else {
            x += dt * 50;
        }

        y += speedY;

        if ( y < 0 ) {
            y = 0;
            speedY *= -1;
        }

        if ( y + height > gc.getHeight() ) {
            y = gc.getHeight() - height;
            speedY *= -1;
        }

        updateComponents( gc, dt );
    }

    @Override
    public void render( GameContainer gc, Renderer renderer ) {
        renderer.drawRect( (int) x, (int) y, (int) width, (int) height, 0xff00ff00 );
    }

    @Override
    public void componentEvent( String name, GameObject object ) {
        if ( name.equalsIgnoreCase( "collider" ) ) {
            if ( object.getX() < x ) {
                left = false;
            } else {
                left = true;
            }

            speedY = -( ( object.getY() + object.getHeight() / 2 ) - ( y + height / 2 ) ) / ( object.getHeight() / 2 );
        }
    }

    @Override
    public void dispose() {

    }

}
