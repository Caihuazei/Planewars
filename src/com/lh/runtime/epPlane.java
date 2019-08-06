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
    private boolean right;

    public epPlane() {
    }

    public epPlane(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }


    public void fire() {
        if (fire >= 80) {
            GameFram gameFram = DataStore.get("gameFram");
            if (image == ImageMap.get("ep01")){
                gameFram.epBullets.add(new epBullet(getX() + 45,
                        getY() + ImageMap.get("epb01").getHeight(null) ,
                        ImageMap.get("epb01")));
        }else if (image == ImageMap.get("ep02")){
                gameFram.epBullets.add(new epBullet(
                        getX() + image.getWidth(null) / 2 - 43,
                        getY() + image.getHeight(null) /2,
                        ImageMap.get("epb02")));
            }
            fire = 0;
        }

    }

    @Override
    public void draw(Graphics g) {
        move();
        if (image == ImageMap.get("ep01")){
            g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
        }
        if (image == ImageMap.get("ep02")){
            g.drawImage(image, getX(), getY(),
                    image.getWidth(null) ,
                    image.getHeight(null) , null);
        }
        fire++;
        fire();
        borderTesting();
    }

    @Override
    public void move() {
        if (getX() < 0){
            right = true;
        }else if (getX() > FramConstant.FRAME_WIDTH - image.getWidth(null)){
            right = false;
        }

        if (image == ImageMap.get("ep01")){
            setY(getY() + index);
        }else if (image == ImageMap.get("ep02") && right){
            setX(getX() + FramConstant.PLANE_INDEX);
            setY(getY() + FramConstant.PLANE_INDEX);
        }else if (image == ImageMap.get("ep02") && right == false){
            setX(getX() - FramConstant.PLANE_INDEX);
            setY(getY() + FramConstant.PLANE_INDEX);
        }

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
