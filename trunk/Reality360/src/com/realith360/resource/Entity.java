package com.realith360.resource;
import java.awt.image.BufferedImage;

public abstract class Entity {
	public abstract void init();
	public abstract BufferedImage paint();
	public abstract void tick();
	public abstract boolean live();
	public abstract int getX();
	public abstract int getY();
	
}
