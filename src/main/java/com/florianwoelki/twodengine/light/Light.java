package com.florianwoelki.twodengine.light;

import com.florianwoelki.twodengine.gfx.Pixel;

/**
 * Created by Florian Woelki on 21.11.16.
 */
public class Light {

    public int[] lightMap;
    public int color, radius, diameter;

    public Light( int color, int radius ) {
        this.color = color;
        this.radius = radius;
        this.diameter = radius * 2;

        lightMap = new int[diameter * diameter];

        for ( int x = 0; x < diameter; x++ ) {
            for ( int y = 0; y < diameter; y++ ) {
                float distance = (float) Math.sqrt( ( x - radius ) * ( x - radius ) + ( y - radius ) * ( y - radius ) );

                if ( distance < radius ) {
                    lightMap[x + y * diameter] = Pixel.getColorPower( color, 1 - distance / radius );
                } else {
                    lightMap[x + y * diameter] = 0;
                }
            }
        }
    }

}
