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
package org.jebtk.modern.tooltip;

import java.awt.Component;
import java.awt.Window;
import java.util.Map;

import org.jebtk.core.collections.DefaultHashMap;
import org.jebtk.core.collections.EntryCreator;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.window.ModernWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * Keeps track of the windows associated with an application so they can be
 * switched between if necessary.
 *
 * @author Antony Holmes
 *
 */
public class ToolTipService implements ModernToolTipEventProducer {

  /**
   * The Class WindowServiceLoader.
   */
  private static class ToolTipServiceLoader {

    /** The Constant INSTANCE. */
    private static final ToolTipService INSTANCE = new ToolTipService();
  }

  private static class ToolTipLog implements ModernToolTipListener {

    @Override
    public void tooltipShown(ModernToolTipEvent e) {
      LOG.info("Show tooltip: {} -> {}.", e.getSource().getClass(), e.getDest().getClass());
    }

    @Override
    public void tooltipAdded(ModernToolTipEvent e) {
      LOG.info("Add tooltip: {} -> {}.", e.getSource().getClass(), e.getDest().getClass());
    }

    @Override
    public void tooltipHidden(ModernToolTipEvent e) {
      LOG.info("Hide tooltip: {} -> {}.", e.getSource().getClass(), e.getDest().getClass());
    }

    @Override
    public void tooltipsHidden(ModernToolTipEvent e) {
      LOG.info("Hide tooltips: {} -> {}.", e.getSource().getClass(), e.getDest().getClass());
    }

  }

  /**
   * Gets the single instance of SettingsService.
   *
   * @return single instance of SettingsService
   */
  public static ToolTipService getInstance() {
    return ToolTipServiceLoader.INSTANCE;
  }

  private static final Object ALL_EVENTS = new Object();

  private Map<Object, ToolTipListeners> mListenerMap = DefaultHashMap.create(new EntryCreator<ToolTipListeners>() {
    @Override
    public ToolTipListeners newEntry() {
      return new ToolTipListeners();
    }
  });

  /**
   * The constant LOG.
   */
  private static final Logger LOG = LoggerFactory.getLogger(ToolTipService.class);

  private ToolTipService() {
    // Log tooltips
    // addAllToolTipListener(new ToolTipLog());
  }

  @Override
  public void addToolTipListener(ModernToolTipListener l) {
    addToolTipListener(l, l);
  }

  /**
   * Register to receive all tooltip events.
   * 
   * @param l
   */
  public void addAllToolTipListener(ModernToolTipListener l) {
    mListenerMap.get(ALL_EVENTS).addToolTipListener(l);
  }

  public void addToolTipListener(ModernToolTipListener dest, ModernToolTipListener l) {
    mListenerMap.get(dest).addToolTipListener(l);
  }

  @Override
  public void removeToolTipListener(ModernToolTipListener l) {
    removeToolTipListener(l, l);
  }

  public void removeAllToolTipListener(ModernToolTipListener l) {
    mListenerMap.get(ALL_EVENTS).addToolTipListener(l);
  }

  public void removeToolTipListener(ModernToolTipListener dest, ModernToolTipListener l) {
    mListenerMap.get(dest).removeToolTipListener(l);
  }

  public void showToolTip(Component c) {
    // Automatically search for destination component by assuming it is
    // the underlying window this component belongs to
    showToolTip(new ModernToolTipEvent(c, ToolTipService.getToolTipWindow(c)));
  }

  @Override
  public void showToolTip(ModernToolTipEvent e) {
    mListenerMap.get(e.getDest()).showToolTip(e);
    mListenerMap.get(ALL_EVENTS).showToolTip(e);
  }

  @Override
  public void addToolTip(ModernToolTipEvent e) {
    mListenerMap.get(e.getDest()).addToolTip(e);
    mListenerMap.get(ALL_EVENTS).addToolTip(e);
  }

  public void hideToolTip(Component c) {
    hideToolTip(c, ToolTipLevel.NORMAL);
  }

  public void hideToolTip(Component c, ToolTipLevel l) {
    hideToolTip(new ModernToolTipEvent(c, ToolTipService.getToolTipWindow(c), l));
  }

  @Override
  public void hideToolTip(ModernToolTipEvent e) {
    mListenerMap.get(e.getDest()).hideToolTip(e);
    mListenerMap.get(ALL_EVENTS).hideToolTip(e);
  }

  @Override
  public void hideToolTips(ModernToolTipEvent e) {
    mListenerMap.get(e.getDest()).hideToolTips(e);
    mListenerMap.get(ALL_EVENTS).hideToolTips(e);
  }

  /**
   * Find the first component in the hierarchy that accepts tooltips
   *
   * @param source the source
   * @return the tool tip display
   */
  public static ModernToolTipListener getToolTipDest(ModernWidget source) {
    Component parent = source.getParent();

    while (parent != null) {
      if (parent instanceof ModernToolTipListener) {
        return (ModernToolTipListener) parent;
      }

      parent = parent.getParent();
    }

    return null;
  }

  /**
   * Returns the underlying window of a component that accepts tooltip events. If
   * no window is found, returns null.
   * 
   * @param source
   * @return
   */
  public static ModernToolTipListener getToolTipWindow(Component source) {
    Window w = ModernWindow.getParentWindow(source);

    if (w instanceof ModernToolTipListener) {
      return (ModernToolTipListener) w;
    } else {
      return null;
    }
  }
}
