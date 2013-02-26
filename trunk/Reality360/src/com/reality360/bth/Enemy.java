package com.reality360.bth;

import java.awt.Image;

import com.reality360.resource.AI;
import com.redsoxfan.libs.pixtact.Pixtact;

public class Enemy extends AI{
	
	protected int hitPoints = 0,deathCount=0;
	protected double locationAngle = 0.00;
	protected boolean inPosition = false, isDying = false;
	protected int shortest = 0;
	protected int longest = 0;
	protected int numerator = 0;
	protected int dx1 = 0;
	protected int dy1 = 0;
	protected int dx2 = 0;
	protected int dy2 = 0;
	protected int x2 = 0;
	protected int y2 = 0;
	protected int damage = 0;
	
	public Enemy(int xPos, int yPos, int moveX, int moveY, int hitPoints){
		super(xPos, yPos, moveX, moveY);
		this.hitPoints = hitPoints;
		damage = hitPoints/10;
		height = 30;
		width = 30;
		img = Pixtact.read(getClass().getResource("/Minion.png"));
		img.resize(width, height);
		
		setX(xPos);
		setY(yPos);
		x2 = moveX;
		y2 = moveY;
		int w = x2 - xPos;
	    int h = y2 - yPos;
	    if(w<0) dx1 = -1; else if(w>0) dx1 = 1;
	    if(h<0) dy1 = -1; else if(h>0) dy1 = 1;
	    if(w<0) dx2 = -1; else if(w>0) dx2 = 1;
	    int longest = Math.abs(w);
	    int shortest = Math.abs(h);
	    if(!(longest>shortest)) {
	        longest = Math.abs(h);
	        shortest = Math.abs(w);
	        if(h<0)dy2 = -1; else if(h>0) dy2 = 1;
	        dx2 = 0;            
	    }
	    this.shortest = shortest;
	    this.longest = longest;
	    this.numerator = longest >> 1 ;
	}
	public void doDamage(int damage){
		hitPoints -= damage;
	}
	public void tick(){
		if(isDying && deathCount>40){
			shortest = (int)(Math.random()*100.0);
			if(shortest>=50) Driver.pickups.add(new Pickup(xPos,yPos,5,1,true));
			else if(shortest>=30) Driver.pickups.add(new Pickup(xPos,yPos,10,1,false));
			isAlive=false;
		}
		if(hitPoints<=0){
			isDying=true;
			deathCount++;
			if(isDying&&deathCount%10==0){
				switch(deathCount){
				case 10:img=Pixtact.read(getClass().getResource("/expWship1.png"));img.resize(width+5,height+5);break;
				case 20:img=Pixtact.read(getClass().getResource("/expWship2.png"));img.resize(width+5,height+5);break;
				case 30:img=Pixtact.read(getClass().getResource("/expWship3.png"));img.resize(width+5,height+5);break;
				case 40:img=Pixtact.read(getClass().getResource("/expWship4.png"));img.resize(width+5,height+5);break;
				}
			}
		}else{
			if(tickCount==0 && (Math.abs(Driver.player.getX()-xPos)<=200) && Driver.player.getY()>yPos+15){
				synchronized(Driver.bullets){
					Driver.bullets.add(new Bullet(xPos+width/2,yPos+height,Driver.player.getX()+15,Driver.player.getY()-15,damage));//finish bullet vectors
				}
				tickCount=30;
			}
			if(inPosition){
				setX(xPos+(int)(((Math.cos(locationAngle))/3.0)*15));
				setY(yPos+(int)(((Math.sin(locationAngle))/3.0)*5));
				locationAngle+=.05;
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
			if(locationAngle>360)locationAngle=0;
			if(tickCount>0)tickCount--;
		}
	}
}
