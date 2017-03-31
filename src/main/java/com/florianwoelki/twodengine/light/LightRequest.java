package com.florianwoelki.twodengine.light;

/**
 * Created by Florian Woelki on 22.11.16.
 */
public class LightRequest {

    public Light light;
    public int x, y;

    public LightRequest(Light light, int x, int y) {
        this.light = light;
        this.x = x;
        this.y = y;
    }

}
