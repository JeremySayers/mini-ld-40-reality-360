package com.reality360.climber;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.reality360.Reality360;
import com.reality360.resource.Entity;
import com.redsoxfan.libs.pixtact.Pixtact;

public class Player extends Entity {
	private static final Pixtact player = Reality360.loadAsPixtact("/Player.png", 40, 40);
	private static boolean alive = true;
	private static int xVel = 0;
	private static int yVel = 0;
	public Player() {
		player.setLocation(Reality360.WIDTH/2-player.getWidth()/2, Reality360.HEIGHT/4*3);
	}
	public int getX() {
		return player.getX();
	}
	public int getY() {
		return player.getY();
	}
	public boolean isAlive() {
		return alive;
	}
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
			xVel = 5;
		}
		if (e.getKeyCode()==KeyEvent.VK_LEFT) {
			xVel = -5;
		}
		if (e.getKeyCode()==KeyEvent.VK_SPACE && yVel==0) {
			yVel += 20;
		}
	}
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
			xVel = 0;
		}
		if (e.getKeyCode()==KeyEvent.VK_LEFT) {
			xVel = 0;
		}
	}
	public void mousePressed(MouseEvent e) {
		
	}
	public void mouseReleased(MouseEvent e) {
		
	}
	public void paint(Graphics g) {
		player.drawImage(g);
	}
	public void tick() {
		player.setX(getX()+xVel);
		player.setY(getY()-(yVel--));
		player.setX(Math.min(Reality360.WIDTH-player.getWidth(), Math.max(0, getX())));
		player.setY(Math.min(Reality360.HEIGHT-player.getHeight(), Math.max(0, getY())));
		if (getY()==Reality360.HEIGHT-player.getHeight()) {
			yVel = 0;
		}
	} 
}
