package net.lyght.render.texture;

import net.lyght.script.Script;

/** Animated texture */
public class Animation extends Script {

    /** The speed of the animation in milliseconds, the index of current texture */
    private int speed, index;
    /** The last run time, timer */
    private long lastTime, timer;
    /** Textures that switch */
    private Texture[] textures;

    /** Default constructor
     * @param speed The speed of the animation in milliseconds
     * @param textures The textures
     * */
    public Animation(int speed, Texture[] textures){
        this.speed = speed;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
        this.textures = textures;
    }

    @Override
    public void tick(){ // TODO: 2020. 02. 22. this would probably go to render if i knew what im doing
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if(timer > speed){
            index++;
            timer = 0;
            if(index >= textures.length)
                index = 0;
        }
    }

    @Override
    public void render(){
        entity.setTexture(getCurrent());
    }

    /** @return The current texture */
    public Texture getCurrent(){
        return textures[index];
    }

}