package org.unware.lyght.script.physics;

import org.unware.lyght.entity.Entity;
import org.unware.lyght.key.input.Keyboard;
import org.unware.lyght.key.input.Mouse;
import org.unware.lyght.key.Key;
import org.unware.lyght.key.KeyType;
import org.unware.lyght.render.Display;
import org.unware.lyght.script.Script;
import org.unware.lyght.util.shape.Rect;

public class Movement extends Script {

    private Key forward, left, back, right;
    private boolean keyMove;
    private int amount;

    private Rect bounds;

    public Movement(boolean keyMove, Key forward, Key left, Key back, Key right, int amount){
        this.forward = forward;
        this.left = left;
        this.back = back;
        this.right = right;
        this.keyMove = keyMove;
        this.amount = amount;
    }

    public Movement(){
        this(false, null, null, null, null, 0);
    }

    public Movement(Keyboard keyboard, Mouse mouse, boolean keyMove){
        this(
                keyMove,
                new Key(Keyboard.KEY_W, KeyType.keyboard, keyboard, mouse),
                new Key(Keyboard.KEY_A, KeyType.keyboard, keyboard, mouse),
                new Key(Keyboard.KEY_S, KeyType.keyboard, keyboard, mouse),
                new Key(Keyboard.KEY_D, KeyType.keyboard, keyboard, mouse),
                2
        );
    }

    public Movement(Display display, boolean keyMove){
        this(display.keyboard, display.mouse, keyMove);
    }

    public void tick(){
        if(keyMove) {
            if (forward.is())
                move(0, -amount);
            if (left.is())
                move(-amount, 0);
            if (back.is())
                move(0, amount);
            if (right.is())
                move(amount, 0);
        }
    }

    private void moveX(int x){
        if(x > 0)
            for (int i = 0; i < x; i++)
                move0(1, 0);

        else if(x == 0)
            return;
        else if(x < 0)
            for (int i = 0; i < - x; i++)
                move0(-1, 0);
    }

    private void moveY(int y){
        if(y > 0)
            for (int i = 0; i < y; i++)
                move0(0, 1);

        else if(y == 0)
            return;
        else if(y < 0)
            for (int i = 0; i < -y; i++)
                move0(0, -1);

    }

    public void move(int x, int y){
        moveX(x);
        moveY(y);
    }

    public void move(float angle, int amount){
        move((int) ((amount) * java.lang.Math.sin(angle)), (int) ((amount) * java.lang.Math.cos(angle)));
    }

    private void move0(int x, int y) {
        boolean right = false, left = false, up = false, down = false;

        Rect collBounds, thisBounds;

        thisBounds = entity.getActualBounds();

        if (entity.getContainer().getObjects().size() == 1) {
            if (x > 0)
                right = true;
            if (x < 0)
                left = true;
            if (y < 0)
                up = true;
            if (y > 0) {
                down = true;
            }
        }

        if (x > 0) {
            for (Entity e : entity.getContainer().getObjects()) {
                if (true) {

                    if (e == entity) {
                        continue;
                    }

                    collBounds = e.getActualBounds();

                    if (collBounds.x == thisBounds.x + thisBounds.width && collBounds.y < thisBounds.y + thisBounds.height && collBounds.y + collBounds.height > thisBounds.y && e.hasCollisionBorder()) {
                        return;
                    }
                    right = true;
                }
            }
        }


        if (x < 0) {
            for (Entity e : entity.getContainer().getObjects()) {
                if (true) {

                    if (e == entity) {
                        continue;
                    }

                    collBounds = e.getActualBounds();

                    if (collBounds.x + collBounds.width == thisBounds.x && collBounds.y < thisBounds.y + thisBounds.height && collBounds.y + collBounds.height > thisBounds.y && e.hasCollisionBorder()) {
                        return;
                    }
                    left = true;
                }
            }
        }


        if (y < 0) {
            for (Entity e : entity.getContainer().getObjects()) {
                if (true) {

                    if (e == entity) {
                        continue;
                    }

                    collBounds = e.getActualBounds();

                    if (collBounds.y + collBounds.height == thisBounds.y && collBounds.x < thisBounds.x + thisBounds.width && collBounds.x + collBounds.width > thisBounds.x && e.hasCollisionBorder()) {
                        return;
                    }
                    up = true;
                }
            }
        }


        if (y > 0) {
            for (Entity e : entity.getContainer().getObjects()) {
                if (true) {

                    if (e == entity) {
                        continue;
                    }

                    collBounds = e.getActualBounds();

                    if (collBounds.y == thisBounds.y + thisBounds.height && collBounds.x < thisBounds.x + thisBounds.width && collBounds.x + collBounds.width > thisBounds.x && e.hasCollisionBorder()) {
                        return;
                    }
                    down = true;
                }
            }
        }
        if ((right && left) || (up && down))
            return;
        if (right)
            entity.getBounds().x += x;
        if (left)
            entity.getBounds().x += x;
        if (up)
            entity.getBounds().y += y;
        if (down) {
            entity.getBounds().y += y;
        }
    }

    public Rect getBounds() {
        return bounds;
    }

    public void setBounds(Rect bounds) {
        this.bounds = bounds;
    }

}
