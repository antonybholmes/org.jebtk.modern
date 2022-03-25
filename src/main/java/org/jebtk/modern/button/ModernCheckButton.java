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
package org.jebtk.modern.button;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.jebtk.modern.UI;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.text.ITextProperty;

// TODO: Auto-generated Javadoc
/**
 * Low height button for small form factor toolbar buttons.
 *
 * @author Antony Holmes
 */
public class ModernCheckButton extends ModernTwoStateWidget implements ITextProperty {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Deals with mouse events specifically for buttons.
   * 
   * @author Antony Holmes
   *
   */
  private class MouseEvents extends MouseAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mouseExited(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseExited(MouseEvent e) {
      setHighlighted(false);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mouseEntered(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseEntered(MouseEvent e) {
      setHighlighted(true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
     */
    @Override
    public void mousePressed(MouseEvent e) {
      if (!isEnabled()) {
        return;
      }

      repaint();
    }
  }

  /**
   * The class FocusEvents.
   */
  private class FocusEvents implements FocusListener {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
     */
    @Override
    public void focusGained(FocusEvent e) {
      // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
     */
    @Override
    public void focusLost(FocusEvent e) {
      setHighlighted(false);
    }
  }

  /**
   * The class ModernClickEvents.
   */
  private class ModernClickEvents implements ModernClickListener {

    /*
     * (non-Javadoc)
     * 
     * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
     * modern .event.ModernClickEvent)
     */
    @Override
    public void clicked(ModernClickEvent e) {
      setHighlighted(false);
    }

  }

  /**
   * The member text1.
   */
  // protected JLabel label1 = new JLabel();
  protected String mText1 = null;

  /**
   * The member icon.
   */
  protected ModernIcon mIcon = null;

  // protected boolean pressed = false;
  // protected boolean selected = false;

  /**
   * Instantiates a new modern check button.
   */
  public ModernCheckButton() {
    setup();
  }

  /**
   * Instantiates a new modern check button.
   *
   * @param text1 the text1
   */
  public ModernCheckButton(String text1) {
    this(text1, false);
  }

  /**
   * Instantiates a new modern check button.
   *
   * @param text1    the text1
   * @param selected the selected
   */
  public ModernCheckButton(String text1, boolean selected) {
    setText(text1);

    setup();

    UI.setSize(this, ModernButton.getButtonSize(text1));

    setSelected(selected);
  }

  /**
   * Instantiates a new modern check button.
   *
   * @param icon the icon
   */
  public ModernCheckButton(ModernIcon icon) {
    setIcon(icon);

    setup();

    UI.setSize(this, ModernButton.ICON_ONLY_SIZE);
  }

  /**
   * Instantiates a new modern check button.
   *
   * @param text1 the text1
   * @param icon  the icon
   */
  public ModernCheckButton(String text1, ModernIcon icon) {
    this(text1, icon, false);
  }

  /**
   * Instantiates a new modern check button.
   *
   * @param text1    the text1
   * @param icon     the icon
   * @param selected the selected
   */
  public ModernCheckButton(String text1, ModernIcon icon, boolean selected) {
    setText(text1);
    setIcon(icon);

    setup();

    UI.setSize(this, ModernButton.getIconButtonSize(text1));

    setSelected(selected);
  }

  /**
   * Instantiates a new modern check button.
   *
   * @param text the text
   * @param size the size
   */
  public ModernCheckButton(String text, Dimension size) {
    this(text);

    UI.setSize(this, size);
  }

  /**
   * Setup.
   */
  private void setup() {

    // getAnimations().add("button-selected");

    addMouseListener(new MouseEvents());
    addFocusListener(new FocusEvents());
    addClickListener(new ModernClickEvents());

    // getDrawStates().add(DrawUIService.getInstance().getRenderer("css-draw"));
    // getAnimations().add("draw-ui");
  }

  /**
   * Sets the icon.
   *
   * @param icon the new icon
   */
  @Override
  public void setIcon(ModernIcon icon) {
    mIcon = icon;
  }

  /**
   * Gets the icon.
   *
   * @return the icon
   */
  @Override
  public ModernIcon getIcon() {
    return mIcon;
  }

  /**
   * Gets the disabled icon.
   *
   * @return the disabled icon
   */
  public ModernIcon getDisabledIcon() {
    return mIcon.getDisabledIcon();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.text.TextProperty#getText()
   */
  @Override
  public String getText() {
    return mText1;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.text.TextProperty#setText(java.lang.String)
   */
  @Override
  public void setText(String text) {
    mText1 = text;

    setClickMessage(text);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    // Rectangle rect = new Rectangle(0, 0, getWidth(), getHeight());

    int iconX = PADDING;

    if (mText1 != null) {
      int x;

      if (getIcon() != null) {
        x = iconX + getIcon().getWidth() + PADDING;
      } else {
        x = (getWidth() - g2.getFontMetrics().stringWidth(mText1)) / 2;
      }

      g2.setColor(getForeground());

      g2.drawString(mText1, x, getTextYPosCenter(g2, getHeight()));
    }

    if (getIcon() != null) {
      if (mText1 == null || mText1.length() == 0) {
        iconX = (getWidth() - getIcon().getWidth()) / 2;
      }

      int iconY = (getHeight() - getIcon().getWidth()) / 2;

      if (isEnabled()) {
        getIcon().drawIcon(g2, iconX, iconY, getIcon().getWidth());
      } else {
        getDisabledIcon().drawIcon(g2, iconX, iconY, getIcon().getWidth());
      }
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernClickWidget#setHighlighted(boolean)
   */
  @Override
  public void setHighlighted(boolean highlight) {
    if (!isEnabled()) {
      return;
    }

    mHighlight = highlight;

    repaint();
  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.swing.JComponent#setEnabled(boolean)
   */
  public void setEnabled(boolean enabled) {

    super.setEnabled(enabled);

    if (this.isEnabled()) {
      setForeground(TEXT_COLOR);
    } else {
      setForeground(ALT_TEXT_COLOR);
    }
  }
}
