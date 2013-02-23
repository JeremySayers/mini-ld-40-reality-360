package com.realith360.resource;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Entity {
	public abstract void init();
	public abstract BufferedImage paint(Graphics g);
	public abstract void tick();
	public abstract boolean live();
	public abstract int getX();
	public abstract int getY();
	
}
