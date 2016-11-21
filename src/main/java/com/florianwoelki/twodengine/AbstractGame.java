package com.florianwoelki.twodengine;

import com.florianwoelki.twodengine.gfx.Renderer;

/**
 * Created by Florian Woelki on 18.11.16.
 */
public abstract class AbstractGame {

    public abstract void update( GameContainer gc, float dt );

    public abstract void render( GameContainer gc, Renderer renderer );

}
