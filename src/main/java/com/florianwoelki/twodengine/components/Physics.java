package com.florianwoelki.twodengine.components;

import com.florianwoelki.twodengine.components.objects.CollideComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Florian Woelki on 22.11.16.
 */
public class Physics {

    private List<CollideComponent> objects = new ArrayList<>();

    public void update() {
        for(int i = 0; i < objects.size(); i++) {
            for(int j = i + 1; j < objects.size(); j++) {
                CollideComponent c0 = objects.get(i);
                CollideComponent c1 = objects.get(j);

                if(Math.abs(c0.getX() - c1.getX()) < c0.getHalfWidth() + c1.getHalfWidth()) {
                    if(Math.abs(c0.getY() - c1.getY()) < c0.getHalfHeight() + c1.getHalfHeight()) {
                        c0.collision(c1.getObject());
                        c1.collision(c0.getObject());
                    }
                }
            }
        }

        objects.clear();
    }

    public void addCollider(CollideComponent collideComponent) {
        objects.add(collideComponent);
    }

}
