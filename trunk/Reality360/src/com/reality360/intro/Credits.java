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
import com.reality360.levels.platform.Platform;
import com.reality360.menu.Menu;
import com.reality360.menu.Menu.MenuFunction;
import com.reality360.menu.Words;
import com.reality360.resource.Level;

public class Credits extends Level {
	private int tick;
	private Intro intro;
	public Credits(Intro i) {
		tick = 0;
		intro = i;
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
		g.setColor(Color.GREEN);
		g.setFont(new Font("Monospace", Font.PLAIN, 14));
		FontMetrics fm = g.getFontMetrics();
		int x = 5;
		int y = fm.getHeight();
		int t = tick;
		int d = 20;
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
			String msg = ">";
			x += paintLine(g, msg, x, y, t, d);
			t -= 180;
			if (t>0) {
				msg = "cat Credits";
				x += paintLine(g, msg, x, y, t, d);
				t -= d*msg.length();
			}
			if (t>0) {
				if (t>0) {
					msg = "\u00A92013 Satan\n" +
							"22 Acacia Avenue\n" +
							"Hell Corportation\n" +
							"\n" +
							"Producer: Team Tibbles\n" +
							"\n" +
							"Story: Spencer Rak, Jeremy Sayers, James Berman and Brian Ashworth\n" +
							"\n" +
							"Graphics and Audio: James Berman and Nathaniel Butterworth\n" +
							"\n" +
							"Game Play: Spencer Rak, Jeremy Sayers, and Brian Ashworth\n" +
							"\n" +
							"We would also like to thank Hitler, Satan, and Big Foot for their ideas!\n" +
							"\n" +
							"This is Team Tibbles entry for Mini Ludum Dare #40 (Real World)";
					d = 5;
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
				
				if (t>0) {
					g.setColor(Color.BLACK);
					h = (int)(Reality360.HEIGHT/2*(t/100.0));
					g.setColor(Color.BLACK);
					g.fillRect(0, 0, Reality360.WIDTH, h-5);
					g.fillRect(0, Reality360.HEIGHT-h+5, Reality360.WIDTH, h);
					g.setColor(Color.GREEN);
					g.fillRect(0, h-5, Reality360.WIDTH, 5);
					g.fillRect(0, Reality360.HEIGHT-h, Reality360.WIDTH, 5);
					t -= 100;
					if (t>0) {
						intro.ticks = intro.closedTicks;
						GamePanel.level = intro;
					}
				}
			} else if (tick%d>d/2) {
				g.drawString("\u258D", x, y);
			}
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
		tick++;
	}
	public void joystickValues(boolean stick, ArrayList<Boolean> buttons,
			float xAxis, float xRot, float yAxis, float yRot, float zAxis,
			float zRot) {
		
	}
}
