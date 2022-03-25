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

import java.awt.LayoutManager;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.jebtk.modern.FocusAdapter;
import org.jebtk.modern.css.CSSWidget;
import org.jebtk.modern.event.HighlightEvent;
import org.jebtk.modern.event.HighlightEventProducer;
import org.jebtk.modern.event.HighlightListener;
import org.jebtk.modern.event.HighlightListeners;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickEventProducer;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.event.ModernClickListeners;
import org.jebtk.modern.event.ModernSelectedEvent;
import org.jebtk.modern.event.ModernSelectedEventProducer;
import org.jebtk.modern.event.ModernSelectedListener;
import org.jebtk.modern.event.ModernSelectedListeners;
import org.jebtk.modern.event.ModernStateEvent;
import org.jebtk.modern.event.ModernStateEventProducer;
import org.jebtk.modern.event.ModernStateListener;
import org.jebtk.modern.event.ModernStateListeners;
import org.jebtk.modern.theme.RenderMode;

/**
 * Provides the foundation for all UI widgets but does not implement responses.
 * 
 * @author Antony Holmes
 */
public abstract class ModernClickWidget extends CSSWidget
    implements ModernClickEventProducer, ModernStateEventProducer,
    ModernSelectedEventProducer, HighlightEventProducer {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member message.
   */
  private String mMessage;

  /**
   * The member action listeners.
   */
  private final ModernClickListeners mActionListeners = new ModernClickListeners();

  /**
   * The member state listeners.
   */
  private final ModernStateListeners mStateListeners = new ModernStateListeners();

  private final ModernSelectedListeners mSelectedListeners = new ModernSelectedListeners();

  private final HighlightListeners mHighlightListeners = new HighlightListeners();

  /**
   * The member highlight.
   */
  protected boolean mHighlight = false;

  /**
   * The class FocusEvents.
   */
  private class FocusEvents extends FocusAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.abh.lib.ui.modern.FocusAdapter#focusLost(java.awt.event.FocusEvent)
     */
    @Override
    public void focusLost(FocusEvent e) {
      // setHighlighted(false);
    }
  }

  /**
   * The class MouseEvents.
   */
  private class MouseEvents extends MouseAdapter {

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
     * @see java.awt.event.MouseAdapter#mouseExited(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseExited(MouseEvent e) {
      setHighlighted(false);
    }
  }

  /**
   * Instantiates a new modern click widget.
   */
  public ModernClickWidget() {
    init();
  }

  /**
   * Instantiates a new modern click widget.
   *
   * @param manager the manager
   */
  public ModernClickWidget(LayoutManager manager) {
    super(manager);

    init();
  }

  /**
   * Setup.
   */
  private void init() {
    addMouseListener(new MouseEvents());
    addFocusListener(new FocusEvents());
    // addMouseMotionListener(new MouseMotionEvents());

    // Add support for CSS style rendering that responds to property changes.
    // getDrawStates().add(DrawUIService.getInstance().getRenderer("css-draw"));
    // getAnimations().add("draw-ui");

  }

  /**
   * Returns the render mode of the button indicating whether it should be
   * rendered as selected, highlighted or nothing.
   *
   * @return the render mode
   */
  public RenderMode getRenderMode() {
    if (isSelected()) {
      return RenderMode.SELECTED;
    } else if (mHighlight) {
      return RenderMode.HIGHLIGHT;
    } else {
      return RenderMode.NONE;
    }
  }

  /**
   * Sets the highlighted.
   *
   * @param highlight the new highlighted
   */
  public void setHighlighted(boolean highlight) {
    mHighlight = highlight;

    repaint();

    fireHighlighted();
  }

  private void fireHighlighted() {
    fireHighlighted(new HighlightEvent(this, -1));
  }

  /**
   * Gets the hightlighted.
   *
   * @return the hightlighted
   */
  public boolean getHightlighted() {
    return mHighlight;
  }

  /**
   * Should return true of the click widget supports popup functions and the
   * popup is visible.
   * 
   * @return
   */
  public boolean getPopupShown() {
    return false;
  }

  /**
   * Returns the message that will be sent when the item is clicked.
   *
   * @return the click message
   */
  public String getClickMessage() {
    return mMessage;
  }

  /**
   * Sets the message that will be sent when the item is clicked.
   *
   * @param clickMessage the new click message
   */
  public void setClickMessage(String clickMessage) {
    mMessage = clickMessage;
  }

  /**
   * Sets the selected.
   *
   * @param selected the new selected
   */
  public void setSelected(boolean selected) {
    repaint();
  }

  /**
   * Simulate a button click. Should be overridden.
   */
  public void doClick() {
    fireClicked();
  }

  /**
   * Fires a default click event to all listeners using the click message.
   */
  public void fireClicked() {
    // setHighlighted(false);

    fireClicked(mMessage);
  }

  /**
   * Sends a message.
   *
   * @param clickMessage the click message
   */
  public void fireClicked(String clickMessage) {
    fireClicked(new ModernClickEvent(this, clickMessage));
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.event.ModernClickEventProducer#addClickListener(org.
   * abh .lib.ui.modern.event.ModernClickListener)
   */
  @Override
  public void addClickListener(ModernClickListener l) {
    mActionListeners.addClickListener(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.event.ModernClickEventProducer#removeClickListener(
   * org. abh.lib.ui.modern.event.ModernClickListener)
   */
  @Override
  public void removeClickListener(ModernClickListener l) {
    mActionListeners.removeClickListener(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.event.ModernClickEventProducer#fireClicked(org.abh.
   * lib. ui.modern.event.ModernClickEvent)
   */
  @Override
  public void fireClicked(final ModernClickEvent e) {
    mActionListeners.fireClicked(e);
  }

  /**
   * Fires a default state change event to all listeners.
   */
  public void fireStateChanged() {
    fireStateChanged(new ModernStateEvent(this));
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.event.ModernStateEventProducer#addStateListener(org.
   * abh .lib.ui.modern.event.ModernStateListener)
   */
  @Override
  public void addStateListener(ModernStateListener l) {
    mStateListeners.addStateListener(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.event.ModernStateEventProducer#removeStateListener(
   * org. abh.lib.ui.modern.event.ModernStateListener)
   */
  @Override
  public void removeStateListener(ModernStateListener l) {
    mStateListeners.removeStateListener(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.event.ModernStateEventProducer#fireStateChanged(org.
   * abh .lib.ui.modern.event.ModernStateEvent)
   */
  @Override
  public void fireStateChanged(ModernStateEvent event) {
    mStateListeners.fireStateChanged(event);
  }

  public void fireSelected() {
    fireSelected(new ModernSelectedEvent(this));
  }

  @Override
  public void addSelectedListener(ModernSelectedListener l) {
    mSelectedListeners.addSelectedListener(l);
  }

  @Override
  public void removeSelectedListener(ModernSelectedListener l) {
    mSelectedListeners.removeSelectedListener(l);
  }

  @Override
  public void fireSelected(ModernSelectedEvent event) {
    mSelectedListeners.fireSelected(event);
  }

  @Override
  public void addHighlightListener(HighlightListener l) {
    mHighlightListeners.addHighlightListener(l);
  }

  @Override
  public void removeHighlightListener(HighlightListener l) {
    mHighlightListeners.removeHighlightListener(l);
  }

  @Override
  public void fireHighlighted(HighlightEvent e) {
    mHighlightListeners.fireHighlighted(e);
  }
}