package com.florianwoelki.twodengine.components;

import com.florianwoelki.twodengine.GameContainer;
import com.florianwoelki.twodengine.components.objects.GameObject;
import com.florianwoelki.twodengine.gfx.Renderer;

/**
 * Created by Florian Woelki on 22.11.16.
 */
public abstract class Component {

    protected String tag = null;

    public abstract void update( GameContainer gc, GameObject object, float dt );

    public abstract void render( GameContainer gc, Renderer renderer );

    public String getTag() {
        return tag;
    }

    public void setTag( String tag ) {
        this.tag = tag;
    }

}
