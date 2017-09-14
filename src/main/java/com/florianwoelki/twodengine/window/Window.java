package com.florianwoelki.twodengine.window;

import com.florianwoelki.twodengine.GameContainer;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 * Created by Florian Woelki on 21.11.16.
 *
 * This class represents a simple Window creating
 * with swing.
 * It contains also setting up the rendering stuff
 * for the window.
 */
public class Window {

    private JFrame frame;
    @Getter
    private Canvas canvas;

    @Getter
    private BufferedImage image;
    private Graphics g;
    private BufferStrategy bs;

    /**
     * This constructor will setup the window and the
     * rendering part for the window.
     *
     * @param gc GameContainer for getting initial values
     */
    public Window(GameContainer gc) {
        image = new BufferedImage(gc.getWidth(), gc.getHeight(), BufferedImage.TYPE_INT_RGB);

        // Creating the size of the window
        canvas = new Canvas();
        Dimension size = new Dimension((int) (gc.getWidth() * gc.getScale()), (int) (gc.getHeight() * gc.getScale()));
        canvas.setPreferredSize(size);
        canvas.setMaximumSize(size);
        canvas.setMinimumSize(size);

        // Creating the frame.
        frame = new JFrame(gc.getTitle());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(canvas);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        // Creating the rendering part
        canvas.createBufferStrategy(1);
        bs = canvas.getBufferStrategy();
        g = bs.getDrawGraphics();
    }

    /**
     * This method will update the window and it will
     * draw the BufferedImage again.
     */
    public void update() {
        g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
        bs.show();
    }

    /**
     * This method cleans up the window and it
     * dispose everything.
     */
    public void cleanUp() {
        g.dispose();
        bs.dispose();
        image.flush();
        frame.dispose();
    }

}
