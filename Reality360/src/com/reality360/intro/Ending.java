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

import com.reality360.Reality360;
import com.reality360.menu.Words;
import com.reality360.resource.Level;
import com.reality360.sounds.Sound;

public class Ending extends Level {
	public int ticks = 0;
	public int closedTicks = 0;
	public boolean credits = false;
	public boolean level1 = false;
	private static final Image bg = Reality360.loadImage("/ending.png");
	public static final Sound snd = new Sound("/com/reality360/sounds/Ending.mp3", true);
	public Ending() {
		Sound.stopAll();
		ticks = 0;
		snd.play();
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
		g.setFont(new Font("Monospace", Font.PLAIN, 16));
		FontMetrics fm = g.getFontMetrics();
		int x = 5;
		int y = fm.getHeight();
		int t = ticks;
		int d = 20;
		String msg = "";
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
			msg = ">";
			x += paintLine(g, msg, x, y, t, d);
			t -= 180;
			if (t>0) {
				msg = "kill -9 666";
				x += paintLine(g, msg, x, y, t, d);
				t -= d*msg.length();
			}
			if (t>0) {
				y += fm.getHeight();
				x = 5;
				msg = "Ending world...";
				d = 2;
				x += paintLine(g, msg, x, y, t, d);
				t -= d*msg.length();
				d = 20;
				msg = new String[]{"|","/","-","\\"}[ticks%40/10];
				x = Reality360.WIDTH-fm.stringWidth("]");
				g.drawString("]", x, y);
				x -= fm.stringWidth("[M");
				g.drawString("[", x, y);
				x += fm.stringWidth("[")+fm.stringWidth("M")/2-fm.stringWidth(msg)/2;
				g.drawString(msg, x, y);
				t -= 500;
				if (t>0) {
					g.setColor(Color.BLACK);
					h = (int)(Reality360.HEIGHT/2*(t/100.0));
					g.fillRect(0, 0, Reality360.WIDTH, h);
					g.fillRect(0, Reality360.HEIGHT-h, Reality360.WIDTH, h);
					t -= 100;
					if (t>0) {
						g.setColor(Color.GREEN);
						int w = (int)(Reality360.WIDTH - Reality360.WIDTH*(t/200.0));
						g.fillRect(Reality360.WIDTH/2-w/2, Reality360.HEIGHT/2-5, w, 10);
						if (w<0) {
							t -= 200;
							closedTicks = ticks-t;
							g.drawImage(bg, 0, 0, null);
							h = (int)(Reality360.HEIGHT/2 - Reality360.HEIGHT/2*(t/100.0));
							g.setColor(Color.BLACK);
							g.fillRect(0, 0, Reality360.WIDTH, h-5);
							g.fillRect(0, Reality360.HEIGHT-h+5, Reality360.WIDTH, h);
							g.setColor(Color.GREEN);
							g.fillRect(0, h-5, Reality360.WIDTH, 5);
							g.fillRect(0, Reality360.HEIGHT-h, Reality360.WIDTH, 5);
							t -= 100;
						} else if (t%30>10) {
							g.setColor(Color.WHITE);
							BufferedImage word = Words.menuWord("Returning...", 35, 35);
							g.drawImage(word, Reality360.WIDTH/2-word.getWidth()/2, Reality360.HEIGHT/2-100, null);
						}
					}
				}
			} else if (ticks%d>d/2) {
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
		ticks++;
	}
	public void joystickValues(boolean stick, ArrayList<Boolean> buttons,
			float xAxis, float xRot, float yAxis, float yRot, float zAxis,
			float zRot) {
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
