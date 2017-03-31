package com.florianwoelki.twodengine.gfx;

import com.florianwoelki.twodengine.GameContainer;
import com.florianwoelki.twodengine.light.Light;
import com.florianwoelki.twodengine.light.LightRequest;
import com.florianwoelki.twodengine.light.ShadowType;
import lombok.Getter;
import lombok.Setter;

import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Florian Woelki on 21.11.16.
 */
public class Renderer {

    private GameContainer gc;

    private int width, height;
    private int[] pixels;
    private int[] lightMap;
    private ShadowType[] shadowMap;

    @Setter
    @Getter
    private int ambientLight = Pixel.getColor(1, 0.1f, 0.1f, 0.1f);
    @Setter
    @Getter
    private int clearColor = 0xff000000;

    @Setter
    @Getter
    private int transX, transY;

    private Font font = Font.STANDARD;

    private List<LightRequest> lightRequests = new ArrayList<>();

    public Renderer(GameContainer gc) {
        this.gc = gc;

        width = gc.getWidth();
        height = gc.getHeight();
        pixels = ((DataBufferInt) gc.getWindow().getImage().getRaster().getDataBuffer()).getData();
        lightMap = new int[pixels.length];
        shadowMap = new ShadowType[pixels.length];
    }

    public void setPixel(int x, int y, int color, ShadowType shadowType) {
        x -= transX;
        y -= transY;

        if((x < 0 || x >= width || y < 0 || y >= height) || color == 0xffff00ff) {
            return;
        }

        pixels[x + y * width] = color;
        this.shadowMap[x + y * width] = shadowType;
    }

    public ShadowType getLightBlock(int x, int y) {
        x -= transX;
        y -= transY;

        if((x < 0 || x >= width || y < 0 || y >= height)) {
            return ShadowType.TOTAL;
        }

        return shadowMap[x + y * width];
    }

    public void setLightMap(int x, int y, int color) {
        x -= transX;
        y -= transY;

        if((x < 0 || x >= width || y < 0 || y >= height)) {
            return;
        }

        lightMap[x + y * width] = Pixel.getMax(color, lightMap[x + y * width]);
    }

    public void drawString(String text, int color, int xOffset, int yOffset) {
        text = text.toUpperCase();

        int offset = 0;

        for(int i = 0; i < text.length(); i++) {
            int unicode = text.codePointAt(i) - 32;

            for(int x = 0; x < font.widths[unicode]; x++) {
                for(int y = 1; y < font.image.height; y++) {
                    if(font.image.pixels[(x + font.offsets[unicode]) + y * font.image.width] == 0xffffffff) {
                        setPixel(x + xOffset + offset, y + yOffset - 1, color, ShadowType.NONE);
                    }
                }
            }

            offset += font.widths[unicode];
        }
    }

    public void clear() {
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                pixels[x + y * width] = clearColor;
            }
        }
    }

    public void flushMaps() {
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                setPixel(x, y, Pixel.getLightBlend(pixels[x + y * width], lightMap[x + y * width], ambientLight), shadowMap[x + y * width]);
                lightMap[x + y * width] = ambientLight;
            }
        }
    }

    public void drawLightArray() {
        for(LightRequest lightRequest : lightRequests) {
            drawLightRequest(lightRequest.light, lightRequest.x, lightRequest.y);
        }

        lightRequests.clear();
    }

    public void drawImage(Image image, int xOffset, int yOffset) {
        for(int x = 0; x < image.width; x++) {
            for(int y = 0; y < image.height; y++) {
                setPixel(x + xOffset, y + yOffset, image.pixels[x + y * width], image.shadowType);
            }
        }
    }

    public void drawImageTile(ImageTile image, int xOffset, int yOffset, int tileX, int tileY) {
        for(int x = 0; x < image.tileWidth; x++) {
            for(int y = 0; y < image.tileHeight; y++) {
                setPixel(x + xOffset, y + yOffset, image.pixels[(x + (tileX * image.tileWidth)) + (y + (tileY * image.tileHeight)) * width], image.shadowType);
            }
        }
    }

    public void drawRect(int xOffset, int yOffset, int width, int height, int color, ShadowType shadowType) {
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                setPixel(x + xOffset, y + yOffset, color, shadowType);
            }
        }
    }

    public void drawLight(Light light, int xOffset, int yOffset) {
        if(gc.isDynamicLights() || gc.isEnableLighting()) {
            lightRequests.add(new LightRequest(light, xOffset, yOffset));
        }
    }

    private void drawLightRequest(Light light, int xOffset, int yOffset) {
        if(gc.isDynamicLights()) {
            for(int i = 0; i <= light.diameter; i++) {
                drawLightLine(light.radius, light.radius, i, 0, light, xOffset, yOffset);
                drawLightLine(light.radius, light.radius, i, light.diameter, light, xOffset, yOffset);
                drawLightLine(light.radius, light.radius, 0, i, light, xOffset, yOffset);
                drawLightLine(light.radius, light.radius, light.diameter, i, light, xOffset, yOffset);
            }
        } else {
            for(int x = 0; x < light.diameter; x++) {
                for(int y = 0; y < light.diameter; y++) {
                    setLightMap(x + xOffset - light.radius, y + yOffset - light.radius, light.getLightValue(x, y));
                }
            }
        }
    }

    private void drawLightLine(int x0, int y0, int x1, int y1, Light light, int xOffset, int yOffset) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);

        int sx = x0 < x1 ? 1 : -1;
        int sy = y0 < y1 ? 1 : -1;

        int err = dx - dy;
        int e2 = 0;

        float power = 1f;
        boolean hit = false;

        while(true) {
            if(light.getLightValue(x0, y0) == 0xff000000) {
                break;
            }

            int screenX = x0 - light.radius + xOffset;
            int screenY = y0 - light.radius + yOffset;

            if(power == 1) {
                setLightMap(screenX, screenY, light.getLightValue(x0, y0));
            } else {
                setLightMap(screenX, screenY, Pixel.getColorPower(light.getLightValue(x0, y0), power));
            }

            if(x0 == x1 && y0 == y1) break;
            if(getLightBlock(screenX, screenY) == ShadowType.TOTAL) break;
            if(getLightBlock(screenX, screenY) == ShadowType.FADE) power -= 0.05f;
            if(getLightBlock(screenX, screenY) == ShadowType.HALF && !hit) {
                power /= 2;
                hit = true;
            }
            if(getLightBlock(screenX, screenY) == ShadowType.NONE && hit) hit = false;
            if(power <= 0.1f) break;

            e2 = 2 * err;

            if(e2 > -1 * dy) {
                err -= dy;
                x0 += sx;
            }

            if(e2 < dx) {
                err += dx;
                y0 += sy;
            }
        }
    }

    public void drawImage(Image image) {
        drawImage(image, 0, 0);
    }

    public void drawImageTile(ImageTile image, int tileX, int tileY) {
        drawImageTile(image, 0, 0, tileX, tileY);
    }

    public void drawLight(Light light) {
        drawLight(light, 0, 0);
    }

    public void drawRect(int xOffset, int yOffset, int width, int height, int color) {
        drawRect(xOffset, yOffset, width, height, color, ShadowType.NONE);
    }

}
