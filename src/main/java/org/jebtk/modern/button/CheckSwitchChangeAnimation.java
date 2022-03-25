/**
 * Copyright 2017 Antony Holmes
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
package org.jebtk.modern.button;

import java.awt.Color;
import java.awt.Graphics2D;

import org.jebtk.core.Props;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.animation.TranslateXAnimation;
import org.jebtk.modern.css.CSSBaseUI;
import org.jebtk.modern.css.CSSProp;
import org.jebtk.modern.css.CSSPropName;

/**
 * Allows for fade in/out animation on an element.
 *
 * @author Antony Holmes
 */
public final class CheckSwitchChangeAnimation extends TranslateXAnimation {

  private static final int HEIGHT = ModernCheckSwitch.SLIDER_HEIGHT;

  private final ModernCheckSwitch mButton;
  // private Color mColor;

  // private FadeAnimation mFade;
  public CheckSwitchChangeAnimation(ModernWidget widget) {
    super(widget);

    // mColor = color;
    mButton = (ModernCheckSwitch) widget;

    // mFade = new FadeAnimation(button).setFadeColor("fill", color1, color2);
    // Animation should be triggered on a state change and not a click
    // event since we want the button to respond to setSelected events.
    mButton.addStateListener(e -> {
      restart();
    });

    restart();
  }

  @Override
  public void restart() {
    int x1;
    int x2;

    if (mButton.isSelected()) {
      // Off to on

      x1 = ModernCheckSwitch.SLIDER_OFFSET;
      x2 = ModernCheckSwitch.SWITCH_ON_OFFSET;
    } else {
      // On to Off
      x1 = ModernCheckSwitch.SWITCH_ON_OFFSET;
      x2 = ModernCheckSwitch.SLIDER_OFFSET;
    }

    restart(x1, x2);
  }

  @Override
  public void drawUntranslatedBG(ModernWidget c, Graphics2D g2, Props props) {
    int frame = props.getInt("frame");
    
    if (!mButton.isSelected()) {
      frame = CSSProp.TO - frame;
    }

    Color bg = CSSBaseUI.getStyle(c).getColor(CSSPropName.BACKGROUND_COLOR).getKeyFrame(frame);

    int y2 = (mWidget.getHeight() - HEIGHT) / 2;

    g2.setColor(bg);

    g2.fillRoundRect(mWidget.getInsets().left + ModernCheckSwitch.SWITCH_ICON_OFFSET, y2,
            ModernCheckSwitch.SLIDER_WIDTH - ModernCheckSwitch.SWITCH_ICON_OFFSET_2,
            HEIGHT,
            HEIGHT,
            HEIGHT);
  }

  @Override
  public void drawTranslation(ModernWidget c, Graphics2D g2, Props props) {
    int s = ModernCheckSwitch.ORB_HEIGHT;

    int h = c.getHeight();
    int y1 = (h - s) / 2;

    g2.setColor(Color.WHITE);
    g2.fillOval(0, y1, s, s);
    //g2.setColor(c.getCSSProps().getColor("border-color"));
    //g2.drawOval(0, y1, s, s);
    // g2.setColor(Color.WHITE);
    // g2.fillOval(0, y1, s, s);
    // g2.setColor(mFade.getFromColor("fill"));
    // g2.drawOval(0, y1, s, s);

    // g2.setColor(Color.WHITE);
    // g2.fillOval(0, y1, s, s);
    // g2.setColor(Color.WHITE);
    // s -= 2;
    // g2.fillOval(1, y1 + 1, s, s);
  }
  
  @Override
  public void drawUntranslatedFG(ModernWidget c, Graphics2D g2, Props props) {
    int frame = props.getInt("frame");
    
    if (!mButton.isSelected()) {
      frame = CSSProp.TO - frame;
    }

    Color br = CSSBaseUI.getStyle(c).getColor(CSSPropName.BORDER_COLOR).getKeyFrame(frame);

    int y2 = (mWidget.getHeight() - HEIGHT) / 2;
    g2.setColor(br);

    g2.drawRoundRect(mWidget.getInsets().left + ModernCheckSwitch.SWITCH_ICON_OFFSET, y2,
            ModernCheckSwitch.SLIDER_WIDTH - ModernCheckSwitch.SWITCH_ICON_OFFSET_2, HEIGHT, HEIGHT, HEIGHT);
  }
}
