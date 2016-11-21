package com.florianwoelki.twodengine.gfx;

import com.florianwoelki.twodengine.GameContainer;

import java.awt.image.DataBufferInt;

/**
 * Created by Florian Woelki on 21.11.16.
 */
public class Renderer {

    private int width, height;
    private int[] pixels;

    private Font font = Font.STANDARD;

    public Renderer( GameContainer gc ) {
        width = gc.getWidth();
        height = gc.getHeight();
        pixels = ( (DataBufferInt) gc.getWindow().getImage().getRaster().getDataBuffer() ).getData();
    }

    public void setPixel( int x, int y, int color ) {
        if ( ( x < 0 || x >= width || y < 0 || y >= height ) || color == 0xffff00ff ) {
            return;
        }

        pixels[x + y * width] = color;
    }

    public void drawString( String text, int color, int xOffset, int yOffset ) {
        text = text.toUpperCase();

        int offset = 0;

        for ( int i = 0; i < text.length(); i++ ) {
            int unicode = text.codePointAt( i ) - 32;

            for ( int x = 0; x < font.widths[unicode]; x++ ) {
                for ( int y = 1; y < font.image.height; y++ ) {
                    if ( font.image.pixels[( x + font.offsets[unicode] ) + y * font.image.width] == 0xffffffff ) {
                        setPixel( x + xOffset + offset, y + yOffset - 1, color );
                    }
                }
            }

            offset += font.widths[unicode];
        }
    }

    public void clear() {
        for ( int x = 0; x < width; x++ ) {
            for ( int y = 0; y < height; y++ ) {
                setPixel( x, y, 0xff000000 );
            }
        }
    }

    public void drawImage( Image image, int xOffset, int yOffset ) {
        for ( int x = 0; x < image.width; x++ ) {
            for ( int y = 0; y < image.height; y++ ) {
                setPixel( x + xOffset, y + yOffset, image.pixels[x + y * width] );
            }
        }
    }

}
