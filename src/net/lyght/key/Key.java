package net.lyght.key;

import net.lyght.key.input.Keyboard;
import net.lyght.key.input.Mouse;

/** Represents a key, either keyboard or mouse */
public class Key extends PrimitiveKey {

    /** Keyboard to check if the key is pressed */
    private Keyboard keyboard;
    /** Mouse to check if the key is pressed */
    private Mouse mouse;

    /** Default constructor
     * @param keycode The keycode either from the keyboard or the mouse
     * @param type The type of keycode
     * @param keyboard The keyboard to check if the key is pressed
     * @param mouse The mouse to check if the key is pressed
     * */
    public Key(int keycode, KeyType type, Keyboard keyboard, Mouse mouse){
        super(keycode, type);
        this.keyboard = keyboard;
        this.mouse = mouse;
    }

    /** Secondary constructor
     * @param key Primitive key to be imported
     * @param keyboard The keyboard to check if the key is pressed
     * @param mouse The mouse to check if the key is pressed
     * */
    public Key(PrimitiveKey key, Keyboard keyboard, Mouse mouse){
        this(key.keycode, key.type, keyboard, mouse);
    }

    /** @return If the key is pressed */
    public boolean isActive(){
        return isActive(keyboard, mouse);
    }

    /** @return If the key is pressed */
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
