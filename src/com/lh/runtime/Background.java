package com.lh.runtime;

import com.lh.base.BaseSprite;
import com.lh.base.Drawable;
import com.lh.base.Moveable;
import until.ImageMap;

import java.awt.*;

public class Background extends BaseSprite implements Drawable, Moveable {

    private Image image;

    public Background() {
        this(0,0,ImageMap.get("bg1"));
    }

    public Background(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void move() {


    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);
    }
}
