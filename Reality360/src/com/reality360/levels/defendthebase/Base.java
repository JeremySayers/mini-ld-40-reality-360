package com.reality360.levels.defendthebase;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.reality360.Reality360;
import com.reality360.resource.Entity;
import com.reality360.resource.RotateImage;

public class Base extends Entity{
	private int mouseX;
	private int mouseY;
	private int playerRotation;
	private int playerX = 350;
	private int playerY = 250;
	private int playerW = 100;
	private int playerH = 100;
	private int centerX = playerX+playerW/2;
	private int centerY = playerY+playerH/2;
	Image base = Reality360.loadImage("/Base.png", 100, 100);
	ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	public Base(){
		
	}
	public void paint(Graphics g) {
		RotateImage.rotate(g,playerX,playerY,base,playerRotation);
	}

	public void tick() {
		calculateRotation();
	}

	public void calculateRotation(){
		if (mouseX-centerX > 0 && mouseY-centerY >0){
			playerRotation = (int) (Math.toDegrees(Math.atan(1.0*(mouseY-centerY)/(mouseX-centerX))));
			System.out.println((mouseY-centerY)+":"+(mouseX-centerX));
			System.out.println((mouseY-centerY)/(mouseX-centerX));
		} else if(mouseX-centerX < 0 && mouseY-centerY >0){
			playerRotation = (int) ((180.0)-Math.toDegrees(Math.atan(1.0*(mouseY-centerY)/(centerX-mouseX))));
		} else if(mouseX-centerX < 0 && mouseY-centerY < 0){
			playerRotation = (int) ((180.0)+Math.toDegrees(Math.atan(1.0*(centerY-mouseY)/(centerX-mouseX))));
		}
		else if(mouseX-centerX > 0 && mouseY-centerY < 0){
			playerRotation = (int) -(Math.toDegrees(Math.atan(1.0*(centerY-mouseY)/(mouseX-centerX))));
		}
		System.out.println();
	}
	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getY() {
		return 0;
	}

	@Override
	public boolean isAlive() {
		return false;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void joystickValues(boolean stick, ArrayList<Boolean> buttons,
			float xAxis, float xRot, float yAxis, float yRot, float zAxis,
			float zRot) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		setMouseX(e.getX());
		setMouseY(e.getY());
	}
	public int getMouseX() {
		return mouseX;
	}
	public void setMouseX(int mouseX) {
		this.mouseX = mouseX;
	}
	public int getMouseY() {
		return mouseY;
	}
	public void setMouseY(int mouseY) {
		this.mouseY = mouseY;
	}

}
