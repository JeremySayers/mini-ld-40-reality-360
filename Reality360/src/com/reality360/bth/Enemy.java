package com.reality360.bth;

import com.reality360.resource.AI;
import com.redsoxfan.libs.pixtact.Pixtact;

public class Enemy extends AI{
	
	private int hitPoints = 0;
	private double locationAngle = 0.00;
	
	public Enemy(int xPos, int yPos, int moveX, int moveY, int hitPoints){
		super(xPos, yPos, moveX, moveY);
		this.hitPoints = hitPoints;
		height = 30;
		width = 30;
		img = Pixtact.read(getClass().getResource("/Minion.png"));
		img.resize(width, height);
	}
	public void doDamage(int damage){
		hitPoints -= damage;
	}
	public void tick(){
		if(hitPoints<=0)isAlive=false;
		if(tickCount==0 && (Math.abs(Driver.player.getX()-xPos)<=200) && Driver.player.getY()>yPos+15){
			synchronized(Driver.bullets){
				Driver.bullets.add(new Bullet(xPos+width/2,yPos+height,Driver.player.getX()+15,Driver.player.getY()-15));//finish bullet vectors
			}
			tickCount=30;
		}
		setX(xPos+(int)(((Math.cos(locationAngle))/2.0)*20));
		setY(yPos+(int)(((Math.sin(locationAngle))/2.0)*5));
		locationAngle+=.05;
		if(locationAngle>360)locationAngle=0;
		if(tickCount>0)tickCount--;
	}
}
