package com.reality360.bth;

import java.awt.Color;
import java.awt.Graphics;

import com.reality360.resource.AI;

public class Bullet extends AI{
	
	private int shortest = 0;
	private int longest = 0;
	private int numerator = 0;
	private int dx1 = 0;
	private int dy1 = 0;
	private int dx2 = 0;
	private int dy2 = 0;
	private int x2 = 0;
	private int y2 = 0;
	
	public Bullet(int xPos, int yPos, int moveX, int moveY){
		super(xPos, yPos, moveX, moveY);
		width=10;
		height=10;
		
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
	    numerator = longest >> 1;
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
		move();
		if(xPos>800||xPos<0||yPos>600||yPos<0)isAlive=false;
	}
	public void move(){
	    this.numerator += this.shortest;
        if(!(numerator<longest)) {
            numerator -= longest;
            setX(getX() + dx1);
            setY(getY() + dy1);
        } else {
            setX(getX() + dx2);
            setY(getY() + dy2);
        }
	}
	public boolean collision(){
		return false;
	}
}
