package org.unware.lyght.script;

import org.unware.lyght.entity.Entity;
import org.unware.lyght.util.LyghtException;

public class Script {

    protected Entity entity;

    public Script(){
    }

    public void tick(){}
    public void start(){}

    public final boolean add(Entity entity){
        if(this.entity != null){
            try {
                throw new LyghtException("This script is already in use.");
            }catch(LyghtException e) {
                e.printStackTrace();
                return false;
            }
        }

        this.entity = entity;
        start();

        return true;
    }

    public Entity getEntity() {
        return entity;
    }
}
