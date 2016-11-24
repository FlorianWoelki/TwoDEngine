package com.florianwoelki.twodengine;

import com.florianwoelki.twodengine.components.Physics;
import com.florianwoelki.twodengine.gfx.Renderer;
import com.florianwoelki.twodengine.input.Input;
import com.florianwoelki.twodengine.window.Window;
import lombok.Getter;
import lombok.Setter;

import java.awt.event.KeyEvent;

/**
 * Created by Florian Woelki on 18.11.16.
 */
public class GameContainer implements Runnable {

    @Setter
    @Getter
    private int width = 320, height = 240;
    @Setter
    @Getter
    private float scale = 2f;
    @Setter
    @Getter
    private String title = "TwoDEngine v1.0 by Florian Woelki";

    @Getter
    private AbstractGame game;
    @Getter
    private Window window;
    private Renderer renderer;
    @Getter
    private Input input;
    @Getter
    private Physics physics;

    private Thread thread;
    private boolean isRunning = false;
    @Setter
    private double frameCap = 1d / 60d;

    @Setter
    @Getter
    private boolean enableLighting = false;
    @Setter
    @Getter
    private boolean dynamicLights = false;
    @Setter
    @Getter
    private boolean clearScreen = false;
    private boolean debug = false;

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
        physics = new Physics();

        window.getCanvas().setFocusable( true );
        window.getCanvas().requestFocus();

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

            while ( unprocessedTime >= frameCap ) {
                if ( input.isKeyPressed( KeyEvent.VK_F2 ) ) debug = !debug;

                game.update( this, (float) frameCap );
                physics.update();
                input.update();
                unprocessedTime -= frameCap;
                render = true;

                if ( frameTime >= 1 ) {
                    frameTime = 0;
                    fps = frames;
                    frames = 0;
                }
            }

            if ( render ) {
                if ( clearScreen ) renderer.clear();

                game.render( this, renderer );

                if ( enableLighting || dynamicLights ) {
                    renderer.drawLightArray();
                    renderer.flushMaps();
                }
                if ( debug ) renderer.drawString( "FPS-" + fps, 0xffffffff, 0, 1 );

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

}
