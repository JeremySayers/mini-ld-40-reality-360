package com.reality360.resource;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class Entity {
	public abstract int getX();
	public abstract int getY();
	public abstract boolean isAlive();
	public abstract void keyPressed(KeyEvent e);
	public abstract void keyReleased(KeyEvent e);
	public abstract void mousePressed(MouseEvent e);
	public abstract void mouseReleased(MouseEvent e);
	public abstract void paint(Graphics g);
	public abstract void tick();
	
	public abstract class AI extends Entity{
		//useless
		public void keyPressed(KeyEvent e){}
		public void keyReleased(KeyEvent e){}
		public void mousePressed(MouseEvent e){}
		public void mouseReleased(MouseEvent e){}
		
	}
}
