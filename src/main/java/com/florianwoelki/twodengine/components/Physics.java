package com.florianwoelki.twodengine.components;

import com.florianwoelki.twodengine.components.objects.Collider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Florian Woelki on 22.11.16.
 */
public class Physics {

    private List<Collider> objects = new ArrayList<>();

    public void update() {
        for ( int i = 0; i < objects.size(); i++ ) {
            for ( int j = i + 1; j < objects.size(); j++ ) {
                Collider c0 = objects.get( i );
                Collider c1 = objects.get( j );

                if ( Math.abs( c0.getX() - c1.getX() ) < c0.getHalfWidth() + c1.getHalfWidth() ) {
                    if ( Math.abs( c0.getY() - c1.getY() ) < c0.getHalfHeight() + c1.getHalfHeight() ) {
                        c0.collision( c1.getObject() );
                        c1.collision( c0.getObject() );
                    }
                }
            }
        }

        objects.clear();
    }

    public void addCollider( Collider collider ) {
        objects.add( collider );
    }

}
