package net.lyght.render;

import net.lyght.key.input.Keyboard;
import net.lyght.key.input.Mouse;
import net.lyght.main.Lyght;
import net.lyght.util.shape.Rect;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.*;


public class Display {

	private JFrame frame;
	private Canvas canvas;

	public Keyboard keyboard;
	public Mouse mouse;

	private net.lyght.key.input.Cursor cursor;

	private int width = 1280;
	private int height = 720;
	private final String title = "Lyght";

	public Canvas getCanvas() {
		return canvas;
	}

	public Renderer getRenderer() {
		return r;
	}

	private Renderer r;

	public Display(int width, int height, String title, BufferedImage icon){
		this(width, height, icon);
		setTitle(title);
	}
	public Display(int width, int height, BufferedImage icon){
		this(width, height);
		setIcon(icon);
	}
	public Display(BufferedImage icon){
		this();
		setIcon(icon);
	}
	public Display(String title, BufferedImage icon){
		this(icon);
		setTitle(title);
	}
	public Display(String title){
		this();
		setTitle(title);
	}
	public Display(int width, int height, String title){
		this(width, height);
		setTitle(title);
	}
	public Display(int width, int height){
		this();
		setSize(width, height);
	}

	public Display() {
		keyboard = new Keyboard();
		mouse = new Mouse();
		attachRenderer(new Renderer(this));

		frame = new JFrame(title);
		frame.setResizable(true);
		frame.setBounds(new Rectangle(200, 200, width, height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(keyboard);


		canvas = new Canvas();
		canvas.setBounds(new Rectangle(0, 0, width, height));
		canvas.setFocusable(false);

		frame.addMouseListener(mouse);
		frame.addMouseMotionListener(mouse);
		frame.addMouseWheelListener(mouse);
		canvas.addMouseListener(mouse);
		canvas.addMouseMotionListener(mouse);
		canvas.addMouseWheelListener(mouse);

		frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                Lyght.lyght.exit(0);
            }
        });

		frame.add(canvas);

		frame.setVisible(true);
	}

	public void addCursor(net.lyght.key.input.Cursor cursor){
		if(cursor == null ||cursor == this.cursor)
			return;

		if(this.cursor == null) {
			BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

			java.awt.Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
					cursorImg, new Point(0, 0), "blank cursor");

			frame.getContentPane().setCursor(blankCursor);
		}

		this.cursor = cursor;
		r.add(cursor);
	}

	public void attachRenderer(Renderer r) {
		this.r = r;
	}

	public void tick(){
		mouse.reset();
		keyboard.tick();

		width = frame.getWidth();
		height = frame.getHeight();
	}

	public void setTitle(String title) {
		frame.setTitle(this.title + " | " + title);
	}

	public void setIcon(BufferedImage img){
		frame.setIconImage(img);
	}

	public BufferedImage getIcon(){
		return (BufferedImage) frame.getIconImage();
	}

	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	public void setWidth(int width) {
		frame.setBounds(frame.getX(), frame.getY(), width, frame.getHeight());
		this.width = width;
	}
	public void setHeight(int height) {
		frame.setBounds(frame.getX(), frame.getY(), frame.getWidth(), height);
		this.height = height;
	}
	public void setSize(int width, int height){
		setWidth(width);
		setHeight(height);
	}
	public void setSize(int x, int y, int width, int height){
		setSize(width, height);
		setPos(x, y);
	}
	public void setSize(Rect rect){
		setSize(rect.x, rect.y, rect.width, rect.height);
	}
	public void setPos(Point pos){
		setPos(pos.x, pos.y);
	}
	public void setPos(int x, int y){
		setX(x);
		setY(y);
	}
	public void setX(int x){
		frame.setBounds(x, frame.getY(), frame.getWidth(), frame.getHeight());
	}
	public void setY(int y){
		frame.setBounds(frame.getX(), y, frame.getWidth(), frame.getHeight());
	}
}
