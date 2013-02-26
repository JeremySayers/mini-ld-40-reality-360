package com.reality360.bth;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class HealthBar {
	
	private int totalHp=0,currentHp=0;	
	
	private Rectangle greenRect, redRect;
	private Color overColor = Color.green, underColor = Color.red;
	
	public HealthBar(int totalHp, int width, int height){
		this.totalHp=totalHp;
		this.currentHp=totalHp;
		greenRect = new Rectangle(width,height);
		redRect = new Rectangle(width,height);
	}
	public void drawBar(int x, int y, Graphics g){
		if(currentHp<=totalHp){
			g.setColor(underColor);
			g.fillRect(x,y,redRect.width,redRect.height);
		}
		greenRect.setSize((int)((((double)redRect.width)/((double)totalHp))*currentHp+0.5),greenRect.height);
		g.setColor(overColor);
		g.fillRect(x,y,greenRect.width,greenRect.height);
	}
	public void heal(int hp){
		if(currentHp<totalHp && currentHp+hp<=totalHp)currentHp+=hp;
		else currentHp=totalHp;
	}
	public void doDamage(int damage){
		if(currentHp>0 && currentHp-damage>=0)currentHp-=damage;
		else currentHp=0;
	}
	public void setOverColor(int r, int g, int b){
		overColor = new Color(r,g,b);
	}
	public void setUnderColor(int r, int g, int b){
		underColor = new Color(r,g,b);
	}
	public int currentHp(){
		return currentHp;
	}
}