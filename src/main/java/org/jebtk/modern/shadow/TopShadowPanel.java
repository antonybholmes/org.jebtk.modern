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
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

import org.jebtk.modern.graphics.ImageUtils;
import org.jebtk.modern.panel.Card;

/**
 * The Class TopShadowPanel.
 */
public class TopShadowPanel extends ShadowPanel {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * The Class TopShadow.
   */
  private static class TopShadow extends RibbonShadow {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    private static final int WIDTH = 200;

    private BufferedImage mImg = null;

    public TopShadow() {
      addComponentListener(new ComponentAdapter() {

        @Override
        public void componentResized(ComponentEvent e) {
          mImg = null;
        }
      });
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.abh.common.ui.ModernComponent#drawBackground(java.awt.Graphics2D)
     */
    @Override
    public void drawBackground(Graphics2D g2) {
      // Graphics2D g2Temp = ImageUtils.createAATextGraphics(g2);

      // try {
      if (mImg == null) {
        mImg = shadow();
      }

      // Scale image
      g2.drawImage(mImg, 0, -Card.HALF_SHADOW_SIZE, getWidth(), Card.SHADOW_SIZE, null);

      // MaterialUtils.drawDropShadow(g2Temp, 0, 0, getWidth(), 0);
      // } finally {
      // g2Temp.dispose();
      // }
    }

    private static BufferedImage shadow() {
      BufferedImage img = Card.createCompatibleImage(WIDTH, Card.SHADOW_SIZE);

      Graphics2D g2 = img.createGraphics();

      try {
        ImageUtils.setQualityHints(g2);
        g2.setColor(Card.COLOR);
        // tg2.translate(-bounds.x, -bounds.y);
        g2.fillRect(0, 0, WIDTH, Card.HALF_SHADOW_SIZE);
      } finally {
        g2.dispose();
      }

      return Card.blur(img, Card.SHADOW_SIZE); // imgMask; //blur(imgMask,
      // size);
    }
  }

  /**
   * Instantiates a new top shadow panel.
   *
   * @param c the c
   */
  public TopShadowPanel(Component c) {
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
    mShadow.setBounds(0, 0, getWidth(), Card.SHADOW_SIZE); // MaterialUtils.SHADOW_HEIGHT);

    super.componentResized(e);
  }
}
