package com.BahKr.main;

import java.awt.*;

public abstract class GameObject {
    protected int x, y;
    protected ID id;
    protected float velX, velY;

    public GameObject(int x, int y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void update(double delta);
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public void setX(int x){
        this.x = x;
    }
    public int getX(){
        return x;
    }
    public void setY(int y){
        this.y = y;
    }
    public int getY(){
        return y;
    }
    public void setId(ID id){
        this.id = id;
    }
    public ID getId(){
        return id;
    }
    public void setVelX(int velX){
        this.velX = velX;
    }
    public float getVelX(){
        return velX;
    }
    public void setVelY(int velY){
        this.velY = velY;
    }
    public float getVelY(){
        return velY;
    }
}
