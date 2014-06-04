package com.kissr.taz40.test.LevelStuff;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.kissr.taz40.test.Graphics.Sprite;

public class Level {
	public String path;
	public int width;
	public int height;
	public int[] pixels;
	
	public Level(String path){
		this.path = path;
		load(path);
	}
	
	public void load(String path){
		try {
			BufferedImage image =  ImageIO.read(Level.class.getResource(path));
			width = image.getWidth();
			height = image.getHeight();
			pixels = new int[(width*16)*(height*16)];
			int[] tiles = new int[width*height];
			image.getRGB(0, 0, width, height, tiles, 0, width);
			
			for(int i = 0; i < tiles.length; i++){
				Sprite sprite = Sprite.voidsprite;
				if(tiles[i] == 0xffff0000){
					sprite = Sprite.X;
				}
				if(tiles[i] == 0xff0000ff){
					sprite = Sprite.O;
				}
				for(int px = 0; px < sprite.width; px++){
					for(int py = 0; py < sprite.height; py++){
						int x = (i % width);
						int y = ((i - x) / width);
						pixels[(px + x*16)+(py + y*16)*(width*16)] = sprite.pixels[px+py*sprite.width];
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
