package net.lyght.util;

public class Debug {

    public static void log(String message) {
        System.out.println("LOG > " + message);
    }

    public static void info(String message) {
        System.out.println("INFO > " + message);
    }

    public static void debug(String message) {
        System.out.println("DEBUG > " + message);
    }

    public static void warn(String message) {
        System.out.println((char)27 + "[33m" + "WARNING > " + message + (char)27 + "[0m");
    }

    public static void error(String message) {
        System.err.println("ERROR > " + message);
    }

}
