package com.reality360.resource;

import java.awt.Graphics;

public abstract class AI {
	public abstract int getX();
	public abstract int getY();
	public abstract boolean isAlive();
	public abstract void paint(Graphics g);
	public abstract void tick();
}
