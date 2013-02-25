package com.reality360.bth;

import com.redsoxfan.libs.pixtact.Pixtact;

public class Laser extends Bullet{
	
	private boolean rightSide = false;
	private boolean vertical = false;
	private int speed = 0;
	
	public Laser(boolean rightSide, int yPos,int speed, int damage){
		super(rightSide?780:0,yPos,rightSide?0:780,yPos,damage);
		img = Pixtact.read(getClass().getResource("/purplewalllaser.png"));
		img.resize(14,23);
		this.rightSide=rightSide;
		this.speed=speed;
	}
//	public Laser(int yPos, int speed, int damage){
//		super();
//		vertical = true;
//	}
	public void tick(){
		if(!vertical){
			if(xPos>0 || xPos<600){
				xPos+=rightSide?-speed:speed;
			}else{
				isAlive=false;
			}
		}
	}
}