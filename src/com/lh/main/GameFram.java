package com.lh.main;

import com.lh.constant.FramConstant;
import com.lh.runtime.Background;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFram extends Frame {
       private Background bg = new Background();

    @Override
    public void paint(Graphics g) {
        bg.draw(g);
    }

    /**
     * 初始化窗口
     */
    public  void init(){
        setSize(FramConstant.FRAME_WIDTH,FramConstant.FRAME_HEIGHT);

        //设置居中

        setLocationRelativeTo(null);

        //设置不允许切换输入法
        enableInputMethods(false);

        //设置退出
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);

        new Thread(){
            @Override
            public void run() {
                repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
