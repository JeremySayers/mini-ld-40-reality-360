package com.reality360;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import net.java.games.input.Component.Identifier;
import net.java.games.input.Controller;
import net.java.games.input.Controller.Type;

import com.reality360.resource.JInputJoystick;

public class DesktopReality360 {
	public static final GraphicsDevice DEVICE = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	public static final JFrame FRAME = new JFrame("Reality 360 (Desktop Version)");
	public static final GamePanel GAME = new GamePanel();
    public static final int HEIGHT = 600;
    public static final int WIDTH = 800;
	public static void main(String[] args) {
		new DesktopReality360();
	}
	private JInputJoystick joystick;
	public DesktopReality360() {
		FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FRAME.setLayout(new BorderLayout());
		FRAME.add(GAME, BorderLayout.CENTER);
        GAME.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        FRAME.pack();
        FRAME.setFocusable(false);
        FRAME.setSize(800, 600);
        FRAME.setLocationRelativeTo(null);
        FRAME.setVisible(true);
		GAME.addKeyListener(new KeyAdapter(){
	        public void keyReleased(KeyEvent event) {
                if (event.getKeyCode()==KeyEvent.VK_F) {
                	if (DEVICE.getFullScreenWindow()==null) {
                        GAME.setPreferredSize(new Dimension(1,1));
                        FRAME.setVisible(false);
                        FRAME.dispose();
                        FRAME.setUndecorated(true);
                        FRAME.setVisible(true);
                        DEVICE.setFullScreenWindow(FRAME);
                        GAME.requestFocus();
	                } else {
                        GAME.setPreferredSize(new Dimension(1,1));
                        FRAME.setVisible(false);
                        FRAME.dispose();
                        FRAME.setUndecorated(false);
                        FRAME.setVisible(true);
                        DEVICE.setFullScreenWindow(null);
                        GAME.requestFocus();
	                }
                }
	        }
		});
		GAME.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "Blank"));
		GAME.requestFocus();
		try{
			joystick = new JInputJoystick(Controller.Type.STICK);
		} catch(Exception e) {
		}
		new java.util.Timer().scheduleAtFixedRate(new java.util.TimerTask(){
            public void run() {
            	try {
					if (joystick.isControllerConnected()) {
		                joystick.pollController();
		                boolean stick = joystick.getControllerType().equals(Type.STICK);
		                GAME.joystickValue(stick, joystick.getButtonsValues(), 
		                		joystick.componentExists(Identifier.Axis.X)?stick?joystick.getXAxisPercentage():joystick.getXAxisValue():0,
		                		joystick.componentExists(Identifier.Axis.RX)?stick?joystick.getXRotationPercentage():joystick.getXRotationValue():0,
		                		joystick.componentExists(Identifier.Axis.Y)?stick?joystick.getYAxisPercentage():joystick.getYAxisValue():0,
		                		joystick.componentExists(Identifier.Axis.RY)?stick?joystick.getYRotationPercentage():joystick.getYRotationValue():0,
		                		joystick.componentExists(Identifier.Axis.Z)?stick?joystick.getZAxisPercentage():joystick.getZAxisValue():0,
		                		joystick.componentExists(Identifier.Axis.RZ)?stick?joystick.getZRotationPercentage():joystick.getZRotationValue():0);
					}
				} catch (Exception e) {
				}
            	GAME.tick();
            }
		}, 1, 1000/60);
	}
}
