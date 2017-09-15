package com.florianwoelki.twodengine.sfx;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 * Created by Florian Woelki on 21.11.16.
 *
 * This class represents a sound clip and you can load easily a
 * sound clip with the {@link com.florianwoelki.twodengine.sfx.AudioSystem}.
 * Here you can play the sound clip, stop it or loop it.
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

    /**
     * With this method you can play the sound clip.
     * It will start from the beginning.
     */
    public void play() {
        if(clip == null) {
            return;
        }

        stop();
        clip.setFramePosition(0);
        clip.start();
    }

    /**
     * With this method you can stop the sound clip.
     */
    public void stop() {
        if(clip.isRunning()) {
            clip.stop();
        }
    }

    /**
     * This method will close the sound clip.
     */
    public void close() {
        stop();
        clip.drain();
        clip.close();
    }

    /**
     * This method will loop the sound clip.
     * It will play continuously.
     */
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
    }

    /**
     * This method will set the volume of the sound clip.
     *
     * @param value Float for the volume of the sound clip
     */
    public void setVolume(float value) {
        gainControl.setValue(value);
    }

}
