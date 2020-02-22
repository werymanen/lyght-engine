package net.lyght.main;

import net.lyght.util.Debug;

import java.io.PrintStream;

/**
 * @author Manen
 */

/** Lyght Main */
public final class Lyght {

	/** Default constructor */
	private Lyght(){
	}

	/** Main instance */
	public static final Lyght lyght = new Lyght();

	/** Default game */
	private PrimitiveGame game = new PrimitiveGame() {
		@Override
		public void sec() {
			Debug.error("An error occurred");
		}
	};

	/** Lyght version */
	private static final float version = 2.301f;

	/** @return Raw version */
	public float getVersionFloat(){
		return version;
	}

	/** @return Version */
	public String getVersion(){
		return "Lyght " + getVersionFloat();
	}

	/** Default max Ticks per second and frame per second */
	private int maxTps = 50, maxFps = 60;

	/** If the game is running */
	private boolean running = true;

	/** If the tps and fps are controlled by maxTps */
	private boolean tpsControl = true;
	/** If the tps and fps are controlled by maxFps */
	private boolean fpsControl = true;

	/** Counts the amount of ticks in the current second */
	private int tps = 0;
	/** Counts the amount of frames in the current second */
	private int fps = 0;
	/** Counts the amount of seconds since the start of the game */
	private int sec = 0;

	/** Starts a game
	 * @param project The game to start
	 * */
	public void main(PrimitiveGame project){
		if(project == null)
			return;
		game = project;
		main();
	}

	/** Starts the game. Called when the game is set */
	private void main() {
		Debug.info("Copyright (c) 2020 werymanen");
		Debug.info(getVersion());

		sec();

		double timePerTick, timePerFrame;
		double delta = 0, frameDelta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;

		while(running) {
			timePerTick = 1000000000 / maxTps;
			timePerFrame = 1000000000 / maxFps;
			now = System.nanoTime();
			delta += (now-lastTime) / timePerTick;
			frameDelta += (now-lastTime) / timePerFrame;
			timer += now-lastTime;
			lastTime = now;

			if(delta >= 1) {
				if(tpsControl){
					tick();
					tps++;
				}
				delta--;
			}

			if(frameDelta >= 1){
				if(fpsControl) {
					render();
					fps++;
				}
				frameDelta--;
			}

			if(timer >= 1000000000) {
				sec();
				tps = 0;
				fps = 0;
				timer = 0;
				sec++;
			}
			if(!tpsControl){
				tick();
				fps++;
			}
			if(!fpsControl) {
				render();
				tps++;
			}
		}
		stop();

	}

	/** Enables logging to a file */
	public void enableLog(){
		try{
			psinit();
		}catch(Throwable e){
			e.printStackTrace();
		}
	}

	/** Disables logging to a file */
	public void disableLog(){
		try{
			psdisinit();
		}catch(Throwable e){
			e.printStackTrace();
		}
	}

	/** Original System.out */
	private PrintStream out0 = System.out;
	/** Original System.err */
	private PrintStream err0 = System.err;

	/** New System.out */
	private PrintStream out = null;
	/** New System.err */
	private PrintStream err = null;

	/** Resets System.out and System.err */
	private void psdisinit(){
		System.setOut(out0);
		System.setErr(err0);
	}

	/** Initializes logging and enables it */
	private void psinit() throws Throwable {
		String filename = ".log";
		if(out == null || err == null) {
			out = (new PrintStream(filename) {
				@Override
				public void write(int b) {
					super.write(b);
					out0.write(b);
				}

				@Override
				public void write(byte[] buf, int off, int len) {
					super.write(buf, off, len);
					out0.write(buf, off, len);
				}
			});
			err = (new PrintStream(filename) {
				@Override
				public void write(int b) {
					super.write(b);
					err0.write(b);
				}

				@Override
				public void write(byte[] buf, int off, int len) {
					super.write(buf, off, len);
					err0.write(buf, off, len);
				}
			});
		}

		System.setOut(out);
		System.setErr(err);
	}

	/** Stops Lyght */
	private void stop() {
		exit(0);
	}

	/** Calls the game to tick */
	private void tick() {
		new Thread("LyghtTick") {
			public void run() {
				game.tick();
				game.tick0();
			}
		}.start();
	}

	/** Calls the game to render */
	private void render(){
		new Thread("LyghtRender") {
			public void run() {
				game.render();
				game.render0();
			}
		}.start();
	}

	/** Calls the game to run sec */
	private void sec(){
		game.sec();
	}

	public PrimitiveGame get(){
		return game;
	}

	public int getFps(){
		return fps;
	}

	public int getTps(){
		return tps;
	}

	public int getSec(){
		return sec;
	}

	public int getMaxFps(){
		return Lyght.lyght.maxFps;
	}

	public int getMaxTps(){
		return Lyght.lyght.maxTps;
	}

	public void setMaxFps(int maxFps){
		Lyght.lyght.maxFps = maxFps;
	}

	public void setMaxTps(int maxTps){
		Lyght.lyght.maxTps = maxTps;
	}

	public boolean isFpsControl(){
		return Lyght.lyght.fpsControl;
	}

	public boolean isTpsControl(){
		return Lyght.lyght.tpsControl;
	}

	public void setFpsControl(boolean fpsControl){
		Lyght.lyght.fpsControl = fpsControl;
	}

	public void setTpsControl(boolean tpsControl){
		Lyght.lyght.tpsControl = tpsControl;
	}

	/** Enables or disables logging
	 * @param active If the new logging is on
	 * */
	public void setLog(boolean active){
		if(active)
			enableLog();
		else
			disableLog();
	}

	/** Stops Lyght
	 * @param status Status code
	 * */
	public void exit(int status) {
		game.stop();
		if(status == 0)
			exit(System.out, status);
		else
			exit(System.err, status);
	}

	public void exit(PrintStream out, int status){
		/*
		out.println("Lyght closed with code "+status);
		System.exit(status);
		running = false;
		*/
	}

}
