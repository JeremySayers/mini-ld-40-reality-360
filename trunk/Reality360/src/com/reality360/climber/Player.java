package com.reality360.climber;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.reality360.Reality360;
import com.reality360.resource.Entity;
import com.redsoxfan.libs.pixtact.Pixtact;

public class Player extends Entity {
	private static final Pixtact player = Reality360.loadAsPixtact("/Player.png", 30, 30);
	private static boolean alive = true;
	private static int xVel = 0;
	private static int yVel = 0;
	private boolean jumping;
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
		return getY()!=Reality360.HEIGHT-player.getHeight();
	}
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
			xVel = 5;
		}
		if (e.getKeyCode()==KeyEvent.VK_LEFT) {
			xVel = -5;
		}
		if (e.getKeyCode()==KeyEvent.VK_SPACE && !jumping) {
			yVel = 15*40/Climber.speed;
			jumping = true;
		}
	}
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_RIGHT && xVel==5) {
			xVel = 0;
		}
		if (e.getKeyCode()==KeyEvent.VK_LEFT && xVel==-5) {
			xVel = 0;
		}
		if (e.getKeyCode()==KeyEvent.VK_SPACE) {
			jumping = false;
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
		player.setY(getY()-yVel);
		yVel-=40/Climber.speed;
		for (int r=0; r<16; r++) {
			for (int c=0; c<20; c++) {
				Tile t = Climber.tiles[r][c];
				if (t!=null) {
					if (player.isColliding(t.getPixtact())) {
						t.life--;
						if (t.life<=0) {
							Climber.tiles[r][c] = null;
						} else {
							if (player.getY()+player.getHeight()>=t.getY() && yVel<0) {
								yVel = 0;
							}
							return;
						}
					}
				}
			}
		}
		player.setX(Math.min(Reality360.WIDTH-player.getWidth(), Math.max(0, getX())));
		player.setY(Math.min(Reality360.HEIGHT-player.getHeight(), Math.max(0, getY())));
	} 
}
