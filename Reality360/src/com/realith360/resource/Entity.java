package com.realith360.resource;
import java.awt.image.BufferedImage;

public interface Entity {
	public BufferedImage paint();
	public void tick();
	public boolean live();
	public int getX();
	public int getY();
}
