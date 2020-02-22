package net.lyght.key.input;

import net.lyght.key.Listener;
import net.lyght.key.KeyType;
import net.lyght.util.shape.Point;

import java.awt.event.*;
import java.util.ArrayList;

/** Responsible for mouse input */
public class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener {

    /** Arrays, that contain active keys
     * If a key is being pressed down, it is added to these arrays with the index of the keycode */
    private boolean[] keys;
    /** If the mouse is in the game frame */
    private boolean in;

    /** Listeners, that, well.. Listen for keys */
    private ArrayList<Listener> listeners = new ArrayList<Listener>();

    /** Location of the cursor */
    public Point mouse = new Point(0, 0);

    /** Default constructor */
    public Mouse(){
        keys = new boolean[100];
    }

    /** Event, called when the mouse wheel is moved */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e){
         if (e.getWheelRotation() == -1.0 ){
             keys[wheelUp] = true;
             press(Mouse.wheelUp);
         }
        if (e.getWheelRotation() == 1.0){
            keys[wheelDown] = true;
            press(Mouse.wheelDown);
        }
    }

    /** Event */
    @Override
    public void mouseClicked(MouseEvent e){
    }

    /** Event, called when a mouse button is pressed */
    @Override
    public void mousePressed(MouseEvent e){
        keys[e.getButton()] = true;
        press(e.getButton());
    }

    /** Event, called when a mouse button is released */
    @Override
    public void mouseReleased(MouseEvent e){
        keys[e.getButton()] = false;
        release(e.getButton());
    }

    /** Called when a button is pressed, calling listeners
     * @param keycode The keycode of the key that was pressed
     * */
    private void press(int keycode){
        for(Listener l : listeners){
            l.press(keycode, KeyType.mouse);
        }
    }

    /** Called when a button is released, calling listeners
     * @param keycode The keycode of the key that was released
     * */
    private void release(int keycode){
        for(Listener l : listeners){
            l.release(keycode, KeyType.mouse);
        }
    }

    /** Event, called when the cursor enters the frame */
    @Override
    public void mouseEntered(MouseEvent e){
        in = true;
    }

    /** Event, called when the cursor exits the frame */
    @Override
    public void mouseExited(MouseEvent e){
        in = false;
    }

    /** Event, called when the cursor drags on the frame */
    @Override
    public void mouseDragged(MouseEvent e){
        mouse.set(e.getX(), e.getY());
    }

    /** Event, called when the cursor is moved */
    @Override
    public void mouseMoved(MouseEvent e){
        mouse.set(e.getX(), e.getY());
    }

    /** Resets the wheel buttons
     * (Because you can't hold the wheel buttons) */
    public void reset(){
        keys[Mouse.wheelUp] = false;
        keys[Mouse.wheelDown] = false;
    }

    public boolean isIn(){
        return in;
    }

    public boolean key(int key){
        return keys[key];
    }

    /** Adds a listener
     * @param listener Listener to be added
     * */
    public void addListener(Listener listener){
        if(listeners.contains(listener))
            return;
        listeners.add(listener);
    }

    /** Converts a keycode to string
     * @param keyCode The keycode to be converted
     * */
    public static String getName(int keyCode){
        switch (keyCode){
            case(left):
                return "left";

            case(wheelButton):
                return "wheelButton";

            case(right):
                return "right";

            case(back):
                return "back";

            case(forward):
                return "forward";

            case(wheelUp):
                return "wheelUp";

            case(wheelDown):
                return "wheelDown";
        }
        return "unknown";
    }

    /** The left or main button on the mouse */
    public static final int left = 1;

    /** The middle or wheel button on the mouse */
    public static final int wheelButton = 2;

    /** The right or secondary button on the mouse */
    public static final int right = 3;

    /** The back button usually found on gaming mice */
    public static final int back = 4;

    /** The forward button usually found on gaming mice */
    public static final int forward = 5;

    /** The wheel up on the scrolling wheel */
    public static final int wheelUp = 98;

    /** The wheel down on the scrolling wheel */
    public static final int wheelDown = 99;

}
