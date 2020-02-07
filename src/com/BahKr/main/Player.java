package com.BahKr.main;

import java.awt.*;

public class Player extends GameObject {
    private float gravity = 0.175f;

    private Handler handler;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

    @Override
    public void update(double delta) {
        velY += gravity * delta;
        y += velY * delta;

        if (velY < -10)
            velY = -10;

        checkDeathConditions();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, 32, 32);
    }

    public void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObj = handler.object.get(i);
            if (tempObj.getId() == ID.Wall) {
                if (getBounds().intersects(tempObj.getBounds())) {
                    restartGame();
                }
            }
        }
    }

    public void checkDeathConditions() {
        collision();
        if (y > Game.HEIGHT - 32 || y < 0) {
            restartGame();
        }
    }

    private void restartGame() {
        y = 50;
        for (int i = handler.object.size() - 1; i > 0; i--) {
            if (handler.object.get(i).getId() == ID.Wall)
                handler.removeObject(handler.object.get(i));
        }
        Game.wallTimer = 0;
    }
}
