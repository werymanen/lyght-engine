package net.lyght.util;

/** Returns a random number */
public class Random {

    /** Random number generator */
    public static final java.util.Random random = new java.util.Random();


    /** @return A random int
     * @param min The minimum value of the random number
     * @param max The maximum value of the random number
     * */
    public static int integer(int min, int max){
        return (int) ((flo() * max - min + 1) + min); //i dunno whats up, it works like this and its fine
    }

    /** @return A random int */
    public static int integer(){
         return random.nextInt();
     }


    /** @return A random double
     * @param min The minimum value of the random number
     * @param max The maximum value of the random number
     * */
    public static double doub(double min, double max){
         return (doub() * max - min + 1) + min;
     }

    /** @return A random double */
    public static double doub(){
         return random.nextDouble();
     }


    /** @return A random float
     * @param min The minimum value of the random number
     * @param max The maximum value of the random number
     * */
    public static float flo(float min, float max){
        return (flo() * max - min + 1) + min;
    }

    /** @return A random float */
    public static float flo(){
         return random.nextFloat();
     }


    /** @return A random long
     * @param min The minimum value of the random number
     * @param max The maximum value of the random number
     * */
    public static long lon(long min, long max){
        return (lon() * max - min + 1) + min;
    }

    /** @return A random long */
    public static long lon(){
         return random.nextLong();
     }


}