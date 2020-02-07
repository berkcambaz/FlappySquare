package com.BahKr.main;

import java.awt.*;
import java.util.LinkedList;

public class Handler {
    LinkedList<GameObject> object = new LinkedList<GameObject>();

    public void update(double delta){
        for (int i = 0; i < object.size(); i++){
            GameObject tempObj = object.get(i);
            tempObj.update(delta);

            if(tempObj.getX() < -65 && tempObj.getId() == ID.Wall){
                this.removeObject(object.get(i));
            }
        }
    }

    public void render(Graphics g){
        for (int i = 0; i < object.size(); i++){
            GameObject tempObj = object.get(i);
            tempObj.render(g);
        }
    }

    public void addObject(GameObject obj){
        object.add(obj);
    }

    public void removeObject(GameObject obj){
        object.remove(obj);
    }
}
