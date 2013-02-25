package com.reality360.climber;

import java.awt.Color;
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
import com.reality360.sounds.Sound;
import com.redsoxfan.libs.pixtact.Pixtact;

public class Climber extends Level {
	private Player player = new Player();
	private int tick = 0;
	public static Tile[][] tiles = new Tile[16][20];
	private static final Image BG = Reality360.loadImage("/purp_lepie.png");
	private static final Pixtact DOOR = Reality360.loadAsPixtact("/Ethernet.png");
	public static int speed = 20;
	public int distance = 0;
	public int maxDistance = 666;
	private boolean end = false;
	private boolean move = false;
	private boolean next = false;
	private int endTick = 0;
	private boolean playable = false;
	private int startTick = 0;
	private boolean canRestart = false;
	private int deadTick = 0;
	private boolean instructions;
	private boolean skipInfo = false;
	private boolean button1 = false;
	public static final Sound snd = new Sound("/com/reality360/sounds/Climber.mp3", true);
	public Climber() {
		this(false);
	}
	public Climber(boolean skip) {
		skipInfo = skip;
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
		snd.play();
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
		if (instructions) {
			
		} else if ((distance<maxDistance || player.getY()+player.getHeight()>(distance-maxDistance)*40) || (end && !player.isCollidingWith(DOOR))) {
			player.keyPressed(e);
		} else {
			player.stop();
		}
	}
	public void keyReleased(KeyEvent e) {
		move = !instructions && !end;
		if (instructions) {
			if (e.getKeyCode()==KeyEvent.VK_SPACE) {
				instructions = false;
				playable = true;
			}
		} else if ((distance<maxDistance || player.getY()+player.getHeight()>(distance-maxDistance)*40) || (end && !player.isCollidingWith(DOOR))) {
			player.keyReleased(e);
		} else {
			player.stop();
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
		if (endTick==0) {
			player.paint(g);
		} else {
			g.setColor(Color.BLACK);
			int h = (int)(Reality360.HEIGHT/2*(endTick/100.0));
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, Reality360.WIDTH, h-5);
			g.fillRect(0, Reality360.HEIGHT-h+5, Reality360.WIDTH, h);
			g.setColor(Color.GREEN);
			g.fillRect(0, h-5, Reality360.WIDTH, 5);
			g.fillRect(0, Reality360.HEIGHT-h, Reality360.WIDTH, 5);
			if (h>=Reality360.HEIGHT/2) {
				next = true;
			}
		}
		if (instructions) {
			g.setColor(new Color(255, 255, 255, 192));
			g.fillRoundRect(50, 50, Reality360.WIDTH-100, Reality360.HEIGHT-100, 100, 100);
			String msg = "C]LEVEL 1 - CLIMBER\n" +
					"\n" +
					"\n" +
					"\n" +
					"Objective:\n" +
					"\tTo get to the top of the map which\n" +
					"\tcontains a door to the next level\n" +
					"\n" +
					"Keyboard Controls:\n"+
					"\tLeft Arrow Key - Go Left\n" +
					"\tRight Arrow Key - Go Right\n" +
					"\tSpace - Jump\n" +
					"\n" +
					"Joystick Controls - Desktop Only:\n"+
					"\tX Axis - Move Left or Right\n" +
					"\tButton 2 - Jump\n" +
					"\n" +
					"\n" +
					"\n" +
					"\n" +
					"\n" +
					"\n" +
					"C]Press SPACE or Button 2 to Continue";
			int y = 100;
			Words.setLetterColor(Color.BLACK);
			for (String m:msg.split("\n")) {
				if (m.isEmpty()) m = " ";
				m = m.replace("\t", "  ");
				boolean center = m.startsWith("C]");
				if (center) {
					m = m.substring(2);
				}
				BufferedImage img = Words.menuWord(m, 12, 12);
				if (center) {
					g.drawImage(img, Reality360.WIDTH/2-img.getWidth()/2, y, null);
				} else {
					g.drawImage(img, 75, y, null);
				}
				y += img.getHeight()+5;
			}
			Words.setLetterColor(Color.WHITE);
		} else if (deadTick>0) {
			g.setColor(Color.BLACK);
			int h = (int)(Reality360.HEIGHT/2*(deadTick/100.0));
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, Reality360.WIDTH, h-5);
			g.fillRect(0, Reality360.HEIGHT-h+5, Reality360.WIDTH, h);
			g.setColor(Color.GREEN);
			g.fillRect(0, h-5, Reality360.WIDTH, 5);
			g.fillRect(0, Reality360.HEIGHT-h, Reality360.WIDTH, 5);
			if (h>=Reality360.HEIGHT/2) {
				canRestart = true;
			}
		} else if (!playable) {
			int t = startTick;
			int h = 0;
			g.setColor(Color.BLACK);
			h = (int)(Reality360.HEIGHT/2 - Reality360.HEIGHT/2*(t/100.0));
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, Reality360.WIDTH, h-5);
			g.fillRect(0, Reality360.HEIGHT-h+5, Reality360.WIDTH, h);
			g.setColor(Color.GREEN);
			g.fillRect(0, h-5, Reality360.WIDTH, 5);
			g.fillRect(0, Reality360.HEIGHT-h, Reality360.WIDTH, 5);
			t -= 100;
			if (t>0) {
				if (skipInfo) {
					instructions = false;
					playable = true;
				} else {
					instructions = true;
				}
			}
		}
	}
	public void tick() {
		if (!playable) {
			startTick ++;
		} else if (instructions) {
			
		} else if (end && player.isCollidingWith(DOOR)) {
			boolean yLoc = player.getY()>DOOR.getY()+DOOR.getHeight()/2;
			boolean size = player.getWidth()/2>0 && player.getHeight()/2>0;
			if (size) {
				if (yLoc)
					player.setY(player.getY()-1);
				if (size && player.getY()<DOOR.getY()+DOOR.getHeight()*2/3)
					player.resize((int)(player.getWidth()-player.getWidth()/(10.0*speed)), (int)(player.getHeight()-player.getHeight()/(10.0*speed)));
			} else if (next) {
				snd.pause();
				GamePanel.level = new Platform();
			} else {
				endTick++;
			}
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
			if (canRestart) {
				snd.pause();
				GamePanel.level = new Climber(true);
			} else {
				deadTick++;
			}
		}
	}
	public void joystickValues(boolean stick, ArrayList<Boolean> buttons,
			float xAxis, float xRot, float yAxis, float yRot, float zAxis,
			float zRot) {
		if (instructions) {
			if (buttons.get(1)) {
				instructions = false;
				playable = true;
				button1 = true;
			}
		} else if ((distance<maxDistance || player.getY()+player.getHeight()>(distance-maxDistance)*40) || (end && !player.isCollidingWith(DOOR))) {
			if (!button1) {
				player.joystickValues(stick, buttons, xAxis, xRot, yAxis, yRot, zAxis, zRot);
				if (!move && (xAxis<40 || xAxis>60 || buttons.get(1))) {
					move = playable && !end;
				}
			} else {
				button1 = buttons.get(1);
			}
		} else {
			player.stop();
		}
	}
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
