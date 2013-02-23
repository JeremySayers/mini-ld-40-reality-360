package com.reality360.levels.platform;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.reality360.Reality360;


public class Player extends com.reality360.resource.Entity{
	int x, y, height, width, xspeed, jumprate;
	boolean isAlive, isJumping, stillHoldingJump;
	final int X_START = 50;
	final int Y_START = 600-30;
	public Player(){
		x = X_START;
		y = Y_START;
		height = 30;
		width = 30;
		stillHoldingJump = false;
	}
	public void updatePlayerMovement(){
		if (this.xspeed != 0){
			this.x+= xspeed;
		}
		if (this.y>Reality360.HEIGHT-this.height){
			this.isJumping = false;
			this.y = Reality360.HEIGHT-this.height;
		}
		if (this.isJumping){
			this.y -= jumprate;
			if(!this.stillHoldingJump){
				jumprate--;
			}
		}
		if (stillHoldingJump){
			if (this.jumprate<20){
				System.out.println(jumprate);
				this.jumprate++;
			} else {
				this.stillHoldingJump = false;
			}
		}
	}
	public void paint(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
	}

	public void tick() {
		updatePlayerMovement();
	}
	
	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public boolean isAlive() {
		return false;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == e.VK_SPACE){
			if (this.isJumping == false){
				this.isJumping = true;
				this.jumprate = 12;
				this.stillHoldingJump = true;
			} 

		}
		if (e.getKeyCode() == e.VK_LEFT){
			this.xspeed = -3;
		}
		if (e.getKeyCode() == e.VK_RIGHT){
			this.xspeed = 3;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == e.VK_LEFT){
			if(xspeed < 0)
				this.xspeed = 0;
		}
		if (e.getKeyCode() == e.VK_RIGHT){
			if(xspeed > 0)
				this.xspeed = 0;
		}
		if(e.getKeyCode() == e.VK_SPACE){
			this.stillHoldingJump = false;
		}
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
