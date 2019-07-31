package com.lh.runtime;

import com.lh.base.BaseSprite;
import com.lh.base.Drawable;
import com.lh.base.Moveable;
import com.lh.constant.FramConstant;
import com.lh.main.GameFram;
import until.DataStore;
import until.ImageMap;

import java.awt.*;

public class epBullet extends BaseSprite implements Drawable, Moveable {
    private Image image;
    private  int index = FramConstant.PLANE_INDEX * 5;


    public epBullet() {
        this(0,0, ImageMap.get("epb01"));

    }

    public epBullet(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
        move();
        borderTesting();
    }

    @Override
    public void move() {
        setY(getY() + index);
    }

    public void borderTesting(){
        if (getY() - image.getHeight(null) >= FramConstant.FRAME_HEIGHT){
            GameFram gameFram= DataStore.get("gameFram");
            gameFram.epBullets.remove(this);
        }
    }

    @Override
    public Rectangle rectangle() {
        return new Rectangle(getX() , getY(),image.getWidth(null),image.getHeight(null));
    }
    public void collisionTesting(Plane plane){
        GameFram gameFram = DataStore.get("gameFram");

        if (plane.rectangle().intersects(this.rectangle())){
            gameFram.epBullets.remove(this);
            gameFram.gameOver = true;
        }
    }
}
