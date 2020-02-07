package com.BahKr.main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {
    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;

    public static float wallTimer = 0;

    private Thread thread;
    private boolean running = false;

    private Handler handler;
    private Random rand;

    public Game() {
        handler = new Handler();
        rand = new Random();
        this.addKeyListener(new KeyInput(handler));

        new Window(WIDTH, HEIGHT, "Title", this);

        handler.addObject(new Player(50, 50, ID.Player, handler));

    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.requestFocus();
        long lastLoopTime = System.nanoTime();
        final int TARGET_FPS = 60;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
        int fps = 0;
        long lastFpsTime = 0;
        while (running) {
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double delta = updateLength / ((double) OPTIMAL_TIME);

            lastFpsTime += updateLength;
            fps++;

            if (lastFpsTime >= 1000000000) {
                //System.out.println("FPS: " + fps);
                lastFpsTime = 0;
                fps = 0;
            }

            update(delta);
            render();

            try {
                Thread.sleep(Math.max(0, (lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        stop();
    }

    private void update(double delta) {
        wallTimer += delta;
        handler.update(delta);
        generateWalls();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        //Draw other stuff here
        handler.render(g);

        g.dispose();
        bs.show();
    }

    private void generateWalls() {
        if (wallTimer >= 75.f) {
            int wallY = rand.nextInt() % 151;
            handler.addObject(new Wall(WIDTH, 0, ID.Wall, 65, (HEIGHT / 2 - 50) + wallY));
            handler.addObject(new Wall(WIDTH, HEIGHT - (HEIGHT / 2 - 50 - wallY), ID.Wall, 65, (HEIGHT / 2 - 50 - wallY)));
            wallTimer = 0;
        }
    }

    public static void main(String[] args) {
        new Game();
    }
}
