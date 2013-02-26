package com.reality360.bth;

import com.reality360.resource.AI;
import com.redsoxfan.libs.pixtact.Pixtact;

public class Pickup extends AI{
	
	private boolean healthPickup = false;
	private int value = 0;
	
	public Pickup(int xPos, int yPos, int value,int fallSpeed, boolean healthPickup){
		super(xPos,yPos,0,fallSpeed);
		if(healthPickup)img = Pixtact.read(getClass().getResource("/health.png"));
		else img = Pixtact.read(getClass().getResource("/damage.png"));
		img.resize(15,15);
		this.healthPickup = healthPickup;
		this.value = value;
	}
	public int getVal(){
		return value;
	}
    public boolean isHealth(){
    	return healthPickup;
    }
	public void tick(){
		yPos+=moveY;
	}
}