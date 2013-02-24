package com.reality360.climber;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.reality360.resource.Entity;
import com.redsoxfan.libs.pixtact.Pixtact;

public class Tile extends Entity {
	private Pixtact tile = new Pixtact(createColorTile(Color.GREEN));
	public Tile() {
	}
	public static BufferedImage createColorTile(Color c) {
		BufferedImage b = new BufferedImage(40, 40, BufferedImage.TYPE_INT_ARGB);
		b.getGraphics().setColor(c);
		b.getGraphics().fillRect(0, 0, 40, 40);
		return b;
	}
	public Pixtact getPixtact() {
		return tile;
	}
	public int getX() {
		return tile.getX();
	}
	public int getY() {
		return tile.getY();
	}
	public boolean isAlive() {
		return false;
	}
	public void move(int x, int y) {
		tile.move(x, y);
	}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	public void paint(Graphics g) {
		tile.drawImage(g);
	}
	public void tick() {
	}
}
