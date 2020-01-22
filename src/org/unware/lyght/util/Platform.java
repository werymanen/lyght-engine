package org.unware.lyght.util;

public enum Platform {

    java,
    android;

    public static final Platform platform = getPlatform();

    public static Platform getPlatform(){
        if(isWindows() || isMac() || isSolaris() || (isUnix() && !isAndroid()))
            return java;

        if(isAndroid())
            return android;

        try {
            throw new LyghtException("Seems like you are running a not supported Operating System.");
        }catch(LyghtException e){
            e.printStackTrace();
            return null;
        }
    }


    public static boolean isWindows() {
        return (System.getProperty("os.name").indexOf("win") >= 0);
    }

    public static boolean isMac() {
        return (System.getProperty("os.name").indexOf("mac") >= 0);
    }

    public static boolean isAndroid(){
        boolean androidClass;
        try{
            Class.forName("android.os.Build");
            androidClass = true;
        }catch(ClassNotFoundException e){
            androidClass = false;
        }

        return isUnix() && androidClass;
    }

    public static boolean isUnix() {
        return (System.getProperty("os.name").indexOf("nix") >= 0 || System.getProperty("os.name").indexOf("nux") >= 0 || System.getProperty("os.name").indexOf("aix") > 0 );
    }

    public static boolean isSolaris() {
        return (System.getProperty("os.name").indexOf("sunos") >= 0);
    }

}
