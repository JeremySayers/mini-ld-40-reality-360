package com.reality360.levels.platform;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.reality360.resource.Level;


public class Platform extends Level {
	Player player = new Player(385,200);
	Random r = new Random();
	public static Tile[][] tiles = new Tile[15][20];
	boolean jumpKey;
	public Platform(){
		//Generate bottom tiles
		for (int i = 0; i < 15; i++){
			for (int j = 0; j < 20; j++){
				if(i == 14){
					tiles[i][j] = new Tile(i*40,j*40,1);
				} else if(i == 0){
					tiles[i][j] = new Tile(i*40,j*40,1);
				}else if(j == 19){
					tiles[i][j] = new Tile(i*40,j*40,1);
				} else if(j == 0){
					tiles[i][j] = new Tile(i*40,j*40,1);
				} else {
					tiles[i][j] = new Tile(i*40,j*40,0);
				}
			}
		}
		for (int i = 0; i < 30; i++){
			tiles[r.nextInt(14)+1][r.nextInt(19)+1].setType(1);
		}
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
				if (tiles[i][j].getType() == 1){
					g.setColor(Color.YELLOW);
					g.fillRect(j*40, i*40,40,40);
					g.setColor(Color.BLACK);
					g.drawRect(j*40, i*40,40,40);
				} 
			}
		}
		player.paint(g);
		
	}
}
