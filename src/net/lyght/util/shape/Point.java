package net.lyght.util.shape;

/** Literally just two points */
public class Point{

    /** X and Y coordinates */
    public int x, y;

    /** Default constructor
     * @param x X coordinate
     * @param y Y coordinate
     * */
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    /** @return If the point is in the rect
     * @param rect The rect */
    public boolean isIn(Rect rect){
        return rect.contains(this);
    }

    /** Sets both the X and Y coordinate */
    public void set(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString(){
        return super.getClass().getName() + "[x=" + x + " y=" + y + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Point))
            return false;

        Point p = (Point) obj;

        return p.x == this.x && p.y == this.y;
    }
}