package org.unware.lyght.key;

import org.unware.lyght.key.input.Keyboard;
import org.unware.lyght.key.input.Mouse;

public class PrimitiveKey {

    protected int keycode;
    protected KeyType type;

    public PrimitiveKey(int keycode, KeyType type){
        this.keycode = keycode;
        this.type = type;
    }

    public boolean isActive(Keyboard keyboard, Mouse mouse){
        if(type == KeyType.keyboard)
            return keyboard.key(keycode);
        else
        if(type == KeyType.mouse)
            return mouse.key(keycode);

        return false;
    }

    public boolean is(Keyboard keyboard, Mouse mouse){
        return isActive(keyboard, mouse);
    }

    public void edit(int keycode, KeyType type){
        this.keycode = keycode;
        this.type = type;
    }

    public void edit(int keycode){
        edit(keycode, this.type);
    }

    public void edit(PrimitiveKey key){
        edit(key.keycode, key.type);
    }

    public boolean is(int keycode, KeyType type){
        return this.keycode == keycode && this.type == type;
    }

    public boolean is(PrimitiveKey key){
        return is(key.keycode, key.type);
    }

    public boolean is(int keycode){
        return is(keycode, this.type);
    }

    public boolean is(KeyType type){
        return is(this.keycode, type);
    }

    public int getKeycode() {
        return keycode;
    }

    public void setKeycode(int keycode) {
        this.keycode = keycode;
    }

    public KeyType getType() {
        return type;
    }

    public void setType(KeyType type) {
        this.type = type;
    }

    public boolean equals(Object obj){
        if(obj instanceof Key)
            return is((PrimitiveKey) obj);
        else return super.equals(obj);
    }

    public String toString(){
        return ((type == KeyType.keyboard) ? (this.getClass().getSimpleName() + "[keycode=" + keycode + ",char=" + (char) keycode + ",type=" + type) : (this.getClass().getSimpleName())) + "[keycode="+keycode+",type="+type+"]";
    }
}
