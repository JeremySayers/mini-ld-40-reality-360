package com.reality360.levels.platform;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import com.reality360.GamePanel;
import com.reality360.Reality360;
import com.reality360.menu.Words;
import com.reality360.resource.Level;


public class Platform extends Level {
	Player player = new Player(50,520);
	static Room rooms = new Room(0);
	static int[][] tiles;
	Random r = new Random();
	Image bg, tile1,tele2,tele3,tele4,tele5,tele6,tele7;
	private boolean instructions = false;
	private boolean playable = false;
	private int startTick;
	private boolean button1;
	private boolean next = false;
	private int endTick = 0;
	public static boolean end = false;
	public static int secretTick = 0;
	public static boolean[] secrets = new boolean[]{false, false, false, false};
	public static int currentRoom = 0;
	public Platform(){
		end = false;
		secretTick = 0;
		currentRoom = 0;
		secrets = new boolean[secrets.length];
		rooms = new Room(currentRoom);
		tiles= rooms.getCurrentRoom();
		bg = Reality360.loadImage("/Background1.png",900,700);
		tile1 = Reality360.loadImage("/TileSquare.png", 40, 40);
		tele2 = Reality360.loadImage("/TileSquare2.png");
		tele3 = Reality360.loadImage("/TileSquare3.png");
		tele4 = Reality360.loadImage("/TileSquare4.png");
		tele5 = Reality360.loadImage("/cc_hologear4.png",40,40);
		tele6 = Reality360.loadImage("/TileSquare.png",40,40);
		tele7 = Reality360.loadImage("/TileSquareBinary.png",40,40);
	}	
	public static void changeRoom(int roomNum){
		rooms = new Room(roomNum);
		tiles = rooms.getCurrentRoom();
		currentRoom = roomNum;
	}
	public void tick(){
		if (!playable) {
			startTick ++;
		} else if (instructions) {
			
		} else if (!secrets[2] && !secrets[3]){
			player.fall();
			player.jump();
			player.updateKeys();
		}
	}

	@Override
	public void keyPressed(KeyEvent event) {
		if (!instructions && !secrets[2] && !secrets[3]) {
			if (event.getKeyCode()==KeyEvent.VK_LEFT)
				player.setMovingLeft(true);
			if (event.getKeyCode()==KeyEvent.VK_RIGHT) 
				player.setMovingRight(true);
			if (event.getKeyCode()==KeyEvent.VK_SPACE){
				player.setJumpKey(true);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {
		if (instructions) {
			if (event.getKeyCode()==KeyEvent.VK_SPACE) {
				instructions = false;
				playable = true;
			}
		} else if (!secrets[2] && !secrets[3]) {
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
				} else if(tiles[i][j] == 5){
					g.drawImage(tele5,j*40,i*40,null);
				} else if (tiles[i][j] == 6){
					g.drawImage(tele6, j*40, i*40, null);
				} else if (tiles[i][j] == 7){
					g.drawImage(tele7, j*40, i*40, null);
				}
			}
		}
		player.paint(g);
		if (instructions) {
			g.setColor(new Color(255, 255, 255, 192));
			g.fillRoundRect(50, 50, Reality360.WIDTH-100, Reality360.HEIGHT-100, 100, 100);
			String msg = "C]LEVEL 2 - PLATFORM MAZE\n" +
					"\n" +
					"\n" +
					"\n" +
					"Objective:\n" +
					"\tTo get to the end\n" +
					"\n" +
					"Secondary Objective:\n" +
					"\tTo find as many of the "+secrets.length+" secrets\n" +
					"\tas possible\n"+
					"\n" +
					"Keyboard Controls:\n"+
					"\tLeft Arrow Key - Go Left\n" +
					"\tRight Arrow Key - Go Right\n" +
					"\tSpace - Jump\n" +
					"\n" +
					"Joystick Controls - Desktop Only:\n"+
					"\tX Axis - Move Left or Right\n" +
					"\tButton 2 - Jump\n" +
					"\n" +
					"\n" +
					"\n" +
					"\n" +
					"\n" +
					"\n" +
					"C]Press SPACE or Button 2 to Continue";
			int y = 100;
			Words.setLetterColor(Color.BLACK);
			for (String m:msg.split("\n")) {
				if (m.isEmpty()) m = " ";
				m = m.replace("\t", "  ");
				boolean center = m.startsWith("C]");
				if (center) {
					m = m.substring(2);
				}
				BufferedImage img = Words.menuWord(m, 12, 12);
				if (center) {
					g.drawImage(img, Reality360.WIDTH/2-img.getWidth()/2, y, null);
				} else {
					g.drawImage(img, 75, y, null);
				}
				y += img.getHeight()+5;
			}
			Words.setLetterColor(Color.WHITE);
		} else if (!playable) {
			int t = startTick;
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
				instructions = true;
			}
		} else if (secretTick>0) {
			if (secretTick%10<5) {
				int i = 0;
				for (boolean s:secrets) {
					if (s)
						i++;
				}
				BufferedImage img = Words.menuWord(""+i+" OF "+secrets.length+" "+"SECRETS FOUND", 24, 24);
				g.drawImage(img, Reality360.WIDTH/2-img.getWidth()/2, img.getHeight()*4, null);
			}
			secretTick--;
		} else if (secrets[2] || end ) {
			if (next) {
				GamePanel.level = new com.reality360.bth.Driver();
			} else {
				endTick++;
				g.setColor(Color.BLACK);
				int h = (int)(Reality360.HEIGHT/2*(endTick/100.0));
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, Reality360.WIDTH, h-5);
				g.fillRect(0, Reality360.HEIGHT-h+5, Reality360.WIDTH, h);
				g.setColor(Color.GREEN);
				g.fillRect(0, h-5, Reality360.WIDTH, 5);
				g.fillRect(0, Reality360.HEIGHT-h, Reality360.WIDTH, 5);
				if (h>=Reality360.HEIGHT/2) {
					next = true;
				}
			}
		} else if (secrets[3]) {
			if (next) {
				GamePanel.level = new com.reality360.levels.defendthebase.Driver();
			} else {
				endTick++;
				g.setColor(Color.BLACK);
				int h = (int)(Reality360.HEIGHT/2*(endTick/100.0));
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, Reality360.WIDTH, h-5);
				g.fillRect(0, Reality360.HEIGHT-h+5, Reality360.WIDTH, h);
				g.setColor(Color.GREEN);
				g.fillRect(0, h-5, Reality360.WIDTH, 5);
				g.fillRect(0, Reality360.HEIGHT-h, Reality360.WIDTH, 5);
				if (h>=Reality360.HEIGHT/2) {
					next = true;
				}
			}
		}
	}
	public void joystickValues(boolean stick, ArrayList<Boolean> buttons,
			float xAxis, float xRot, float yAxis, float yRot, float zAxis,
			float zRot) {
		if (instructions) {
			if (buttons.get(1)) {
				instructions = false;
				playable = true;
				button1 = true;
			}
		} else if (button1) {
			button1 = buttons.get(1);
		} else if (!secrets[2] && !secrets[3]) {
			if (xAxis>60){
	        	player.movePlayer(1,0,0);
	        	player.setMovingRightIdle(true);
				player.setMovingLeftIdle(false);
	        } else if (xAxis<40){
	        	player.movePlayer(-1,0,0);
	        	player.setMovingRightIdle(false);
				player.setMovingLeftIdle(true);
	        }
	        
	        if (buttons.get(1)){
	                player.setJumpKey(true);
	        } else {
	                player.setJumpKey(false);
	        }
		}
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
