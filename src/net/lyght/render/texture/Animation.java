package net.lyght.render.texture;

import net.lyght.script.Script;

public class Animation extends Script {

    private int speed, index;
    private long lastTime, timer;
    private Texture[] textures;

    public Animation(int speed, Texture[] textures){
        this.speed = speed;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
        this.textures = textures;
    }

    public void tick(){
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if(timer > speed){
            index++;
            timer = 0;
            if(index >= textures.length)
                index = 0;
        }
    }

    public void render(){
        entity.setTexture(getCurrent());
    }

    public Texture getCurrent(){
        return textures[index];
    }

}