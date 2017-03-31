package com.florianwoelki.twodengine.components;

import com.florianwoelki.twodengine.GameContainer;
import com.florianwoelki.twodengine.components.objects.GameObject;
import com.florianwoelki.twodengine.gfx.Renderer;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Florian Woelki on 22.11.16.
 */
public abstract class Component {

    @Setter
    @Getter
    protected String tag = null;

    public abstract void update(GameContainer gc, GameObject object, float dt);

    public abstract void render(GameContainer gc, Renderer renderer);

}
