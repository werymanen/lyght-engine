package net.lyght.util;

/** Responsible for telling the platform */
public enum Platform {

    /** If Lyght is running on java */
    java,
    /** If Lyght is running on Android */
    android;

    /** Current platform */
    public static final Platform platform = getPlatform();

    /** @return The current */
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

    /** @return If the current operating system is Windows */
    public static boolean isWindows() {
        return (System.getProperty("os.name").indexOf("win") >= 0);
    }

    /** @return If the current operating system is Mac */
    public static boolean isMac() {
        return (System.getProperty("os.name").indexOf("mac") >= 0);
    }

    /** @return If the current operating system is Android */
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

    /** @return If the current operating system is Unix */
    public static boolean isUnix() {
        return (System.getProperty("os.name").indexOf("nix") >= 0 || System.getProperty("os.name").indexOf("nux") >= 0 || System.getProperty("os.name").indexOf("aix") > 0 );
    }

    /** @return If the current operating system is Solaris */
    public static boolean isSolaris() {
        return (System.getProperty("os.name").indexOf("sunos") >= 0);
    }

}
