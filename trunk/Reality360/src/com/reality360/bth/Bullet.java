package com.reality360.bth;

import com.reality360.resource.AI;

public class Bullet extends AI{
	
	public Bullet(int xPos, int yPos, int moveX, int moveY){
		super(xPos, yPos, moveX, moveY);
		
	}
	public boolean collision(){
		return false;
	}
}
