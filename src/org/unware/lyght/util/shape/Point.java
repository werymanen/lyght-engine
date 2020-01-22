package org.unware.lyght.util.shape;

public class Point{

    public int x, y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean isIn(Rect rect){
        return rect.contains(this);
    }

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