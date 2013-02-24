package com.reality360.bth;

import com.reality360.resource.AI;
import com.redsoxfan.libs.pixtact.Pixtact;

public class PlayerProjectile extends AI{
	
	public PlayerProjectile(int xPos, int yPos, int speed){
		super(xPos,yPos,0,speed);
		img = Pixtact.read(getClass().getResource("/bullet.png"));
		width = 5;
		height = 5;
		img.resize(width, height);
		isAlive=true;
	}
	public void tick(){
		img.setY(yPos-=moveY);
		if(yPos<0)isAlive=false;
	}
}
