package com.reality360.levels.platform;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.reality360.resource.Level;


public class Platform extends Level {
	Player player = new Player(50,520);
	static Room rooms = new Room(0);
	static int[][] tiles;
	Random r = new Random();
	boolean jumpKey;
	public Platform(){
		 tiles= rooms.getCurrentRoom();
	}
	public static void changeRoom(int roomNum){
		rooms = new Room(roomNum);
		tiles = rooms.getCurrentRoom();
	}
	public void tick(){
		player.fall();
		player.jump();
		player.updateKeys();
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
		for (int i = 0; i < 15; i++){
			for (int j = 0; j < 20; j++){
				if (tiles[i][j]== 1){
					g.setColor(Color.YELLOW);
					g.fillRect(j*40, i*40,40,40);
					g.setColor(Color.BLACK);
					g.drawRect(j*40, i*40,40,40);
				} else if(tiles[i][j] == 2){
					g.setColor(Color.BLUE);
					g.fillRect(j*40, i*40,40,40);
					g.setColor(Color.BLACK);
					g.drawRect(j*40, i*40,40,40);
				} else if(tiles[i][j] == 3){
					g.setColor(Color.ORANGE);
					g.fillRect(j*40, i*40,40,40);
					g.setColor(Color.BLACK);
					g.drawRect(j*40, i*40,40,40);
				} else if(tiles[i][j] == 4){
					g.setColor(Color.MAGENTA);
					g.fillRect(j*40, i*40,40,40);
					g.setColor(Color.BLACK);
					g.drawRect(j*40, i*40,40,40);
				}
			}
		}
		player.paint(g);
		
	}
}
