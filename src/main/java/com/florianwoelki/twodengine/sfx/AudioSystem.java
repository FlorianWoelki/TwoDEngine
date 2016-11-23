package com.florianwoelki.twodengine.sfx;

/**
 * Created by Florian Woelki on 23.11.16.
 */
public class AudioSystem {

    private static AudioSystem instance;

    public SoundClip loadSoundClip( String path ) {
        SoundClip clip = new SoundClip( path );
        return clip;
    }

    public static AudioSystem getInstance() {
        if ( instance == null ) {
            instance = new AudioSystem();
        }
        return instance;
    }

}
