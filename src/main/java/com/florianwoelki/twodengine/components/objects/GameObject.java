package com.florianwoelki.twodengine.components.objects;

import com.florianwoelki.twodengine.GameContainer;
import com.florianwoelki.twodengine.components.Component;
import com.florianwoelki.twodengine.gfx.Renderer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Florian Woelki on 22.11.16.
 */
public abstract class GameObject {

    protected float x, y, width, height;
    protected String tag = "null";
    protected boolean isDead = false;

    protected List<Component> components = new ArrayList<>();

    public abstract void update( GameContainer gc, float dt );

    public abstract void render( GameContainer gc, Renderer renderer );

    public abstract void componentEvent( String name, GameObject object );

    public abstract void dispose();

    public void addComponent( Component component ) {
        components.add( component );
    }

    public void removeComponent( String tag ) {
        for ( int i = 0; i < components.size(); i++ ) {
            if ( components.get( i ).getTag().equalsIgnoreCase( tag ) ) {
                components.remove( i );
            }
        }
    }

    public void updateComponents( GameContainer gc, float dt ) {
        for ( Component component : components ) {
            component.update( gc, this, dt );
        }
    }

    public void renderComponents( GameContainer gc, Renderer renderer ) {
        for ( Component component : components ) {
            component.render( gc, renderer );
        }
    }

    public void setX( float x ) {
        this.x = x;
    }

    public void setY( float y ) {
        this.y = y;
    }

    public void setWidth( float width ) {
        this.width = width;
    }

    public void setHeight( float height ) {
        this.height = height;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void setTag( String tag ) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead( boolean dead ) {
        isDead = dead;
    }

}
