package com.reality360;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Level {
	public abstract void init();
	public abstract void paint(BufferedImage buffer, Graphics g);
	public abstract void tick(BufferedImage buffer, Graphics g);
}
