package com.florianwoelki.twodengine.components.objects;

import com.florianwoelki.twodengine.GameContainer;
import com.florianwoelki.twodengine.components.Component;
import com.florianwoelki.twodengine.gfx.Renderer;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Florian Woelki on 22.11.16.
 */
public class CollideComponent extends Component {

    @Getter
    private GameObject object;
    @Setter
    @Getter
    private float x, y, halfWidth, halfHeight;

    public CollideComponent() {
        setTag("collide");
    }

    @Override
    public void update(GameContainer gc, GameObject object, float dt) {
        if(this.object == null) this.object = object;

        halfWidth = object.getWidth() / 2;
        halfHeight = object.getHeight() / 2;
        x = object.getX() + halfWidth;
        y = object.getY() + halfHeight;

        gc.getPhysics().addCollider(this);
    }

    @Override
    public void render(GameContainer gc, Renderer renderer) {

    }

    public void collision(GameObject object) {
        this.object.componentEvent(tag, object);
    }

}
