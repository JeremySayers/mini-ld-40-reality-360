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
	public static int speed = 20;
	public Climber() {
		for (int i=0; i<16; i++) {
			tiles[i] = new Tile[20];
		}
		tiles[14][11] = createTile();
		tiles[14][10] = createTile();
		tiles[14][9] = createTile();
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
			tick++;
			tick%=speed;
			if (tick==0) {
				for (int r=15; r>0; r--) {
					for (int c=0; c<20; c++) {
						tiles[r][c] = tiles[r-1][c];
					}
				}
				generate(0);
			}
		}
	}
}
