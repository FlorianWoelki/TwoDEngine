package com.florianwoelki.twodengine.sfx;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 * Created by Florian Woelki on 21.11.16.
 */
public class SoundClip {

    private Clip clip;
    private FloatControl gainControl;

    public SoundClip(String path) {
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(getClass().getResourceAsStream(path)));
            clip.start();

            gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if(clip == null) {
            return;
        }

        stop();
        clip.setFramePosition(0);
        clip.start();
    }

    public void stop() {
        if(clip.isRunning()) {
            clip.stop();
        }
    }

    public void close() {
        stop();
        clip.drain();
        clip.close();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
    }

    public void setVolume(float value) {
        gainControl.setValue(value);
    }

}
