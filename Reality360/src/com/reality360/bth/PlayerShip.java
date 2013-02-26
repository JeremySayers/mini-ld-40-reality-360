package com.reality360.bth;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.reality360.Reality360;
import com.reality360.resource.Entity;
import com.redsoxfan.libs.pixtact.Pixtact;

public class PlayerShip extends Entity{

	private Pixtact img = null, player = null;
	private int powerLevel = 0, powerTemp = 0, hitPoints = 100;
	private int speed = 5;
	private int width = 30, height = 30, xPos = 385, yPos = 550;
	private long tickCount = 0;
	private boolean isAlive = false, upKey = false, downKey = false, leftKey = false, rightKey = false, shooting = false;
	private HealthBar hb, db;
	private Color panelColor = new Color(150,150,150);
	
	public PlayerShip(){
		img = Pixtact.read(getClass().getResource("/redbullet.png"));
		img.resize(10,10);
		img.setX(xPos+10);
		img.setY(yPos+10);
		player = Pixtact.read(getClass().getResource("/Player.png"));
		player.resize(width, height);
		player.setX(xPos);
		player.setY(yPos);
		hb = new HealthBar(hitPoints,100,10);
		db = new HealthBar(100,100,10);
		db.setOverColor(255,170,0);
		db.setUnderColor(100,100,100);
		db.doDamage(100);
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
	public Pixtact getImg(){
		return img;
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
			case KeyEvent.VK_SHIFT: powerTemp = powerLevel; powerLevel = 0; speed = 3; break;
		}
	}
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()){
			case KeyEvent.VK_UP: upKey = false; break;
			case KeyEvent.VK_DOWN: downKey = false; break;
			case KeyEvent.VK_LEFT: leftKey = false; break;
			case KeyEvent.VK_RIGHT: rightKey = false; break;
			case KeyEvent.VK_SPACE: shooting = false; break;
			case KeyEvent.VK_SHIFT: powerLevel = powerTemp; powerTemp = 0; speed = 5; break;
		}
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	
	public void doDamge(int damage){
		hb.doDamage(damage);
		db.doDamage(damage/5);
	}
	public void pickupHealth(int hp){
		hb.heal(hp);
	}
	public void pickupDmg(int dmg){
		db.heal(dmg);
	}
	public int getHP(){
		return hb.currentHp();
	}
	public int getPower(){
		return (db.currentHp()>0)?db.currentHp():1;
	}
	public void paint(Graphics g) {
		player.drawImage(g);
		img.drawImage(g);
		g.setColor(panelColor);
		g.fillRect(0,565,175,35);
		hb.drawBar(5,585,g);
		db.drawBar(5,570,g);
	}
	public void tick() {
		if(upKey){
			player.setY(yPos>0?yPos-=speed:yPos);
			img.setY(player.getY()+10);
		}
		if(downKey){
			player.setY(yPos+height<Reality360.HEIGHT?yPos+=speed:yPos);
			img.setY(player.getY()+10);
		}
		if(leftKey){
			player.setX(xPos>0?xPos-=speed:xPos);
			img.setX(player.getX()+10);
		}
		if(rightKey){
			player.setX(xPos+width<Reality360.WIDTH?xPos+=speed:xPos);
			img.setX(player.getX()+10);
		}
		
		if(powerLevel<30){
			if(shooting&&tickCount==0){
				Driver.projectiles.add(new PlayerProjectile(xPos+width/2,yPos,8,powerLevel,false));
				tickCount=5;
			}
		}else if(powerLevel>=30 && powerLevel<60){
			if(shooting&&tickCount==0){
				Driver.projectiles.add(new PlayerProjectile(xPos,yPos,7,powerLevel,false));
				Driver.projectiles.add(new PlayerProjectile(xPos+width,yPos,7,powerLevel,false));
				tickCount=6;
			}
		}else if(powerLevel>=60 && powerLevel<90){
			if(shooting&&tickCount==0){
				Driver.projectiles.add(new PlayerProjectile(xPos,yPos,6,powerLevel,false));
				Driver.projectiles.add(new PlayerProjectile(xPos+width,yPos,6,powerLevel,false));
				Driver.projectiles.add(new PlayerProjectile(xPos+width/2,yPos,6,powerLevel,false));
				tickCount=8;
			}
		}else{
			if(shooting&&tickCount==0){
				Driver.projectiles.add(new PlayerProjectile(xPos,yPos,5,powerLevel,false));
				Driver.projectiles.add(new PlayerProjectile(xPos+width,yPos,5,powerLevel,false));
				Driver.projectiles.add(new PlayerProjectile(xPos+width/2,yPos,5,powerLevel,false));
				Driver.projectiles.add(new PlayerProjectile(xPos+width/2,yPos,8,powerLevel,true));
				tickCount=10;
			}
		}
		powerLevel=1+db.currentHp();
		if(tickCount>0)tickCount--;
	}
	public void joystickValues(boolean stick, ArrayList<Boolean> buttons,
			float xAxis, float xRot, float yAxis, float yRot, float zAxis,
			float zRot) {
		if (xAxis>60){
			rightKey = true;
			leftKey = false;
        } else if (xAxis<40){
        	leftKey = true;
        	rightKey = false;
        } else {
        	leftKey = false;
        	rightKey = false;
        }
		if (yAxis>60){
			downKey = true;
			upKey = false;
        } else if (yAxis<40){
        	upKey = true;
        	downKey = false;
        } else {
        	upKey = false;
        	downKey = false;
        }
        shooting = buttons.get(1);
        powerLevel = 0;
        if (buttons.get(4)) {
        	powerLevel = 30;
        } else if (buttons.get(5)) {
        	powerLevel = 60;
        } else if (buttons.get(6)) {
        	powerLevel = 90;
        }
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
