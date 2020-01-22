package org.unware.lyght.key;

import java.util.ArrayList;

public class Keybind {

    private ArrayList<Key> keys = new ArrayList<Key>();

    public Keybind(Key[] keys){
        this();
        for (int i = 0; i < keys.length; i++) {
            this.keys.add(i, keys[i]);
        }
    }

    public Keybind(){
    }

    public boolean isActive(){
        for(Key k : keys){
            if(!k.is())
                return false;
        }
        return true;
    }

    public boolean is(){
        return isActive();
    }
}
