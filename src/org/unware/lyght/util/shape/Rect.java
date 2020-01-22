package org.unware.lyght.util.shape;

import java.awt.*;

public class Rect {

	public int x = 0, y = 0, width = 0, height = 0;

	public Rect() {

	}

	public Rect(Rectangle rectangle) {
		rectangle.x = x;
		rectangle.y = y;
		rectangle.width = width;
		rectangle.height = height;
	}

	public Rect(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	/** stolen from java.awt.Rectangle */
	public boolean contains(Rect rect) {
	    int thiswidth = this.width;
	    int thisheight = this.height;
	    int rectwidth = rect.width;
	    int rectheight = rect.height;
	    if (rectwidth <= 0 || rectheight <= 0 || thiswidth <= 0 || thisheight <= 0) {
	        return false;
	    }
	    int thisx = this.x;
	    int thisy = this.y;
	    int rectx = rect.x;
	    int recty = rect.y;
	    rectwidth += rectx;
	    rectheight += recty;
	    thiswidth += thisx;
	    thisheight += thisy;
	    //      overflow || intersect
	    return ((rectwidth < rectx || rectwidth > thisx) &&
	    		(rectheight < recty || rectheight > thisy) &&
	           	(thiswidth < thisx || thiswidth > rectx) &&
	           	(thisheight < thisy || thisheight > recty));
	}

	/** stolen from java.awt.Rectangle */
	public boolean contains(Point p){
		int w = this.width;
		int h = this.height;
		if ((w | h) < 0) {
			// At least one of the dimensions is negative...
			return false;
		}
		// Note: if either dimension is zero, tests below must return false...
		int x = this.x;
		int y = this.y;
		if (p.x < x || p.y < y) {
			return false;
		}
		w += x;
		h += y;
		//    overflow || intersect
		return ((w < x || w > p.x) &&
				(h < y || h > p.y));
	}

	public Rect add(Rect r){
		return new Rect(r.x + this.x, r.y + this.y, r.width, r.height);
	}

	public Rectangle getRectangle(){
		return new Rectangle(x, y, width, height);
	}

	public String toString(){
		return super.toString()+"[x:"+x+",y:"+y+",width:"+width+",height:"+height+"]";
	}

}
