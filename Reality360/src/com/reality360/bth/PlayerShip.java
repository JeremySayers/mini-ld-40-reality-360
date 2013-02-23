package com.reality360.bth;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.reality360.resource.Entity;

public class PlayerShip extends Entity{

	private BufferedImage img = null;
	private int width = 30;
	private int height = 30;
	private int xPos = 385;
	private int yPos = 550;
	private long tickCount = 0;
	private boolean isAlive = false, upKey = false, downKey = false, leftKey = false, rightKey = false, shooting = false;
	
	public PlayerShip(){
		isAlive = true;
	}
	public int getX() {
		return xPos;
	}
	public int getY() {
		return yPos;
	}
	public boolean isAlive() {
		return isAlive;
	}
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
			case KeyEvent.VK_UP: upKey = true; break;
			case KeyEvent.VK_DOWN: downKey = true; break;
			case KeyEvent.VK_LEFT: leftKey = true; break;
			case KeyEvent.VK_RIGHT: rightKey = true; break;
			case KeyEvent.VK_SPACE: shooting = true; break;
		}
	}
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()){
			case KeyEvent.VK_UP: upKey = false; break;
			case KeyEvent.VK_DOWN: downKey = false; break;
			case KeyEvent.VK_LEFT: leftKey = false; break;
			case KeyEvent.VK_RIGHT: rightKey = false; break;
			case KeyEvent.VK_SPACE: shooting = false; break;
		}
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void paint(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(xPos,yPos,width,height);
	}
	public void tick() {
		if(upKey)yPos-=2;
		if(downKey)yPos+=2;
		if(leftKey)xPos-=2;
		if(rightKey)xPos+=2;
		if(shooting&&tickCount==0){
			Driver.bullets.add(new Bullet(xPos+width/2,yPos,xPos+width/2,0));
			tickCount=10;
		}
		if(tickCount>0)tickCount--;
	}
}
