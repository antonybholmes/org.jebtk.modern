/**
 * Copyright 2016 Antony Holmes
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
package org.jebtk.modern.graphics.colormap;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Point2D;

import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.window.ModernWindow;

/**
 * Display a color map and allow users to edit the colors.
 */
public class ColorMapEditPanel extends ModernWidget implements ModernClickListener {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The m p1. */
  private ColorMapColorButton mP1;

  /** The m p2. */
  private ColorMapColorButton mP2;

  /** The m p3. */
  private ColorMapColorButton mP3;

  /** The m p4. */
  private ColorMapColorButton mP4;

  /** The m p5. */
  private ColorMapColorButton mP5;

  /** The m color map. */
  private ColorMap mColorMap;

  /** The m colors. */
  private int mColors = 65;

  /** The m points. */
  private ColorMapType mPoints = ColorMapType.FIVE_COLOR;

  /** The Constant PREFERRED_HEIGHT. */
  private static final int PREFERRED_HEIGHT = 34;

  /** The Constant PREFERRED_SIZE. */
  private static final Dimension PREFERRED_SIZE = new Dimension(500, PREFERRED_HEIGHT);

  /** The Constant MIN_SIZE. */
  private static final Dimension MIN_SIZE = new Dimension(100, PREFERRED_HEIGHT);

  /**
   * Instantiates a new color map edit panel.
   *
   * @param parent the parent
   */
  public ColorMapEditPanel(ModernWindow parent) {
    UI.setSize(this, PREFERRED_SIZE);

    setLayout(null);

    mP1 = new ColorMapColorButton(parent, Color.WHITE);
    mP2 = new ColorMapColorButton(parent, Color.WHITE);
    mP3 = new ColorMapColorButton(parent, Color.WHITE);
    mP4 = new ColorMapColorButton(parent, Color.WHITE);
    mP5 = new ColorMapColorButton(parent, Color.WHITE);

    add(mP1);
    add(mP2);
    add(mP3);
    add(mP4);
    add(mP5);

    addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent arg0) {
        mP1.setLocation(0, getHeight() - mP1.getHeight());
        mP2.setLocation(getWidth() / 4 - mP2.getWidth() / 2, getHeight() - mP1.getHeight());
        mP3.setLocation(getWidth() / 2 - mP3.getWidth() / 2, getHeight() - mP1.getHeight());
        mP4.setLocation(getWidth() * 3 / 4 - mP4.getWidth() / 2, getHeight() - mP1.getHeight());
        mP5.setLocation(getWidth() - mP5.getWidth(), getHeight() - mP1.getHeight());
      }
    });

    mP1.addClickListener(this);
    mP2.addClickListener(this);
    mP3.addClickListener(this);
    mP4.addClickListener(this);
    mP5.addClickListener(this);

    update();
  }

  /**
   * Sets the colors.
   *
   * @param c1     the c1
   * @param c2     the c2
   * @param colors the colors
   */
  public void setColors(Color c1, Color c2, int colors) {
    mColors = colors;

    mP1.setSelectedColor(c1);
    mP5.setSelectedColor(c2);

    setColors(ColorMapType.TWO_COLOR, mColors);
  }

  /**
   * Sets the colors.
   *
   * @param c1     the c1
   * @param c2     the c2
   * @param c3     the c3
   * @param colors the colors
   */
  public void setColors(Color c1, Color c2, Color c3, int colors) {
    mColors = colors;

    mP1.setSelectedColor(c1);
    mP3.setSelectedColor(c2);
    mP5.setSelectedColor(c3);

    setColors(ColorMapType.THREE_COLOR, mColors);
  }

  /**
   * Sets the colors.
   *
   * @param c1     the c1
   * @param c2     the c2
   * @param c3     the c3
   * @param c4     the c4
   * @param c5     the c5
   * @param colors the colors
   */
  public void setColors(Color c1, Color c2, Color c3, Color c4, Color c5, int colors) {
    mColors = colors;

    mP1.setSelectedColor(c1);
    mP2.setSelectedColor(c2);
    mP3.setSelectedColor(c3);
    mP4.setSelectedColor(c4);
    mP5.setSelectedColor(c5);

    setColors(ColorMapType.FIVE_COLOR, mColors);
  }

  /**
   * Sets the colors.
   *
   * @param points the points
   * @param colors the colors
   */
  public void setColors(ColorMapType points, int colors) {
    mColors = colors;

    mPoints = points;

    update();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.event.ModernClickListener#clicked(org.abh.common.ui.
   * event. ModernClickEvent)
   */
  @Override
  public void clicked(ModernClickEvent e) {
    update();
  }

  /**
   * Update.
   */
  private void update() {
    switch (mPoints) {
    case FIVE_COLOR:
      mColorMap = ColorMap.createFiveColorMap("map", mP1.getSelectedColor(), mP2.getSelectedColor(),
          mP3.getSelectedColor(), mP4.getSelectedColor(), mP5.getSelectedColor(), mColors);

      mP1.setVisible(true);
      mP2.setVisible(true);
      mP3.setVisible(true);
      mP4.setVisible(true);
      mP5.setVisible(true);

      break;
    case THREE_COLOR:
      mColorMap = ColorMap.createThreeColorMap("map", mP1.getSelectedColor(), mP3.getSelectedColor(),
          mP5.getSelectedColor(), mColors);

      mP1.setVisible(true);
      mP2.setVisible(false);
      mP3.setVisible(true);
      mP4.setVisible(false);
      mP5.setVisible(true);

      break;
    default:
      mColorMap = ColorMap.createTwoColorMap("map", mP1.getSelectedColor(), mP5.getSelectedColor(), mColors);

      mP1.setVisible(true);
      mP2.setVisible(false);
      mP3.setVisible(false);
      mP4.setVisible(false);
      mP5.setVisible(true);

      break;
    }

    repaint();
  }

  /**
   * Gets the color map.
   *
   * @return the color map
   */
  public ColorMap getColorMap() {
    return mColorMap;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.ModernWidget#drawBackground(java.awt.Graphics2D)
   */
  @Override
  public void drawBackground(Graphics2D g2) {
    int x = 7;
    int y = 0;

    int w = getWidth() - 14;

    LinearGradientPaint paint = mColorMap.getAnchorColors().toGradientPaint(new Point2D.Float(x, 0),
        new Point2D.Float(x + w, 0));

    g2.setPaint(paint);

    g2.fillRect(x, y, w, 16);

    /*
     * double c = 0; double inc = (mColorMap.getColorCount() - 1) / (double)w;
     * 
     * for (int i = 0; i < w; ++i) { g2.setColor(mColorMap.getColorByIndex((int)c));
     * 
     * g2.drawLine(x, y, x, y + 16);
     * 
     * ++x;
     * 
     * c += inc; }
     */

    // g2.setColor(DARK_LINE_COLOR);
    // g2.drawRect(x, y, w, 16);
  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.swing.JComponent#getPreferredSize()
   */
  @Override
  public Dimension getPreferredSize() {
    return PREFERRED_SIZE;
  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.swing.JComponent#getMinimumSize()
   */
  @Override
  public Dimension getMinimumSize() {
    return MIN_SIZE;
  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.swing.JComponent#getMaximumSize()
   */
  @Override
  public Dimension getMaximumSize() {
    return PREFERRED_SIZE;
  }
}
