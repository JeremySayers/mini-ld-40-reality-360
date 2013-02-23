package com.realith360.resource;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Level {
	public abstract void paint(Graphics g);
	public abstract void tick();
}
