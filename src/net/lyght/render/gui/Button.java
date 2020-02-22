package net.lyght.render.gui;

import net.lyght.key.Listener;
import net.lyght.key.input.Mouse;
import net.lyght.render.Drawer;
import net.lyght.key.KeyType;
import net.lyght.render.texture.Texture;

/** Button element */
public class Button extends Element {

    /** Texture to be rendered */
    protected Texture texture;
    /** Mouse to detect when clicked */
    protected Mouse mouse;
    /** Listener to detect when clicked */
    protected ButtonListener bl;
    /** If the button is clicked */
    private boolean clicks;

    /** Default constructor
     * @param x X coordinate of the button
     * @param y Y coordinate of the button
     * @param width Width of the button
     * @param height Height of the button
     * @param texture Texture of the button
     * @param mouse Mouse to check if clicked
     * */
    public Button(int x, int y, int width, int height, Texture texture, Mouse mouse) {
        super(x, y, width, height);
        bl = new ButtonListener(mouse);
        this.texture = texture;
        this.mouse = mouse;
    }

    @Override
    public void render(Drawer d) {
        d.draw(0, 0, bounds.width, bounds.height, texture.getImage());
    }

    @Override
    public void tick() {
        clicks = false;
    }

    /** @return If the mouse is hovering */
    public boolean isHover(){
        return mouse.mouse.isIn(bounds);
    }

    /** @return If the button is hovered and clicked */
    public boolean isClick(){
        return isHover() && mouse.key(Mouse.left);
    }

    /** @return If the button is just clicked */
    public boolean isClicks(){
        return clicks;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Mouse getMouse() {
        return mouse;
    }

    public void setMouse(Mouse mouse) {
        this.mouse = mouse;
    }

    /** Listener to check if the button is clicked */
    class ButtonListener extends Listener {

        /** Default constructor
         * @param mouse Mouse to listen
         * */
        public ButtonListener(Mouse mouse) {
            super(null, mouse);
        }

        @Override
        public void press(int keycode, KeyType type) {
            if (type != KeyType.mouse || (keycode != Mouse.left && keycode != Mouse.right && keycode != Mouse.wheelButton))
                return;

            if(mouse.mouse.isIn(bounds))
                clicks = true;
        }
    }
}
