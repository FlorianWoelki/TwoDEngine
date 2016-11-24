package com.florianwoelki.twodengine.components.objects;

import com.florianwoelki.twodengine.GameContainer;
import com.florianwoelki.twodengine.components.Component;
import com.florianwoelki.twodengine.gfx.Renderer;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Florian Woelki on 22.11.16.
 */
public abstract class GameObject {

    @Setter
    @Getter
    protected float x, y, width, height;
    @Setter
    @Getter
    protected String tag = "null";
    @Setter
    @Getter
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

}
