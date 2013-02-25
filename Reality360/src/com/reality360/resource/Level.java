package com.reality360.resource;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public abstract class Level {
	public abstract void keyPressed(KeyEvent e);
	public abstract void keyReleased(KeyEvent e);
	public abstract void mousePressed(MouseEvent e);
	public abstract void mouseReleased(MouseEvent e);
	public abstract void mouseMoved(MouseEvent e);
	public abstract void joystickValues(boolean stick, ArrayList<Boolean> buttons, float xAxis, float xRot, float yAxis, float yRot, float zAxis, float zRot);
	public abstract void paint(Graphics g);
	public abstract void tick();
}
