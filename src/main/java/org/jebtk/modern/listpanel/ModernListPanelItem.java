package org.jebtk.modern.listpanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.jebtk.modern.ModernWidget;

public class ModernListPanelItem extends ModernWidget {

  private static final long serialVersionUID = 1L;
  public static final int HANDLE_SIZE = 32;

  public static final int BURGER_WIDTH = 16;

  protected final Color mColor;

  public ModernListPanelItem(Component c, Color color) {
    mColor = color;

    setLayout(new ModernListPanelItemLayout());

    add(c);

    setBorder(ModernWidget.BORDER);

    setAnimations("listpanel");

    addMouseListener(new MouseAdapter() {

      @Override
      public void mousePressed(MouseEvent e) {
        if (e.getButton() != MouseEvent.BUTTON1) {
          return;
        }

        setCursor(new Cursor(Cursor.MOVE_CURSOR));
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      }
    });
  }
}
