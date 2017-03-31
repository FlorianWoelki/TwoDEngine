package com.florianwoelki.twodengine.gfx;

/**
 * Created by Florian Woelki on 21.11.16.
 */
public class ImageTile extends Image {

    public int tileWidth, tileHeight;

    public ImageTile(String path, int tileWidth, int tileHeight) {
        super(path);
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
    }

}
