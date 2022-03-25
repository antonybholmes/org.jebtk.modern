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
package org.jebtk.modern.window;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.SwingUtilities;

import org.jebtk.core.collections.DefaultHashMap;
import org.jebtk.core.event.ChangeEvent;
import org.jebtk.modern.DeviceConfig;
import org.jebtk.modern.UI;
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
public class WindowService extends ModernWindowEventListeners implements Iterable<ModernWindow> {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The Class WindowServiceLoader.
   */
  private static class WindowServiceLoader {

    /** The Constant INSTANCE. */
    private static final WindowService INSTANCE = new WindowService();
  }

  /**
   * Gets the single instance of SettingsService.
   *
   * @return single instance of SettingsService
   */
  public static WindowService getInstance() {
    return WindowServiceLoader.INSTANCE;
  }

  /**
   * The member windows.
   */
  // private Map<String, ModernWindow> windows = new HashMap<String,
  // ModernWindow>();
  private List<ModernWindow> mWindows = new ArrayList<ModernWindow>();

  /** The m max map. */
  private Map<ModernWindow, Boolean> mMaxMap = DefaultHashMap.create(false);

  /** The m max bound map. */
  private Map<ModernWindow, Rectangle> mMaxBoundMap = new HashMap<ModernWindow, Rectangle>();

  /**
   * The constant LOG.
   */
  private static final Logger LOG = LoggerFactory.getLogger(WindowService.class);

  /**
   * The class BringWindowToFront.
   */
  private static class BringWindowToFront implements Runnable {

    /**
     * The window.
     */
    private ModernWindow window;

    /**
     * Instantiates a new bring window to front.
     *
     * @param window the window
     */
    public BringWindowToFront(ModernWindow window) {
      this.window = window;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
      window.toFront();
      window.requestFocusInWindow();
    }

  }

  /**
   * Register.
   *
   * @param window the window
   */
  public synchronized void register(ModernWindow window) {

    if (mWindows.contains(window)) {
      return;
    }

    LOG.info("Register {}", window.getTitle());

    mWindows.add(window);

    fireWindowAdded(new ChangeEvent(this));
  }

  /**
   * Removes the.
   *
   * @param window the window
   */
  public synchronized void remove(ModernWindow window) {

    if (!mWindows.contains(window)) {
      return;
    }

    LOG.info("Unregister {}", window.getTitle());

    mWindows.remove(window);

    /*
     * windows.clear();
     * 
     * for (int i = 0; i < windowList.size(); ++i) { windows.put(windowList.get(i),
     * i); }
     */

    fireWindowRemoved(new ChangeEvent(this));
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Iterable#iterator()
   */
  public Iterator<ModernWindow> iterator() {
    return mWindows.iterator();
  }

  /**
   * Finds an active window by name, otherwise returns null.
   *
   * @param name the name
   * @return the modern window
   */
  public final ModernWindow findByName(final String name) {
    if (name == null) {
      return null;
    }

    for (ModernWindow frame : mWindows) {
      if (frame.getTitle().contains(name)) {
        return frame;
      }
    }

    return null;
  }

  /**
   * Sets the focus.
   *
   * @param window the new focus
   */
  public static final void setFocus(ModernWindow window) {
    SwingUtilities.invokeLater(new BringWindowToFront(window));
  }

  /**
   * Size.
   *
   * @return the int
   */
  public int size() {
    return mWindows.size();
  }

  /**
   * Tile.
   */
  public void tile() {
    GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    double g = Math.ceil(Math.sqrt(mWindows.size()));

    g *= g;

    double s = Math.sqrt(g);

    int rows = (int) s;

    int cols = rows;

    if (s > Math.floor(s)) {
      ++cols;
    }

    LOG.info("Tile windows ({} x {})", rows, cols);

    int width = gd.getDisplayMode().getWidth() / cols;
    int height = gd.getDisplayMode().getHeight() / rows;

    int x = 0;
    int y = 0;

    for (int i = 0; i < rows; i++) {
      x = 0;

      for (int j = 0; j < cols; j++) {

        int c = i * rows + j;

        if (c >= mWindows.size()) {
          continue;
        }

        mWindows.get(c).setBounds(x, y, width, height);

        x += width;
      }

      y += height;
    }
  }

  /**
   * Arrange horizontally.
   */
  public void arrangeHorizontally() {
    GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    int width = gd.getDisplayMode().getWidth();
    int height = gd.getDisplayMode().getHeight() / mWindows.size();

    int x = 0;
    int y = 0;

    for (ModernWindow window : mWindows) {
      window.setBounds(x, y, width, height);

      y += height;
    }
  }

  /**
   * Arrange vertically.
   */
  public void arrangeVertically() {
    GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    int width = gd.getDisplayMode().getWidth() / mWindows.size();
    int height = gd.getDisplayMode().getHeight();

    int x = 0;
    int y = 0;

    for (ModernWindow window : mWindows) {
      window.setBounds(x, y, width, height);

      x += width;
    }
  }

  /**
   * Auto maximize.
   *
   * @param window the window
   */
  public void autoMaximize(ModernWindow window) {
    DeviceConfig config = UI.findDeviceConfig(window);

    if (config != null) {

      // Force window to respond sinze resize appears not to trigger
      // window or resize events automatically
      // config.device.setFullScreenWindow(mIsMax ? null : mWindow);
      // mWindow.revalidate();
      // mWindow.dispatchEvent(new WindowEvent(mWindow,
      // WindowEvent.WINDOW_STATE_CHANGED));

      if (mMaxMap.get(window)) {
        Rectangle bounds = mMaxBoundMap.get(window);
        window.setSize(bounds.width, bounds.height);
        window.setLocation(bounds.x, bounds.y);
      } else {
        mMaxBoundMap.put(window, new Rectangle(window.getBounds()));

        Rectangle bounds = config.device.getDefaultConfiguration().getBounds();

        // Try to account for task bars etc
        Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(config.device.getDefaultConfiguration());

        bounds.width -= insets.left + insets.right;
        bounds.height -= insets.top + insets.bottom;

        if (config.deviceIndex == 0) {
          window.setSize(bounds.width, bounds.height);
        } else {
          window.setSize(bounds.width - 1, bounds.height - 1);
        }

        window.setLocation(bounds.x, bounds.y);
      }

      window.revalidate();
      window.repaint();

      // DisplayMode dm = config.device.getDisplayMode();

      // mWindow.setLocation(0, 0);
      // mWindow.setSize(dm.getWidth(), dm.getHeight());
      // mWindow.validate();

      mMaxMap.put(window, !mMaxMap.get(window));
    }

  }
}
