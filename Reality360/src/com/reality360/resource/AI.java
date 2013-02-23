package com.reality360.resource;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class AI {
	
	protected BufferedImage img = null;
	protected int width = 0;
	protected int height = 0;
	protected int xPos = 0;
	protected int yPos = 0;
	protected int moveX = 0;
	protected int moveY = 0;
	protected long tickCount = 0;
	protected boolean isAlive = false;
	
	public AI(int xPos, int yPos, int moveX, int moveY){
		this.xPos = xPos;
		this.yPos = yPos;
		this.moveX = moveX;
		this.moveY = moveY;
		isAlive = true;
	}
	public int getX(){
		return xPos;
	}
	public int getY(){
		return yPos;
	}
	public void setX(int x){
		this.xPos = x;
	}
	public void setY(int y){
		this.yPos = y;
	}
	public boolean isAlive(){
		return isAlive;
	}
	public void paint(Graphics g){
		g.drawImage(img,width,height,xPos,yPos,null);
	}
	public abstract void tick();
}
