package com.reality360.resource;

import java.awt.Graphics;

import com.redsoxfan.libs.pixtact.Pixtact;

public abstract class AI {
	
	protected Pixtact img = null;
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
	public void kill(){
		isAlive=false;
	}
	public boolean isAlive(){
		return isAlive;
	}
	public Pixtact getImg(){
		return img;
	}
	public void paint(Graphics g){
		if(img!=null){
			img.setX(xPos);
			img.setY(yPos);
			img.drawImage(g);
		}else{
			g.fillRect(xPos, yPos, width, height);
		}
	}
	public boolean isHit(Pixtact p){
//		return img.isCollidion(p);
		return img.isBoundingBoxesColliding(p);
	}
	public abstract void tick();
}
