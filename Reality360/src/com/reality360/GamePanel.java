package com.reality360;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.reality360.intro.Intro;
import com.reality360.climber.Climber;
import com.reality360.levels.platform.Platform;
import com.reality360.resource.Level;
import com.redsoxfan.libs.pixtact.Pixtact;

public class GamePanel extends JPanel {
	public static final BufferedImage BUFFER = new BufferedImage(Reality360.WIDTH, Reality360.HEIGHT, BufferedImage.TYPE_INT_RGB);
	public static final Graphics BG = BUFFER.getGraphics();
	public static Level level = null;
	public static Intro INTRO = null;
	public GamePanel() {
		addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent arg0) {
				if (level!=null) {
					level.keyPressed(arg0);
				}
			}
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode()==KeyEvent.VK_F1 && arg0.isControlDown()) {
					if (Pixtact.isDrawingBounds())
						Pixtact.turnOffDrawBounding();
					else
						Pixtact.turnOnDrawBounding(Color.RED);
				} else if (arg0.isControlDown() && arg0.getKeyCode()==KeyEvent.VK_M) {
					level = null;
				}
				if (level!=null) {
					level.keyReleased(arg0);
				} else if (arg0.getKeyCode()==KeyEvent.VK_P) {
					level = new Platform();
				} else if (arg0.getKeyCode()==KeyEvent.VK_B) {
					level = new com.reality360.bth.Driver();
				} else if (arg0.getKeyCode()==KeyEvent.VK_R) {
					level = new com.reality360.levels.defendthebase.Driver();

				} else if (arg0.getKeyCode()==KeyEvent.VK_C) {
					level = new Climber();

				} else if (arg0.getKeyCode()==KeyEvent.VK_I) {
					if (INTRO==null) {
						INTRO = new Intro();
					} else {
						INTRO.ticks = INTRO.closedTicks;
					}
					level = INTRO;
				}
			}
		});
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				if (level!=null) {
					level.mousePressed(arg0);
				}
			}
			public void mouseReleased(MouseEvent arg0) {
				if (level!=null) {
					level.mouseReleased(arg0);
				}
			}
		});
		addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				if (level!=null){
					level.mouseMoved(e);
				}
			}
			public void mouseDragged(MouseEvent e) {
				if (level!=null){
					level.mouseMoved(e);
				}
			}
		});
	}
	public void joystickValue(boolean stick, ArrayList<Boolean> buttons, float xAxis, float xRot, float yAxis, float yRot, float zAxis, float zRot) {
		if (level!=null) {
			level.joystickValues(stick, buttons, xAxis, xRot, yAxis, yRot, zAxis, zRot);
		}
	}
	public void tick() {
		if (level!=null) {
			level.tick();	
		}
		repaint();	
	}
	public void paintComponent(Graphics g) {
		BG.setColor(Color.BLACK);
		BG.fillRect(0, 0, BUFFER.getWidth(), BUFFER.getHeight());
		if (level!=null) {
			level.paint(BG);
		}
		g.drawImage(BUFFER, 0, 0, getWidth(), getHeight(), 0, 0, BUFFER.getWidth(), BUFFER.getHeight(), null);
	}
}
