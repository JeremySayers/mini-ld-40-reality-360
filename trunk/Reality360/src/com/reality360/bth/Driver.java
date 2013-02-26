package com.reality360.bth;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.reality360.Reality360;
import com.reality360.menu.Words;
import com.reality360.resource.Level;
import com.reality360.sounds.Sound;
import com.redsoxfan.libs.pixtact.Pixtact;

public class Driver extends Level{

	public static ArrayList<Enemy> enemies;
	public static ArrayList<Bullet> bullets;
	public static ArrayList<PlayerProjectile> projectiles;
	public static ArrayList<Pickup> pickups;
	public static PlayerShip player;
	private int scrolldistance1 = 0, scrolldistance2 = -700, tickCount = 10800;
	private Pixtact background1, background2;
	private boolean instructions;
	private boolean playable;
	private int startTick;
	private boolean button1;
	public static final Sound snd = new Sound("/com/reality360/sounds/DragonForce-TTFATF.mp3", true);
	public Driver(){
		Sound.stopAll();
		background1 = Pixtact.read(getClass().getResource("/bthBackground.png"));
		background2 = Pixtact.read(getClass().getResource("/bthBackground2.png"));
		background1.resize(900,700);
		background2.resize(900,700);
		enemies = new ArrayList<Enemy>();
		bullets = new ArrayList<Bullet>();
		projectiles = new ArrayList<PlayerProjectile>();
		pickups = new ArrayList<Pickup>();
		player = new PlayerShip();
		snd.play();
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
		synchronized(pickups){
			for(Pickup p:pickups){
				p.paint(g);
			}
		}
		g.drawImage(Words.menuWord(Integer.toString(tickCount/60),20,20),110,570,Integer.toString(tickCount/60).length()*20,20,null);
		
		if (instructions) {
			g.setColor(new Color(255, 255, 255, 192));
			g.fillRoundRect(50, 50, Reality360.WIDTH-100, Reality360.HEIGHT-100, 100, 100);
			String msg = "C]LEVEL 3 - BOSS LEVEL\n" +
					"\n" +
					"Objective:\n" +
					"\tTo survive until the timer reaches\n" +
					"\tzero then defeat the master Virtual\n" +
					"\tReality core\n" +
					"\n" +
					"\tMake sure to grab pickups to boost\n"+ 
					"\tyour damage and health\n" +
					"\t\t-Green pickups are HP\n" +
					"\t\t-Yellow pickups are Damage\n" +
					"\n" +
					"\tHealth and Damage Bars are in bottom\n" +
					"\tleft along with the timer\n" +
					"\n"+
					"Keyboard Controls:\n"+
					"\tArrow Keys - Move\n" +
					"\tSpace - Shoot\n" +
					"\n" +
					"Joystick Controls - Desktop Only:\n"+
					"\tX Axis - Move Left or Right\n" +
					"\tY Axis - Move Up or Down\n" +
					"\tButton 2 - Shoot\n" +
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
		}
	}
	public void tick() {
		if (!playable) {
			startTick ++;
		} else if (!instructions) {
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
		}
		synchronized(pickups){
			for(int i=0; i<pickups.size(); i++){
				if(!pickups.get(i).isAlive()){
					pickups.remove(i);
					i--;
				}else{
					pickups.get(i).tick();
					if(pickups.get(i).isHit(Driver.player.getImg())){
						if(pickups.get(i).isHealth()) Driver.player.pickupHealth(pickups.get(i).getVal());
						else Driver.player.pickupDmg(pickups.get(i).getVal());
						pickups.get(i).kill();
					}
				}
			}
		}
		if(tickCount%120==0){
			if(tickCount/60>180){
				if(enemies.size()<3)enemies.add(new Enemy((int)(Math.random()*100.0)+300,-50,(int)(Math.random()*500.0)+50,(int)(Math.random()*100.0)+100,10*player.getPower()));
				else if(Math.random()*100>=90) enemies.add(new WallEnemy(10*player.getPower(),(Math.random()>.5)));
			}else if(tickCount/60>150){
				if(enemies.size()<5)enemies.add(new Enemy((int)(Math.random()*100.0)+300,-50,(int)(Math.random()*500.0)+50,(int)(Math.random()*100.0)+100,20*player.getPower()));
				else if(Math.random()*100>=80) enemies.add(new WallEnemy(15*player.getPower(),(Math.random()>.5)));
			}else if(tickCount/60>120){
				if(enemies.size()<8)enemies.add(new Enemy((int)(Math.random()*100.0)+300,-50,(int)(Math.random()*500.0)+50,(int)(Math.random()*100.0)+100,30*player.getPower()));
				else if(Math.random()*100>=50) enemies.add(new WallEnemy(30*player.getPower(),(Math.random()>.5)));
			}else if(tickCount/60>90){
				if(enemies.size()<10)enemies.add(new Enemy((int)(Math.random()*100.0)+300,-50,(int)(Math.random()*500.0)+50,(int)(Math.random()*100.0)+100,40*player.getPower()));
				else if(Math.random()*100>=50) enemies.add(new WallEnemy(40*player.getPower(),(Math.random()>.5)));
			}else if(tickCount/60>60){
				if(enemies.size()<10)enemies.add(new Enemy((int)(Math.random()*100.0)+300,-50,(int)(Math.random()*500.0)+50,(int)(Math.random()*100.0)+100,40*player.getPower()));
				else if(Math.random()*100>=50) enemies.add(new WallEnemy(40*player.getPower(),(Math.random()>.5)));
			}else if(tickCount/60>0){
				if(enemies.size()<10)enemies.add(new Enemy((int)(Math.random()*100.0)+300,-50,(int)(Math.random()*500.0)+50,(int)(Math.random()*100.0)+100,40*player.getPower()));
				else if(Math.random()*100>=50) enemies.add(new WallEnemy(40*player.getPower(),(Math.random()>.5)));
			}
		}
		tickCount--;
		scrolldistance1++;
		scrolldistance2++;
		player.tick();
	}
	public void keyPressed(KeyEvent e) {
		if (!instructions) {
			player.keyPressed(e);
		}
	}
	public void keyReleased(KeyEvent e) {
		if (instructions) {
			if (e.getKeyCode()==KeyEvent.VK_SPACE) {
				instructions = false;
				playable = true;
			}
		} else {
			player.keyReleased(e);
		}
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
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
		}  else {
			player.joystickValues(stick, buttons, xAxis, xRot, yAxis, yRot, zAxis, zRot);
		}
	}
	public void mouseMoved(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
}
