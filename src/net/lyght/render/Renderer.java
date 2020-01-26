package net.lyght.render;

import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import net.lyght.entity.Entity;
import net.lyght.key.input.Cursor;
import net.lyght.util.Color;
import net.lyght.render.gui.Element;
import net.lyght.render.texture.Texture;

public class Renderer {

	private Display display;
	private BufferStrategy bs;
	private Drawer g;
	private Cursor cursor;
	private Texture background;

	private Container container = new Container(this);
	private ArrayList<Element> elements = new ArrayList<>();

	public Renderer(Display display){
		background = new Texture(Color.darkGray);
		this.display = display;
	}

	public void render() {
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = new AwtDrawer(bs.getDrawGraphics());

		g.clearRect(0, 0, display.getWidth(), display.getHeight());

		g.fillRect(0, 0, display.getWidth(), display.getHeight(), Color.black);
		g.draw(0, 0, display.getWidth(), display.getHeight(), background);

		for (int i = 0; i < elements.size(); i++){
			Element e = elements.get(i);
			e.render(g.crop(e.getX(), e.getY()));
		}

		if(cursor != null) {
			g.draw(display.mouse.mouse.x - cursor.width / 2, display.mouse.mouse.y - cursor.height / 2, cursor.width, cursor.height, cursor.texture);
		}

		bs.show();

		if(g instanceof AwtDrawer){
			AwtDrawer awtd = (AwtDrawer) (this.g);
			awtd.dispose();
		}
	}

	public void tick(){
		for (int i = 0; i < container.getObjects().size(); i++){
			Entity e = container.getObjects().get(i);
			if(!e.isActive())
				continue;
			e.tick();
		}

		for (int i = 0; i < elements.size(); i++){
			Element e = elements.get(i);
			e.tick();
		}
	}

	public Cursor add(Cursor cursor){
		this.cursor = cursor;
		return cursor;
	}

	public Element add(Element element){
		elements.add(element);
		return element;
	}

	public void remove(Element element){
		elements.remove(element);
	}

	public void clearElements(){
		elements.clear();
	}

	public Display getDisplay(){
		return display;
	}

	public Container getContainer() {
		return container;
	}

	public void setBackground(Texture texture){
		background = texture;
	}

	public Texture getBackground(){
		return background;
	}
}
