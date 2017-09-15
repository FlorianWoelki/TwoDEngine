package com.florianwoelki.twodengine.sfx;

/**
 * Created by Florian Woelki on 23.11.16.
 *
 * This class represents a audio system.
 * With this class you can easily load a sound clip.
 */
public class AudioSystem {

    private static AudioSystem instance;

    /**
     * This method will load a sound clip with the
     * specific path.
     *
     * @param path String where the audio file is
     * @return SoundClip with the specific path
     */
    public SoundClip loadSoundClip(String path) {
        SoundClip clip = new SoundClip(path);
        return clip;
    }

    /**
     * This method will return an instance of this
     * class.
     *
     * @return AudioSystem instance
     */
    public static AudioSystem getInstance() {
        if(instance == null) {
            instance = new AudioSystem();
        }
        return instance;
    }

}
