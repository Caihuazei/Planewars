package com.lh.runtime;

import com.lh.base.BaseSprite;
import com.lh.base.Drawable;
import com.lh.base.Moveable;

import java.awt.*;

public class Energy extends BaseSprite implements Drawable {
    private Image image;

    public Energy() {

    }

    public Energy(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {

    }

}
