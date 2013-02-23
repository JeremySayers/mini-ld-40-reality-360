package com.reality360.bth;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.reality360.resource.Level;

public class Driver extends Level{

	public static ArrayList<Enemy> enemies;
	public static ArrayList<Bullet> bullets;
	public static PlayerShip player;
	
	public Driver(){
			enemies = new ArrayList<Enemy>();
			bullets = new ArrayList<Bullet>();
			player = new PlayerShip();
	}
	
	
	public void paint(Graphics g) {
		player.paint(g);
		for(Bullet b:bullets){
			b.paint(g);
		}
	}
	public void tick() {
		for(int i=0; i<enemies.size(); i++){
			if(!enemies.get(i).isAlive()){
				enemies.remove(i);
				i--;
			}else{ 
				enemies.get(i).tick();
			}
		}
		for(int i=0; i<bullets.size(); i++){
			if(!bullets.get(i).isAlive()){
				bullets.remove(i);
				i--;
			}else{
				bullets.get(i).tick();
			}
		}
		player.tick();
	}
	public void keyPressed(KeyEvent e) {
		player.keyPressed(e);
	}
	public void keyReleased(KeyEvent e) {
		player.keyReleased(e);
	}
	public void mousePressed(MouseEvent e) {
		
	}
	public void mouseReleased(MouseEvent e) {
		
	}
}
