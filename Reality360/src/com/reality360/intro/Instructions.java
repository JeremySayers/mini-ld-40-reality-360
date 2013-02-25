package com.reality360.intro;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.reality360.GamePanel;
import com.reality360.Reality360;
import com.reality360.resource.Level;

public class Instructions extends Level {
	private int tick;
	private Intro intro;
	public Instructions(Intro i) {
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
				msg = "cat README";
				x += paintLine(g, msg, x, y, t, d);
				t -= d*msg.length();
			}
			if (t>0) {
				if (t>0) {
					msg = "Objective:\n" +
						  "    Get back to reality by completing various levels\n" +
						  "\n" +
						  "Keyboard Controls:\n" +
						  "    F - Toggle Fullscreen\n" +
						  "    Level specific controls will be shown at the beginning of that level\n" +
						  "\n" +
						  "Joystick Controls (Desktop Version Only):\n" +
						  "    Button 9 - Windowed Mode\n" +
						  "    Button 10 - Fullscreen Mode\n" +
						  "    Level specific controls will be shown at the beginning of that level" +
						  " \b \b \b \b \b \b \b \b \b \b \b \b \b \b \b \b \b \b \b \b";
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
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
