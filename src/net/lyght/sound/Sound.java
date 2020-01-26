package net.lyght.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class Sound {

    private File file;
    private Clip clip;

    public Sound(File file) {
        this.file = file;
    }

    public AudioInputStream getAudioStream(){
        try{
            return AudioSystem.getAudioInputStream(file);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void play(){
        play(1);
    }

    public void play(double volume){
        play(this, volume);
    }

    public void stop(){
        stop(this);
    }



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

    public static void setVolume(Sound sound, double volume){
        if(sound.clip == null)
            return;
        setVolume0(volume, sound.clip);
    }

    private static void setVolume0(double volume, Clip clip){
        FloatControl gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float dB = (float) Math.log(volume) / (float) Math.log(10) * 20;
        gain.setValue(dB);
    }

}
