package com.florianwoelki.twodengine.gfx;

import com.florianwoelki.twodengine.GameContainer;
import com.florianwoelki.twodengine.light.Light;

import java.awt.image.DataBufferInt;

/**
 * Created by Florian Woelki on 21.11.16.
 */
public class Renderer {

    private int width, height;
    private int[] pixels;
    private int[] lightMap;

    private int ambientLight = Pixel.getColor( 1, 0.1f, 0.1f, 0.1f );

    private Font font = Font.STANDARD;

    public Renderer( GameContainer gc ) {
        width = gc.getWidth();
        height = gc.getHeight();
        pixels = ( (DataBufferInt) gc.getWindow().getImage().getRaster().getDataBuffer() ).getData();
        lightMap = new int[pixels.length];
    }

    public void setPixel( int x, int y, int color ) {
        if ( ( x < 0 || x >= width || y < 0 || y >= height ) || color == 0xffff00ff ) {
            return;
        }

        pixels[x + y * width] = color;
    }

    public void setLightMap( int x, int y, int color ) {
        if ( ( x < 0 || x >= width || y < 0 || y >= height ) ) {
            return;
        }

        lightMap[x + y * width] = Pixel.getMax( color, lightMap[x + y * width] );
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
                pixels[x + y * width] = 0xff000000;
                lightMap[x + y * width] = ambientLight;
            }
        }
    }

    public void combineMaps() {
        for ( int x = 0; x < width; x++ ) {
            for ( int y = 0; y < height; y++ ) {
                setPixel( x, y, Pixel.getLightBlend( pixels[x + y * width], lightMap[x + y * width], ambientLight ) );
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

    public void drawImageTile( ImageTile image, int xOffset, int yOffset, int tileX, int tileY ) {
        for ( int x = 0; x < image.tileWidth; x++ ) {
            for ( int y = 0; y < image.tileHeight; y++ ) {
                setPixel( x + xOffset, y + yOffset, image.pixels[( x + ( tileX * image.tileWidth ) ) + ( y + ( tileY * image.tileHeight ) ) * width] );
            }
        }
    }

    public void drawLight( Light light, int xOffset, int yOffset ) {
        for ( int x = 0; x < light.diameter; x++ ) {
            for ( int y = 0; y < light.diameter; y++ ) {
                setLightMap( x + xOffset - light.radius, y + yOffset - light.radius, light.lightMap[x + y * light.diameter] );
            }
        }
    }

}
