package org.unware.lyght.util;

public class Random {

     public static int integer(int min, int max){
        return (int)(integer() * max - min + 1) + min;
     }

     public static int integer(){
         return (int) doub();
     }

     public static double doub(double min, double max){
         return (doub() * max - min + 1) + min;
     }

     public static double doub(){
         return Math.random();
     }

     public static float flo(){
         return (float) doub();
     }

     public static float flo(float min, float max){
         return (int)(flo() * max - min + 1) + min;
     }

     public static long lon(){
         return (long) doub();
     }

     public static long lon(long min, long max){
         return (int)(lon() * max - min + 1) + min;
     }

}