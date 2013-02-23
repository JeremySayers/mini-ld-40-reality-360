package com.reality360.bth;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.reality360.resource.AI;

public class Bullet extends AI{

	private static BufferedImage img;
	private int xPos = 0;
	private int yPos = 0;
	private int moveX = 0;
	private int moveY = 0;
	
	public Bullet(int xPos, int yPos, int moveX, int moveY){
		this.xPos = xPos;
		this.yPos = yPos;
		this.moveX = moveX;
		this.moveY = moveY;
	}
	public int getX() {
		return 0;
	}
	public int getY() {
		return 0;
	}
	public boolean isAlive() {
		return false;
	}
	public void paint(Graphics g) {
		
	}
	public void tick() {
		xPos += moveX;
		yPos += moveY;
	}

}
