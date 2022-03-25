package org.jebtk.modern.css;

import java.util.HashMap;
import java.util.Map;



public class CSSColorMapService {

  private static class CSSColorServiceLoader {
    private static final CSSColorMapService INSTANCE = new CSSColorMapService();
  }

  public static CSSColorMapService getInstance() {
    return CSSColorServiceLoader.INSTANCE;
  }

  private final Map<String, CSSBaseColorProp> mColorMap = new HashMap<>();

  private CSSColorMapService() {
    // Do nothing
  }

  public CSSBaseColorProp get(String color) {
    if (!mColorMap.containsKey(color)) {
      mColorMap.put(color, CSSColorMap.fromHtml(color));
    }
    
    return mColorMap.get(color);
  }

  public CSSBaseColorProp get(String color1, String color2) {
    String key = color1 + color2;
    
    System.err.println("css color map:" + key);
    
    if (!mColorMap.containsKey(key)) {
      mColorMap.put(key, CSSColorMap.fromHtml(color1, color2));
    }
    
    return mColorMap.get(key);
  }
}
