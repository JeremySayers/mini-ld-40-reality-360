package com.reality360.levels.defendthebase;
import java.awt.Color;


public class Particle {
    
    public int x,y,dx,dy,size,life;
    public Color color;
    
    public Particle(int x, int y, int dx, int dy, int size, int life, Color color){
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.size = size;
        this.life = life;
        this.color = color;
    }
}