package net.lyght.util;

public class Random {

    public static final java.util.Random random = new java.util.Random();


    public static int integer(int min, int max){
        return (int) ((flo() * max - min + 1) + min); //i dunno whats up, it works like this and its fine
     }

    public static int integer(){
         return random.nextInt();
     }


    public static double doub(double min, double max){
         return (doub() * max - min + 1) + min;
     }

    public static double doub(){
         return random.nextDouble();
     }


    public static float flo(float min, float max){
        return (flo() * max - min + 1) + min;
    }

    public static float flo(){
         return random.nextFloat();
     }


    public static long lon(long min, long max){
        return (lon() * max - min + 1) + min;
    }

    public static long lon(){
         return random.nextLong();
     }


}