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
package org.jebtk.modern.graphics.color;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import org.jebtk.modern.button.ModernClickWidget;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.theme.ThemeService;

/**
 * The class ColorPicker.
 */
public class ColorPicker extends ModernClickWidget {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The colors.
   */
  private List<List<Color>> mColors = new ArrayList<List<Color>>();

  /**
   * The constant GAP.
   */
  public static final int GAP = 4;

  /**
   * The constant BLOCK_SIZE.
   */
  public static final int BLOCK_SIZE = 20;

  /** The Constant SPACE. */
  private static final int SPACE = GAP + BLOCK_SIZE;

  /** The Constant SELECTION_COLOR. */
  private static final Color SELECTION_COLOR = ThemeService.getInstance().getColors().getGray(10);

  private static final int OFFSET = 0; // PADDING

  private static final int DOUBLE_OFFSET = OFFSET * 2;

  /**
   * The p.
   */
  private Point mP = new Point();

  /**
   * The selected p.
   */
  private Point mSelectedP = new Point();

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

  /**
   * The selected color.
   */
  private Color mSelectedColor;

  private Dimension mSize;

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
      setSelectedColor(mRow, mCol);
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
      int r = ((e.getY() - OFFSET) / BLOCK_SIZE);
      int c = ((e.getX() - OFFSET) / SPACE);

      if (c < 0 || c >= mColors.size()) {
        return;
      }

      if (r < 0 || r >= mColors.get(0).size()) {
        return;
      }

      mRow = r;
      mCol = c;

      mP.x = OFFSET + SPACE * mCol;
      mP.y = OFFSET + BLOCK_SIZE * mRow;

      repaint();
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
   * Instantiates a new color picker.
   */
  public ColorPicker() {
    addMouseMotionListener(new MouseMoveEvents());
    addMouseListener(new MouseEvents());
  }

  /**
   * Sets the colors.
   *
   * @param colors the new colors
   */
  public void setColors(List<List<Color>> colors) {
    mColors = colors;

    mSize = new Dimension(BLOCK_SIZE * mColors.size() + GAP * (mColors.size() - 1) + DOUBLE_OFFSET + 1,
        mColors.get(0).size() * BLOCK_SIZE + DOUBLE_OFFSET + 1);
  }

  /**
   * Sets the selected color.
   *
   * @param color the new selected color
   */
  public void setSelectedColor(Color color) {
    mSelectedColor = color;

    // Select the color if it exists on the palette

    for (int col = 0; col < mColors.size(); ++col) {
      boolean found = false;

      for (int row = 0; row < mColors.get(0).size(); ++row) {
        if (mColors.get(col).get(row).equals(color)) {
          setSelectedColor(row, col);

          found = true;
          break;
        }
      }

      if (found) {
        break;
      }
    }
  }

  /**
   * Sets the selected color.
   *
   * @param row the row
   * @param col the col
   */
  public void setSelectedColor(int row, int col) {
    mSelectedRow = row;
    mSelectedCol = col;

    mSelectedP.x = OFFSET + SPACE * mSelectedCol;
    mSelectedP.y = OFFSET + BLOCK_SIZE * mSelectedRow;

    if (mSelectedCol < 0 || mSelectedCol > mColors.size()) {
      return;
    }

    if (mSelectedRow < 0 || mSelectedRow > mColors.get(mSelectedCol).size()) {
      return;
    }

    mSelectedColor = mColors.get(mSelectedCol).get(mSelectedRow);

    fireClicked(new ModernClickEvent(this, ColorSelectionModel.COLOR_CHANGED));
  }

  /**
   * Gets the selected color.
   *
   * @return the selected color
   */
  public Color getSelectedColor() {
    return mSelectedColor;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.common.ui.widget.ModernWidget#drawBackground(java.awt.Graphics2D)
   */
  @Override
  public void drawBackground(Graphics2D g2) {
    // Do nothing
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    int x = OFFSET;
    int y = OFFSET;

    for (List<Color> column : mColors) {
      y = OFFSET;

      for (Color c : column) {
        if (c != null) {
          g2.setColor(c);
          g2.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
        } else {
          g2.setColor(Color.WHITE);
          g2.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
          g2.setColor(Color.RED);
          g2.drawLine(x, y + BLOCK_SIZE - 1, x + BLOCK_SIZE - 1, y);
        }

        y += BLOCK_SIZE;
      }

      // g2.setColor(ModernWidget.LINE_COLOR);

      // g2.drawRect(x, GAP, BLOCK_SIZE, h);

      x += BLOCK_SIZE + GAP;
    }

    g2.setColor(LINE_COLOR);

    x = OFFSET;
    y = OFFSET;

    int h = BLOCK_SIZE * mColors.get(0).size();

    for (int i = 0; i < mColors.size(); ++i) {
      g2.drawRect(x, y, BLOCK_SIZE, h);

      x += BLOCK_SIZE + GAP;
    }

    if (mRow != -1) {
      // g2.setColor(Color.WHITE);
      // g2.drawRect(mP.x + 1, mP.y + 1, BLOCK_SIZE - 3, BLOCK_SIZE - 3);
      g2.setColor(SELECTION_COLOR);
      g2.drawRect(mP.x, mP.y, BLOCK_SIZE, BLOCK_SIZE);
    }

    if (mSelectedRow != -1) {
      g2.setColor(SELECTION_COLOR);
      g2.drawRect(mSelectedP.x, mSelectedP.y, BLOCK_SIZE, BLOCK_SIZE);
    }
  }

  @Override
  public Dimension getPreferredSize() {
    return mSize;
  }

  @Override
  public Dimension getMinimumSize() {
    return getPreferredSize();
  }

  @Override
  public Dimension getMaximumSize() {
    return getPreferredSize();
  }
}
