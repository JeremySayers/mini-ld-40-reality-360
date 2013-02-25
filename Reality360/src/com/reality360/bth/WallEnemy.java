package com.reality360.bth;

import com.redsoxfan.libs.pixtact.Pixtact;

public class WallEnemy extends Enemy{

	private boolean rightWall = false;
	
	public WallEnemy(int hitPoints, boolean rightWall){
		super(rightWall?750:0,-50,rightWall?750:0,(int)(Math.random()*200+300),hitPoints);
		if(rightWall)img = Pixtact.read(getClass().getResource("/wallEnemyRight.png"));
		else img = Pixtact.read(getClass().getResource("/wallEnemyLeft.png"));
		img.resize(width=50,height=50);
		this.rightWall=rightWall;
	}
	public void tick(){
		if(isDying && deathCount>40)isAlive=false;
		if(hitPoints<=0){
			isDying=true;
			deathCount++;
			if(isDying&&deathCount%10==0){
				switch(deathCount){
				case 10:img=Pixtact.read(getClass().getResource(rightWall?"/wallEnemyRight.png":"/wallEnemyLeft.png"));img.resize(width,height);break;
				case 20:img=Pixtact.read(getClass().getResource(rightWall?"/wallEnemyRight.png":"/wallEnemyLeft.png"));img.resize(width,height);break;
				case 30:img=Pixtact.read(getClass().getResource(rightWall?"/wallEnemyRight.png":"/wallEnemyLeft.png"));img.resize(width,height);break;
				case 40:img=Pixtact.read(getClass().getResource(rightWall?"/wallEnemyRight.png":"/wallEnemyLeft.png"));img.resize(width,height);break;
				}
			}
		}else{
			if(inPosition){
				if(tickCount%3==0){
					if(Math.abs(Driver.player.getY()-yPos)<=20){
						Driver.bullets.add(new Laser(rightWall,yPos+15,5,5));
					}
				}
				if(tickCount==60)tickCount=0;
				tickCount++;
			}else{
				 this.numerator += this.shortest;
			        if(!(numerator<longest)) {
			            numerator -= longest;
			            setX(getX() + dx1);
			            setY(getY() + dy1);
			        } else {
			            setX(getX() + dx2);
			            setY(getY() + dy2);
			        }
			        if(xPos==moveX && yPos==moveY)inPosition=true;
			}
		}
	}
}