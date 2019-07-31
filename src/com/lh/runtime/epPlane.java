package com.lh.runtime;

import com.lh.base.BaseSprite;
import com.lh.base.Drawable;
import com.lh.base.Moveable;
import com.lh.constant.FramConstant;
import com.lh.main.GameFram;
import until.DataStore;
import until.ImageMap;

import java.awt.*;

public class epPlane extends BaseSprite implements Drawable, Moveable {
    private Image image;
    private  int index = FramConstant.PLANE_INDEX * 2;
    private int fire = 0;

    public epPlane() {
        this(100,100,ImageMap.get("ep01"));
    }

    public epPlane(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }


    public void fire() {
        if (fire >= 80) {
            GameFram gameFram = DataStore.get("gameFram");
            gameFram.epBullets.add(new epBullet(getX() + 45,
                    getY() + ImageMap.get("epb01").getHeight(null) ,
                    ImageMap.get("epb01")));
            fire = 0;

        }
    }

    @Override
    public void draw(Graphics g) {
        fire++;
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
        fire();
        move();
        borderTesting();
    }

    @Override
    public void move() {
        setY(getY() + index);
    }
    public void borderTesting(){
        if (getY() - image.getHeight(null)  >= FramConstant.FRAME_HEIGHT){
            GameFram gameFram= DataStore.get("gameFram");
            gameFram.epPlanes.remove(this);
        }
    }

    @Override
    public Rectangle rectangle() {
        return new Rectangle(getX() , getY(),image.getWidth(null),image.getHeight(null));
    }
}
