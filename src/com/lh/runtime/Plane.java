package com.lh.runtime;

import com.lh.base.BaseSprite;
import com.lh.base.Drawable;
import com.lh.base.Moveable;
import com.lh.constant.FramConstant;
import com.lh.main.GameFram;
import until.DataStore;
import until.ImageMap;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Plane extends BaseSprite implements Drawable, Moveable {

    private Image image;
    private boolean top, right, down, left;
    private int index = FramConstant.PLANE_INDEX * 3;
    private boolean fire;
    private int o = 0;

    public Plane() {
        this((FramConstant.FRAME_WIDTH - ImageMap.get("plan2").getWidth(null)) / 2,
                FramConstant.FRAME_HEIGHT - ImageMap.get("plan2").getHeight(null),
                ImageMap.get("plan2"));
    }

    public Plane(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        o++;
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
        epPlane();
        move();
        borderTesting();


    }

    @Override
    public void move() {
        if (top) {
            setY(getY() - index);
        }
        if (right) {
            setX(getX() + index);
        }
        if (down) {
            setY(getY() + index);
        }
        if (left) {
            setX(getX() - index);
        }

    }

    /**
     * 加载子弹
     */
    public void fire() {
        if (fire) {
            GameFram gameFram = DataStore.get("gameFram");
            gameFram.bullets.add(new Bullet((getX()+ImageMap.get("plan2").getWidth(null)/2 - 49) ,
                    getY() ,
                    ImageMap.get("myb01")));

        }
    }

    /**
     * 加载敌方飞机
     */

    public void epPlane() {
        Random random = new Random();
        if (o>=150) {
            int r = random.nextInt(800);
            GameFram gameFram = DataStore.get("gameFram");
            gameFram.epPlanes.add(new epPlane(r, 0, ImageMap.get("ep01")));
            o = 0;
        }
    }

    /**
     * 设置边界
     */
    public void borderTesting() {
        if (getX() < 0) {
            setX(0);
        }
        if (getX() > FramConstant.FRAME_WIDTH - image.getWidth(null)) {
            setX(FramConstant.FRAME_WIDTH - image.getWidth(null));
        }
        if (getY() < 30) {
            setY(30);
        }
        if (getY() > FramConstant.FRAME_HEIGHT - image.getHeight(null)) {
            setY(FramConstant.FRAME_HEIGHT - image.getHeight(null));
        }
    }

    /**
     * 设置键盘按下效果
     *
     * @param e
     */
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            top = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            down = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_J) {
            fire = true;
        }
    }

    /**
     * 设置键盘抬起效果
     *
     * @param e
     */
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            top = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            right = false;

        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            down = false;

        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_J) {
            fire();
            fire = false;

        }

    }

    @Override
    public Rectangle rectangle() {
        return new Rectangle(getX() , getY(),image.getWidth(null),image.getHeight(null));
    }
}
