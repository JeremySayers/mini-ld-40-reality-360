package com.reality360.bth;

import com.reality360.resource.AI;
import com.redsoxfan.libs.pixtact.Pixtact;

public class Enemy extends AI{
	
	private int hitPoints = 0;//health
	
	public Enemy(int xPos, int yPos, int moveX, int moveY, int hitPoints){
		super(xPos, yPos, moveX, moveY);
		this.hitPoints = hitPoints;
		height = 30;
		width = 30;
		img = Pixtact.read(getClass().getResource("/Minion.png"));
		img.resize(width, height);
	}
	public void tick(){
		if(hitPoints<=0)isAlive=false;
		if(tickCount==0 && (Math.abs(Driver.player.getX()-xPos)<=100)){
			Driver.bullets.add(new Bullet(xPos+width/2,yPos+height,Driver.player.getX()+width/2,Driver.player.getY()));//finish bullet vectors
			tickCount=30;
		}
		if(tickCount>0)tickCount--;
	}
}
