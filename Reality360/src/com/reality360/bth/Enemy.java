package com.reality360.bth;

import com.reality360.resource.AI;

public class Enemy extends AI{
	
	private int hitPoints = 0;//health
	private static int attackSpeed = 1;//bullets generated per second
	
	public Enemy(int xPos, int yPos, int moveX, int moveY, int hitPoints){
		super(xPos, yPos, moveX, moveY);
		this.hitPoints = hitPoints;
		
	}
	public void tick(){
		xPos += moveX;
		yPos += moveY;
		tickCount++;
		if((tickCount%(60/attackSpeed)==0) && (Math.abs(Driver.player.getX()-xPos)<=100)){
			Driver.bullets.add(new Bullet(xPos,yPos,));//finish bullet vectors
		}
		if(tickCount==60)tickCount=0;
	}
}
