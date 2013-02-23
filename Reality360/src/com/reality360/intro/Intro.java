package com.reality360.intro;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.reality360.Reality360;
import com.reality360.resource.Level;

public class Intro extends Level {
	private int tick = 0;
	public Intro() {
		tick = 0;
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
			msg = "Executing world...";
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
						"       Extracting Idiots.tar.gz ... \b \b \b \b \b \b\n" +
						"           Extracting SayersJeremy.tar.gz ... \b \b \b \b \b \b\n" +
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
			msg = new String[]{"|","/","-","\\"}[tick%40/10];
			x = Reality360.WIDTH-fm.stringWidth("]");
			g.drawString("]", x, y);
			x -= fm.stringWidth("[M");
			g.drawString("[", x, y);
			x += fm.stringWidth("[")+fm.stringWidth("M")/2-fm.stringWidth(msg)/2;
			g.drawString(msg, x, y);
		} else if (tick%d>d/2) {
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
		tick++;
	}
}
