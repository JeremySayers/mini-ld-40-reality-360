package com.reality360.levels.defendthebase;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import com.reality360.resource.Level;

public class Driver extends Level{
	Base player;
	int mouseX, mouseY;
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	int tickCounter = 0;
	public Driver(){
		player = new Base();
	}
	public void paint(Graphics g) {
		player.paint(g);
		g.setColor(Color.RED);
		g.fillRect(mouseX-10, mouseY-1, 20, 2);
		g.fillRect(mouseX-1, mouseY-10, 2, 20);
		g.setColor(Color.BLUE);
		for (Enemy e: enemies){
			g.fillRect(e.getX(), e.getY(), 40, 40);
		}
	}

	public void tick() {
		player.tick();
		if (tickCounter == 120){
			tickCounter = 0;
			makeEnemy();
		} else {
			tickCounter++;
		}
		for (int i = 0; i < enemies.size(); i++){
			
		}
	}
	public void makeEnemy(){
		Random r = new Random();
		int select = r.nextInt(4)+1;
		if (select == 1){
			enemies.add(new Enemy(r.nextInt(800)+1,-50,player.getCenterX(),player.getCenterY(),1));
		} else if (select == 2){
			enemies.add(new Enemy(880,r.nextInt(600)+1,player.getCenterX(),player.getCenterY(),1));
		} else if (select == 3){
			enemies.add(new Enemy(r.nextInt(800)+1,700,player.getCenterX(),player.getCenterY(),1));
		} else if (select == 4){
			enemies.add(new Enemy(-100,r.nextInt(600)+1,player.getCenterX(),player.getCenterY(),1));
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		player.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		player.mouseReleased(e);
	}

	@Override
	public void joystickValues(boolean stick, ArrayList<Boolean> buttons,
			float xAxis, float xRot, float yAxis, float yRot, float zAxis,
			float zRot) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		player.mouseMoved(e);
		mouseX = e.getX();
		mouseY = e.getY();
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		player.mouseMoved(e);
		mouseX = e.getX();
		mouseY = e.getY();
		
	}

}
