package com.florianwoelki.twodengine.components;

import com.florianwoelki.twodengine.GameContainer;
import com.florianwoelki.twodengine.components.objects.ObjectManager;
import com.florianwoelki.twodengine.gfx.Renderer;
import lombok.Getter;

/**
 * Created by Florian Woelki on 22.11.16.
 */
public abstract class State {

    @Getter
    protected ObjectManager manager = new ObjectManager();

    public abstract void update(GameContainer gc, float dt);

    public abstract void render(GameContainer gc, Renderer renderer);

    public abstract void dispose();

}
