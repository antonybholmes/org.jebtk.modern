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
package org.jebtk.modern.shadow;

import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;

import org.jebtk.modern.theme.MaterialUtils;

/**
 * For components on the left side, so draw shadowing on the right.
 * 
 * @author Antony Holmes
 *
 */
public class LeftShadowPanel extends ShadowPanel {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * The Class TopShadow.
   */
  private static class TopShadow extends RibbonShadow {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /*
     * (non-Javadoc)
     * 
     * @see org.abh.common.ui.ModernComponent#drawBackground(java.awt.Graphics2D)
     */
    @Override
    public void drawBackground(Graphics2D g2) {
      GradientPaint paint = new GradientPaint(0, 0, MaterialUtils.SHADOW_COLOR_1, getWidth(), 0,
          MaterialUtils.SHADOW_COLOR_2);

      g2.setPaint(paint);

      g2.fillRect(0, 0, getWidth(), getHeight());
    }
  }

  /**
   * Instantiates a new left shadow panel.
   *
   * @param c the c
   */
  public LeftShadowPanel(Component c) {
    super(c, new TopShadow());
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.shadow.ShadowPanel#componentResized(java.awt.event.
   * ComponentEvent)
   */
  @Override
  public void componentResized(ComponentEvent e) {
    mShadow.setBounds(getWidth() - MaterialUtils.SHADOW_HEIGHT, 0, MaterialUtils.SHADOW_HEIGHT, getHeight());

    super.componentResized(e);
  }
}
