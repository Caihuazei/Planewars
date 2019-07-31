package com.lh.runtime;

import com.lh.base.BaseSprite;
import com.lh.base.Drawable;
import com.lh.base.Moveable;
import com.lh.constant.FramConstant;
import until.ImageMap;

import java.awt.*;

public class Background extends BaseSprite implements Drawable, Moveable {

    private Image image;


    public Background() {
        this(0,FramConstant.FRAME_HEIGHT-ImageMap.get("bg1").getHeight(null),
                ImageMap.get("bg1"));
    }

    public Background(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void move() {
        setY(getY() + FramConstant.PLANE_INDEX);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);
        move();
    }
}
