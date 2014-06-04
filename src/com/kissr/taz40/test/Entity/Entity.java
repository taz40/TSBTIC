package com.kissr.taz40.test.Entity;

import com.kissr.taz40.test.Graphics.Screen;
import com.kissr.taz40.test.Graphics.Sprite;

public abstract class Entity {

	public int x, y, mx, my;
	public Sprite sprite;
	public boolean renders = true;
	public Screen screen;
	public boolean enabled = true;
	
	public Entity(int x, int y, Sprite sprite, Screen screen, boolean renders){
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		this.renders = renders;
		this.screen = screen;
	}
	
	public abstract void reset();
	
	public abstract void onUpdate();
	
	public abstract void onDraw();
	
	public void update(){
		if(enabled){
		onUpdate();
		x -= mx;
		y -= my;
		mx = 0;
		my = 0;
		}
	}
	
	public void render(){
		if(enabled){
		if(renders){
			screen.renderSprite(sprite, x, y, true);
		}
		onDraw();
		}
	}
	
}
