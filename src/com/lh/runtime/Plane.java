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
    private int hp = 0;

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
        hp++;
        move();
        DropHp();
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
        epPlane();
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
        if (fire && FramConstant.KILL_EPPLAN <= 10) {
            GameFram gameFram = DataStore.get("gameFram");
            gameFram.bullets.add(new Bullet((getX() + ImageMap.get("plan2").getWidth(null) / 2 - 49),
                    getY(),
                    ImageMap.get("myb01")));

        }
        if (fire && FramConstant.KILL_EPPLAN >= 10 && FramConstant.KILL_EPPLAN <20) {
            GameFram gameFram = DataStore.get("gameFram");
            gameFram.bullets.add(new Bullet((getX() + ImageMap.get("plan2").getWidth(null) / 2 - 49),
                    getY(),
                    ImageMap.get("myb02")));

        }
        if (fire && FramConstant.KILL_EPPLAN >= 20) {
            GameFram gameFram = DataStore.get("gameFram");
            gameFram.bullets.add(new Bullet((getX() + ImageMap.get("plan2").getWidth(null) / 2 - 49),
                    getY(),
                    ImageMap.get("myb03")));

        }
    }

    /**
     * 加载敌方飞机
     */

    public void epPlane() {
        Random random = new Random();
        if (o >= 150) {
            int r = random.nextInt(600);
            int r2 = random.nextInt(600);
            GameFram gameFram = DataStore.get("gameFram");
            if (FramConstant.KILL_EPPLAN > 1) {
                if (r > 300)
                gameFram.epPlanes.add(new epPlane(r2 , 0, ImageMap.get("ep02")));
            }
            gameFram.epPlanes.add(new epPlane(r, 0, ImageMap.get("ep01")));
            o = 0;
        }
    }



    /**
     * 加载掉落生命、保护罩
     */

    public void DropHp() {
        GameFram gameFram = DataStore.get("gameFram");
        Random random = new Random();
        //加载护盾
        if (random.nextInt(1000) >=850){
            if (FramConstant.SHIELD){
                gameFram.items.add(new DropHp(gameFram.plane.getX() + image.getWidth(null) / 2 - 30,
                        gameFram.plane.getY() + image.getHeight(null) / 2 ,
                        ImageMap.get("shield3")));
            }
        }
        if (hp >= 300) {
            int r = random.nextInt(800);
            if (r % 2 == 0){
                gameFram.dropHps.add(new DropHp(r, 0, ImageMap.get("Thp")));
            }else {
                gameFram.dropHps.add(new DropHp(r, 0, ImageMap.get("shield")));
                //想items集合加载保护罩
            }
            hp = 0;
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
        if (e.getKeyCode() == KeyEvent.VK_R) {
            FramConstant.R = true;
        }

    }

    //创建矩形对象
    @Override
    public Rectangle rectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
    }
}
