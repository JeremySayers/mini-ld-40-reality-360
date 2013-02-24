package com.reality360;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.reality360.sounds.Sound;
import com.redsoxfan.libs.pixtact.Pixtact;

public class Reality360 extends Applet {
	public static final GraphicsDevice DEVICE = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	public static final JFrame FRAME = new JFrame("Reality 360");
	public static final GamePanel GAME = new GamePanel();
    public static final int HEIGHT = 600;
    public static final int WIDTH = 800;
    public void init() {            
		// Initialize Applet
		setSize(WIDTH, HEIGHT);
		setLayout(new BorderLayout());
		
		// Initialize Game Panel
		GAME.setFocusable(true);
		add(GAME, BorderLayout.CENTER);
		
		/* Start Of Full Screen Pop Out Stuff */
		FRAME.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		FRAME.setLayout(new BorderLayout());
		FRAME.setUndecorated(true);
		GAME.addKeyListener(new KeyAdapter(){
	        public void keyReleased(KeyEvent event) {
                if (event.getKeyCode()==KeyEvent.VK_F) {
                	setFullscreen(!FRAME.isVisible());
                }
	        }
		});
		/* End Of Full Screen Pop Out Class */
		
		Sound s = new Sound("/com/reality360/sounds/ChocRain8b.mp3", true);
		s.play();
		
		GAME.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "Blank"));
		
		new java.util.Timer().scheduleAtFixedRate(new java.util.TimerTask(){
            public void run() {
            	GAME.tick();
            }
		}, 1, 1000/60);
    }

    public void setFullscreen(boolean fulls) {
        if (fulls) {
                FRAME.add(GAME, BorderLayout.CENTER);
                GAME.setPreferredSize(new Dimension(1,1));
                FRAME.setVisible(true);
                GAME.requestFocus();
                DEVICE.setFullScreenWindow(FRAME); // Re-Associate popout with full screen.
        } else {
                add(GAME, BorderLayout.CENTER);
                GAME.setSize(new Dimension(WIDTH,HEIGHT));
                FRAME.setVisible(false);
                GAME.requestFocus();
                DEVICE.setFullScreenWindow(null); // Un-Associate popout with full screen.
        }
    }
    
    public static Pixtact loadAsPixtact(String path) {
    	try {
    		return Pixtact.createFromImage(loadImage(path));
    	} catch (Exception e) {
    	}
    	return null;
    }
    
    public static Pixtact loadAsPixtact(String path, int width, int height) {
    	try {
    		Pixtact p = Pixtact.createFromImage(loadImage(path));
    		p.resize(width, height);
    		return p;
    	} catch (Exception e) {
    	}
    	return null;
    }
    
    public static Image loadImage(String path) {
    	try {
    		return new ImageIcon(loadResource(path)).getImage();
    	} catch (Exception e) {
    	}
    	return null;
    }
    
    public static Image loadImage(String path, int width, int height) {
    	try {
    		return loadAsPixtact(path, width, height).getImage();
    	} catch (Exception e) {
    	}
    	return null;
    }
    
    public static URL loadResource(String path) {
    	try {
    		return Reality360.class.getResource(path);
    	} catch (Exception e) {
    	}
    	return null;
    }
    
    public static AudioInputStream loadSound(String path) {
    	try {
    		return AudioSystem.getAudioInputStream(loadResource(path));
    	} catch (Exception e) {
    	}
    	return null;
    }
}
