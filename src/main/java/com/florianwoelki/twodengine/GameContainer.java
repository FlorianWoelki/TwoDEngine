package com.florianwoelki.twodengine;

import com.florianwoelki.twodengine.gfx.Renderer;
import com.florianwoelki.twodengine.input.Input;
import com.florianwoelki.twodengine.window.Window;

/**
 * Created by Florian Woelki on 18.11.16.
 */
public class GameContainer implements Runnable {

    private int width = 320, height = 240;
    private float scale = 2f;
    private String title = "TwoDEngine v1.0 by Florian Woelki";

    private AbstractGame game;
    private Window window;
    private Renderer renderer;
    private Input input;

    private Thread thread;
    private boolean isRunning;
    private final double FRAME_CAP = 1d / 60d;

    public GameContainer( AbstractGame game ) {
        this.game = game;
    }

    public void start() {
        if ( isRunning ) {
            return;
        }

        window = new Window( this );
        renderer = new Renderer( this );
        input = new Input( this );

        isRunning = true;
        thread = new Thread( this );
        thread.start();
    }

    public void stop() {
        if ( isRunning ) {
            return;
        }

        isRunning = false;
        try {
            thread.join();
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        double firstTime = 0;
        double lastTime = System.nanoTime() / 1e9;
        double passedTime = 0;
        double unprocessedTime = 0;

        double frameTime = 0;
        int frames = 0;
        int fps = 0;

        while ( isRunning ) {
            boolean render = true;

            firstTime = System.nanoTime() / 1e9;
            passedTime = firstTime - lastTime;
            lastTime = firstTime;

            unprocessedTime += passedTime;
            frameTime += passedTime;

            while ( unprocessedTime >= FRAME_CAP ) {
                game.update( this, (float) FRAME_CAP );
                input.update();
                unprocessedTime -= FRAME_CAP;
                render = true;

                if ( frameTime >= 1 ) {
                    frameTime = 0;
                    fps = frames;
                    frames = 0;
                }
            }

            if ( render ) {
                renderer.clear();
                game.render( this, renderer );
                renderer.drawString( "FPS-" + fps, 0xffffffff, 0, 1 );
                window.update();
                frames++;
            } else {
                try {
                    Thread.sleep( 1 );
                } catch ( InterruptedException e ) {
                    e.printStackTrace();
                }
            }
        }

        cleanUp();
        stop();
    }

    private void cleanUp() {
        window.cleanUp();
    }

    public void setWidth( int width ) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight( int height ) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setScale( float scale ) {
        this.scale = scale;
    }

    public float getScale() {
        return scale;
    }

    public void setTitle( String title ) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public Window getWindow() {
        return window;
    }

}
