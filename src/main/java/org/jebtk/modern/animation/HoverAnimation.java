/**
 * Copyright 2017 Antony Holmes
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
package org.jebtk.modern.animation;

import java.awt.Component;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.jebtk.modern.ModernWidget;

/**
 * Allows for fade in/out animation on an element.
 *
 * @author Antony Holmes
 */
public abstract class HoverAnimation extends TimerAnimation {

  /** The m mouse clicked timer. */
  // private Timer mMouseClickedTimer;

  /** The m entry mode. */
  private boolean mHoverMode = false;

  /** The m pressed. */
  // protected boolean mPressed = false;

  /**
   * The Class MouseEvents.
   */
  private class MouseEvents extends MouseAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseEntered(MouseEvent e) {
      fireEvent(AnimationEventType.MOUSE_ENTERED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseExited(MouseEvent e) {
      fireEvent(AnimationEventType.MOUSE_EXITED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
     */
    // @Override
    // public void mousePressed(MouseEvent e) {
    // if (e.getButton() == MouseEvent.BUTTON1) {
    // mPressed = true;
    // }
    // }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
     */
    // @Override
    // public void mouseReleased(MouseEvent e) {
    // mPressed = false;
    //
    // if (!mEntryMode) {
    // pseudoMouseExited();
    // }
    // }

  }

  /**
   * Instantiates a new mouse animation.
   *
   * @param widget the widget
   */
  public HoverAnimation(ModernWidget widget) {
    super(widget);

    bind(widget);
  }

  @Override
  public void fireEvent(AnimationEventType trigger) {
    switch (trigger) {
    case MOUSE_ENTERED:
      mHoverMode = true;
      restart();
      break;
    case MOUSE_EXITED:
      pseudoMouseExited();
      break;
    default:
      break;
    }
  }

  /**
   * Bind the widget so it can respond with an animation.This widget can be
   * auxiliary to the primary widget whose animation is being controlled.
   * 
   * @param c
   * @return
   */
  public HoverAnimation bind(Component c) {
    c.addMouseListener(new MouseEvents());

    return this;
  }

  @Override
  public void animate(int frame) {
    if (mHoverMode) {
      animateMouseEntered();
    } else {
      animateMouseExited();
    }
  }

  /**
   * Animate mouse entered.
   */
  public void animateMouseEntered() {
    repaint();
  }

  /**
   * Animate mouse exited.
   */
  public void animateMouseExited() {
    repaint();
  }

  /**
   * Triggers the events an animation that a mouse exit event would, but without
   * requiring an actual mouse event
   */
  public void pseudoMouseExited() {
    mHoverMode = false;

    stop();
    
    reset();

    animateMouseExited(); // restart();
  }

  @Override
  public void bindChildren() {
    getWidget().addContainerListener(new ContainerListener() {

      @Override
      public void componentAdded(ContainerEvent e) {
        bind();
      }

      @Override
      public void componentRemoved(ContainerEvent e) {
        // bind();
      }
    });

    bind();
  }

  private void bind() {
    for (int i = 0; i < getWidget().getComponentCount(); ++i) {
      bind(getWidget().getComponent(i));
    }
  }
}
