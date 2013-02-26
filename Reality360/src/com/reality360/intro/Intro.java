package com.reality360.intro;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.reality360.GamePanel;
import com.reality360.Reality360;
import com.reality360.climber.Climber;
import com.reality360.menu.Menu;
import com.reality360.menu.Menu.MenuFunction;
import com.reality360.menu.Words;
import com.reality360.resource.Level;
import com.reality360.sounds.Sound;

public class Intro extends Level {
	public int ticks = 0;
	public int closedTicks = 0;
	public boolean credits = false;
	public boolean level1 = false;
	private float y = 50;
	private boolean sensitive = false;
	protected boolean instructions;
	private static final Image menubg = Reality360.loadImage("/title.jpg");
	private static final Menu menu = new Menu(" ");
	private static int start = 0;
	public static final Sound snd = new Sound("/com/reality360/sounds/ChocRain8b.mp3", true);
	public Intro() {
		Sound.stopAll();
		ticks = 0;
		menu.addSelection(new MenuFunction() {
			public void run() {
				sensitive = false;
				level1 = true;
				start = ticks;
			}
			public String name() {
				return "Start";
			}
		});
		menu.addSelection(new MenuFunction() {
			public void run() {
				sensitive = false;
				instructions = true;
				start = ticks;
			}
			public String name() {
				return "Instructions";
			}
		});
		menu.addSelection(new MenuFunction() {
			public void run() {
				sensitive = false;
				credits = true;
				start = ticks;
			}
			public String name() {
				return "Credits";
			}
		});
		snd.play();
	}
	public void keyPressed(KeyEvent e) {
		
	}
	public void keyReleased(KeyEvent e) {
		if (sensitive) {
			if (e.getKeyCode()==KeyEvent.VK_UP) {
				menu.selectorUp();
			} else if (e.getKeyCode()==KeyEvent.VK_DOWN) {
				menu.selectorDown();
			} else if (e.getKeyCode()==KeyEvent.VK_ENTER) {
				menu.select();
			}
		}
	}
	public void mousePressed(MouseEvent e) {
		
	}
	public void mouseReleased(MouseEvent e) {
		
	}
	public void paint(Graphics g) {
		g.setColor(Color.GREEN);
		g.setFont(new Font("Monospace", Font.PLAIN, 14));
		FontMetrics fm = g.getFontMetrics();
		int x = 5;
		int y = fm.getHeight();
		int t = ticks;
		int d = 20;
		String msg = ">";
		x += paintLine(g, msg, x, y, t, d);
		t -= 180;
		if (t>0) {
			msg = "sh wolrd\b\b\brld";
			x += paintLine(g, msg, x, y, t, d);
			t -= d*msg.length();
		}
		if (t>0) {
			y += fm.getHeight();
			x = 5;
			msg = "Executing world... \b \b \b \b \b \bPID 666";
			d = 2;
			x += paintLine(g, msg, x, y, t, d);
			t -= d*msg.length();
			d = 20;
		}
		if (t>0) {
			if (t>0) {
				msg = "\u00A92013 Satan\n" +
						"22 Acacia Avenue\n" +
						"Hell Corportation\n" +
						"\n" +
						"Extracting MilkyWay.tar.gz ... \b \b \b \b \b \b \b\n" +
						"    Extracting Sun.tar.gz ... \b \b \b \b \b \b \b\n" +
						"    Extracting Earth.tar.gz ... \b \b \b \b \b \b \b\n" +
						"       Extracting Homosapiens.tar.gz ... \b \b \b \b \b \b \b\n" +
						"           Extracting Hilter.tar.gz ... \b \b \b \b \b \b \b\n" +
						"       Extracting BigFoot.tar.gz ... \b \b \b \b \b \b\n"+
						"       Extracting Australians.tar.gz ... \b \b \b \b \b \b \b\n" +
						"\n" +
						"Disabling TimeTravel ... \b \b \b \b\n" +
						"Enabling MultiDimensionalTravel ... \b \b \b \b\n" +
						"\n" +
						"Removing Blue.pill ... \b \b \b \b \b \b \b\n" +
						"Extracting RedPill.tar.gz ... \b \b \b \b \b \b \b\n" +
						"Loading WonderLand ... \b \b \b \b \b \b \b";
				d = 2;
				for (String m:msg.split("\n")) {
					x = 5;
					y += fm.getHeight();
					x += paintLine(g, m, x, y, t, d);
					t -= d*m.length();
					if (t<=0) {
						break;
					}
				}
				d = 20;
			}
			msg = new String[]{"|","/","-","\\"}[ticks%40/10];
			x = Reality360.WIDTH-fm.stringWidth("]");
			g.drawString("]", x, y);
			x -= fm.stringWidth("[M");
			g.drawString("[", x, y);
			x += fm.stringWidth("[")+fm.stringWidth("M")/2-fm.stringWidth(msg)/2;
			g.drawString(msg, x, y);
			
			if (t>0) {
				g.setColor(Color.BLACK);
				int h = (int)(Reality360.HEIGHT/2*(t/100.0));
				g.fillRect(0, 0, Reality360.WIDTH, h);
				g.fillRect(0, Reality360.HEIGHT-h, Reality360.WIDTH, h);
				t -= 100;
				if (t>0) {
					g.setColor(Color.GREEN);
					int w = (int)(Reality360.WIDTH*(t/200.0));
					g.fillRect(Reality360.WIDTH/2-w/2, Reality360.HEIGHT/2-5, w, 10);
					if (w>=Reality360.WIDTH) {
						t -= 200;
						closedTicks = ticks-t;
						g.drawImage(menubg, 0, 0, null);
						menu.paintMenu(g);
						h = (int)(Reality360.HEIGHT/2 - Reality360.HEIGHT/2*(t/100.0));
						g.setColor(Color.BLACK);
						g.fillRect(0, 0, Reality360.WIDTH, h-5);
						g.fillRect(0, Reality360.HEIGHT-h+5, Reality360.WIDTH, h);
						g.setColor(Color.GREEN);
						g.fillRect(0, h-5, Reality360.WIDTH, 5);
						g.fillRect(0, Reality360.HEIGHT-h, Reality360.WIDTH, 5);
						t -= 100;
						if (t>0) {
							sensitive = true;
						}
					} else if (t%30>10) {
						g.setColor(Color.WHITE);
						BufferedImage word = Words.menuWord("Initializing...", 35, 35);
						g.drawImage(word, Reality360.WIDTH/2-word.getWidth()/2, Reality360.HEIGHT/2-100, null);
					}
				}
			}
			if (t>0 && credits) {
				g.setColor(Color.BLACK);
				int h = (int)(Reality360.HEIGHT/2*((ticks-start)/100.0));
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, Reality360.WIDTH, h-5);
				g.fillRect(0, Reality360.HEIGHT-h+5, Reality360.WIDTH, h);
				g.setColor(Color.GREEN);
				g.fillRect(0, h-5, Reality360.WIDTH, 5);
				g.fillRect(0, Reality360.HEIGHT-h, Reality360.WIDTH, 5);
				if (h>=Reality360.HEIGHT/2) {
					GamePanel.level = new Credits(this);
					credits = false;
				}
				t -= 100;
			} else if (t>0 && level1) {
				g.setColor(Color.BLACK);
				int h = (int)(Reality360.HEIGHT/2*((ticks-start)/100.0));
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, Reality360.WIDTH, h-5);
				g.fillRect(0, Reality360.HEIGHT-h+5, Reality360.WIDTH, h);
				g.setColor(Color.GREEN);
				g.fillRect(0, h-5, Reality360.WIDTH, 5);
				g.fillRect(0, Reality360.HEIGHT-h, Reality360.WIDTH, 5);
				if (h>=Reality360.HEIGHT/2) {
					snd.pause();
					GamePanel.level = new Climber();
					level1 = false;
				}
				t -= 100;
			} else if (t>0 && instructions) {
				g.setColor(Color.BLACK);
				int h = (int)(Reality360.HEIGHT/2*((ticks-start)/100.0));
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, Reality360.WIDTH, h-5);
				g.fillRect(0, Reality360.HEIGHT-h+5, Reality360.WIDTH, h);
				g.setColor(Color.GREEN);
				g.fillRect(0, h-5, Reality360.WIDTH, 5);
				g.fillRect(0, Reality360.HEIGHT-h, Reality360.WIDTH, 5);
				if (h>=Reality360.HEIGHT/2) {
					GamePanel.level = new Instructions(this);
					instructions = false;
				}
				t -= 100;
			}
		} else if (ticks%d>d/2) {
			g.drawString("\u258D", x, y);
		}
	}
	private int paintLine(Graphics g, String line, int x, int y, int t, int d) {
		int len = Math.min(line.length(), t/d);
		String part = line.substring(0, len);
		while (part.contains("\b")) {
			part = part.replaceAll("[^\b]\b", "");
		}
		g.drawString(part, x, y);
		return g.getFontMetrics().stringWidth(part);
	}
	public void tick() {
		ticks++;
	}
	public void joystickValues(boolean stick, ArrayList<Boolean> buttons,
			float xAxis, float xRot, float yAxis, float yRot, float zAxis,
			float zRot) {
		if (sensitive) {
			if (yAxis>40 && yAxis<60 && y<40) {
				menu.selectorUp();
			} else if (yAxis>40 && yAxis<60 && y>60) {
				menu.selectorDown();
			}
			if (buttons.get(1)) {
				menu.select();
			}
			y  = yAxis;
		}
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
