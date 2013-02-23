package com.reality360.bth;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.reality360.resource.AI;

public class Enemy extends AI{
	
	private BufferedImage img;//image to be drawn in paint
	private int hitPoints = 0;//health
	private static int attackSpeed = 1;//bullets generated per second
	
	public Enemy(int hitPoints){
		this.hitPoints = hitPoints;
		
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
		
	}
}
