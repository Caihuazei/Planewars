package com.lh.runtime;

import com.lh.base.BaseSprite;
import com.lh.base.Drawable;
import com.lh.base.Moveable;
import com.lh.constant.FramConstant;
import com.lh.main.GameFram;
import until.DataStore;
import until.ImageMap;

import java.awt.*;
import java.util.List;

public class Bullet extends BaseSprite implements Drawable, Moveable {
    private Image image;
    private  int index = FramConstant.PLANE_INDEX * 10;

    public Bullet() {
//        this(3 * (ImageMap.get("plan2").getWidth(null)) /2 + ImageMap.get("myb01").getWidth(null)/2,
//                FramConstant.FRAME_HEIGHT - ImageMap.get("plan2").getHeight(null) - ImageMap.get("myb01").getHeight(null) ,
//                ImageMap.get("myb01"));
    }

    public Bullet(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        if (FramConstant.KILL_EPPLAN <= 10) {
            g.drawImage(image, getX(), getY(),
                    image.getWidth(null) ,
                    image.getHeight(null) ,
                    null);
        }
        if (FramConstant.KILL_EPPLAN > 10) {
            g.drawImage(image, getX(), getY(),
                    image.getWidth(null)  ,
                    image.getHeight(null) ,
                    null);
        }
        if (FramConstant.KILL_EPPLAN >= 20) {
            g.drawImage(image, getX(), getY(),
                    image.getWidth(null)  ,
                    image.getHeight(null)  ,
                    null);
        }

        move();
        borderTesting();
    }

    @Override
    public void move() {
        setY(getY() - index);
    }

    //边缘检测
    public void borderTesting(){
        if (getY() + image.getHeight(null) <= 30){
            GameFram gameFram= DataStore.get("gameFram");
            gameFram.bullets.remove(this);
        }
    }
    //创建矩形对象
    @Override
    public Rectangle rectangle() {
        return new Rectangle(getX(), getY(),
                image.getWidth(null),
                image.getHeight(null));
    }

    /**
     * 我方子弹与敌方飞机 碰撞检测
     * @param epPlaneList
     */
    public void  collisionTesting(List<epPlane> epPlaneList){
        GameFram gameFram = DataStore.get("gameFram");
        for (epPlane epPlane : epPlaneList) {
            if (epPlane.rectangle().intersects(this.rectangle())){
                epPlaneList.remove(epPlane);
                gameFram.bullets.remove(this);
                FramConstant.KILL_EPPLAN++;
            }
        }
    }
}
