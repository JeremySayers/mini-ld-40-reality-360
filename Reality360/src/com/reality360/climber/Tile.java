package com.reality360.climber;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.reality360.Reality360;
import com.reality360.resource.Entity;
import com.redsoxfan.libs.pixtact.Pixtact;

public class Tile extends Entity {
	private Pixtact tile;
	public int life;
	public Tile(int life) {
		this.life = life==0?Integer.MAX_VALUE:life;
		tile = Reality360.loadAsPixtact(life==0?"/Tile.jpg":"/PoofTile.jpg", 40, 10);
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
