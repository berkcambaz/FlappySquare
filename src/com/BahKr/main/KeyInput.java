package com.BahKr.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    private Handler handler;
    private boolean buttonPressed = false;

    public KeyInput(Handler handler){
        this.handler = handler;
    }

    @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++){
            GameObject tempObj = handler.object.get(i);

            if (tempObj.getId() == ID.Player){
                if (key == KeyEvent.VK_W && !buttonPressed) {
                    tempObj.setVelY(-4);
                    buttonPressed = true;
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        for (int i = 0; i < handler.object.size(); i++){
            GameObject tempObj = handler.object.get(i);

            if (tempObj.getId() == ID.Player){
                if (key == KeyEvent.VK_W) buttonPressed = false;
        }
        }
    }
}
