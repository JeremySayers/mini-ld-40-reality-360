package com.reality360.bth;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.reality360.resource.Entity;

public class PlayerShip extends Entity{

	private BufferedImage img = null;
	private int width = 0;
	private int height = 0;
	private int xPos = 0;
	private int yPos = 0;
	private int moveX = 0;
	private int moveY = 0;
	private long tickCount = 0;
	private boolean isAlive = false;
	
	public PlayerShip(){
		
	}
	
	public int getX() {
		return xPos;
	}
	public int getY() {
		return yPos;
	}
	public boolean isAlive() {
		return false;
	}
	public void keyPressed(KeyEvent e) {
		
	}
	public void keyReleased(KeyEvent e) {
		
	}
	public void mousePressed(MouseEvent e) {
		
	}
	public void mouseReleased(MouseEvent e) {
		
	}
	public void paint(Graphics g) {
		
	}
	public void tick() {
		
	}
}
