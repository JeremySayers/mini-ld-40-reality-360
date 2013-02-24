package com.reality360.bth;

import java.awt.Color;
import java.awt.Graphics;

import com.reality360.resource.AI;

public class Bullet extends AI{
	
	private int modMove = 0, dX = 0, dY = 0;
	private boolean xLarger=false;
	
	public Bullet(int x1, int y1, int x2, int y2){
		super(x1,y1,x2,y2);
		width=5;
		height=5;
		
		dX=x2-x1;
		dY=y2-y1;
		if(Math.abs(dY)>Math.abs(dX)){
			modMove = dY%dX;
			xLarger=false;
		}else if(Math.abs(dY)<Math.abs(dX)){
			modMove = dX%dY;
			xLarger=true;
		}
	    
		isAlive = true;
	}
	public void paint(Graphics g){
		g.setColor(Color.RED);
		g.fillOval(xPos,yPos,width,height);
		/* img.setX(xPos);
		 * img.setY(yPos);
		 * img.drawImage(g);
		 */
	}
	public void tick(){
		tickCount++;
		move();
		if(xPos>800||xPos<0||yPos>600||yPos<0)isAlive=false;
		if(tickCount==60)tickCount=0;
	}
	public void move(){
		if(xLarger){
			System.out.println("xLarger");
			if(tickCount%modMove==0){xPos++;System.out.println("X is larger, incrementing on every "+modMove);}
			yPos++;
		}else{
			System.out.println("yLarger");
			if(tickCount%modMove==0){yPos++;System.out.println("Y is larger, incrementing on every "+modMove);}
			xPos++;
		}
	}
	public boolean collision(){
		return false;
	}
}