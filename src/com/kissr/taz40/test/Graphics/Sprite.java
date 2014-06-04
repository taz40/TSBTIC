package com.kissr.taz40.test.Graphics;

public class Sprite {

	public int width;
	public int height;
	int x;
	int y;
	SpriteSheet sheet;
	public int[] pixels;
	
	public static final Sprite board = new Sprite(SpriteSheet.test, 0, 0, 66, 66);
	public static final Sprite X = new Sprite(SpriteSheet.X, 0, 0, 16, 16);
	public static final Sprite O = new Sprite(SpriteSheet.O, 0, 0, 16, 16);
	public static final Sprite clear = new Sprite(0xffff00ff, 16, 16);
	public static final Sprite voidsprite = new Sprite(0xff000000, 16, 16);
	
	public Sprite(SpriteSheet sheet, int x, int y, int width, int height){
		this.sheet = sheet;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		load();
	}
	
	public Sprite(int color, int width, int height){
		this.width = width;
		this.height = height;
		this.x = 0;
		this.y = 0;
		this.sheet = null;
		pixels = new int[width*height];
		for(int i = 0; i < pixels.length; i++){
			pixels[i] = color;
		}
	}
	
	public void load(){
		pixels = new int[width*height];
		for(int px = 0; px < width; px++){
			for(int py = 0; py < height; py++){
				pixels[px+py*width] = sheet.pixels[(px+(width*x))+(py+(height*y))*sheet.width];
			}
		}
	}
	
}
