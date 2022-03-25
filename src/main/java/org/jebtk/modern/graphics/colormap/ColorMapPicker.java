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
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.List;

import org.jebtk.core.geom.IntDim;
import org.jebtk.core.geom.IntPos2D;
import org.jebtk.modern.button.ModernClickWidget;

/**
 * The class ColorPicker.
 */
public class ColorMapPicker extends ModernClickWidget {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant BLOCK_SIZE.
   */
  public static final IntDim BLOCK_SIZE = new IntDim(90, 30);

  /** The Constant HEAT_MAP_SIZE. */
  public static final IntDim HEAT_MAP_SIZE = new IntDim(80, 20);

  /** The Constant OFFSET. */
  public static final Point OFFSET = new Point((BLOCK_SIZE.getW() - HEAT_MAP_SIZE.getW()) / 2,
      (BLOCK_SIZE.getH() - HEAT_MAP_SIZE.getH()) / 2);

  /** The Constant BLOCK_OFFSET. */
  private static final int BLOCK_OFFSET = 10;

  /**
   * The p.
   */
  private IntPos2D mP = null;

  /**
   * The selected p.
   */
  private IntPos2D mSelectedP = null;

  /**
   * The row.
   */
  private int mRow = -1;

  /**
   * The col.
   */
  private int mCol = -1;

  /**
   * The selected row.
   */
  private int mSelectedRow = -1;

  /**
   * The selected col.
   */
  private int mSelectedCol = -1;

  /** The m cols. */
  private int mCols;

  /** The m rows. */
  private int mRows;

  /** The m color maps. */
  private List<ColorMap> mColorMaps;

  /** The m selected index. */
  private int mSelectedIndex;

  /** The m selected color map. */
  private ColorMap mSelectedColorMap;

  /**
   * The class MouseEvents.
   */
  private class MouseEvents extends MouseAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
     */
    @Override
    public void mousePressed(MouseEvent e) {
      setSelectedColorMap(mRow, mCol);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mouseExited(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseExited(MouseEvent e) {
      mRow = -1;
      mCol = -1;
      mSelectedRow = -1;
      mSelectedCol = -1;
      mSelectedIndex = -1;

      repaint();
    }
  }

  /**
   * The class MouseMoveEvents.
   */
  private class MouseMoveEvents implements MouseMotionListener {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseMoved(MouseEvent e) {
      int r = (e.getY() - DOUBLE_PADDING) / BLOCK_SIZE.getH();
      int c = (e.getX() - DOUBLE_PADDING) / BLOCK_SIZE.getW();
      int i = r * mCols + c;

      if (i < mColorMaps.size()) {
        mRow = r;
        mCol = c;

        mP = new IntPos2D(DOUBLE_PADDING + BLOCK_SIZE.getW() * mCol, DOUBLE_PADDING + BLOCK_SIZE.getH() * mRow);

        repaint();
      }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.
     * MouseEvent)
     */
    @Override
    public void mouseDragged(MouseEvent arg0) {
      // TODO Auto-generated method stub

    }
  }

  /**
   * Instantiates a new color map picker.
   *
   * @param cols the cols
   */
  public ColorMapPicker(int cols) {
    mCols = cols;

    addMouseMotionListener(new MouseMoveEvents());
    addMouseListener(new MouseEvents());
  }

  /**
   * Update.
   *
   * @param colorMaps the color maps
   */
  public void update(List<ColorMap> colorMaps) {
    mRows = colorMaps.size() / mCols + (colorMaps.size() % mCols == 0 ? 0 : 1);
    mColorMaps = colorMaps;

    setPreferredSize(new Dimension(QUAD_PADDING + BLOCK_SIZE.getW() * mCols - BLOCK_OFFSET,
        QUAD_PADDING + BLOCK_SIZE.getH() * mRows - BLOCK_OFFSET));
  }

  /**
   * Sets the selected color.
   *
   * @param row the row
   * @param col the col
   */
  public void setSelectedColorMap(int row, int col) {
    mSelectedRow = row;
    mSelectedCol = col;
    mSelectedIndex = row * mCols + col;

    mSelectedP = new IntPos2D(BLOCK_SIZE.getW() * mSelectedCol + DOUBLE_PADDING,
        BLOCK_SIZE.getH() * mSelectedRow + DOUBLE_PADDING);

    if (mSelectedIndex < mColorMaps.size()) {
      mSelectedColorMap = mColorMaps.get(mSelectedIndex);

      fireClicked();
    }
  }

  /**
   * Gets the selected color.
   *
   * @return the selected color
   */
  public ColorMap getSelectedColorMap() {
    return mSelectedColorMap;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.widget.ModernClickWidget#drawBackgroundAA(java.awt.
   * Graphics2D)
   */
  @Override
  public void drawBackgroundAA(Graphics2D g2) {
    // do nothing
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForeground(Graphics2D g2) {
    int x = DOUBLE_PADDING;
    int y = DOUBLE_PADDING;

    for (int i = 0; i < mColorMaps.size(); ++i) {
      ColorMap colorMap = mColorMaps.get(i);

      y = i / mCols * BLOCK_SIZE.getH() + DOUBLE_PADDING;
      x = (i % mCols) * BLOCK_SIZE.getW() + DOUBLE_PADDING;

      LinearGradientPaint paint = colorMap.getAnchorColors().toGradientPaint(new Point2D.Float(x, 0),
          new Point2D.Float(x + HEAT_MAP_SIZE.getW(), 0));

      g2.setPaint(paint);
      // g2.fillRoundRect(x, y, HEAT_MAP_SIZE.getW(), HEAT_MAP_SIZE.getH(),
      // MaterialService.getInstance().getInts().cornerRadius();,
      // MaterialService.getInstance().getInts().cornerRadius(););
      g2.fillRect(x, y, HEAT_MAP_SIZE.getW(), HEAT_MAP_SIZE.getH());

      g2.setColor(LINE_COLOR);
      // g2.drawRoundRect(x, y, HEAT_MAP_SIZE.getW(), HEAT_MAP_SIZE.getH(),
      // MaterialService.getInstance().getInts().cornerRadius();,
      // MaterialService.getInstance().getInts().cornerRadius(););
      g2.drawRect(x, y, HEAT_MAP_SIZE.getW(), HEAT_MAP_SIZE.getH());

      /*
       * double c = 0; double inc = (double)(colorMap.getColorCount() - 1) /
       * HEAT_MAP_SIZE.getW();
       * 
       * int cx = x; for (int j = 0; j < HEAT_MAP_SIZE.getW(); ++j) {
       * g2.setColor(colorMap.getColorByIndex((int)c));
       * 
       * g2.drawLine(cx, y, cx, y + HEAT_MAP_SIZE.getH());
       * 
       * ++cx;
       * 
       * c += inc; }
       */

      // g2.setColor(DARK_LINE_COLOR);
      // g2.drawRect(x, y, HEAT_MAP_SIZE.getW(), HEAT_MAP_SIZE.getH());
    }

    if (mSelectedRow != -1) {
      g2.setColor(Color.BLACK);

      /*
       * g2.drawRoundRect(mSelectedP.getX(), mSelectedP.getY(), HEAT_MAP_SIZE.getW(),
       * HEAT_MAP_SIZE.getH(),
       * MaterialService.getInstance().getInts().cornerRadius();,
       * MaterialService.getInstance().getInts().cornerRadius(););
       */

      g2.drawRect(mSelectedP.getX(), mSelectedP.getY(), HEAT_MAP_SIZE.getW(), HEAT_MAP_SIZE.getH());
    }

    if (mRow != -1) {
      g2.setColor(Color.BLACK);

      /*
       * g2.drawRoundRect(mP.getX(), mP.getY(), HEAT_MAP_SIZE.getW(),
       * HEAT_MAP_SIZE.getH(),
       * MaterialService.getInstance().getInts().cornerRadius();,
       * MaterialService.getInstance().getInts().cornerRadius(););
       */

      g2.drawRect(mP.getX(), mP.getY(), HEAT_MAP_SIZE.getW(), HEAT_MAP_SIZE.getH());
    }

  }
}
