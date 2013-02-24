package com.reality360.levels.platform;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Random;

import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Version;

import com.reality360.Reality360;
import com.reality360.resource.JInputJoystick;
import com.reality360.resource.Level;


public class Platform extends Level {
	Player player = new Player(50,520);
	static Room rooms = new Room(0);
	static int[][] tiles;
	Random r = new Random();
	boolean jumpKey;
	static JInputJoystick joystick;
	Image bg, tile1,tele2,tele3,tele4;
	public Platform(){
		tiles= rooms.getCurrentRoom();
		joystick = new JInputJoystick(Controller.Type.STICK);
		bg = Reality360.loadImage("/Background1.png",900,700);
		tile1 = Reality360.loadImage("/TileSquare.png", 40, 40);
		tele2 = Reality360.loadImage("/TileSquare2.png");
		tele3 = Reality360.loadImage("/TileSquare3.png");
		tele4 = Reality360.loadImage("/TileSquare4.png");
	}	
	public static void changeRoom(int roomNum){
		rooms = new Room(roomNum);
		tiles = rooms.getCurrentRoom();
	}
	public void tick(){
		player.fall();
		player.jump();
		player.updateKeys();
		checkJoystick();
	}

	@Override
	public void keyPressed(KeyEvent event) {
		if (event.getKeyCode()==KeyEvent.VK_LEFT)
			player.setMovingLeft(true);
		if (event.getKeyCode()==KeyEvent.VK_RIGHT) 
			player.setMovingRight(true);
		if (event.getKeyCode()==KeyEvent.VK_SPACE){
			player.setJumpKey(true);
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {
		if (event.getKeyCode()==KeyEvent.VK_LEFT){ 
			player.setMovingLeft(false);
		}
		if (event.getKeyCode()==KeyEvent.VK_RIGHT){
			player.setMovingRight(false);
		}
		if (event.getKeyCode()==KeyEvent.VK_SPACE ){ 
			player.setJumpKey(false);
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(bg,0-(player.getX()*100/800),0-(player.getY()*100/800),null);
		for (int i = 0; i < 15; i++){
			for (int j = 0; j < 20; j++){
				if (tiles[i][j]== 1){
					g.drawImage(tile1,j*40,i*40,null);
				} else if(tiles[i][j] == 2){
					g.drawImage(tele2,j*40,i*40,null);
				} else if(tiles[i][j] == 3){
					g.drawImage(tele3,j*40,i*40,null);
				} else if(tiles[i][j] == 4){
					g.drawImage(tele4,j*40,i*40,null);
				}
			}
		}
		player.paint(g);
		
	}
	public void checkJoystick(){
        if(joystick.isControllerConnected()){
                joystick.pollController();
        
        if (joystick.getXAxisPercentage() > 60){
        	player.movePlayer(1,0,0);
        	player.setMovingRightIdle(true);
			player.setMovingLeftIdle(false);
        } else if (joystick.getXAxisPercentage() < 40){
        	System.out.println("LOL");
        	player.movePlayer(-1,0,0);
        	player.setMovingRightIdle(false);
			player.setMovingLeftIdle(true);
        }
        	
        }
        
        if (joystick.getButtonValue(1)){
                player.setJumpKey(true);
        } else {
                player.setJumpKey(false);
        }
        if (joystick.getButtonValue(1)){
    }
}
}
