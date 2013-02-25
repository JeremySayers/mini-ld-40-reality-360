package com.reality360.bth;

import com.reality360.resource.AI;
import com.redsoxfan.libs.pixtact.Pixtact;

public class PlayerProjectile extends AI{
	
	private int damage = 0;
	
	public PlayerProjectile(int xPos, int yPos, int speed, boolean superBullet){
		super(xPos,yPos,0,speed);
		if(!superBullet){
			img = Pixtact.read(getClass().getResource("/redbullet.png"));
			damage = 1;
		}else{
			img = Pixtact.read(getClass().getResource("/SuperBullet.png"));
			damage = 3;
		}
		width = 8;
		height = 8;
		img.resize(width, height);
		isAlive=true;
	}
	public void tick(){
		img.setY(yPos-=moveY);
		if(yPos<0)isAlive=false;
	}
	public void kill(){
		isAlive = false;
	}
	public int getDamage(){
		return damage;
	}
}
