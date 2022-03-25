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
import java.awt.Point;

import org.jebtk.core.event.Event;

/**
 * The class ModernToolTipEvent.
 */
public class ModernToolTipEvent extends Event {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  private static final String DEFAULT_MESSAGE = "tooltip";

  /**
   * The tooltip.
   */
  private Component mTooltip;

  private Component mSource;

  private ModernToolTipListener mDest;

  private Point mP;

  private ToolTipLevel mLevel = ToolTipLevel.NORMAL;

  // public ModernToolTipEvent(Component dest) {
  // this(dest, (ModernToolTipListener) dest);
  // }

  public ModernToolTipEvent(Component source, ModernToolTipListener dest) {
    this(source, dest, ToolTipLevel.NORMAL);
  }

  public ModernToolTipEvent(Component source, ModernToolTipListener dest, ToolTipLevel l) {
    this(source, dest, source, l);
  }

  public ModernToolTipEvent(Component source, ModernToolTipListener dest, Component tooltip) {
    this(source, dest, tooltip, ToolTipLevel.NORMAL);
  }

  public ModernToolTipEvent(Component source, ModernToolTipListener dest, Component tooltip, ToolTipLevel l) {
    this(source, dest, tooltip, null, l);
  }

  public ModernToolTipEvent(Component source, ModernToolTipListener dest, Component tooltip, Point p) {
    this(source, dest, tooltip, p, ToolTipLevel.NORMAL);
  }

  public ModernToolTipEvent(Component source, ModernToolTipListener dest, Component tooltip, Point p, ToolTipLevel l) {
    this(source, dest, tooltip, p, l, DEFAULT_MESSAGE);
  }

  /**
   * Instantiates a new modern tool tip event.
   *
   * @param source  the source
   * @param message the message
   * @param tooltip the tooltip
   */
  public ModernToolTipEvent(Component source, ModernToolTipListener dest, Component tooltip, Point p,
      ToolTipLevel level, String message) {
    super(source, message);

    mSource = source;
    mDest = dest;
    mTooltip = tooltip;
    mP = p;
    mLevel = level;
  }

  @Override
  public Component getSource() {
    return mSource;
  }

  public ModernToolTipListener getDest() {
    return mDest;
  }

  public Component getTooltip() {
    return mTooltip;
  }

  public Point getP() {
    return mP;
  }

  public ToolTipLevel getLevel() {
    return mLevel;
  }
}
