package org.unware.lyght.key;

import org.unware.lyght.key.input.Keyboard;
import org.unware.lyght.key.input.Mouse;

public class Key extends PrimitiveKey{

    private Keyboard keyboard;
    private Mouse mouse;

    public Key(int keycode, KeyType type, Keyboard keyboard, Mouse mouse){
        super(keycode, type);
        this.keyboard = keyboard;
        this.mouse = mouse;
    }

    public Key(PrimitiveKey key, Keyboard keyboard, Mouse mouse){
        this(key.keycode, key.type, keyboard, mouse);
    }

    public boolean isActive(){
        return isActive(keyboard, mouse);
    }

    public boolean is() {
        return isActive();
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    public Mouse getMouse() {
        return mouse;
    }

    public void setMouse(Mouse mouse) {
        this.mouse = mouse;
    }

}
