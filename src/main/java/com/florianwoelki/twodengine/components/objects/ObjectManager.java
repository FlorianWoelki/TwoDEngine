package com.florianwoelki.twodengine.components.objects;

import com.florianwoelki.twodengine.GameContainer;
import com.florianwoelki.twodengine.gfx.Renderer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Florian Woelki on 22.11.16.
 */
public class ObjectManager {

    private List<GameObject> objects = new ArrayList<>();

    public void updateObjects( GameContainer gc, float dt ) {
        for ( int i = 0; i < objects.size(); i++ ) {
            objects.get( i ).update( gc, dt );

            if ( objects.get( i ).isDead() ) {
                objects.remove( i );
            }
        }
    }

    public void renderObjects( GameContainer gc, Renderer renderer ) {
        for ( GameObject object : objects ) {
            object.render( gc, renderer );
        }
    }

    public void disposeObjects() {
        for ( GameObject object : objects ) {
            object.dispose();
        }
    }

    public void addObject( GameObject object ) {
        objects.add( object );
    }

    public GameObject findObject( String tag ) {
        for ( GameObject object : objects ) {
            if ( object.getTag().equalsIgnoreCase( tag ) ) {
                return object;
            }
        }
        return null;
    }

}
