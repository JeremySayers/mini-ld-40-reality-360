package com.reality360.bth;

import com.reality360.resource.AI;
import com.redsoxfan.libs.pixtact.Pixtact;

public class Pickup extends AI{
	
	public Pickup(int xPos, int yPos, int fallSpeed){
		super(xPos,yPos,0,fallSpeed);
		img = Pixtact.read(getClass().getResource("/.png"));
	}
	public void tick(){
		yPos+=moveY;
	}
}