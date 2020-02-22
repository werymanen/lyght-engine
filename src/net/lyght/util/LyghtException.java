package net.lyght.util;

import net.lyght.main.Lyght;

/** An exception for Lyght */
public class LyghtException extends Exception {

    /** Default constructor
     * @param text The text of the exception
     * @param exit If the exception needs to exit
     * */
    public LyghtException(String text, boolean exit){
        super(text);
        if(exit)
            Lyght.lyght.exit(-1);
    }

    /** Secondary constructor
     * @param text The text of the exception
     * Note: Lyght will exit
     * */
    public LyghtException(String text){
        this(text, true);
    }
}
