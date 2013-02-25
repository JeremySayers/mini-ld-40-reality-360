package com.reality360.climber;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.reality360.GamePanel;
import com.reality360.Reality360;
import com.reality360.levels.platform.Platform;
import com.reality360.menu.Words;
import com.reality360.resource.Level;
import com.redsoxfan.libs.pixtact.Pixtact;

public class Climber extends Level {
	private Player player = new Player();
	private int tick = 0;
	public static Tile[][] tiles = new Tile[16][20];
	private static final Image BG = Reality360.loadImage("/redcomputer1.png");
	private static final Pixtact DOOR = Reality360.loadAsPixtact("/Ethernet.png");
	public static int speed = 20;
	public int distance = 0;
	public int maxDistance = 600;
	private boolean end = false;
	private boolean move = false;
	public Climber() {
		DOOR.move(400-DOOR.getWidth()/2, 540-DOOR.getHeight());
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
		return new Tile(Math.random()>0.9?speed/2:0);
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
		if (distance%2==0) {
			int len = (int)(Math.random()*3)+2;
			int sep = (int)(Math.random()*2);
			double ty = Math.random();
			if (ty<0.3) {
				int start = Math.min(19-len, Math.max(0, li-sep-len));
				for (int c=0;c<len;c++) {
					tiles[row][start+c] = createTile();
				}
			} else if (ty<0.6) {
				int start = Math.min(19, Math.max(len, Math.min(ri-len-sep, li+sep+len)));
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
		if (distance<maxDistance || !player.isCollidingWith(DOOR)) {
			player.keyPressed(e);
		}
	}
	public void keyReleased(KeyEvent e) {
		move = !end;
		if (distance<maxDistance || !player.isCollidingWith(DOOR)) {
			player.keyReleased(e);
		}
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
					if (tiles[r][c].life<=0) {
						tiles[r][c] = null;
					} else {
						tiles[r][c].move(40*c, 40*(r-1)+tick*40/speed);
						tiles[r][c].paint(g);
					}
				}
			}
		}
		if (distance-maxDistance<0) { 
			BufferedImage img = Words.menuWord(""+(maxDistance-distance), 20, 20);
			g.drawImage(img, Reality360.WIDTH/2-img.getWidth()/2, img.getHeight()*2, null);
		} else {
			DOOR.drawImage(g);
		}
		player.paint(g);
	}
	public void tick() {
		if (distance>=maxDistance && player.isCollidingWith(DOOR)) {
			GamePanel.level = new Platform();
		} else if (player.isAlive()) {
			player.tick();
			DOOR.setY((distance-maxDistance-4)*40-DOOR.getHeight()+tick*40/speed);
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
							if (distance-maxDistance==0) {
								tiles[0][c] = new Tile(0);
							} else if (distance-maxDistance>=14) {
								tiles[0][c] = null;
							} else {
								tiles[0][c] = c==0 || c==19 ? new Tile(0) : null;
							}
						}
						if (distance-maxDistance>=15) {
							tick = 10;
							move = false;
							end = true;
						}
					} else {
						generate(0);
					}
				}
			}
		} else {
			GamePanel.level = new Climber();
		}
	}
	public void joystickValues(boolean stick, ArrayList<Boolean> buttons,
			float xAxis, float xRot, float yAxis, float yRot, float zAxis,
			float zRot) {
		if (distance<maxDistance || !player.isCollidingWith(DOOR)) {
			player.joystickValues(stick, buttons, xAxis, xRot, yAxis, yRot, zAxis, zRot);
			if (!move && (xAxis<40 || xAxis>60 || buttons.get(1))) {
				move = !end;
			}
		}
	}
}
