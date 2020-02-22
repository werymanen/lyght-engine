package net.lyght.key;

import java.util.ArrayList;

/** Represents a combination of keys */
public class Keybind {

    /** The list of keys */
    private ArrayList<Key> keys = new ArrayList<Key>();

    /** Default constructor
     * @param keys The keys that need to be pressed for the keybind to be active
     * */
    public Keybind(Key[] keys){
        for (int i = 0; i < keys.length; i++) {
            this.keys.add(i, keys[i]);
        }
    }

    /** Secondary constructor
     * @param keys The keys that need to be pressed for the keybind to be active
     * */
    public Keybind(ArrayList<Key> keys) {
        this.keys = keys;
    }

    /** @return If the keyboard is pressed */
    public boolean isActive(){
        for(Key k : keys){
            if(!k.is())
                return false;
        }
        return true;
    }

    /** @return If the keyboard is pressed */
    public boolean is(){
        return isActive();
    }
}
