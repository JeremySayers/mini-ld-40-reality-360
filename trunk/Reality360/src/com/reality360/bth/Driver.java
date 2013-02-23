package com.reality360.bth;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.reality360.resource.Level;

public class Driver extends Level{

	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	
	
	public void paint(Graphics g) {
		
	}
	public void tick() {
		for(Enemy e:enemies){
			if(!e.isAlive())enemies.remove(e);
		}
		for(Bullet b:bullets){
			if(!b.isAlive())bullets.remove(b);
		}
		
	}
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
			case KeyEvent.VK_UP: break;
			case KeyEvent.VK_DOWN: break;
			case KeyEvent.VK_LEFT: break;
			case KeyEvent.VK_RIGHT: break;
			case KeyEvent.VK_SPACE: break;
		}
	}
	public void keyReleased(KeyEvent e) {
		
	}
	public void mousePressed(MouseEvent e) {
		
	}
	public void mouseReleased(MouseEvent e) {
		
	}
}
