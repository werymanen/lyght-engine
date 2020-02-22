package net.lyght.script.physics;

import net.lyght.entity.Entity;
import net.lyght.key.input.Keyboard;
import net.lyght.key.input.Mouse;
import net.lyght.render.Display;
import net.lyght.script.Script;
import net.lyght.util.shape.Rect;
import net.lyght.key.Key;
import net.lyght.key.KeyType;

/** Responsible for movement
 * You would've never guessed right? */
public class Movement extends Script {

    /** Keys for input movement */
    private Key forward, left, back, right;
    /** If key movement is active */
    private boolean keyMove;
    /** The amount of pixels the entity moves for one keypress */
    private int amount;

    /** Collision bounds */
    private Rect bounds;

    /** Default constructor
     * @param keyMove If the entity needs to move on keypresses
     * @param forward The key which the entity will be going forward
     * @param left The key which the entity will be going left
     * @param back The key which the entity will be going back
     * @param right The key which the entity will be going right
     * @param amount The amount of pixels that the entity moves on a keypress
     * */
    public Movement(boolean keyMove, Key forward, Key left, Key back, Key right, int amount){
        this.forward = forward;
        this.left = left;
        this.back = back;
        this.right = right;
        this.keyMove = keyMove;
        this.amount = amount;
    }

    /** Secondary constructor */
    public Movement(){
        this(false, null, null, null, null, 0);
    }

    /** Tertiary constructor
     * Will assign letters W, A, S, D
     * @param keyboard Keyboard
     * @param mouse Mouse
     * @param keyMove If keypress movement is active
     * */
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

    /** Quaternary constructor
     * @param display The display that contains the keyboard and mouse
     * @param keyMove If keypress movement is active
     * */
    public Movement(Display display, boolean keyMove){
        this(display.keyboard, display.mouse, keyMove);
    }

    /** Input movement on tick */
    @Override
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

    /** Moves towards the X axis
     * @param x Amount of move
     * */
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

    /** Moves towards the Y axis
     * @param y Amount of move
     * */
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

    /** Moves
     * @param x The amount of move to the Y direction
     * @param y The amount of move to the Y direction
     * */
    public void move(int x, int y){
        moveX(x);
        moveY(y);
    }

    /** Moves in a specific angle
     * @param angle The angle to go in
     * @param amount The amount of pixels to go
     * */
    public void move(float angle, int amount){
        move((int) ((amount) * java.lang.Math.sin(angle)), (int) ((amount) * java.lang.Math.cos(angle)));
    }

    /** Actually moves
     * @param x Amount of movement to the X coordinate
     * @param y Amount of movement to the Y coordinate
     * */
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
