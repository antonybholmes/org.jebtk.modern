package org.jebtk.modern.animation;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.jebtk.modern.ModernWidget;

/**
 * Allows for fade in/out animation on an element.
 *
 * @author Antony Holmes
 */
public abstract class MousePressReleaseAnimation extends TimerAnimation {

  protected boolean mPressed = false;

  /**
   * The Class MouseEvents.
   */
  private class MouseEvents extends MouseAdapter {
    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
     */
    @Override
    public void mousePressed(MouseEvent e) {
      mPressed = true;

      start();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      mPressed = false;

      stop();
    }

    @Override
    public void mouseExited(MouseEvent e) {
      reset();
    }
  }

  /**
   * Instantiates a new mouse animation.
   *
   * @param widget the widget
   */
  public MousePressReleaseAnimation(ModernWidget widget) {
    super(widget);

    bind(widget);
  }

  /**
   * Bind the widget so it can respond with an animation. This widget can be
   * auxiliary to the primary widget whose animation is being controlled.
   * 
   * @param mWidget
   * @return
   */
  public MousePressReleaseAnimation bind(Component c) {
    c.addMouseListener(new MouseEvents());

    return this;
  }

  public boolean isPressed() {
    return mPressed;
  }
}