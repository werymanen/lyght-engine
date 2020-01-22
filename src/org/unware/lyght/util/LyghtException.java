package org.unware.lyght.util;

import org.unware.lyght.main.Lyght;

public class LyghtException extends Exception {

    public LyghtException(String text, boolean exit){
        super(text);
        if(exit)
            Lyght.lyght.exit(-1);
    }

    public LyghtException(String text){
        this(text, true);
    }
}
