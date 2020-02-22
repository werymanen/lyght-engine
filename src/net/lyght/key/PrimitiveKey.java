package net.lyght.key;

import net.lyght.key.input.Keyboard;
import net.lyght.key.input.Mouse;

/** Holds a keycode and a key type */
public class PrimitiveKey {

    /** The keycode of the key */
    protected int keycode;
    /** The type of the key */
    protected KeyType type;

    /** Default constructor
     * @param keycode The keycode of the key
     * @param type The type of the key
     * */
    public PrimitiveKey(int keycode, KeyType type){
        this.keycode = keycode;
        this.type = type;
    }

    /** @return If the key is pressed down
     * @param keyboard The keyboard to check from
     * @param mouse The mouse to check from
     * */
    public boolean isActive(Keyboard keyboard, Mouse mouse){
        if(type == KeyType.keyboard)
            return keyboard.key(keycode);
        else
        if(type == KeyType.mouse)
            return mouse.key(keycode);

        return false;
    }

    /** @return If the key is pressed down
     * @param keyboard The keyboard to check from
     * @param mouse The mouse to check from
     * */
    public boolean is(Keyboard keyboard, Mouse mouse){
        return isActive(keyboard, mouse);
    }

    /** Sets both the keycode and type
     * @param keycode New keycode
     * @param type New type
     * */
    public void edit(int keycode, KeyType type){
        this.keycode = keycode;
        this.type = type;
    }

    public boolean equals(int keycode, KeyType type){
        return this.keycode == keycode && this.type == type;
    }

    public boolean equals(PrimitiveKey key){
        return equals(key.keycode, key.type);
    }

    public boolean equals(int keycode){
        return equals(keycode, this.type);
    }

    public boolean equals(KeyType type){
        return equals(this.keycode, type);
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

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Key)
            return equals((PrimitiveKey) obj);
        else return super.equals(obj);
    }

    @Override
    public String toString(){
        return this.getClass().getSimpleName() + "[keycode=" + keycode + ",type=" + type + ",char=" + (type == KeyType.keyboard ? Keyboard.getName(keycode) : Mouse.getName(keycode)) + "]";
    }
}
