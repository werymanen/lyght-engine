package org.unware.lyght.project;

import org.unware.lyght.entity.Entity;
import org.unware.lyght.main.Game;
import org.unware.lyght.main.Lyght;
import org.unware.lyght.render.texture.Animation;
import org.unware.lyght.render.texture.Texture;
import org.unware.lyght.script.physics.Movement;
import org.unware.lyght.util.Color;

public class Project extends Game {

    private Entity e;

    public Project(){
        e = new Entity(200, 200, 100, 150);
        e.addScript(new Movement(display, true));
        e.addScript(new Animation(300, new Texture[]{new Texture(Color.orange), new Texture(Color.cyan), new Texture(Color.green)}));
        container.add(e);
    }

    public void render(){
    }

    public void tick(){
    }

    public static void main(String[] args) {
        Lyght.lyght.main(new Project());
    }

}

