package com.florianwoelki.twodengine.window;

import com.florianwoelki.twodengine.GameContainer;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 * Created by Florian Woelki on 21.11.16.
 */
public class Window {

    private JFrame frame;
    @Getter
    private Canvas canvas;

    @Getter
    private BufferedImage image;
    private Graphics g;
    private BufferStrategy bs;

    public Window(GameContainer gc) {
        image = new BufferedImage(gc.getWidth(), gc.getHeight(), BufferedImage.TYPE_INT_RGB);

        canvas = new Canvas();
        Dimension size = new Dimension((int) (gc.getWidth() * gc.getScale()), (int) (gc.getHeight() * gc.getScale()));
        canvas.setPreferredSize(size);
        canvas.setMaximumSize(size);
        canvas.setMinimumSize(size);

        frame = new JFrame(gc.getTitle());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(canvas);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        canvas.createBufferStrategy(1);
        bs = canvas.getBufferStrategy();
        g = bs.getDrawGraphics();
    }

    public void update() {
        g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
        bs.show();
    }

    public void cleanUp() {
        g.dispose();
        bs.dispose();
        image.flush();
        frame.dispose();
    }

}
