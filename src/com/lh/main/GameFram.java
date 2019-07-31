package com.lh.main;

import com.lh.constant.FramConstant;
import com.lh.runtime.*;


import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameFram extends Frame {
       private Background bg = new Background();
       private Plane plane = new Plane();
       public final  CopyOnWriteArrayList<Bullet> bullets = new CopyOnWriteArrayList<>();
       public final  CopyOnWriteArrayList<epPlane> epPlanes = new CopyOnWriteArrayList<>();
       public final  CopyOnWriteArrayList<epBullet> epBullets = new CopyOnWriteArrayList<>();
       public  boolean gameOver;


       //添加画笔
    @Override
    public void paint(Graphics g) {
        if (!gameOver){
            bg.draw(g);
            plane.draw(g);
            for (Bullet bullet : bullets) {
                bullet.draw(g);
                bullet.collisionTesting(epPlanes);
            }
            for (epPlane epPlane : epPlanes) {
                epPlane.draw(g);
            }
            for (epBullet epBullet : epBullets) {
                epBullet.draw(g);
                epBullet.collisionTesting(plane);
            }

            g.setColor(Color.red);
            g.drawString(""+ epBullets.size(),100,100);
        }

    }

    /**
     * 初始化窗口
     */
    public  void init(){

        setSize(FramConstant.FRAME_WIDTH,FramConstant.FRAME_HEIGHT);

        //设置居中

        setLocationRelativeTo(null);

        //设置不允许切换输入法
        enableInputMethods(false);

        //设置退出
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //添加键盘监听
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                plane.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                plane.keyReleased(e);

            }
        });


        //设置延时器
        new Thread(){
            @Override
            public void run() {
                while (true) {
                    repaint();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        setVisible(true);
    }

    /**
     * 消除闪频 双缓存
     *
     */
    private Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(800,1000);
        }
        Graphics goff = offScreenImage.getGraphics();

        paint(goff);
        g.drawImage(offScreenImage,0,0,null);
    }
}
