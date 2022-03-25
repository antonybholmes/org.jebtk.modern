/**
 * Copyright 2018 Antony Holmes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jebtk.modern.css;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import org.jebtk.core.Props;
import org.jebtk.core.geom.IntRect;
import org.jebtk.modern.ModernComponent;
import org.jebtk.modern.theme.DrawUI;

/**
 * The Class DrawUI provides reusable drawing routines that can be shared by
 * multiple widgets. For example a button renderer can be used by a button or to
 * highlight the items in a list view for example.
 */
public abstract class CSSBaseUI extends DrawUI {
  
  @Override
  public void fill(ModernComponent c, Graphics2D g2, IntRect rect, Props props) {
    cssFill(c, g2, rect, props);
  }
  
  @Override
  public void outline(ModernComponent c, Graphics2D g2, IntRect rect, Props props) {
    cssOutline(c, g2, rect, props);
  }
  
  
  public static void setColor(String name, ModernComponent c, Graphics2D g2) {
    setColor(CSSPropName.parse(name), c, g2);
  }
  
  public static void setToColor(String name, ModernComponent c, Graphics2D g2) {
    setColor(CSSPropName.parse(name), c, g2, CSSProp.TO);
  }
  
  public static void setColor(String name, ModernComponent c, Graphics2D g2, Props props) {
    setColor(CSSPropName.parse(name), c, g2, props.getInt("frame"));
  }
  
  public static void setColor(CSSPropName name, ModernComponent c, Graphics2D g2) {
    setColor(name, c, g2, CSSProp.FROM);
  }
  
  public static void setColor(CSSPropName name, ModernComponent c, Graphics2D g2, int frame) {
    CSS2Props props = getStyle(c);
    
    System.err.println("css base ui:" + name);
    System.err.println("css base ui:" + c);
    System.err.println(frame);
    System.err.println(props.getColor(name).getKeyFrame(frame));
    g2.setColor(props.getColor(name).getKeyFrame(frame));
  }
  

  public static void cssFill(ModernComponent c, Graphics2D g2, IntRect rect, Props props) {
    int frame = props.getInt("frame");
    
    Color color;
    
    if (props != null && props.contains("color")) {
      color = props.getColor("color");
    } else {
      color = getStyle(c).getColor(CSSPropName.BACKGROUND_COLOR).getKeyFrame(frame);
    }
    
    if (color.getAlpha() == 0) {
      return;
    }
    
    g2.setColor(color);

    int rounding = cssRounding(c, Math.min(rect.w, rect.h), frame);
    
    if (c != null){
      System.err.println("Frame " + frame + " " + c.getClass() + " " + color + " " + rect + " " + rounding);
    }
    
    if (rounding > 0) {
      if (rect.w == rect.h && rounding >= rect.h / 2) {
        g2.fillOval(rect.x, rect.y, rect.w - 1, rect.w - 1);
      } else {
        System.err.println("roundr ");
        int r = Math.min(Math.min(rect.w, rect.h), rounding * 2);
        g2.fillRoundRect(rect.x, rect.y, rect.w, rect.h, r, r);
      }
    } else {
      g2.fillRect(rect.x, rect.y, rect.w, rect.h);
    }
  }

  

  public void cssOutline(ModernComponent c, Graphics2D g2, IntRect rect, Props props) {
    int frame = props.getInt("frame");
    
    CSS2Props style = getStyle(c);
    
    int lw = style.getNum(CSSPropName.BORDER_WIDTH).getKeyFrame(frame).getInt();
    
    if (lw == 0) {
      return;
    }
    
    g2.setStroke(new BasicStroke(lw));
    
    Color color;
    
    if (props != null && props.contains("color")) {
      color = props.getColor("color");
    } else {
      color = style.getColor(CSSPropName.BORDER_COLOR).getKeyFrame(frame);
    }
    
    if (color.getAlpha() == 0) {
      return;
    }
    
    g2.setColor(color);

    int rounding = cssRounding(c, Math.min(rect.w, rect.h), frame);

    if (rounding > 0) {
      if (rect.w == rect.h && rounding >= rect.h / 2) {
        g2.drawOval(rect.x + 1, rect.y + 1, rect.w - 2, rect.w - 2);
      } else {
        int r = Math.min(Math.min(rect.w, rect.h), rounding * 2) - 1;
        g2.drawRoundRect(rect.x, rect.y, rect.w - 1, rect.h - 1, r, r);
      }
    } else {
      g2.drawRect(rect.x, rect.y, rect.w - 1, rect.h - 1);
    }
  }
  
  public void cssBottomBorder(ModernComponent c, Graphics2D g2, IntRect rect, Props props) {
    int frame = props.getInt("frame");
    
    CSS2Props style = getStyle(c);
    
    int lw = style.getNum(CSSPropName.BORDER_WIDTH).getKeyFrame(frame).getInt();
    
    if (lw == 0) {
      return;
    }
    
    g2.setStroke(new BasicStroke(lw));
    
    Color color;
    
    if (props != null && props.contains("color")) {
      color = props.getColor("color");
    } else {
      color = style.getColor(CSSPropName.BORDER_BOTTOM_COLOR).getKeyFrame(frame);
    }
    
    if (color.getAlpha() == 0) {
      return;
    }
    
    g2.setColor(color);

    int y = rect.y + rect.h - 1;

    g2.drawLine(rect.x, y, rect.x + rect.w, y);
  }

  public static int cssRounding(ModernComponent c, int h, int frame) {
    CSSNum n = getStyle(c).getNum(CSSPropName.BORDER_RADIUS).getKeyFrame(frame);

    switch (n.getNumType()) {
      case PERCENT:
        return Math.min(h, (int) Math.round(n.getFloat() / 100 * h));
      default:
        return n.getInt();
    }
  }
  
  public static CSS2Props getStyle(ModernComponent c) {
    if (c != null) {
      return c.getCSSProps(); // getFromKeyFrame();
    } else {
      // Return the reference style class if all else fails
      return CSSKeyFramesService.getInstance().getStyleClass("widget");
    }
  }
}
