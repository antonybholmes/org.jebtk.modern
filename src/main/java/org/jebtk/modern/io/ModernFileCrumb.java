package org.jebtk.modern.io;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.event.ChangeEventProducer;
import org.jebtk.core.event.ChangeListener;
import org.jebtk.core.event.ChangeListeners;
import org.jebtk.core.geom.IntBlock;
import org.jebtk.core.io.FileUtils;
import org.jebtk.core.io.PathUtils;
import org.jebtk.modern.AssetService;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;
import org.jebtk.modern.graphics.icons.CheveronRightVectorIcon;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.ribbon.Ribbon;
import org.jebtk.modern.text.ModernTextField;

public class ModernFileCrumb extends ModernWidget implements ChangeEventProducer {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  private Path mDir;

  private List<Path> mPaths;

  private static final int ICON_SIZE = 12;

  private final ModernIcon CRUMB_ICON = AssetService.getInstance().loadIcon(CheveronRightVectorIcon.class, ICON_SIZE);

  private final ModernTextField mTextField = new ModernTextField();

  private boolean mEnterMode = false;

  private final ChangeListeners mListeners = new ChangeListeners();

  private final List<IntBlock> mStarts = new ArrayList<>(50);

  private int mSelectedIndex = -1;

  private class ResizeEvents extends ComponentAdapter {
    @Override
    public void componentResized(ComponentEvent e) {
      mTextField.setBounds(PADDING, PADDING, getWidth() - DOUBLE_PADDING, getHeight() - DOUBLE_PADDING);
    }
  }

  /**
   * Instantiates a new modern text border panel.
   *
   * @param textField the text field
   * @param color     the color
   */
  public ModernFileCrumb(Path file) {
    setup();

    setDir(file);
  }

  /**
   * Setup.
   */
  private void setup() {
    setLayout(null);

    add(mTextField);

    mTextField.setVisible(false);
    // mTextField.setBackground(MaterialService); // ColorUtils.TRANS_COLOR);

    addComponentListener(new ResizeEvents());
    addMouseListener(new MouseAdapter() {
      /*
       * (non-Javadoc)
       * 
       * @see java.awt.event.MouseAdapter#mouseEntered(java.awt.event.MouseEvent)
       */
      // @Override
      // public void mouseEntered(MouseEvent e) {
      // setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
      // }

      /*
       * (non-Javadoc)
       * 
       * @see java.awt.event.MouseAdapter#mouseExited(java.awt.event.MouseEvent)
       */
      @Override
      public void mouseExited(MouseEvent e) {
        reset();
      }

      @Override
      public void mousePressed(MouseEvent e) {
        if (e.getClickCount() > 1) {
          setEnterMode(true);
        } else {
          if (mSelectedIndex != -1) {
            setDir(mPaths.get(mSelectedIndex));
          }
        }
      }
    });

    addMouseMotionListener(new MouseMotionListener() {
      @Override
      public void mouseDragged(MouseEvent arg0) {
        // TODO Auto-generated method stub

      }

      @Override
      public void mouseMoved(MouseEvent e) {
        int i = getIndex(e.getX());

        if (i != -1) {
          setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

          if (mSelectedIndex != i) {
            mSelectedIndex = i;
            repaint();
          }
        } else {
          setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
      }
    });

    addFocusListener(new FocusListener() {
      @Override
      public void focusGained(FocusEvent arg0) {
        // TODO Auto-generated method stub

      }

      @Override
      public void focusLost(FocusEvent arg0) {
        setEnterMode(false);

        reset();
      }
    });

    mTextField.addFocusListener(new FocusListener() {
      @Override
      public void focusGained(FocusEvent arg0) {
        // TODO Auto-generated method stub

      }

      @Override
      public void focusLost(FocusEvent arg0) {
        setEnterMode(false);

        reset();
      }
    });

    mTextField.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          setDir(PathUtils.getPath(mTextField.getText()));
        }
      }
    });

    // setMinimumSize(mComponent.getMinimumSize());
    // setMaximumSize(mComponent.getMaximumSize());

    UI.setSize(this, STANDARD_SIZE);

    // setBorder(SMALL_BORDER);

    // getBackgroundAnimations().set("button-outline");
  }

  private int getIndex(int x) {
    for (int i = 0; i < mStarts.size(); ++i) {
      IntBlock start = mStarts.get(i);

      if (x >= start.mX && x < start.mX + start.mW) {
        return i;
      }
    }

    return -1;
  }

  public void setDir(Path dir) {
    if (updateDir(dir)) {
      fireChanged();
    }
  }

  public boolean updateDir(Path dir) {
    if (!FileUtils.isDirectory(dir)) {
      return false;
    }

    mDir = dir.toAbsolutePath();

    Deque<Path> stack = new ArrayDeque<>();

    Path p = mDir;

    while (p != null) {
      stack.push(p);

      p = p.getParent();
    }

    mPaths = new ArrayList<>(stack.size());

    while (!stack.isEmpty()) {
      mPaths.add(stack.pop());
    }

    mTextField.setText(PathUtils.toString(mDir));

    mStarts.clear();

    setEnterMode(false);

    return true;
  }

  // @Override
  // public void drawAnimatedBackground(Graphics2D g2) {
  // getWidgetRenderer().drawContentBox(g2, mRect);

  // super.drawAnimatedBackground(g2);
  // }

  @Override
  public void drawForegroundAA(Graphics2D g2) {
    if (!mEnterMode) {
      boolean cache = mStarts.isEmpty();

      int x = PADDING;
      int w;

      int y1 = getTextYPosCenter(g2, getHeight());
      int y2 = (getHeight() - CRUMB_ICON.getHeight()) / 2;

      for (int i = 0; i < mPaths.size(); ++i) {
        Path part = mPaths.get(i);

        String name = PathUtils.getName(part);

        if (name.length() > 0) {
          CRUMB_ICON.drawIcon(g2, x, y2, ICON_SIZE);

          x += ICON_SIZE + PADDING;

          if (i == mSelectedIndex) {
            g2.setColor(Ribbon.BAR_BACKGROUND);
          } else {
            g2.setColor(TEXT_COLOR);
          }

          g2.drawString(name, x, y1);
        }

        w = getStringWidth(g2, name);

        if (cache) {
          mStarts.add(IntBlock.create(x, w));
        }

        x += w + PADDING;
      }
    }
  }

  private void setEnterMode(boolean mode) {
    mEnterMode = mode;

    mTextField.setVisible(mode);

    if (mode) {
      mTextField.requestFocusInWindow();

      mTextField.selectAll();
    }

    repaint();
  }

  @Override
  public void addChangeListener(ChangeListener l) {
    mListeners.addChangeListener(l);
  }

  @Override
  public void removeChangeListener(ChangeListener l) {
    mListeners.removeChangeListener(l);
  }

  private void fireChanged() {
    fireChanged(new ChangeEvent(this));
  }

  @Override
  public void fireChanged(ChangeEvent e) {
    mListeners.fireChanged(e);
  }

  public Path getDir() {
    return mDir;
  }

  private void reset() {
    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    mSelectedIndex = -1;
    repaint();
  }
}