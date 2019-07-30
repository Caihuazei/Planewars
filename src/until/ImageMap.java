package until;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ImageMap {

    private  static final Map<String, Image> map = new HashMap<>();

    static {
        map.put("bg1", ImageUntil.getImage("com\\lh\\imgs\\bg\\bg1.jpg"));
    }

    public static Image get(String key){
        return map.get(key);
    }
}
