/**
 * Copyright (C) 2016, Antony Holmes
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *  1. Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *  3. Neither the name of copyright holder nor the names of its contributors 
 *     may be used to endorse or promote products derived from this software 
 *     without specific prior written permission. 
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 */
package org.jebtk.modern.graphics.icons;

import java.awt.Color;
import java.awt.Graphics2D;

import org.jebtk.core.Props;
import org.jebtk.core.settings.SettingsService;
import org.jebtk.modern.graphics.ImageUtils;

/**
 * The class OpenFolderVectorIcon.
 */
public class SettingsVectorIcon extends ModernVectorScalableIcon {

  /**
   * The constant WIDTH_SCALE.
   */
  private static final double WIDTH_SCALE = SettingsService.getInstance()
      .getDouble("theme.icons.settings-icon.width-scale");

  /** The Constant LINE_WIDTH. */
  private static final double LINE_WIDTH = SettingsService.getInstance()
      .getDouble("theme.icons.settings-icon.line-width");

  /** The Constant COG_SCALE. */
  private static final double COG_SCALE = SettingsService.getInstance()
      .getDouble("theme.icons.settings-icon.cog-scale");

  /** The Constant THETA_INC. */
  private static final double THETA_INC = Math.PI / 3;

  /**
   * Instantiates a new settings vector icon.
   */
  public SettingsVectorIcon() {
    this(Color.BLACK);
  }

  /**
   * Instantiates a new settings vector icon.
   *
   * @param color1 the color 1
   */
  public SettingsVectorIcon(Color color1) {
    super(color1);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.icons.ModernIcon#drawForeground(java.awt.Graphics2D,
   * java.awt.Rectangle)
   */
  @Override
  public void drawIcon(Graphics2D g2, int x, int y, int w, int h, Props props) {
    int wf = (int) (w * WIDTH_SCALE);
    int wf2 = (int) (w * WIDTH_SCALE / 2);

    int lw = (int) Math.max(1, wf * LINE_WIDTH);
    int d = (int) (wf * COG_SCALE);
    int d2 = (int) (wf * COG_SCALE / 2) + 1;

    double theta = 0;

    Graphics2D g2Temp = ImageUtils.clone(g2);

    try {
      g2Temp.setColor(mColor1);

      g2Temp.translate(x + w / 2, y + h / 2);

      for (int i = 0; i < 3; ++i) {
        Graphics2D g2Temp2 = ImageUtils.clone(g2Temp);

        try {
          g2Temp2.rotate(theta);

          g2Temp2.setStroke(ImageUtils.createStroke(lw));
          g2Temp2.drawLine(-wf2, 0, wf2, 0);
        } finally {
          g2Temp2.dispose();
        }

        theta += THETA_INC;
      }

      g2Temp.fillOval(-d2, -d2, d, d);
    } finally {
      g2Temp.dispose();
    }
  }
}
