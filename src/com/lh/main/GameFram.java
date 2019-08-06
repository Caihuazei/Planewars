package com.lh.main;

import com.lh.constant.FramConstant;
import com.lh.runtime.*;
import until.ImageMap;


import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameFram extends Frame {
    /**
     * 创建精灵对象
     */
    public boolean gameOver;

    private Background bg = new Background();
    public Plane plane = new Plane();
    private Kill kill = new Kill();


    public final CopyOnWriteArrayList<Bullet> bullets = new CopyOnWriteArrayList<>();
    public final CopyOnWriteArrayList<epPlane> epPlanes = new CopyOnWriteArrayList<>();
    public final CopyOnWriteArrayList<epBullet> epBullets = new CopyOnWriteArrayList<>();
    private Hp Thp = new Hp();
    public final CopyOnWriteArrayList<DropHp> dropHps = new CopyOnWriteArrayList<>();
    public Boss bos = new Boss();
    public final CopyOnWriteArrayList<bossBullet> bossBullets = new CopyOnWriteArrayList<>();
    public final ArrayList<DropHp> items = new ArrayList<>();
//    public final CopyOnWriteArrayList<Boss> bosses  = new CopyOnWriteArrayList<>();


    /**
     * 加载画笔 及各个元素
     *
     * @param g
     */
    public int index;

    @Override
    public void paint(Graphics g) {
        if (!gameOver) {

            bg.draw(g);

            Thp.draw(g);

            kill.draw(g);

            plane.draw(g);

            for (Bullet bullet : bullets) {
                bullet.draw(g);
                bullet.collisionTesting(epPlanes);
            }

            for (epPlane epPlane : epPlanes) {
                epPlane.draw(g);
            }

            //便利 护盾生命
            for (epBullet epBullet : epBullets) {
                epBullet.draw(g);
                //判断护盾
                if (!FramConstant.SHIELD) {
                    epBullet.collisionTesting(plane);
                } else if (FramConstant.SHIELD) {
                    index--;
                    if (index == 0){
                        FramConstant.SHIELD = false;
                    }

                }
            }
            for (DropHp dropHp : dropHps) {
                dropHp.draw(g);
                dropHp.collisionTesting(plane);

            }
            for (bossBullet bossBullet : bossBullets) {
                bossBullet.draw(g);
            }
            for (int i = 0; i < FramConstant.BOSS_HP; i++) {
                if (FramConstant.KILL_EPPLAN >= 40) {
                bos.collisionTesting(bullets);}
            }
            if (FramConstant.KILL_EPPLAN >= 40) {
            bos.draw(g);
            }

            for (DropHp item : items) {
                item.draw(g);
            }


//            bosses.get(index++ / 3).draw(g);
//            if (index == 27){
//                index = 0;
//            }
            double x;
            x = (double) index /100;
            g.setFont(new Font("楷体", Font.BOLD, 25));
            g.drawString("无敌时刻：" + x, 0, 100);
            g.setColor(Color.red);
            g.setFont(new Font("Tahoma", Font.BOLD, 20));
            g.drawString(": " + FramConstant.NUMBER_COLLISIONS + "",
                    25 + ImageMap.get("Thp").getWidth(null),
                    50 + ImageMap.get("Thp").getHeight(null) / 2);
            if (FramConstant.BOSS == true) {
                gameOver = true;
            }

        } else {

                if (FramConstant.BOSS_HP == 0 ){
                    bg.draw(g);
                    g.setColor(Color.red);
                    g.setFont(new Font("微软雅黑", Font.BOLD, 40));
//            g.drawString("game over", 280, 500);
                    g.drawString("Congratulations on your victory！", 80, 500);
                    g.setFont(new Font("微软雅黑", Font.BOLD, 20));
                    g.setColor(Color.pink);
                    g.drawString("  The producer is Managing Wu Yanzu", 400, 960);
//            if (FramConstant.R == true) {
//                gameOver = false;
//                reLoad();
//
//            }
                }
            if (FramConstant.NUMBER_COLLISIONS == 0){
                bg.draw(g);
                g.setColor(Color.red);
                g.setFont(new Font("微软雅黑", Font.BOLD, 50));
                g.drawString("game over", 280, 500);

                g.setColor(Color.pink);
                g.setFont(new Font("微软雅黑", Font.BOLD, 30));
                g.drawString("Press R to resurrect", 30, 80);
            }


            if (FramConstant.R == true) {
                gameOver = false;
                reLoad();

            }
        }
    }

    /**
     * 重启方法
     */
    public void reLoad() {
        FramConstant.NUMBER_COLLISIONS = 5;
        epPlanes.clear();
        epBullets.clear();
        dropHps.clear();
        bullets.clear();
        bossBullets.clear();
        FramConstant.BOSS = false;
        FramConstant.BOSS_HP = 100;
        FramConstant.KILL_EPPLAN = 0;
        FramConstant.R = false;

    }

    /**
     * 初始化窗口
     */
    public void init() {
        //设置窗口大小
        setSize(FramConstant.FRAME_WIDTH, FramConstant.FRAME_HEIGHT);

        //设置居中
        setLocationRelativeTo(null);

        //设置不允许切换输入法
        enableInputMethods(false);

        //固定窗口
        setResizable(false);

        //设置退出
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //添加键盘监听事件
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
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    repaint();
                    try {
                        Thread.sleep(11);
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
     */
    private Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(800, 1000);
        }
        Graphics goff = offScreenImage.getGraphics();

        paint(goff);
        g.drawImage(offScreenImage, 0, 0, null);
    }
}
