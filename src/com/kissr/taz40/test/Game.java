package com.kissr.taz40.test;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.kissr.taz40.test.Entity.Entity;
import com.kissr.taz40.test.Entity.mob.Player;
import com.kissr.taz40.test.Graphics.Screen;
import com.kissr.taz40.test.LevelStuff.Level;
import com.kissr.taz40.test.input.Keyboard;
import com.kissr.taz40.test.input.Mouse;

public class Game extends Canvas implements Runnable{
	
	private int width = 300;
	private int height = width / 16 * 9;
	private int scale = 3;
	JFrame frame;
	private final String TITLE = "test";
	private Thread gamethread;
	private boolean running = false;
	private Screen screen;
	private Keyboard key;
	private Player p;
	public ArrayList<Entity> entities = new ArrayList<Entity>();
	Level level;
	
	BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	public Game(){
		frame = new JFrame();
		frame.setTitle(TITLE);
		frame.setSize(new Dimension(width*scale, height*scale));
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		key = new Keyboard();
		this.addKeyListener(key);
		this.addMouseListener(new Mouse());
		frame.add(this);
		screen = new Screen(width, height, this);
		start();
	}
	
	public synchronized void start(){
		level = new Level("/Levels/testlevel.png");
		running = true;
		gamethread = new Thread(this, "Display");
		gamethread.start();
		
	}
	
	public synchronized void stop(){
		running = false;
		Thread stop = new Thread("stop"){
			public void run(){
		try {
			gamethread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame.dispose();
			}
		};
		stop.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		long lastTime = System.nanoTime();
		final double nsups = 1000000000.0 / 60.0;
		final double nsfps = 1000000000.0 / 200.0;
		double deltaups = 0;
		double deltafps = 0;
		long frames = 0;
		long updates = 0;
		long timer = System.currentTimeMillis();
		requestFocus();
		while(running){
			long now = System.nanoTime();
			deltaups += (now - lastTime) / nsups;
			deltafps += (now - lastTime) / nsfps;
			lastTime = now;
			while(deltaups >= 1){
				update();
				updates++;
				deltaups--;
			}
			while(deltafps >= 1){
				render();
				frames++;
				deltafps--;
			}
			
			if(System.currentTimeMillis() - timer >= 1000){
				timer += 1000;
				frame.setTitle(TITLE + " - " + updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
			
		}
	}
	
	public void update(){
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).update();
		}
		if(key.getKey(KeyEvent.VK_ESCAPE)){
			this.stop();
		}
	}
	
	public void render(){
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		screen.clear(Color.black.getRGB());
		screen.renderString("perfict", 10, 10, true, 0xffffffff);
		
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).render();
		}
		
		for(int i = 0; i < pixels.length; i++){
			pixels[i] = screen.pixels[i];
		}
		
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		
		g.dispose();
		bs.show();
	}
}
