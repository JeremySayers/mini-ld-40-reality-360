package com.reality360.levels.defendthebase;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.reality360.resource.Level;

public class Driver extends Level{
	Base player;
	int mouseX, mouseY;
	public Driver(){
		player = new Base();
	}
	public void paint(Graphics g) {
		player.paint(g);
		g.setColor(Color.RED);
		g.fillRect(mouseX-10, mouseY-1, 20, 2);
		g.fillRect(mouseX-1, mouseY-10, 2, 20);
	}

	public void tick() {
		player.tick();
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
		// TODO Auto-generated method stub
		
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
		player.mouseMoved(e);
		mouseX = e.getX();
		mouseY = e.getY();
	}

}
