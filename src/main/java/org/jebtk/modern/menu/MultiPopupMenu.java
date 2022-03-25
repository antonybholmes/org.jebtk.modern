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
package org.jebtk.modern.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The class MultiPopupMenu.
 */
public class MultiPopupMenu {

  /**
   * The main method.
   *
   * @param args the arguments
   */
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    // frame.setFocusable(true);

    // Create popup
    JDialog popup1 = createPopup(frame, "First label");
    // JDialog popup2 = createPopup(frame, "Second label");

    // Create labels
    JLabel label1 = new JLabel("abcde");
    JLabel label2 = new JLabel("1234");

    JPanel panel = new JPanel();
    panel.add(label1);
    panel.add(label2);

    // Add labels

    frame.add(panel);

    frame.setPreferredSize(new Dimension(200, 100));
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);

    // Show popups
    popup1.pack();
    // popup2.pack();

    Point floc = frame.getLocation();
    Point loc = label1.getLocation();
    System.out.println(floc);
    popup1.setLocation((int) (floc.getX() + loc.getX()) - 20, (int) (floc.getY() + loc.getY()) + 40);
    loc = label2.getLocation();
    // popup2.setLocation((int)(floc.getX()+loc.getX())+20,
    // (int)(floc.getY()+loc.getY())+40);

    popup1.setBackground(Color.YELLOW);
    popup1.setVisible(true);
    // popup2.setVisible(true);
  }

  /**
   * Creates the popup.
   *
   * @param parent the parent
   * @param label  the label
   * @return the j dialog
   */
  private static JDialog createPopup(Frame parent, String label) {
    JDialog popup = new JDialog(parent);
    popup.setUndecorated(true);

    JLabel lblTest = new JLabel(label);
    popup.add(lblTest);
    popup.getContentPane().setBackground(Color.YELLOW);

    popup.addWindowFocusListener(new WindowFocusListener() {

      @Override
      public void windowGainedFocus(WindowEvent e) {
        // TODO Auto-generated method stub

      }

      @Override
      public void windowLostFocus(WindowEvent e) {
        System.err.println("dssdjsdj");

        e.getWindow().setVisible(false);
        e.getWindow().dispose();
      }
    });

    popup.addWindowListener(new WindowListener() {

      @Override
      public void windowActivated(WindowEvent e) {
        // TODO Auto-generated method stub

      }

      @Override
      public void windowClosed(WindowEvent e) {
        System.err.println("dssdjsdj");
      }

      @Override
      public void windowClosing(WindowEvent e) {
        System.err.println("dssdjsdj");
      }

      @Override
      public void windowDeactivated(WindowEvent e) {
        System.err.println("dssdjsdj");
      }

      @Override
      public void windowDeiconified(WindowEvent e) {
        // TODO Auto-generated method stub

      }

      @Override
      public void windowIconified(WindowEvent e) {
        // TODO Auto-generated method stub

      }

      @Override
      public void windowOpened(WindowEvent e) {
        // TODO Auto-generated method stub

      }
    });

    return popup;
  }
}
