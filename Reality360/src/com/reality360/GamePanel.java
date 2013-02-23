package com.reality360;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	public static final BufferedImage BUFFER = new BufferedImage(Reality360.WIDTH, Reality360.HEIGHT, BufferedImage.TYPE_INT_RGB);
	public static final Graphics BG = BUFFER.getGraphics();
	public static Level level;
	public GamePanel() {
		new java.util.Timer().scheduleAtFixedRate(new java.util.TimerTask(){
            public void run() {
                    tick();
            }
		}, 1, 1000/60);
	}
	public void paintComponent(Graphics g) {
		BG.setColor(Color.BLUE);
		BG.fillRect(0, 0, BUFFER.getWidth(), BUFFER.getHeight());
		BG.setColor(Color.RED);
		BG.fillRect(20, 20, 100, 100);
		g.drawImage(BUFFER, 0, 0, getWidth(), getHeight(), 0, 0, BUFFER.getWidth(), BUFFER.getHeight(), null);
	}
	public void tick() {
		repaint();
		if (level!=null) {
			level.tick(BUFFER, BG);
		}
	}
}
