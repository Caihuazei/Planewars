package until;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ImageMap {

    private  static final Map<String, Image> map = new HashMap<>();

    static {
        //添加背景
        map.put("bg1", ImageUntil.getImage("com\\lh\\imgs\\bg\\bg1.jpg"));
        //添加我方飞机
        map.put("plan2", ImageUntil.getImage("com\\lh\\imgs\\plane\\my_2.png"));
        //添加我方子弹
        map.put("myb01", ImageUntil.getImage("com\\lh\\imgs\\Bullet\\myb_1.png"));
        //加载敌方飞机
        map.put("ep01", ImageUntil.getImage("com\\lh\\imgs\\plane\\ep_1.png"));
        //加载敌方子弹
        map.put("epb01", ImageUntil.getImage("com\\lh\\imgs\\Bullet\\epb_1.png"));

    }

    public static Image get(String key){
        return map.get(key);
    }
}
