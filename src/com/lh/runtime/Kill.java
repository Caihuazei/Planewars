package com.lh.runtime;

import com.lh.base.BaseSprite;
import com.lh.base.Drawable;
import com.lh.constant.FramConstant;
import until.ImageMap;

import java.awt.*;

public class Kill extends BaseSprite implements Drawable {
    private  Image image;

    public Kill(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    public Kill() {
        this(120,
                FramConstant.FRAME_HEIGHT-200,
                ImageMap.get("kill"));
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, getX(), getY(),
                image.getWidth(null) ,
                image.getHeight(null) ,
                null);
        g.setColor(Color.red);
        g.setFont(new Font("楷体", Font.BOLD, 30));
        g.drawString("击毁了：" + FramConstant.KILL_EPPLAN + " 架战机",
                 0,
                 FramConstant.FRAME_HEIGHT - 20);
    }
}
