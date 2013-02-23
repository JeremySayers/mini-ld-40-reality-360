package com.reality360.levels.platform;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;


public class Player extends com.reality360.resource.Entity{
	int x, y, height, width, xspeed, yspeed, jumprate;
	boolean isAlive, isJumping;
	final int X_START = 50;
	final int Y_START = 550;
	public Player(){
		x = X_START;
		y = Y_START;
		height = 30;
		width = 30;
	}
	public void paint(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
	}

	public void tick(Graphics g) {
		
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public boolean isAlive() {
		return false;
	}

	public void tick() {
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
