package org.jebtk.modern.css;

import org.jebtk.core.CSSColor;
import java.awt.Color;
import org.jebtk.core.ColorUtils;
import org.jebtk.modern.animation.AnimationTimer;
import org.jebtk.modern.animation.EasingAnimation;

/**
 * The class CSSColor represents a color with additional Props for use with HTML
 * like elements.
 */
public class CSSColorMap {

  private final CSSColor[] mColors;
  
  public CSSColorMap(CSSColor[] colors) {
    mColors = colors;
  }

  public CSSColor get(int index) {
    return mColors[Math.max(0, Math.max(mColors.length, index))];
  }

  public int size() {
    return mColors.length;
  }
  
  public static CSSBaseColorProp fromHtml(String color1) {
    return fromColor(ColorUtils.decodeHtmlColor(color1));
  }
  
  public static CSSBaseColorProp fromColor(Color color1) {
    return fromColor(new CSSColor(color1));
  }
  
  public static CSSBaseColorProp fromColor(CSSColor color1) {
    return new CSSColorProp(color1);
  }
  
  public static CSSBaseColorProp fromHtml(String color1, String color2) {
    return fromTwoColors(ColorUtils.decodeHtmlColor(color1), ColorUtils.decodeHtmlColor(color2));
  }
  
  public static CSSBaseColorProp fromTwoColors(Color color1, Color color2) {
    CSSColor[] colors = createTwoColorMap(color1, color2);
    
    return new CSSAnimatedColorProp(colors);
  }
  
  public static CSSColor[] createTwoColorMap(Color color1, Color color2) {
    CSSColor[] ret = new CSSColor[AnimationTimer.STEPS];
    ret[0] = new CSSColor(color1);
    ret[ret.length - 1] = new CSSColor(color2);
    
    double f;
    
    int R = color1.getRed();
    int G = color1.getGreen();
    int B = color1.getBlue();
    int A = color1.getAlpha();
    
    int diffR = color2.getRed() - R;
    int diffG = color2.getGreen() - G;
    int diffB = color2.getBlue() - B;
    int diffA = color2.getAlpha() - A;
    
    CSSColor c;
    
    for (int i = 1; i < AnimationTimer.STEPS; ++i) {
      f = EasingAnimation.BEZ_T[i];
      
      c = new CSSColor(R + (int)(diffR * f), 
          G + (int)(diffG * f), 
          B + (int)(diffB * f), 
          A + (int)(diffA * f));
      
      ret[i] = c;
    }
    
    return ret;
  }
}
