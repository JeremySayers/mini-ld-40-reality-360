package com.reality360.climber;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.reality360.Reality360;
import com.reality360.resource.Level;

public class Climber extends Level {
	private Player player = new Player();
	private int tick = 0;
	public static Tile[][] tiles = new Tile[16][20];
	private static final Image BG = Reality360.loadImage("/redcomputer1.png");
	public static int speed = 20;
	public int distance = 0;
	public int maxDistance = 1000;
	private boolean end = false;
	private boolean move = false;
	public Climber() {
		for (int i=0; i<16; i++) {
			tiles[i] = new Tile[20];
		}
		for (int c=0; c<20; c++) {
			tiles[14][c] = new Tile(0);
		}
		for (int r=12; r>=0; r-=2) {
			generate(r);
		}
	}
	private Tile createTile() {
		return new Tile(Math.random()>0.75?3*speed/2:0);
	}
	public void generate(int row) {
		int li = 0;
		for (int c=0; c<20; c++) {
			if (tiles[row+2][c]!=null) {
				li = c;
				break;
			}
		}
		int ri = li;
		while (ri+1!=20 && tiles[row+2][ri+1]!=null) {
			ri++;
		}
		for (int c=0; c<20; c++) {
			tiles[row][c] = null;
		}
		if (ri!=li) {
			int len = (int)(Math.random()*2)+2;
			int sep = (int)(Math.random()*3);
			double ty = Math.random();
			if (ty<0.3) {
				int start = Math.min(19-len, Math.max(0, li-sep-len));
				for (int c=0;c<len;c++) {
					tiles[row][start+c] = createTile();
				}
			} else if (ty<0.6) {
				int start = Math.min(19, Math.max(len, Math.min(li-len-sep, li+sep+len)));
				for (int c=0;c<len;c++) {
					tiles[row][start-c] = createTile();
				}
			} else {
				int start = Math.max(len, Math.min(19, ri+sep+len));
				for (int c=0;c<len;c++) {
					tiles[row][start-c] = createTile();
				}
			}
		}
	}
	public void keyPressed(KeyEvent e) {
		player.keyPressed(e);
	}
	public void keyReleased(KeyEvent e) {
		move = !end;
		player.keyReleased(e);
	}
	public void mousePressed(MouseEvent e) {
		player.mousePressed(e);
	}
	public void mouseReleased(MouseEvent e) {
		player.mouseReleased(e);
	}
	public void paint(Graphics g) {
		g.drawImage(BG, 0, 0, null);
		for (int r=0; r<16; r++) {
			for (int c=0; c<20; c++) {
				if (tiles[r][c]!=null) {
					tiles[r][c].move(40*c, 40*(r-1)+tick*40/speed);
					tiles[r][c].paint(g);
				}
			}
		}
		player.paint(g);
	}
	public void tick() {
		if (player.isAlive()) {
			player.tick();
			if (move) {
				tick++;
				tick%=speed;
				if (tick==0) {
					for (int r=15; r>0; r--) {
						for (int c=0; c<20; c++) {
							tiles[r][c] = tiles[r-1][c];
						}
					}
					distance++;
					if (distance>=maxDistance) {
						for (int c=0; c<20; c++) {
							if (Math.abs(distance-maxDistance)%15==0) {
								tiles[0][c] = new Tile(0);
							} else {
								tiles[0][c] = c==0 || c==19 ? new Tile(0) : null;
							}
						}
						if (distance-maxDistance>=15) {
							move = false;
							end = true;
						}
					} else {
						generate(0);
					}
				}
			}
		}
	}
}
