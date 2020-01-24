package org.unware.lyght.project;

import org.unware.lyght.entity.Entity;
import org.unware.lyght.key.KeyGenerator;
import org.unware.lyght.key.KeyType;
import org.unware.lyght.key.PrimitiveKey;
import org.unware.lyght.key.input.Keyboard;
import org.unware.lyght.key.input.Mouse;
import org.unware.lyght.render.gui.Text;
import org.unware.lyght.main.Game;
import org.unware.lyght.main.Lyght;
import org.unware.lyght.render.texture.Texture;
import org.unware.lyght.render.texture.TextureRender;
import org.unware.lyght.script.Script;
import org.unware.lyght.script.physics.Movement;
import org.unware.lyght.sound.Sound;
import org.unware.lyght.util.Color;
import org.unware.lyght.util.Loader;
import org.unware.lyght.util.Random;
import org.unware.lyght.util.shape.Rect;

import java.awt.*;
import java.io.File;

public class Project extends Game {

    private Entity e;

    public Project(){
        e = new Entity(200, 200, 100, 150, Loader.load.resFileTexture("/dance.png"));
        e.addScript(new Movement(display, true));
        container.add(e);

        System.out.println(new PrimitiveKey(Keyboard.KEY_2, KeyType.keyboard).toString());
    }

    public void render(){
        renderer.render();
    }

    public void tick(){
    }

    public static void main(String[] args) {
        Lyght.lyght.main(new Project());
    }

}

