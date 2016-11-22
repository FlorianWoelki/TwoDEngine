package com.florianwoelki.twodengine.components.objects;

import com.florianwoelki.twodengine.GameContainer;
import com.florianwoelki.twodengine.components.Component;
import com.florianwoelki.twodengine.gfx.Renderer;

/**
 * Created by Florian Woelki on 22.11.16.
 */
public class Collider extends Component {

    private GameObject object;
    private float x, y, halfWidth, halfHeight;

    public Collider() {
        setTag( "collider" );
    }

    @Override
    public void update( GameContainer gc, GameObject object, float dt ) {
        if ( this.object == null ) this.object = object;

        halfWidth = object.getWidth() / 2;
        halfHeight = object.getHeight() / 2;
        x = object.getX() + halfWidth;
        y = object.getY() + halfHeight;

        gc.getPhysics().addCollider( this );
    }

    @Override
    public void render( GameContainer gc, Renderer renderer ) {

    }

    public void collision( GameObject object ) {
        this.object.componentEvent( tag, object );
    }

    public GameObject getObject() {
        return object;
    }

    public void setX( float x ) {
        this.x = x;
    }

    public void setY( float y ) {
        this.y = y;
    }

    public void setHalfWidth( float halfWidth ) {
        this.halfWidth = halfWidth;
    }

    public void setHalfHeight( float halfHeight ) {
        this.halfHeight = halfHeight;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getHalfWidth() {
        return halfWidth;
    }

    public float getHalfHeight() {
        return halfHeight;
    }
}
