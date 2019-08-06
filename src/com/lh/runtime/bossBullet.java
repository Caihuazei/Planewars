package com.lh.runtime;

import com.lh.base.BaseSprite;
import com.lh.base.Drawable;
import com.lh.base.Moveable;
import com.lh.constant.FramConstant;
import com.lh.main.GameFram;
import until.DataStore;
import until.ImageMap;

import java.awt.*;
import java.util.Random;

public class bossBullet extends BaseSprite implements Moveable, Drawable {
    private Image image;
    private int index;
    private double angel;

    public bossBullet() {

    }

    public bossBullet(int x, int y, Image image,double angel) {
        super(x, y);
        this.image = image;
        this.angel = angel;
    }

    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
        borderTesting();

        /*if ( 150 <= index){
            move();
        }else if (index == 200){
            index = 0;
        }*/

    }

    @Override
    public void move() {
        GameFram gameFram = DataStore.get("gameFram");

                setX(getX() + (int)( 5 * Math.cos(angel)) );
                setY(getY() + (int)( 5 * Math.sin(angel)) );


//        if (getX() > 0 ||  image.getWidth(null) + getX() < FramConstant.FRAME_WIDTH
//                || getY() > 0 || getY() +image.getHeight(null) > FramConstant.FRAME_HEIGHT){

//        }
//        System.out.println(getX());


//        Random random = new Random();
//        int x = random.nextInt(11);
//        int y = random.nextInt(21);
//        if (getX() == getY()) {
//            if (x > 5) {
//                setX(getX() - FramConstant.PLANE_INDEX * 3);
//            }
//            if (x < 5) {
//                setX(getX() + FramConstant.PLANE_INDEX * 3);
//            }
//            setY(getY() - FramConstant.PLANE_INDEX * 6);
//        }
    }

    public void borderTesting() {
        if (getY() <= 30 || getY() >= FramConstant.FRAME_HEIGHT  ) {
            GameFram gameFram = DataStore.get("gameFram");
            gameFram.bossBullets.remove(this);
        }
        if (getX() <= 0 || getX() + image.getWidth(null) >= 800 ){
            GameFram gameFram= DataStore.get("gameFram");
            gameFram.bossBullets.remove(this);
        }
    }
}
