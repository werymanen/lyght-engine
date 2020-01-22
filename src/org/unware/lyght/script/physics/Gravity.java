package org.unware.lyght.script.physics;

import org.unware.lyght.script.Script;

public class Gravity extends Script {

    private float amount;
    private int l;

    public void tick(){
        Movement mov = (Movement) (entity.getScript(Movement.class));
        amount += 0.15f;
        mov.move(0, (int) amount);

        if(l == entity.getY()){
            amount = 1;
        }

        l = entity.getY();
    }
}
