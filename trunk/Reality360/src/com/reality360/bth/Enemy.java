package com.reality360.bth;

import com.reality360.resource.AI;

public class Enemy extends AI{
	
	private int hitPoints = 0;//health
	private static int attackSpeed = 1;//bullets generated per second
	
	public Enemy(int xPos, int yPos, int moveX, int moveY, int hitPoints){
		super(xPos, yPos, moveX, moveY);
		this.hitPoints = hitPoints;
		
	}
}
