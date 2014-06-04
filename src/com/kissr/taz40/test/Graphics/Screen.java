package com.kissr.taz40.test.Graphics;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.kissr.taz40.test.Game;
import com.kissr.taz40.test.LevelStuff.Level;

public class Screen {
	int width;
	int height;
	public int[] pixels;
	public int xOffset, yOffset;
	public Game game;
	
	public Screen(int width, int height, Game g){
		game = g;
		this.width = width;
		this.height = height;
		pixels = new int[width*height];
	}
	
	public void clear(int color){
		for(int i = 0; i < pixels.length; i++){
			pixels[i] = color;
		}
	}
	
	public void renderSprite(Sprite sprite, int xa, int ya, boolean fixed){
		if(fixed){
			xa -= xOffset;
			ya -= yOffset;
		}
		for(int x = 0; x < sprite.width; x++){
			for(int y = 0; y < sprite.height; y++){
				if((x+xa) < 0 || (x+xa >= width) || (y+ya) < 0 || (y+ya) >= height) continue;
				int col = sprite.pixels[x+y*sprite.width];
				if(col != 0xffff00ff) pixels[(x+xa)+(y+ya)*width] = col;
			}
		}
	}
	
	public void renderLevel(Level level, int xa, int ya){
			xa -= xOffset;
			ya -= yOffset;
		for(int x = 0; x < (level.width)*16; x++){
			for(int y = 0; y < (level.height)*16; y++){
				if((x+xa) < 0 || (x+xa >= width) || (y+ya) < 0 || (y+ya) >= height) continue;
				int col = level.pixels[x+y*(level.width*16)];
				if(col != 0xffff00ff) pixels[(x+xa)+(y+ya)*width] = col;
			}
		}
	}
	
	public void renderString(String text, int xp, int yp, boolean fixed, int col1){
		if(fixed){
			xp -= xOffset;
			yp -= yOffset;
		}
		
		FontMetrics fm = game.getFontMetrics(game.getFont());
		int stringheight = fm.getHeight();
		int stringwidth = fm.stringWidth(text);
		BufferedImage image = new BufferedImage(stringwidth, stringheight, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		if(col1 == 0xff000000) col1 = 0xff010101;
		g.setColor(new Color(col1));
		g.drawString(text, 0, stringheight-5);
		g.dispose();
		
		int[] textpixels = new int[stringheight*stringwidth];
		image.getRGB(0, 0, stringwidth, stringheight, textpixels, 0, stringwidth);
		
		for(int y = 0; y < stringheight; y++){
			for(int x = 0; x < stringwidth; x++){
				int xa = x+xp;
				int ya = y+yp;
				if(xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				int col = textpixels[x+y*stringwidth];
				if(col == 0xff000000) col = 0xffff00ff;
				if(col != 0xffff00ff) pixels[xa+ya*width] = col;
			}
		}
	}
	
}
