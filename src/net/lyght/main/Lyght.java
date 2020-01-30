package net.lyght.main;

import java.io.PrintStream;

/**
 * @author Unware
 * @author Manen
 */

public final class Lyght {

	private Lyght(){
	}

	public static final Lyght lyght = new Lyght();

	private PrimitiveGame game = new PrimitiveGame() {
		@Override
		public void sec() {
			System.err.println("An error occurred");
		}
	};

	private static final float version = 2.3f;

	public float getVersionFloat(){
		return version;
	}

	public String getVersion(){
		return "Lyght " + getVersionFloat();
	}

	private int maxTps = 50, maxFps = 60;

	private boolean running = true;

	private boolean fpsControl = true;
	private boolean tpsControl = true;

	private int tps = 0;
	private int fps = 0;
	private int sec = 0;

	public void main(PrimitiveGame project){
		if(project == null)
			return;
		game = project;
		main();
	}

	private void main() {
		System.out.println("Copyright (c) 2020 werymanen");
		System.out.println(getVersion());

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

	public void enableLog(){
		try{
			psinit();
		}catch(Throwable e){
			e.printStackTrace();
		}
	}

	public void disableLog(){
		try{
			psdisinit();
		}catch(Throwable e){
			e.printStackTrace();
		}
	}

	private PrintStream out0 = System.out;
	private PrintStream err0 = System.err;

	private PrintStream out = null;
	private PrintStream err = null;

	private void psdisinit(){
		System.setOut(out0);
		System.setErr(err0);
	}

	private void psinit() throws Throwable{
		String filename = "lyght.log";
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

	private void stop() {
		exit(0);
	}

	private void tick() {
		new Thread("LyghtTick") {
			public void run() {
				game.tick();
				game.tick0();
			}
		}.start();
	}

	private void render(){
		new Thread("LyghtRender") {
			public void run() {
				game.render();
				game.render0();
			}
		}.start();
	}

	private void sec(){
		game.sec();
	}

	public void set(Game game) {
		this.game = game;
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

	public void setLog(boolean active){
		if(active)
			enableLog();
		else
			disableLog();
	}

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
