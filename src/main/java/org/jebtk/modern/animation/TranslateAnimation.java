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
package org.jebtk.modern.animation;

import java.awt.Graphics2D;

import org.jebtk.core.Props;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.graphics.ImageUtils;

/**
 * Allows for graphics to transition between two fixed points using a Bezier
 * curve to control the speed of animation
 *
 * @author Antony Holmes
 */
public abstract class TranslateAnimation extends EasingAnimation {

  private final int[] mXPos = new int[AnimationTimer.STEPS];

  /**
   * Instantiates a new state animation.
   *
   * @param widget
   */
  public TranslateAnimation(ModernWidget widget) {
    super(widget);

    /*
     * widget.addAncestorListener(new SingleUseAncestorMovedListener() {
     * 
     * @Override public void action(AncestorEvent event) { restart(); } });
     * 
     * widget.addAncestorListener(new SingleUseAncestorAddedListener() {
     * 
     * @Override public void action(AncestorEvent event) { restart(); } });
     */

    /*
     * widget.addComponentListener(new ComponentListener() {
     * 
     * @Override public void componentResized(ComponentEvent e) { //restart(); }
     * 
     * @Override public void componentMoved(ComponentEvent e) {
     * 
     * }
     * 
     * @Override public void componentShown(ComponentEvent e) { //restart(); }
     * 
     * @Override public void componentHidden(ComponentEvent e) { // TODO
     * Auto-generated method stub
     * 
     * }});
     */

    // restart();
  }

  public synchronized void restart(int x1, int x2) {
    int mD = (x2 - x1); // / MAX_INDEX;

    reset();

    mXPos[0] = x1;
    mXPos[mXPos.length - 1] = x2;

    for (int i = 1; i < AnimationTimer.MAX_STEP_INDEX; ++i) {
      mXPos[i] = x1 + (int) (BEZ_T[i] * mD); // BEZIER.eval(t) mXPos[i - 1] +
      // mD;
    }

    start();
  }

  public int getX() {
    return mXPos[getFrame()];
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.animation.Animation#draw(org.abh.common.ui.widget.
   * ModernWidget, java.awt.Graphics2D, java.lang.Object[])
   */
  @Override
  public void draw(ModernWidget c, Graphics2D g2, Props props) {
    super.draw(c, g2, props);
    
    drawUntranslatedBG(c, g2, props);

    Graphics2D g2Temp = ImageUtils.clone(g2);

    try {
      translate(g2Temp);

      drawTranslation(mWidget, g2Temp, props);
    } finally {
      g2Temp.dispose();
    }
    
    drawUntranslatedFG(c, g2, props);
  }
  
  public void drawUntranslatedBG(ModernWidget c, Graphics2D g2, Props props) {
    // do nothing
  }
  
  public void drawUntranslatedFG(ModernWidget c, Graphics2D g2, Props props) {
    // do nothing
  }

  public abstract void translate(Graphics2D g2);

  public abstract void drawTranslation(ModernWidget widget, Graphics2D g2, Props props);
}
