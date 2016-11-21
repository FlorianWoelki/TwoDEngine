package com.florianwoelki.twodengine.sfx;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.BufferedInputStream;
import java.io.InputStream;

/**
 * Created by Florian Woelki on 21.11.16.
 */
public class SoundClip {

    private Clip clip;
    private FloatControl gainControl;

    public SoundClip( String path ) {
        try {
            InputStream audioSource = getClass().getResourceAsStream( path );
            InputStream bufferedInput = new BufferedInputStream( audioSource );
            AudioInputStream ais = AudioSystem.getAudioInputStream( bufferedInput );
            AudioFormat baseFormat = ais.getFormat();
            AudioFormat decodeFormat = new AudioFormat( AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16, baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false );
            AudioInputStream dais = AudioSystem.getAudioInputStream( decodeFormat, ais );

            clip = AudioSystem.getClip();
            clip.open( dais );

            gainControl = (FloatControl) clip.getControl( FloatControl.Type.MASTER_GAIN );
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public void play() {
        if ( clip == null ) {
            return;
        }

        stop();
        clip.setFramePosition( 0 );
        while ( !clip.isRunning() ) {
            clip.start();
        }
    }

    public void stop() {
        if ( clip.isRunning() ) {
            clip.stop();
        }
    }

    public void close() {
        stop();
        clip.drain();
        clip.close();
    }

    public void loop() {
        clip.loop( Clip.LOOP_CONTINUOUSLY );
        while ( !clip.isRunning() ) {
            clip.start();
        }
    }

    public void setVolume( float value ) {
        gainControl.setValue( value );
    }

}
