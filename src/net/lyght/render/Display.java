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

/** Responsible for making a frame and allowing others to draw in it */
public class Display {

	/** The frame */
	private JFrame frame;
	/** The canvas we're drawing to */
	private Canvas canvas;

	/** Keyboard instance */
	public Keyboard keyboard;
	/** Mouse instance */
	public Mouse mouse;

	/** Cursor */
	private net.lyght.key.input.Cursor cursor;

	/** Width of the frame */
	private int width = 1280;
	/** Height of the frame */
	private int height = 720;
	/** Title of the frame */
	private final String title = "Lyght";

	public Canvas getCanvas() {
		return canvas;
	}

	public Renderer getRenderer() {
		return r;
	}

	/** Renderer */
	private Renderer r;

	/** Default constructor */
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

	/** Sets the cursor to render
	 * @param cursor New cursor
	 * */
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

	/** Attaches renderer
	 * @param r Renderer to attach
	 * */
	public void attachRenderer(Renderer r) {
		this.r = r;
	}

	/** Runs given times a second */
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

	/** Sets the size of the frame
	 * @param width New width
	 * @param height New height
	 * */
	public void setSize(int width, int height){
		setWidth(width);
		setHeight(height);
	}

	/** Sets the size of the frame
	 * @param x New X coordinate
	 * @param y New Y coordinate
	 * @param width New width
	 * @param height New height
	 * */
	public void setSize(int x, int y, int width, int height){
		setSize(width, height);
		setPos(x, y);
	}

	/** Sets the size of the frame
	 * @param rect The new bounds of the frame
	 * */
	public void setSize(Rect rect){
		setSize(rect.x, rect.y, rect.width, rect.height);
	}

	/** Sets the position of the frame
	 * @param pos New position
	 * */
	public void setPos(Point pos){
		setPos(pos.x, pos.y);
	}

	/** Sets the position of the frame
	 * @param x New X coordinates
	 * @param y New Y coordinates
	 * */
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
