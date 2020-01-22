package org.unware.lyght.util;

public class Color {

    private int r = 0;
    private int g = 0;
    private int b = 0;

    public java.awt.Color getColor(){
        return new java.awt.Color(r, g, b);
    }

    public Color(java.awt.Color color) {
        r = color.getRed();
        g = color.getGreen();
        b = color.getBlue();
        check();
    }

    public Color(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
        check();
    }

    private void check() {
        if (r > 255 || g > 255 || b > 255) {
            try {
                throw new LyghtException("Your beautiful rainbow (" + toString() + ")'s any color can't be higher than 255, it is the limit!");
            }catch(LyghtException e){
                e.printStackTrace();
            }
        }
    }

    public int getR(){
        return r;
    }

    public int getG(){
        return g;
    }

    public int getB(){
        return b;
    }

    @Override
    public String toString(){
        return super.toString() + "[red:" + r + " green:" + g + " blue:" + b + "]";
    }

    public static final Color white = new Color(255, 255, 255);
    public static final Color lightGray = new Color(192, 192, 192);
    public static final Color gray = new Color(128, 128, 128);
    public static final Color darkGray = new Color(64, 64, 64);
    public static final Color black = new Color(0, 0, 0);
    public static final Color red = new Color(255, 0, 0);
    public static final Color pink = new Color(255, 175, 175);
    public static final Color orange = new Color(255, 145, 0);
    public static final Color yellow = new Color(255, 255, 0);
    public static final Color green = new Color(0, 200, 0);
    public static final Color magenta = new Color(255, 0, 255);
    public static final Color cyan = new Color(0, 255, 255);
    public static final Color blue = new Color(0, 0, 255);

    public static final Color green0 = new Color(0, 255, 0);
    public static final Color orange0 = new Color(255, 200, 0);

}
