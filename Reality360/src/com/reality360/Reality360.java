package com.reality360;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.reality360.sounds.Sound;

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
    
    public static Image loadImage(String path) {
    	try {
    		return new ImageIcon(loadResource(path)).getImage();
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
