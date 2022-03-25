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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.List;

import org.jebtk.core.geom.IntDim;
import org.jebtk.core.geom.IntPos2D;
import org.jebtk.modern.button.ModernClickWidget;
import org.jebtk.modern.graphics.ImageUtils;

/**
 * The class ColorPicker.
 */
public class ColorMapBlockPicker extends ModernClickWidget {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  public static int OUTER_PADDING = 0;

  public static final int DOUBLE_OUTER_PADDING = 2 * OUTER_PADDING;

  /** The Constant HEAT_MAP_SIZE. */
  public static final IntDim HEAT_MAP_SIZE = new IntDim(54, 36);

  /**
   * The constant BLOCK_SIZE.
   */
  public static final IntDim BLOCK_SIZE = new IntDim(56, 38);

  public static final IntDim HIGHLIGHT_MAP_SIZE = HEAT_MAP_SIZE;

  /** The Constant OFFSET. */
  public static final IntDim OFFSET = new IntDim((BLOCK_SIZE.getW() - HEAT_MAP_SIZE.getW()) / 2,
      (BLOCK_SIZE.getH() - HEAT_MAP_SIZE.getH()) / 2);

  public static final IntDim SELECTED_OFFSET = OFFSET;

  /** The Constant BLOCK_OFFSET. */
  // private static final int BLOCK_OFFSET = 10;

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

  private int mI = -1;

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
      int r = (e.getY() - OUTER_PADDING) / BLOCK_SIZE.getH();
      int c = (e.getX() - OUTER_PADDING) / BLOCK_SIZE.getW();
      int i = r * mCols + c;

      if (i < mColorMaps.size()) {
        mRow = r;
        mCol = c;
        mI = i;

        mP = new IntPos2D(OUTER_PADDING + BLOCK_SIZE.getW() * mCol + SELECTED_OFFSET.getW(),
            OUTER_PADDING + BLOCK_SIZE.getH() * mRow + SELECTED_OFFSET.getH());

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
  public ColorMapBlockPicker(int cols) {
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
    mRows = colorMaps.size() / mCols + 1; // + (colorMaps.size() % mCols == 0 ?
    // 0 : 1);
    mColorMaps = colorMaps;

    setPreferredSize(new Dimension(DOUBLE_OUTER_PADDING + BLOCK_SIZE.getW() * mCols,
        DOUBLE_OUTER_PADDING + BLOCK_SIZE.getH() * mRows));
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

    mSelectedP = new IntPos2D(BLOCK_SIZE.getW() * mSelectedCol + OUTER_PADDING + SELECTED_OFFSET.getW(),
        BLOCK_SIZE.getH() * mSelectedRow + OUTER_PADDING + SELECTED_OFFSET.getH());

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
    int x = OUTER_PADDING;
    int y = OUTER_PADDING;

    Graphics2D g2Temp = ImageUtils.createAAStrokeGraphics(g2);

    try {
      for (int i = 0; i < mColorMaps.size(); ++i) {
        ColorMap colorMap = mColorMaps.get(i);

        y = i / mCols * BLOCK_SIZE.getH() + OUTER_PADDING + OFFSET.getH();
        x = (i % mCols) * BLOCK_SIZE.getW() + OUTER_PADDING + OFFSET.getW();

        LinearGradientPaint paint;

        if (i == mI) {
          paint = colorMap.getAnchorColors().toGradientPaint(new Point2D.Float(x, 0),
              new Point2D.Float(x + HEAT_MAP_SIZE.getW(), 0), 0.5);

        } else {
          paint = colorMap.getAnchorColors().toGradientPaint(new Point2D.Float(x, 0),
              new Point2D.Float(x + HEAT_MAP_SIZE.getW(), 0));

        }

        g2Temp.setPaint(paint);
        // g2.fillRoundRect(x, y, HEAT_MAP_SIZE.getW(), HEAT_MAP_SIZE.getH(),
        // MaterialService.getInstance().getInts().cornerRadius();,
        // MaterialService.getInstance().getInts().cornerRadius(););
        // g2Temp.fillOval(x, y, HEAT_MAP_SIZE, HEAT_MAP_SIZE);
        g2Temp.fillRect(x, y, HEAT_MAP_SIZE.getW(), HEAT_MAP_SIZE.getH());

        // g2Temp.setColor(LIGHT_LINE_COLOR);
        // g2.drawRoundRect(x, y, HEAT_MAP_SIZE.getW(), HEAT_MAP_SIZE.getH(),
        // MaterialService.getInstance().getInts().cornerRadius();,
        // MaterialService.getInstance().getInts().cornerRadius(););
        // g2Temp.drawOval(x, y, HEAT_MAP_SIZE, HEAT_MAP_SIZE);
        // g2Temp.drawRect(x, y, HEAT_MAP_SIZE.getW(), HEAT_MAP_SIZE.getH());

        // y = i / mCols * BLOCK_SIZE + OUTER_PADDING + SELECTED_OFFSET;
        // x = (i % mCols) * BLOCK_SIZE + OUTER_PADDING + SELECTED_OFFSET;

        // g2.setColor(LIGHT_LINE_COLOR);
        // g2Temp.drawOval(x,
        // y,
        // HIGHLIGHT_MAP_SIZE,
        // HIGHLIGHT_MAP_SIZE);

      }

      // g2Temp.setStroke(ThemeService.DOUBLE_LINE_STROKE);

      if (mSelectedRow != -1) {
        g2Temp.setColor(Color.BLACK); // ModernWidgetRenderer.SELECTED_FILL_COLOR);

        g2Temp.drawRect(mSelectedP.getX(), mSelectedP.getY(), HIGHLIGHT_MAP_SIZE.getW(), HIGHLIGHT_MAP_SIZE.getH());
      }

      /*
       * if (mRow != -1) {
       * g2Temp.setColor(ModernWidgetRenderer.HIGHLIGHTED_FILL_COLOR);
       * 
       * g2Temp.drawRect(mP.getX(), mP.getY(), HIGHLIGHT_MAP_SIZE.getW(),
       * HIGHLIGHT_MAP_SIZE.getH()); }
       */
    } finally {
      g2Temp.dispose();
    }
  }
}
