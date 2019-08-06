package com.lh.runtime;

import com.lh.base.BaseSprite;
import com.lh.base.Drawable;
import com.lh.base.Moveable;
import com.lh.constant.FramConstant;
import com.lh.main.GameFram;
import until.DataStore;
import until.ImageMap;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Boss extends BaseSprite implements Drawable, Moveable {

    private Image image;
    private int index = 0;
    private boolean right;
    private boolean top;
    private int fire;
    public static double angle;
//    static {
//        GameFram gameFram = DataStore.get("gameFram");
//        gameFram.bosses.add(new Boss(0,0, ImageMap.get("boss01")));
//        gameFram.bosses.add(new Boss(0,0, ImageMap.get("boss02")));
//        gameFram.bosses.add(new Boss(0,0, ImageMap.get("boss03")));
//        gameFram.bosses.add(new Boss(0,0, ImageMap.get("boss04")));
//        gameFram.bosses.add(new Boss(0,0, ImageMap.get("boss05")));
//        gameFram.bosses.add(new Boss(0,0, ImageMap.get("boss06")));
//        gameFram.bosses.add(new Boss(0,0, ImageMap.get("boss07")));
//        gameFram.bosses.add(new Boss(0,0, ImageMap.get("boss08")));
//        gameFram.bosses.add(new Boss(0,0, ImageMap.get("boss09")));
//    }

    private List<Image> imageList = new ArrayList<>();

    public List<Image> getImageList() {
        return imageList;
    }

    public Boss() {
        imageList.add(ImageMap.get("boss01"));
        imageList.add(ImageMap.get("boss02"));
        imageList.add(ImageMap.get("boss03"));
        imageList.add(ImageMap.get("boss04"));
        imageList.add(ImageMap.get("boss05"));
        imageList.add(ImageMap.get("boss06"));
        imageList.add(ImageMap.get("boss07"));
        imageList.add(ImageMap.get("boss08"));
        imageList.add(ImageMap.get("boss09"));
    }

    public Boss(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {


            fire++;
            g.drawImage(imageList.get(index++ / 4), getX(), getY(),
                    imageList.get(0).getWidth(null),
                    imageList.get(0).getHeight(null),
                    null);
            if (index == 36) {
                index = 0;
            }
            move();
            fire();


    }

    @Override
    public void move() {
        if (getX() < 0) {
            right = true;
        } else if (getX() > FramConstant.FRAME_WIDTH - imageList.get(0).getWidth(null)) {
            right = false;
        }
        if (getY() < 0) {
            top = false;
        } else if (getY() > FramConstant.FRAME_HEIGHT - imageList.get(0).getHeight(null) * 2) {
            top = true;
        }

        if (top) {
            setY(getY() - FramConstant.PLANE_INDEX);
        } else {
            setY(getY() + FramConstant.PLANE_INDEX);
        }
        if (right) {
            setX(getX() + FramConstant.PLANE_INDEX);
        } else {
            setX(getX() - FramConstant.PLANE_INDEX);

        }
    }

    public void fire() {
        if (fire == 10) {
            GameFram gameFram = DataStore.get("gameFram");

//            gameFram.bossBullets.add(new bossBullet((getX() + ImageMap.get("boss01").getWidth(null) / 2 ),
//                    getY() +  ImageMap.get("boss01").getHeight(null) / 2,
//                    ImageMap.get("epboss1")));
//            Random random = new Random();
//            int a = random.nextInt(10);
//            angle = a;
//            if (a!=0)
            angle += Math.PI / 6;
            gameFram.bossBullets.add(new bossBullet(
                    getX() + ImageMap.get("boss01").getWidth(null) / 2 /*+ 30 * (int) Math.cos(angle)*/,
                    getY() + ImageMap.get("boss01").getHeight(null) / 2 /*+ 30 * (int) Math.sin(angle)*/,
                    ImageMap.get("epboss1"),angle));

//            angle += Math.PI / 18;
//            System.out.println(angle);
            fire = 0;
        }

    }

    @Override
    public Rectangle rectangle() {
        return new Rectangle(getX(), getY(),
                imageList.get(0).getWidth(null),
                imageList.get(0).getHeight(null));
    }

    public void collisionTesting(List<Bullet> bullets) {
        GameFram gameFram = DataStore.get("gameFram");
        for (Bullet bullet : bullets) {
            if (bullet.rectangle().intersects(this.rectangle())) {
                bullets.remove(bullet);
//                imageList.remove(0);
                FramConstant.BOSS_HP--;
//                System.out.println(FramConstant.BOSS_HP);
                if (FramConstant.BOSS_HP <= 0) {
                    FramConstant.BOSS = true;
                }
            }
        }
    }
}
