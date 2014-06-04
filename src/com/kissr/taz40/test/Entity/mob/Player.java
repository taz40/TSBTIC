package com.kissr.taz40.test.Entity.mob;

import java.awt.event.KeyEvent;

import com.kissr.taz40.test.Graphics.Screen;
import com.kissr.taz40.test.Graphics.Sprite;
import com.kissr.taz40.test.input.Keyboard;

public class Player extends Mob{
	
	private Keyboard key;
	
	public Player(int x, int y, Sprite sprite, Screen screen, Keyboard keyboard){
		super(x, y, sprite, screen, true);
		key = keyboard;
	}

	@Override
	public void onUpdate() {
		// TODO Auto-generated method stub
		if(key.getKey(KeyEvent.VK_W)){
			my++;
		}
		if(key.getKey(KeyEvent.VK_S)){
			my--;
		}
		if(key.getKey(KeyEvent.VK_A)){
			mx++;
		}
		if(key.getKey(KeyEvent.VK_D)){
			mx--;
		}
	}

	@Override
	public void onDraw() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
