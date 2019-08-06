package com.lh.runtime;

import com.lh.base.BaseSprite;
import com.lh.base.Drawable;
import until.ImageMap;

import java.awt.*;

public class Hp extends BaseSprite implements Drawable {
    private Image image;

    public Hp(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    public Hp() {
        this(20,40, ImageMap.get("Thp"));
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, getX(), getY(),
                image.getWidth(null),
                image.getHeight(null),
                null);
    }


}
