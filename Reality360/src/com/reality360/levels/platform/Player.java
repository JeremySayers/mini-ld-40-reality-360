package com.reality360.levels.platform;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.reality360.GamePanel;
import com.reality360.Reality360;
import com.reality360.climber.Climber;
import com.reality360.resource.Entity;
import com.reality360.resource.RotateImage;


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
	private boolean movingLeftIdle;
	private boolean movingRightIdle = true;
	
	private boolean jumpKey;
	
	Image charLeftIdle, charRightIdle, charLeftMoving, charRightMoving, charJumping;
	
	private int downY, upY, leftX, rightX, upLeft, downLeft, upRight, downRight, xTile, yTile;
	private boolean down = false;
	
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
		charLeftIdle = Reality360.loadImage("/spensker_idlel.png", 30, 30);
		charRightIdle = Reality360.loadImage("/spensker_idler.png", 30, 30);
		charLeftMoving = Reality360.loadImage("/spensker_leftanim.png", 30, 30);
		charRightMoving = Reality360.loadImage("/spensker_rightanim.png", 30, 30);
		charJumping = Reality360.loadImage("/spensker_jumping.png", 30, 30);
	}

	public void movePlayer(int xVel, int yVel, int jump) {
		if (Math.abs(jump)==1) {
		    speed=jumpSpeed*jump;
		  } else {
		    speed = 5;
		}
		  getMyCorners(x, y+speed*yVel,Platform.tiles);
		  if (yVel == -1) {
		    if (upLeft <= 0 && upRight <= 0) {
		      y += speed*yVel;
		    } else if (checkTeleportCondition(upLeft, upRight) != 9999){
		    	teleport(checkTeleportCondition(upLeft, upRight));
		  	}else {
		      y = yTile*40;
		      jumpSpeed = 0;
		    }
		  }
		  if (yVel == 1) {
		    if ((downLeft == 0 && downRight == 0) || (down && downLeft==-2 && downRight==-2)) {
		      speed++;
		      y += speed*yVel;
		    } else if (checkTeleportCondition(downLeft, downRight) != 9999){
		    	teleport(checkTeleportCondition(downLeft, downRight));
		  	}else {
		      y = (yTile+1)*40-height;
		      isJumping = false;
		    }
		  }
		  getMyCorners (x+speed*xVel, y, Platform.tiles);
		  if (xVel == -1) {
			  
		    if (downLeft == 0 && upLeft == 0) {
		      x += speed*xVel;
		    } else if (checkTeleportCondition(downLeft,upLeft) != 9999){
		    	teleport(checkTeleportCondition(downLeft, upLeft));
		  	}else {
		    	
		      System.out.println("DL: "+downLeft);
		      x = xTile*40;
		    }
		    fall();
		  }
		  if (xVel == 1) {
			 
		    if (upRight == 0 && downRight == 0) {
		      x += speed*xVel;
		    } else if (checkTeleportCondition(upRight, downRight) != 9999){
		    	teleport(checkTeleportCondition(upRight, downRight));
		  	}else {
		       x = (xTile+1)*40-width;
		    }
		    fall();
		  }
		  xTile = (int)(Math.floor(x/40));
		  yTile = (int)(Math.floor(y/40));
	} 
	public int checkTeleportCondition(int tile1, int tile2){
		if(tile1 == 2 && tile2 == 2){
			return 1;
		} else if(tile1 == 3 && tile2 == 3){
			return 2;
		} else if(tile1 == 4 && tile2 == 4){
			return 3;
		} else if(tile1 == 5 && tile2 == 5){
			return 444;
		} else if(tile1 == 6 && tile2==6){
			return 4;
		} else if (tile1 == 7 && tile2==7){
			return 666;
		} else if (tile1 == 8 && tile2==8){
			return 0;
		} else if (tile1 == 9 && tile1==9) {
			return 444;
		} else if (tile1 == 10 && tile2==10) {
			return 777;
		} else if (tile1 == -5 && tile2==-5) {
			return 5;
		} else {
			return 9999;
		}
	}
	public void teleport(int roomNum){
		if  (roomNum == 666){
			if (!Platform.secrets[3]) {
				Platform.secretTick = 180;
				Platform.secrets[3] = true;
			}
			Platform.changing = 3;
		} else if (roomNum == 444) {
			if (Platform.currentRoom==0 && !Platform.secrets[2]) {
				Platform.secretTick = 180;
				Platform.secrets[2] = true;
				Platform.changing = 2;
			} else {
				Platform.end = true;
				Platform.changing = 2;
			}
		} else if (roomNum==777) {
			if (!Platform.secrets[4]) {
				Platform.secretTick = 180;
				Platform.secrets[4] = true;
			}
			Platform.changing = 4;
		} else {
			Platform.changeRoom(roomNum);
			x = 60;
			y = 520;
			if (roomNum==0 && !Platform.secrets[0]) {
				Platform.secrets[0] = true;
				Platform.secretTick = 180;
			} else if (roomNum==4 && !Platform.secrets[1]) {
				Platform.secrets[1] = true;
				Platform.secretTick = 180;
			} else if (roomNum==5 && !Platform.secrets[5]) {
				Platform.secrets[5] = true;
				Platform.secretTick = 180;
			}
		}
	}
	public void getMyCorners (int x, int y,int tiles[][]) {
		  downY = (int) Math.floor((y+30-1)/40);
		  upY = (int) Math.floor((y)/40);
		  leftX = (int) Math.floor((x)/40);
		  rightX = (int) Math.floor((x+30-1)/40);
		  //check if they are walls
		  upLeft = tiles[upY][leftX];
		  downLeft = tiles[downY][leftX];
		  upRight = tiles[upY][rightX];
		  downRight = tiles[downY][rightX];
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
		if (movingLeft){
			movePlayer(-1,0,0);
			setMovingLeftIdle(true);
			setMovingRightIdle(false);
		}
		if (movingRight){
			movePlayer(1,0,0);
			setMovingRightIdle(true);
			setMovingLeftIdle(false);
		}
		if (jumpKey){
			if (!isJumping){
			setJumping(true);
			setJumpSpeed(getJumpStart());
			System.out.println("Should be jumping!");
			}
		}
			
	}
	public void paint(Graphics g) {	
		 if (isJumping){
				if (isMovingRight()){
					RotateImage.rotate(g, x, y, charJumping,45);
				} else if(isMovingLeft()){
					RotateImage.rotate(g, x, y, charJumping,-45);
				} else {
					g.drawImage(charJumping,x,y,null);
				}
		} else if (movingLeft){
			g.drawImage(charLeftMoving, x, y,null);
		} else if (movingRight){
			g.drawImage(charRightMoving, x, y,null);
		}  else if (isMovingLeftIdle()){
			g.drawImage(charLeftIdle, x, y,null);
		} else if (isMovingRightIdle()){
			g.drawImage(charRightIdle, x, y,null);
		}  
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
		if (e.getKeyCode()==KeyEvent.VK_DOWN) {
			down = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_DOWN) {
			down = false;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	public boolean isMovingLeftIdle() {
		return movingLeftIdle;
	}

	public void setMovingLeftIdle(boolean movingLeftIdle) {
		this.movingLeftIdle = movingLeftIdle;
	}

	public boolean isMovingRightIdle() {
		return movingRightIdle;
	}

	public void setMovingRightIdle(boolean movingRightIdle) {
		this.movingRightIdle = movingRightIdle;
	}

	public void joystickValues(boolean stick, ArrayList<Boolean> buttons,
			float xAxis, float xRot, float yAxis, float yRot, float zAxis,
			float zRot) {
		down = xAxis>60;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}