package org.unware.lyght.key;

import org.unware.lyght.key.input.Keyboard;
import org.unware.lyght.key.input.Mouse;

public class Listener {

    public Listener(Keyboard keyboard, Mouse mouse){
        if(keyboard != null)
            keyboard.addListener(this);

        if(mouse != null)
            mouse.addListener(this);
    }

    public Listener(){
    }

    public void press(int keycode, KeyType type){
    }

    public void release(int keycode, KeyType type){
    }
}
