package net.lyght.util;

/** Represents a visible color */
public class Color {

    /** The red value of the color */
    private int r = 0;
    /** The green value of the color */
    private int g = 0;
    /** The blue value of the color */
    private int b = 0;

    /** Converts to an awt color */
    public java.awt.Color toColor(){
        return new java.awt.Color(r, g, b);
    }

    /** Default constructor
     * @param r Red value
     * @param g Green value
     * @param b Blue value
     * */
    public Color(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
        check();
    }

    /** Secondary constructor
     * Converts itself from an awt color
     * @param color The awt color
     * */
    public Color(java.awt.Color color) {
        r = color.getRed();
        g = color.getGreen();
        b = color.getBlue();
        check();
    }

    /** Checks if the color is invalid */
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
