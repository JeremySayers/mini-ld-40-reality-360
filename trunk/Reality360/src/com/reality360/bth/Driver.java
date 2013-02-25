package com.reality360.bth;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.reality360.menu.Words;
import com.reality360.resource.Level;
import com.redsoxfan.libs.pixtact.Pixtact;

public class Driver extends Level{

	public static ArrayList<Enemy> enemies;
	public static ArrayList<Bullet> bullets;
	public static ArrayList<PlayerProjectile> projectiles;
	public static ArrayList<Pickup> pickups;
	public static PlayerShip player;
	private int scrolldistance1 = 0, scrolldistance2 = -700, tickCount = 18000;
	private Pixtact background1, background2;
	
	public Driver(){
		background1 = Pixtact.read(getClass().getResource("/bthBackground.png"));
		background2 = Pixtact.read(getClass().getResource("/bthBackground2.png"));
		background1.resize(900,700);
		background2.resize(900,700);
		enemies = new ArrayList<Enemy>();
		bullets = new ArrayList<Bullet>();
		projectiles = new ArrayList<PlayerProjectile>();
		pickups = new ArrayList<Pickup>();
		player = new PlayerShip();
	}
	public void paint(Graphics g) {
		
		background1.setX(0-player.getX()*100/800);
		background1.setY(scrolldistance1);
		background2.setX(0-player.getX()*100/800);
		background2.setY(scrolldistance2);
		
		if(scrolldistance1>=700){
			scrolldistance1=-700;
		}else if(scrolldistance2>=700){
			scrolldistance2=-700;
		}
		
		background1.drawImage(g);
		background2.drawImage(g);
		player.paint(g);
		synchronized(enemies){
			for(Enemy e:enemies){
				e.paint(g);
			}
		}
		synchronized(bullets){
			for(Bullet b:bullets){
				b.paint(g);
			}
		}
		synchronized(projectiles){
			for(PlayerProjectile p:projectiles){
				p.paint(g);
			}
		}
		g.drawImage(Words.menuWord(Integer.toString(tickCount/60),20,20),400,10,Integer.toString(tickCount/60).length()*20,20,null);
	}
	public void tick() {
		synchronized(enemies){
			for(int i=0; i<enemies.size(); i++){
				if(!enemies.get(i).isAlive()){
					enemies.remove(i);
					i--;
				}else{ 
					enemies.get(i).tick();
					synchronized(projectiles){
						for(PlayerProjectile p:projectiles){
							if(enemies.get(i).isHit(p.getImg())){
								enemies.get(i).doDamage(p.getDamage());
								p.kill();
							}
						}
					}
				}
			}
		}
		synchronized(bullets){
			for(int i=0; i<bullets.size(); i++){
				if(!bullets.get(i).isAlive()){
					bullets.remove(i);
					i--;
				}else{
					bullets.get(i).tick();
					if(bullets.get(i).collision()){
						Driver.player.doDamge(bullets.get(i).getDamage());
						bullets.remove(i);
					}
				}
			}
		}
		synchronized(projectiles){
			for(int i=0; i<projectiles.size(); i++){
				if(!projectiles.get(i).isAlive()){
					projectiles.remove(i);
					i--;
				}else{
					projectiles.get(i).tick();
				}
			}
		}
		if(tickCount%120==0){
			if(tickCount/60>280){
				if(enemies.size()<3)enemies.add(new Enemy((int)(Math.random()*100.0)+300,-50,(int)(Math.random()*500.0)+50,(int)(Math.random()*100.0)+100,10));
				else if(Math.random()*100>=90) enemies.add(new WallEnemy(15,(Math.random()>.5)));
			}else if(tickCount/60>240){
				if(enemies.size()<5)enemies.add(new Enemy((int)(Math.random()*100.0)+300,-50,(int)(Math.random()*500.0)+50,(int)(Math.random()*100.0)+100,20));
				else if(Math.random()*100>=80) enemies.add(new WallEnemy(30,(Math.random()>.5)));
			}else if(tickCount/60>200){
				if(enemies.size()<5)enemies.add(new Enemy((int)(Math.random()*100.0)+300,-50,(int)(Math.random()*500.0)+50,(int)(Math.random()*100.0)+100,30));
				else if(Math.random()*100>=70) enemies.add(new WallEnemy(50,(Math.random()>.5)));
			}else if(tickCount/60>150){
				
			}
		}
		tickCount--;
		scrolldistance1++;
		scrolldistance2++;
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
	public void joystickValues(boolean stick, ArrayList<Boolean> buttons,
			float xAxis, float xRot, float yAxis, float yRot, float zAxis,
			float zRot) {
		player.joystickValues(stick, buttons, xAxis, xRot, yAxis, yRot, zAxis, zRot);
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
