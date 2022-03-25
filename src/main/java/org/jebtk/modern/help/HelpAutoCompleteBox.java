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
package org.jebtk.modern.help;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;
import org.jebtk.modern.menu.ModernMenuItem;
import org.jebtk.modern.menu.ModernMenuPanel;
import org.jebtk.modern.panel.ModernPanel;
import org.jebtk.modern.ribbon.Ribbon;
import org.jebtk.modern.text.ModernTextField;

/**
 * The Class HelpAutoCompleteBox.
 */
public class HelpAutoCompleteBox extends ModernPanel {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The m field. */
  private ModernTextField mField = new ModernTextField("HelpAutoCompleteBox");

  /** The m panel. */
  private ModernMenuPanel mPanel = new ModernMenuPanel();

  /** The m ref. */
  private HelpAutoCompleteBox mRef;

  /** The m frame. */
  private JFrame mFrame;

  /**
   * Instantiates a new help auto complete box.
   *
   * @param frame the frame
   */
  public HelpAutoCompleteBox(JFrame frame) {
    mFrame = frame;

    setBackground(Ribbon.BAR_BACKGROUND);

    add(mField);

    UI.setSize(this, 200, ModernWidget.WIDGET_HEIGHT);

    mRef = this;

    mPanel.setVisible(false);

    Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
      @Override
      public void eventDispatched(AWTEvent event) {
        if (event instanceof MouseEvent) {
          MouseEvent e = (MouseEvent) event;
          if (e.getID() == MouseEvent.MOUSE_PRESSED) {
            if (checkInsidePanel(e)) {
              System.err.println("Inside panel");
            } else if (checkInsideMenu(e)) {
              System.err.println("Inside menu");
            } else {
              mPanel.setVisible(false);
            }
          }
        }
      }

      public boolean checkInsidePanel(MouseEvent e) {
        Point l = getLocation();

        SwingUtilities.convertPointToScreen(l, mRef);

        Point p = e.getPoint();

        SwingUtilities.convertPointToScreen(p, e.getComponent());

        // System.err.println(p + " " + l);

        return p.x >= l.x && p.x <= l.x + getWidth() && p.y >= l.y && p.y <= l.y + getHeight();

      }

      public boolean checkInsideMenu(MouseEvent e) {
        Point l = getLocation();

        SwingUtilities.convertPointToScreen(l, mPanel);

        Point p = e.getPoint();

        SwingUtilities.convertPointToScreen(p, e.getComponent());

        return p.x >= l.x && p.x <= l.x + mPanel.getWidth() && p.y >= l.y && p.y <= l.y + mPanel.getHeight();
      }

    }, AWTEvent.MOUSE_EVENT_MASK);

    frame.getLayeredPane().add(mPanel, JLayeredPane.POPUP_LAYER);

    mField.addMouseListener(new MouseListener() {

      @Override
      public void mouseClicked(MouseEvent arg0) {
        // TODO Auto-generated method stub

      }

      @Override
      public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub

      }

      @Override
      public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub

      }

      @Override
      public void mousePressed(MouseEvent arg0) {
        checkForAndShowSuggestions();
      }

      @Override
      public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub

      }
    });

    mField.getDocument().addDocumentListener(new DocumentListener() {

      @Override
      public void changedUpdate(DocumentEvent arg0) {
        // checkForAndShowSuggestions();
      }

      @Override
      public void insertUpdate(DocumentEvent arg0) {
        // checkForAndShowSuggestions();
      }

      @Override
      public void removeUpdate(DocumentEvent arg0) {
        // checkForAndShowSuggestions();
      }
    });

    mField.addKeyListener(new KeyListener() {

      @Override
      public void keyPressed(KeyEvent arg0) {

      }

      @Override
      public void keyReleased(KeyEvent arg0) {
        checkForAndShowSuggestions();
      }

      @Override
      public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub

      }
    });

    mField.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), "Down released");
    mField.getActionMap().put("Down released", new AbstractAction() {
      private static final long serialVersionUID = 1L;

      @Override
      public void actionPerformed(ActionEvent ae) {// focuses the first label on
        // popwindow
        // mWindow.toFront();
        // mWindow.requestFocusInWindow();
      }
    });

    /*
     * Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
     * 
     * @Override public void eventDispatched(AWTEvent event) {
     * System.err.println(event.getID() + " " + event.getSource());
     * 
     * if (event instanceof WindowEvent) { System.out.println("WindowEvent");
     * WindowEvent evt = (WindowEvent) event; if (evt.getID() ==
     * WindowEvent.WINDOW_GAINED_FOCUS) { System.out.println("I got you babe"); }
     * else if (evt.getID() == WindowEvent.WINDOW_LOST_FOCUS) {
     * System.out.println("Don't leave me!"); } } else if (event instanceof
     * FocusEvent) { System.out.println("FocusEvent"); } } },
     * AWTEvent.WINDOW_FOCUS_EVENT_MASK | AWTEvent.FOCUS_EVENT_MASK |
     * AWTEvent.COMPONENT_EVENT_MASK);
     */
  }

  /**
   * Check for and show suggestions.
   */
  private void checkForAndShowSuggestions() {
    mPanel.removeAll();

    for (int i = 0; i < Math.min(mField.getText().length(), 10); ++i) {
      mPanel.add(new ModernMenuItem("Menu " + i));
    }

    mPanel.revalidate();
    mPanel.repaint();

    showPopUpWindow();
  }

  /**
   * Show pop up window.
   */
  private void showPopUpWindow() {
    // mWindow.setMinimumSize(new Dimension(mField.getWidth(), 30));
    // mWindow.setSize(tW, tH);

    Point p = new Point(mField.getLocation().x, mField.getLocation().y + mField.getHeight());

    SwingUtilities.convertPointToScreen(p, mField);

    SwingUtilities.convertPointFromScreen(p, mFrame.getLayeredPane());
    mPanel.setLocation(p);
    mPanel.setSize(getWidth(), mPanel.getComponentCount() * ModernMenuItem.HEIGHT);

    mPanel.setVisible(true);
  }

  /**
   * The main method.
   *
   * @param args the arguments
   */
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.getContentPane().setLayout(new BorderLayout());
    frame.getContentPane().add(new HelpAutoCompleteBox(frame), BorderLayout.PAGE_START);

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(200, 200);
    frame.setVisible(true);
  }
}
