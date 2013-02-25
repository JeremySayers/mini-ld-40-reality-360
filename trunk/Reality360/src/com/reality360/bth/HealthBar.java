package com.reality360.bth;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class HealthBar {
	
	private int totalHp=0,currentHp=0;	
	
	private Rectangle greenRect, redRect;
	
	public HealthBar(int totalHp, int width, int height){
		this.totalHp=totalHp;
		this.currentHp=totalHp;
		greenRect = new Rectangle(width,height);
		redRect = new Rectangle(width,height);
	}
	public void drawBar(int x, int y, Graphics g){
		if(currentHp<=totalHp){
			g.setColor(Color.RED);
			g.fillRect(x,y,redRect.width,redRect.height);
		}
		greenRect.setSize((int)((((double)redRect.width)/((double)totalHp))*currentHp+0.5),greenRect.height);
		g.setColor(Color.GREEN);
		g.fillRect(x,y,greenRect.width,greenRect.height);
	}
	public void doDamage(int damage){
		currentHp-=damage;
	}
	public int currentHp(){
		return currentHp;
	}
}