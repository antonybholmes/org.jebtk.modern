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

/**
 * The class PasteVectorIcon.
 */
public class PasteVectorIcon extends ModernVectorIcon {

  /**
   * The constant WIDTH_SCALE.
   */
  private static final double WIDTH_SCALE = SettingsService.getInstance()
      .getDouble("theme.icons.paste-icon.width-scale");

  /**
   * The constant BOARD_WIDTH_SCALE.
   */
  private static final double BOARD_WIDTH_SCALE = SettingsService.getInstance()
      .getDouble("theme.icons.paste-icon.board-width-scale");

  /**
   * The constant BOARD_HEIGHT_SCALE.
   */
  private static final double BOARD_HEIGHT_SCALE = SettingsService.getInstance()
      .getDouble("theme.icons.paste-icon.board-height-scale");

  /**
   * The constant CLIP_WIDTH_SCALE.
   */
  private static final double CLIP_WIDTH_SCALE = SettingsService.getInstance()
      .getDouble("theme.icons.paste-icon.clip-width-scale");

  /**
   * The constant CLIP_HEIGHT_SCALE.
   */
  private static final double CLIP_HEIGHT_SCALE = SettingsService.getInstance()
      .getDouble("theme.icons.paste-icon.clip-height-scale");

  /**
   * The constant CLIP_TOP_WIDTH_SCALE.
   */
  private static final double CLIP_TOP_WIDTH_SCALE = SettingsService.getInstance()
      .getDouble("theme.icons.paste-icon.clip-top-width-scale");

  /**
   * The constant CLIP_HOLE_WIDTH_SCALE.
   */
  private static final double CLIP_HOLE_WIDTH_SCALE = SettingsService.getInstance()
      .getDouble("theme.icons.paste-icon.clip-hole-width-scale");

  /**
   * The constant FILE_WIDTH_SCALE.
   */
  private static final double FILE_WIDTH_SCALE = SettingsService.getInstance()
      .getDouble("theme.icons.paste-icon.file-width-scale");

  /**
   * The constant BOARD_COLOR.
   */
  private static final Color BOARD_COLOR = SettingsService.getInstance()
      .getColor("theme.icons.paste-icon.colors.board");

  /**
   * The constant CLIP_COLOR.
   */
  private static final Color CLIP_COLOR = SettingsService.getInstance().getColor("theme.icons.paste-icon.colors.clip");

  /**
   * The constant FILE_ICON.
   */
  private static final ModernIcon FILE_ICON = new FileVectorIcon();

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.icons.ModernIcon#drawForeground(java.awt.Graphics2D,
   * java.awt.Rectangle)
   */
  @Override
  public void drawIcon(Graphics2D g2, int x, int y, int w, int h, Props props) {

    double wf = w * WIDTH_SCALE;
    double bw = wf * BOARD_WIDTH_SCALE;
    double bh = wf * BOARD_HEIGHT_SCALE;

    double fw = wf * FILE_WIDTH_SCALE;

    double xf = x + (w - wf) / 2.0;
    double yf = y + (h - wf) / 2.0;

    g2.setColor(BOARD_COLOR);

    g2.fillRect((int) Math.round(xf), (int) Math.round(yf + (wf - bh) / 2.0), (int) Math.round(bw),
        (int) Math.round(bh));

    FILE_ICON.drawIcon(g2, (int) Math.round(xf + wf - fw), (int) Math.round(yf + wf - fw), (int) Math.round(fw));

    g2.setColor(CLIP_COLOR);

    double cw = bw * CLIP_WIDTH_SCALE;
    double ch = bw * CLIP_HEIGHT_SCALE;

    g2.fillRect((int) Math.round(xf + (bw - cw) / 2.0), (int) Math.round(yf + (wf - bh) / 2.0), (int) Math.round(cw),
        (int) Math.round(ch));

    double cw2 = cw * CLIP_TOP_WIDTH_SCALE;

    g2.fillRect((int) Math.round(xf + (bw - cw2) / 2.0), (int) Math.round(yf + (wf - bh) / 2.0 - ch),
        (int) Math.round(cw2), (int) Math.round(ch));

    g2.setColor(Color.WHITE);

    double hw = cw * CLIP_HOLE_WIDTH_SCALE;

    g2.fillRect((int) Math.round(xf + (bw - hw) / 2.0), (int) Math.round(yf + (wf - bh) / 2.0 - ch + hw / 2),
        (int) Math.round(hw), (int) Math.round(hw));

  }
}
