package com.BahKr.main;

import java.awt.*;

public class Wall extends GameObject {
    private int sizeX, sizeY;
    public Wall(int x, int y, ID id, int sizeX, int sizeY) {
        super(x, y, id);
        velX = -4.25f;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    @Override
    public Rectangle getBounds(){
        return new Rectangle(x, y, sizeX, sizeY);
    }

    @Override
    public void update(double delta) {
        x += velX * delta;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, sizeX, sizeY);
    }
}
