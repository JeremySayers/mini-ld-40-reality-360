package com.reality360.climber;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.reality360.resource.Level;

public class Climber extends Level {
	private Player player = new Player();
	private int tick = 0;
	public static Tile[][] tiles = new Tile[16][20];
	public static int offset = 0;
	public Climber() {
		for (int i=0; i<16; i++) {
			tiles[i] = new Tile[20];
		}
		tiles[14][11] = new Tile();
		tiles[14][10] = new Tile();
		tiles[14][9] = new Tile();
		player.move(400, 490);
		for (int r=12; r>0; r--) {
			for (int c=1; c<19; c++) {
				if (Math.random()>0.9) {
					tiles[r][c-1] = new Tile();
					tiles[r][c] = new Tile();
					tiles[r][c+1] = new Tile();
					c+=2;
				}
			}
		}
	}
	public void keyPressed(KeyEvent e) {
		player.keyPressed(e);
	}
	public void keyReleased(KeyEvent e) {
		player.keyReleased(e);
	}
	public void mousePressed(MouseEvent e) {
		player.mousePressed(e);
	}
	public void mouseReleased(MouseEvent e) {
		player.mouseReleased(e);
	}
	public void paint(Graphics g) {
		for (int r=0; r<16; r++) {
			for (int c=0; c<20; c++) {
				if (tiles[r][c]!=null) {
					tiles[r][c].move(40*c, 40*(r-1)+tick%40);
					tiles[r][c].paint(g);
				}
			}
		}
		player.paint(g);
	}
	public void tick() {
		player.move(player.getX(), player.getY()+tick%40);
		player.tick();
		tick++;
		tick%=40;
		if (tick%40==0) {
			for (int r=15; r>0; r--) {
				for (int c=0; c<20; c++) {
					tiles[r][c] = tiles[r-1][c];
				}
			}
			for (int c=1; c<19; c++) {
				if (Math.random()>0.95) {
					tiles[0][c-1] = new Tile();
					tiles[0][c] = new Tile();
					tiles[0][c+1] = new Tile();
					c+=2;
				} else {
					tiles[0][c] = null;
				}
			}
		}
	}
}
