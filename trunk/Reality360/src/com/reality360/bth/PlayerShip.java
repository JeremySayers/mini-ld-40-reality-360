package com.reality360.bth;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;

import com.reality360.Reality360;
import com.reality360.resource.Entity;
import com.redsoxfan.libs.pixtact.Pixtact;

public class PlayerShip extends Entity{

	private Pixtact img = null;
	private int powerLevel = 0;
	private int speed = 5;
	private int width = 30;
	private int height = 30;
	private int xPos = 385;
	private int yPos = 550;
	private long tickCount = 0;
	private boolean isAlive = false, upKey = false, downKey = false, leftKey = false, rightKey = false, shooting = false;
	
	public PlayerShip(){
		img = Pixtact.read(getClass().getResource("/Player.png"));
		img.resize(width,height);
		img.setX(xPos);
		img.setY(yPos);
		isAlive = true;
	}
	public int getX() {
		return xPos;
	}
	public int getY() {
		return yPos;
	}
	public boolean isAlive() {
		return isAlive;
	}
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
			case KeyEvent.VK_UP: upKey = true; break;
			case KeyEvent.VK_DOWN: downKey = true; break;
			case KeyEvent.VK_LEFT: leftKey = true; break;
			case KeyEvent.VK_RIGHT: rightKey = true; break;
			case KeyEvent.VK_SPACE: shooting = true; break;
			case KeyEvent.VK_0: powerLevel=0; break;
			case KeyEvent.VK_1: powerLevel=30; break;
			case KeyEvent.VK_2: powerLevel=60; break;
			case KeyEvent.VK_3: powerLevel=90; break;
		}
	}
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()){
			case KeyEvent.VK_UP: upKey = false; break;
			case KeyEvent.VK_DOWN: downKey = false; break;
			case KeyEvent.VK_LEFT: leftKey = false; break;
			case KeyEvent.VK_RIGHT: rightKey = false; break;
			case KeyEvent.VK_SPACE: shooting = false; break;
		}
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void paint(Graphics g) {
		img.drawImage(g);
	}
	public void tick() {
		if(upKey)img.setY(yPos>0?yPos-=speed:yPos);
		if(downKey)img.setY(yPos+height<Reality360.HEIGHT?yPos+=speed:yPos);
		if(leftKey)img.setX(xPos>0?xPos-=speed:xPos);
		if(rightKey)img.setX(xPos+width<Reality360.WIDTH?xPos+=speed:xPos);
		if(powerLevel<30){
			if(shooting&&tickCount==0){
				Driver.projectiles.add(new PlayerProjectile(xPos+width/2,yPos,8));
				tickCount=5;
			}
		}else if(powerLevel>=30 && powerLevel<60){
			if(shooting&&tickCount==0){
				Driver.projectiles.add(new PlayerProjectile(xPos,yPos,8));
				Driver.projectiles.add(new PlayerProjectile(xPos+width,yPos,8));
				tickCount=6;
			}
		}else if(powerLevel>=60 && powerLevel<90){
			if(shooting&&tickCount==0){
				Driver.projectiles.add(new PlayerProjectile(xPos,yPos,8));
				Driver.projectiles.add(new PlayerProjectile(xPos+width,yPos,8));
				Driver.projectiles.add(new PlayerProjectile(xPos+width/2,yPos,8));
				tickCount=8;
			}
		}else{
			if(shooting&&tickCount==0){
				Driver.projectiles.add(new PlayerProjectile(xPos,yPos,8));
				Driver.projectiles.add(new PlayerProjectile(xPos+width,yPos,8));
				Driver.projectiles.add(new PlayerProjectile(xPos+width/2,yPos,8));
				tickCount=8;
			}
		}
		if(tickCount>0)tickCount--;
	}
}
