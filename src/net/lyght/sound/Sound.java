package net.lyght.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

/** A sound that can be played
 * (sometimes) */
public class Sound {

    /** Source file */
    private File file;
    /** Audio clip */
    private Clip clip;

    /** Default constructor
     * @param file The sound file
     * */
    public Sound(File file) {
        this.file = file;
    }

    /** @return The audio stream */
    public AudioInputStream getAudioStream(){
        try{
            return AudioSystem.getAudioInputStream(file);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /** Plays the audio */
    public void play(){
        play(1);
    }

    /** Plays the audio
     * @param volume The volume to play the sound
     * */
    public void play(double volume){
        play(this, volume);
    }

    /** Stops the audio */
    public void stop(){
        stop(this);
    }


    /** Plays a sound
     * @param sound The sound to start
     * @param volume The volume of the sound
     * */
    public static synchronized void play(Sound sound, double volume) {
        Thread thread = new Thread() {
            public void run() {
                try {
                    AudioInputStream stream = sound.getAudioStream();
                    Clip clip = AudioSystem.getClip();

                    clip.open(stream);
                    sound.clip = clip;
                    setVolume0(volume, clip);
                    clip.start();

                    while(clip.isOpen())
                        Thread.sleep(10);

                    sound.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    /** Stops a sound
     * @param sound The sound to stop
     * */
    public static void stop(Sound sound){
        try {
            if(sound.clip == null)
                return;
            Clip clip = sound.clip;
            clip.stop();

            sound.clip = null;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /** Sets the volume of an audio
     * @param sound The sound
     * @param volume The new volume
     * */
    public static void setVolume(Sound sound, double volume){
        if(sound.clip == null)
            return;
        setVolume0(volume, sound.clip);
    }

    /** Sets the volume of an audio
     * @param volume New volume
     * @param clip Clip of the sound
     * */
    private static void setVolume0(double volume, Clip clip){
        FloatControl gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float dB = (float) Math.log(volume) / (float) Math.log(10) * 20;
        gain.setValue(dB);
    }

}
