package org.unware.lyght.key.input;

import org.unware.lyght.key.KeyType;
import org.unware.lyght.key.Listener;
import org.unware.lyght.util.shape.Point;

import java.awt.event.*;
import java.util.ArrayList;

public class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener {

    private boolean[] keys;
    private boolean in;

    private ArrayList<Listener> listeners = new ArrayList<Listener>();

    public Point mouse = new Point(0, 0);

    public Mouse(){
        keys = new boolean[100];
    }

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

    @Override
    public void mouseClicked(MouseEvent e){
    }

    @Override
    public void mousePressed(MouseEvent e){
        keys[e.getButton()] = true;
        press(e.getButton());
    }

    @Override
    public void mouseReleased(MouseEvent e){
        keys[e.getButton()] = false;
        release(e.getButton());
    }

    private void press(int keycode){
        for(Listener l : listeners){
            l.press(keycode, KeyType.mouse);
        }
    }

    private void release(int keycode){
        for(Listener l : listeners){
            l.release(keycode, KeyType.mouse);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e){
        in = true;
    }

    @Override
    public void mouseExited(MouseEvent e){
        in = false;
    }

    @Override
    public void mouseDragged(MouseEvent e){
        mouse.set(e.getX(), e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e){
        mouse.set(e.getX(), e.getY());
    }

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

    public void addListener(Listener listener){
        if(listeners.contains(listener))
            return;
        listeners.add(listener);
    }

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

    public static final int left = 1;
    public static final int wheelButton = 2;
    public static final int right = 3;
    public static final int back = 4;
    public static final int forward = 5;
    public static final int wheelUp = 98;
    public static final int wheelDown = 99;

}
