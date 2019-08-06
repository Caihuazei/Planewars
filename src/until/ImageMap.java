package until;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ImageMap {

    private  static final Map<String, Image> map = new HashMap<>();

    static {
        //添加背景
        map.put("bg1", ImageUntil.getImage("com\\lh\\imgs\\bg\\bg1.jpg"));
        //加载标题生命值图标
        map.put("Thp",ImageUntil.getImage("com\\lh\\imgs\\Hp\\TitleHp.png"));
        //添加我方飞机
        map.put("plan2", ImageUntil.getImage("com\\lh\\imgs\\plane\\my_2.png"));
        map.put("plan3", ImageUntil.getImage("com\\lh\\imgs\\plane\\my_3.png"));
        //添加我方子弹
        map.put("myb01", ImageUntil.getImage("com\\lh\\imgs\\Bullet\\myb_1.png"));
        map.put("myb02", ImageUntil.getImage("com\\lh\\imgs\\Bullet\\myb_2.png"));
        map.put("myb03", ImageUntil.getImage("com\\lh\\imgs\\Bullet\\myb_3.png"));

        //加载敌方飞机
        map.put("ep01", ImageUntil.getImage("com\\lh\\imgs\\plane\\ep_1.png"));
        map.put("ep02", ImageUntil.getImage("com\\lh\\imgs\\plane\\ep_2.png"));
        //加载敌方子弹
        map.put("epb01", ImageUntil.getImage("com\\lh\\imgs\\Bullet\\epb_1.png"));
        map.put("epb02", ImageUntil.getImage("com\\lh\\imgs\\Bullet\\epb_02.png"));

        //加载boss子弹
        map.put("epboss1", ImageUntil.getImage("com\\lh\\imgs\\Bullet\\bossBullet.png"));

        //加载击杀图标
        map.put("kill", ImageUntil.getImage("com\\lh\\imgs\\Kill\\Kill_01.png"));
        //加载护盾
        map.put("shield", ImageUntil.getImage("com\\lh\\imgs\\items\\Shield3.png"));
        map.put("shield2", ImageUntil.getImage("com\\lh\\imgs\\items\\shield2.png"));
        map.put("shield3", ImageUntil.getImage("com\\lh\\imgs\\items\\shield3.png"));


        //加载Boss飞船
        map.put("boss01", ImageUntil.getImage("com\\lh\\imgs\\boss\\boss_A_01.png"));
        map.put("boss02", ImageUntil.getImage("com\\lh\\imgs\\boss\\boss_A_02.png"));
        map.put("boss03", ImageUntil.getImage("com\\lh\\imgs\\boss\\boss_A_03.png"));
        map.put("boss04", ImageUntil.getImage("com\\lh\\imgs\\boss\\boss_A_04.png"));
        map.put("boss05", ImageUntil.getImage("com\\lh\\imgs\\boss\\boss_A_05.png"));
        map.put("boss06", ImageUntil.getImage("com\\lh\\imgs\\boss\\boss_A_06.png"));
        map.put("boss07", ImageUntil.getImage("com\\lh\\imgs\\boss\\boss_A_07.png"));
        map.put("boss08", ImageUntil.getImage("com\\lh\\imgs\\boss\\boss_A_08.png"));
        map.put("boss09", ImageUntil.getImage("com\\lh\\imgs\\boss\\boss_A_09.png"));


    }

    public static Image get(String key){
        return map.get(key);
    }
}
