package com.reality360.bth;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.reality360.Reality360;
import com.reality360.resource.Level;

public class Driver extends Level{

	public static ArrayList<Enemy> enemies;
	public static ArrayList<Bullet> bullets;
	public static ArrayList<PlayerProjectile> projectiles;
	public static PlayerShip player;
	
	public Driver(){
			enemies = new ArrayList<Enemy>();
			bullets = new ArrayList<Bullet>();
			projectiles = new ArrayList<PlayerProjectile>();
			player = new PlayerShip();
			enemies.add(new Enemy(Reality360.WIDTH/2,0,0,0,10));
	}
	public void paint(Graphics g) {
		player.paint(g);
		for(Bullet b:bullets){
			b.paint(g);
		}
		for(PlayerProjectile p:projectiles){
			p.paint(g);
		}
		for(Enemy e:enemies){
			e.paint(g);
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
		for(int i=0; i<projectiles.size(); i++){
			if(!projectiles.get(i).isAlive()){
				projectiles.remove(i);
				i--;
			}else{
				projectiles.get(i).tick();
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
