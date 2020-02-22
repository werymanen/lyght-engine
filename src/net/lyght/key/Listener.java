package net.lyght.key;

import net.lyght.key.input.Keyboard;
import net.lyght.key.input.Mouse;

/** Listens for keys */
public class Listener {

    /** Default constructor
     * @param keyboard The keyboard to add this listener to
     * @param mouse The mouse to add to this listener
     * */
    public Listener(Keyboard keyboard, Mouse mouse){
        if(keyboard != null)
            keyboard.addListener(this);

        if(mouse != null)
            mouse.addListener(this);
    }

    /** Secondary constructor */
    public Listener(){
    }

    /** Event, called when a key is pressed
     * @param keycode The keycode which has been pressed
     * @param type The type of key which has been released
     * */
    public void press(int keycode, KeyType type){
    }

    /** Event, called when a key is released
     * @param keycode The keycode which has been released
     * @param type The type of key which has been released
     * */
    public void release(int keycode, KeyType type){
    }
}
