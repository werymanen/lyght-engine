package net.lyght.render.gui;

import net.lyght.key.Listener;
import net.lyght.key.input.Mouse;
import net.lyght.render.Drawer;
import net.lyght.key.KeyType;
import net.lyght.render.texture.Texture;

public class Button extends Element {

    protected Texture texture;
    protected Mouse mouse;
    protected ButtonListener bl;
    private boolean clicks;

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

    public boolean isHover(){
        return mouse.mouse.isIn(bounds);
    }
    public boolean isClick(){
        return isHover() && mouse.key(Mouse.left);
    }

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

    class ButtonListener extends Listener {

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
