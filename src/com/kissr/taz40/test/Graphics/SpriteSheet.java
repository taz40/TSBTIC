package com.kissr.taz40.test.Graphics;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	int width;
	int height;
	public int[] pixels;
	String path;
	
	public static final SpriteSheet test = new SpriteSheet("/textures/board.png");
	public static final SpriteSheet X = new SpriteSheet("/textures/X.png");
	public static final SpriteSheet O = new SpriteSheet("/textures/O.png");
	
	public SpriteSheet(String path){
		this.path = path;
		load();
	}
	
	public void load(){
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			width = image.getWidth();
			height = image.getHeight();
			pixels = new int[width*height];
			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
