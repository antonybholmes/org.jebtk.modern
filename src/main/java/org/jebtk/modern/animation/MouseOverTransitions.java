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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Timer;

import org.jebtk.core.Mathematics;
import org.jebtk.modern.ModernWidget;

/**
 * Allows for fade in/out animation on an element.
 *
 * @author Antony Holmes
 */
public class MouseOverTransitions extends Transitions {

  /** The m mouse over timer. */
  private Timer mMouseOverTimer;

  private boolean mForward = true;

  private double mT = 0;

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
      mForward = true;

      startMouseOverTimer();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseExited(MouseEvent e) {
      // System.err.println("mouse exit " + e.getSource());

      mForward = false;

      pseudoMouseExited();
    }
  }

  /**
   * The Class HoverEvents.
   */
  private class HoverEvents implements ActionListener {

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      if (mForward) {
        animateTime();
      } else {
        animateMouseExited();
      }
    }
  }

  /**
   * Instantiates a new mouse animation.
   *
   * @param widget the widget
   */
  public MouseOverTransitions(ModernWidget widget) {
    super(widget);

    mMouseOverTimer = new Timer(0, new HoverEvents());
    mMouseOverTimer.setDelay(AnimationTimer.DELAY_MS);
    // mMouseClickedTimer = new Timer(0, new ClickedEvents());
    // mMouseClickedTimer.setDelay(DELAY_MS);

    bind(widget);
  }

  public MouseOverTransitions(ModernWidget widget, TransitionTimer timer) {
    super(widget, timer);

    mMouseOverTimer = new Timer(0, new HoverEvents());
    mMouseOverTimer.setDelay(AnimationTimer.DELAY_MS);
    // mMouseClickedTimer = new Timer(0, new ClickedEvents());
    // mMouseClickedTimer.setDelay(DELAY_MS);

    bind(widget);
  }

  /**
   * Bind the widget so it can respond with an animation. This widget can be
   * auxiliary to the primary widget whose animation is being controlled.
   * 
   * @param mWidget
   * @return
   */
  public MouseOverTransitions bind(Component c) {
    c.addMouseListener(new MouseEvents());

    return this;
  }

  public void startMouseOverTimer() {
    if (!mMouseOverTimer.isRunning()) {
      mMouseOverTimer.start();
    }
  }

  /**
   * Stop mouse over timer.
   */
  public void stopMouseOverTimer() {
    mMouseOverTimer.stop();
  }

  /**
   * Animate mouse entered.
   */
  public void animateTime() {
    if (mT == 0.0 || mT == 1.0) {
      mMouseOverTimer.stop();
    }

    mT = Mathematics.bound(getTimer().nextT(mForward), 0.0, 1.0);

    updateState(mT, mForward);

    // After updating the state, force a repaint
    mWidget.repaint();
  }

  /**
   * Animate mouse exited.
   */
  public void animateMouseExited() {
    mWidget.repaint();
  }

  /**
   * Triggers the events an animation that a mouse exit event would, but without
   * requiring an actual mouse event
   */
  public void pseudoMouseExited() {
    mForward = false;

    startMouseOverTimer();
  }

  public void bindChildren() {
    mWidget.addContainerListener(new ContainerListener() {

      @Override
      public void componentAdded(ContainerEvent arg0) {
        bind();
      }

      @Override
      public void componentRemoved(ContainerEvent arg0) {
        // bind();
      }
    });

    bind();
  }

  private void bind() {
    for (int i = 0; i < mWidget.getComponentCount(); ++i) {
      bind(mWidget.getComponent(i));
    }
  }

  @Override
  public String getName() {
    return "mouse-over-transitions";
  }
}
