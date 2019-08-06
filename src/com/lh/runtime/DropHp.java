package com.lh.runtime;

import com.lh.base.BaseSprite;
import com.lh.base.Drawable;
import com.lh.base.Moveable;
import com.lh.constant.FramConstant;
import com.lh.main.GameFram;
import until.DataStore;
import until.ImageMap;

import java.awt.*;

public class DropHp extends BaseSprite implements Drawable, Moveable {
    private Image image;

    public DropHp() {

    }

    public DropHp(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        if (image == ImageMap.get("Thp")) {
            g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
        }
        if (image == ImageMap.get("shield")) {
            g.drawImage(image, getX(), getY(), image.getWidth(null) ,
                    image.getHeight(null), null);
        } else  {
            g.drawImage(image, getX(), getY(),
                    image.getWidth(null)  / 2,
                    image.getHeight(null) / 2
                    , null);
        }
        move();
    }

    @Override
    public void move() {
        setY(getY() + FramConstant.PLANE_INDEX * 3);
    }

    @Override
    public Rectangle rectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
    }

    public void collisionTesting(Plane plane) {

        GameFram gameFram = DataStore.get("gameFram");

        if (plane.rectangle().intersects(this.rectangle())) {
            gameFram.dropHps.remove(this);

            //判断血包相交
            if (this.image.equals(ImageMap.get("Thp"))) {
                FramConstant.NUMBER_COLLISIONS++;
            }
            //判断护盾相交
            if (this.image.equals(ImageMap.get("shield"))) {
                FramConstant.SHIELD = true;
                gameFram.index +=1000;
//                gameFram.dropHps.remove(this);
//                this.setX(ImageMap.get("plan2").);
            }

            //判断生命值
            if (FramConstant.NUMBER_COLLISIONS == 0) {
                gameFram.gameOver = true;
            }
        }
    }
}
