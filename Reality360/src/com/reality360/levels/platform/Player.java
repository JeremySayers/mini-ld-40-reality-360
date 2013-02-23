package com.reality360.levels.platform;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.reality360.resource.Entity;


public class Player extends Entity {
	private int x;
	private int y;
	private int xVel;
	private int yVel;
	private int speed;
	private int gravity;
	private int width;
	private int height;
	private boolean movingLeft;
	private boolean movingRight;
	
	private boolean jumpKey;
	
	private int downY, upY, leftX, rightX, upLeft, downLeft, upRight, downRight, xTile, yTile;
	
	private int jumpStart = -18;
	private boolean isJumping = false;
	private int jumpSpeed = 0;
	public int getJumpSpeed() {
		return jumpSpeed;
	}

	public void setJumpSpeed(int jumpSpeed) {
		this.jumpSpeed = jumpSpeed;
	}

	public Player(int x, int y){
		setX(x);
		setY(y);
		xTile = (int)(Math.floor(x/40));
		yTile = (int)(Math.floor(y/40));
		setxVel(0);
		setyVel(0);
		setGravity(1);
		setWidth(30);
		setHeight(30);
		setSpeed(5);
	}

	public void movePlayer(int xVel, int yVel, int jump) {
		if (Math.abs(jump)==1) {
		    speed=jumpSpeed*jump;
		  } else {
		    speed=5;
		}
		  getMyCorners(x, y+speed*yVel,Platform.tiles);
		  if (yVel == -1) {
		    if (upLeft == 0 && upRight == 0) {
		      y += speed*yVel;
		    } else {
		      y = yTile*40;
		      jumpSpeed = 0;
		    }
		  }
		  if (yVel == 1) {
		    if (downLeft == 0 && downRight == 0) {
		      y += speed*yVel;
		    } else {
		      y = (yTile+1)*40-height;
		      isJumping = false;
		    }
		  }
		  getMyCorners (x+speed*xVel, y, Platform.tiles);
		  if (xVel == -1) {
			  
		    if (downLeft == 0 && upLeft == 0) {
		      x += speed*xVel;
		    } else {
		    	
		    	System.out.println("DL: "+downLeft);
		      x = xTile*40;
		    }
		    fall();
		  }
		  if (xVel == 1) {
			 
		    if (upRight == 0 && downRight == 0) {
		      x += speed*xVel;
		    } else {
		       x = (xTile+1)*40-width;
		    }
		    fall();
		  }
		  xTile = (int)(Math.floor(x/40));
		  yTile = (int)(Math.floor(y/40));
	} 
	public void getMyCorners (int x, int y,Tile tiles[][]) {
		  downY = (int) Math.floor((y+30-1)/40);
		  upY = (int) Math.floor((y)/40);
		  leftX = (int) Math.floor((x)/40);
		  rightX = (int) Math.floor((x+30-1)/40);
		  //check if they are walls
		  upLeft = tiles[upY][leftX].getType();
		  downLeft = tiles[downY][leftX].getType();
		  upRight = tiles[upY][rightX].getType();
		  downRight = tiles[downY][rightX].getType();
	}
	public void jump() {
		  jumpSpeed = jumpSpeed+gravity;
		  if (jumpSpeed>40-height) {
		    jumpSpeed = 40-height;
		  }
		  if (jumpSpeed<0) {
		    movePlayer(0, -1, -1);  
		  } else if (jumpSpeed>0) {
		    movePlayer(0, 1, 1);  
		  }
	}
	public void fall() {
		  if (!isJumping) {
		    getMyCorners (x, y+1,Platform.tiles);
		    if (downLeft == 0 && downRight == 0) {
		      jumpSpeed = 0;
		      isJumping = true;
		    }
		  }
	}
	public void updateKeys(){
		if (movingLeft)
			movePlayer(-1,0,0);
		if (movingRight)
			movePlayer(1,0,0);
		if (jumpKey){
			if (!isJumping){
			setJumping(true);
			setJumpSpeed(getJumpStart());
			System.out.println("Should be jumping!");
			}
		}
			
	}
	public void paint(Graphics g) {		
		//Draw the player
		g.setColor(Color.GREEN);
		g.fillRect(this.getX(),this.getY(),30,30);
	}	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getxVel() {
		return xVel;
	}

	public void setxVel(int xVel) {
		this.xVel = xVel;
	}

	public int getyVel() {
		return yVel;
	}

	public void setyVel(int yVel) {
		this.yVel = yVel;
	}

	public int getGravity() {
		return gravity;
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

	public boolean isJumpKey() {
		return jumpKey;
	}

	public void setJumpKey(boolean jumpKey) {
		this.jumpKey = jumpKey;
	}

	public int getJumpStart() {
		return jumpStart;
	}

	public void setJumpStart(int jumpStart) {
		this.jumpStart = jumpStart;
	}

	public boolean isJumping() {
		return isJumping;
	}

	public void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}

	public void setGravity(int gravity) {
		this.gravity = gravity;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return false;
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
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
}