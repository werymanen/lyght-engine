package net.lyght.util;

/** Responsible for debug stuff */
public class Debug {

    /** Logs to the console
     * @param message The log message
     * */
    public static void log(String message) {
        System.out.println("LOG > " + message);
    }

    /** Logs info to the console
     * @param message The info message
     * */
    public static void info(String message) {
        System.out.println("INFO > " + message);
    }

    /** Logs debug to the console
     * @param message The debug message
     * */
    public static void debug(String message) {
        System.out.println("DEBUG > " + message);
    }

    /** Logs warning to the console
     * @param message The warning message
     * */
    public static void warn(String message) {
        System.out.println((char)27 + "[33m" + "WARNING > " + message + (char)27 + "[0m");
    }

    /** Logs error to the console
     * @param message The error message
     * */
    public static void error(String message) {
        System.err.println("ERROR > " + message);
    }

}
