package net.lyght.key;

import net.lyght.key.input.Keyboard;
import net.lyght.key.input.Mouse;
import net.lyght.render.Display;

public class KeyGenerator {

    private Keyboard keyboard;
    private Mouse mouse;

    private KeyListenerGenerator klg;

    public KeyGenerator(Keyboard keyboard, Mouse mouse){
        this.keyboard = keyboard;
        this.mouse = mouse;
        klg = new KeyListenerGenerator();
    }

    public KeyGenerator(Display display){
        this(display.keyboard, display.mouse);
    }

    public KeyGenerator(Key key){
        this(key.getKeyboard(), key.getMouse());
    }

    public Key generate(int keycode, KeyType type){
        return new Key(keycode, type, keyboard, mouse);
    }

    public Key generate(PrimitiveKey key){
        return new Key(key, keyboard, mouse);
    }

    public void listen(){
        listen(100);
    }

    public void listen(int wait){
        klg.listen(wait);
    }

    public Key getGenerated(){
        return klg.getGenerated();
    }

    class KeyListenerGenerator extends Listener {

        private Key generated;

        private KeyListenerGenerator(){
            super(keyboard, mouse);
        }

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
                        System.out.print("");
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
