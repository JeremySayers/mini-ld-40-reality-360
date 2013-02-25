package com.reality360.levels.defendthebase;

public class Bullet {

	private int shortest;
	private int longest;
	private int numerator;
	private int dx1;
	private int dy1;
	private int dx2;
	private int dy2;
	private boolean moving;
	private int x;
	private int y;
	private int x2;
	private int y2;
	private String name;

	public Bullet(int x, int y, int x2, int y2){
		this.setX(x);
		this.setY(y);
		this.x2 = x2;
		this.y2 = y2;
		int w = x2 - x ;
	    int h = y2 - y ;
	    int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0 ;
	    if (w<0) dx1 = -1 ; else if (w>0) dx1 = 1 ;
	    if (h<0) dy1 = -1 ; else if (h>0) dy1 = 1 ;
	    if (w<0) dx2 = -1 ; else if (w>0) dx2 = 1 ;
	    int longest = Math.abs(w) ;
	    int shortest = Math.abs(h) ;
	    if (!(longest>shortest)) {
	        longest = Math.abs(h) ;
	        shortest = Math.abs(w) ;
	        if (h<0) dy2 = -1 ; else if (h>0) dy2 = 1 ;
	        dx2 = 0 ;            
	    }
	    this.shortest = shortest;
	    this.longest = longest;
	    this.numerator = longest >> 1 ;
	    this.dx1 = dx1;
	    this.dy1 = dy1;
	    this.dx2 = dx2;
	    this.dy2 = dy2;
	    this.moving = true;
	    this.name = name;
	}
	
	public Bullet(int x, int y, String name){
		this.x = x;
		this.y = y;
		this.setName(name);
	}
	public void move(){
		    this.numerator += this.shortest ;
	        if (!(this.numerator<this.longest)) {
	            this.numerator -= this.longest ;
	            this.setX(this.getX() + this.dx1) ;
	            this.setY(this.getY() + this.dy1) ;
	        } else {
	            this.setX(this.getX() + this.dx2) ;
	            this.setY(this.getY() + this.dy2) ;
	        }
	        this.moving = false;
	     
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getY() {
		return y;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getX() {
		return x;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}
