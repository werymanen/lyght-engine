package net.lyght.key;

import net.lyght.key.input.Keyboard;
import net.lyght.key.input.Mouse;
import net.lyght.render.Display;

/** Key generation unit */
public class KeyGenerator {

    /** The keyboard for the keys to be generated with */
    private Keyboard keyboard;
    /** The mouse for the keys to be generated with */
    private Mouse mouse;

    /** The listener to generate listened keys */
    private KeyListenerGenerator klg;

    /** Default constructor
     * @param keyboard The keyboard for the keys to be generated with
     * @param mouse The mouse for the keys to be generated with
     * */
    public KeyGenerator(Keyboard keyboard, Mouse mouse){
        this.keyboard = keyboard;
        this.mouse = mouse;
        klg = new KeyListenerGenerator();
    }

    /** Secondary constructor
     * @param display Display, that contains the keyboard and the mouse
     * */
    public KeyGenerator(Display display){
        this(display.keyboard, display.mouse);
    }

    /** Tertiary constructor
     * @param key Key, that contains the keyboard and the mouse
     * */
    public KeyGenerator(Key key){
        this(key.getKeyboard(), key.getMouse());
    }

    /** Generates a key
     * @param keycode The keycode
     * @param type The type of keycode
     * */
    public Key generate(int keycode, KeyType type){
        return new Key(keycode, type, keyboard, mouse);
    }

    /** Generates a key
     * @param key A primitive key, that contains the keycode and the type
     * */
    public Key generate(PrimitiveKey key){
        return new Key(key, keyboard, mouse);
    }

    /** Listens for a key */
    public void listen(){
        listen(100);
    }

    /** Listens for a key
     * @param wait Milliseconds of wait before listening
     * */
    public void listen(int wait){
        klg.listen(wait);
    }

    /** @return The listened key */
    public Key getGenerated(){
        return klg.getGenerated();
    }

    /** Responsible for listening for keys */
    class KeyListenerGenerator extends Listener {

        /** The generated key */
        private Key generated;

        /** Default constructor */
        private KeyListenerGenerator(){
            super(keyboard, mouse);
        }

        /** Listens for a key
         * @param wait Milliseconds to wait before listening
         * */
        private void listen(int wait){
            new Thread() {
                public void run() {
                    try {
                        Thread.sleep(wait);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (generated != null)
                        generated = null;
                    while (true) {
                        System.out.print(""); //Needed because java is buggy
                        if (generated != null) {
                            this.stop();
                        }
                    }
                }
            }.start();
        }

        public Key getGenerated() {
            return generated;
        }

        @Override
        public void press(int keycode, KeyType type) {
            generated = generate(keycode, type);
        }
    }
}
