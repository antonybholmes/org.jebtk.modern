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
package org.jebtk.modern;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.filechooser.FileSystemView;

import org.jebtk.core.ColorUtils;
import org.jebtk.core.geom.IntDim;
import org.jebtk.core.io.PathUtils;
import org.jebtk.core.settings.SettingsService;
import org.jebtk.core.sys.OpSys;
import org.jebtk.core.text.TextUtils;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.window.ModernWindowTitleBarStyle;

// TODO: Auto-generated Javadoc
/**
 * Contains constant strings for commonly used command names within the program.
 *
 * @author Antony Holmes
 *
 */
public class UI {

  /**
   * The constant MENU_FILE.
   */
  public static final String MENU_FILE = "Path";

  /**
   * The constant MENU_OPEN.
   */
  public static final String MENU_OPEN = "Open";

  /**
   * The constant MENU_EXIT.
   */
  public static final String MENU_EXIT = "Exit";

  /**
   * The constant MENU_CLOSE.
   */
  public static final String MENU_CLOSE = "Close";

  /**
   * The constant MENU_SAVE.
   */
  public static final String MENU_SAVE = "Save";

  /**
   * The constant MENU_SAVE_AS.
   */
  public static final String MENU_SAVE_AS = "Save As";

  /**
   * The constant MENU_EXPORT.
   */
  public static final String MENU_EXPORT = "Export";

  /**
   * The constant MENU_SHOW_ALL.
   */
  public static final String MENU_SHOW_ALL = "Show All";

  /**
   * The constant MENU_TOOLS.
   */
  public static final String MENU_TOOLS = "Tools";

  /**
   * The constant MENU_SEARCH.
   */
  public static final String MENU_SEARCH = "Search";

  /**
   * The constant MENU_DOWNLOAD.
   */
  public static final String MENU_DOWNLOAD = "Download";

  /**
   * The constant MENU_CUT.
   */
  public static final String MENU_CUT = "Cut";

  /**
   * The constant MENU_COPY.
   */
  public static final String MENU_COPY = "Copy";

  /**
   * The constant MENU_PASTE.
   */
  public static final String MENU_PASTE = "Paste";

  /**
   * The constant MENU_SELECT_ALL.
   */
  public static final String MENU_SELECT_ALL = "Select All";

  /**
   * The constant MENU_DESELECT_ALL.
   */
  public static final String MENU_DESELECT_ALL = "Deselect All";

  /**
   * The constant MENU_SETTINGS.
   */
  public static final String MENU_SETTINGS = "Settings";

  /**
   * The constant MENU_RECENT.
   */
  public static final String MENU_RECENT = "Recent";

  /**
   * The constant MENU_CLEAR.
   */
  public static final String MENU_CLEAR = "Clear";

  /**
   * The constant MENU_EMAIL.
   */
  public static final String MENU_EMAIL = "Email";

  /**
   * The constant MENU_IMPORT.
   */
  public static final String MENU_IMPORT = "Import";

  /**
   * The constant MENU_CHECK_SELECTED.
   */
  public static final String MENU_CHECK_SELECTED = "Check Selected";

  /**
   * The constant MENU_UNCHECK_SELECTED.
   */
  public static final String MENU_UNCHECK_SELECTED = "Uncheck Selected";

  /**
   * The constant MENU_HELP.
   */
  public static final String MENU_HELP = "Help";

  /**
   * The constant MENU_INFO.
   */
  public static final String MENU_INFO = "Info";

  /**
   * The constant MENU_BROWSE.
   */
  public static final String MENU_BROWSE = "Browse";

  /**
   * The constant MENU_OPTIONS.
   */
  public static final String MENU_OPTIONS = "Options...";

  /**
   * The constant MENU_ABOUT.
   */
  public static final String MENU_ABOUT = "About";

  /**
   * The constant MENU_BACK.
   */
  public static final String MENU_BACK = "Back";

  /**
   * The constant MENU_VIEW_LOG.
   */
  public static final String MENU_VIEW_LOG = "View Log";

  /**
   * The constant MENU_NEW.
   */
  public static final String MENU_NEW = "New";

  /**
   * The constant MENU_SAVE_ALL.
   */
  public static final String MENU_SAVE_ALL = "Save All";

  /**
   * The constant MENU_LOAD.
   */
  public static final String MENU_LOAD = "Load...";

  /**
   * The constant MENU_ZOOM.
   */
  public static final String MENU_ZOOM = "Zoom";

  /**
   * The constant MENU_ZOOM_100.
   */
  public static final String MENU_ZOOM_100 = "100 %";

  /**
   * The constant MENU_DELETE.
   */
  public static final String MENU_DELETE = "Delete";

  /**
   * The constant MENU_REMOVE.
   */
  public static final String MENU_REMOVE = "Remove";

  /**
   * The constant MENU_ADD.
   */
  public static final String MENU_ADD = "Add";

  /**
   * The constant MENU_EDIT.
   */
  public static final String MENU_EDIT = "Edit";

  /**
   * The constant MENU_MINIMIZE.
   */
  public static final String MENU_MINIMIZE = "Minimize";

  /**
   * The constant MENU_MAXIMIZE.
   */
  public static final String MENU_MAXIMIZE = "Maximize";

  /** The Constant MENU_NEW_WINDOW. */
  public static final String MENU_NEW_WINDOW = "New Window";

  public static final String MENU_LICENSE = "License";

  /**
   * The constant BUTTON_OK.
   */
  public static final String BUTTON_OK = "OK";

  /**
   * The constant BUTTON_CANCEL.
   */
  public static final String BUTTON_CANCEL = "Cancel";

  /**
   * The constant BUTTON_FILTER.
   */
  public static final String BUTTON_FILTER = "Filter";

  /**
   * The constant BUTTON_OPEN.
   */
  public static final String BUTTON_OPEN = "Open";

  /**
   * The constant BUTTON_LOGIN.
   */
  public static final String BUTTON_LOGIN = "Login";

  /**
   * The constant BUTTON_SIGN_IN.
   */
  public static final String BUTTON_SIGN_IN = "Sign In";

  /**
   * The constant BUTTON_IMPORT.
   */
  public static final String BUTTON_IMPORT = "Import...";

  public static final String BUTTON_LOAD = "Load...";

  /**
   * The constant BUTTON_EXPORT.
   */
  public static final String BUTTON_EXPORT = "Export...";

  /**
   * The constant BUTTON_BROWSE.
   */
  public static final String BUTTON_BROWSE = "Browse";

  /**
   * The constant MENU_SWITCH_WINDOW.
   */
  public static final String MENU_SWITCH_WINDOW = "Switch";

  /**
   * The constant STATUS_READY.
   */
  public static final String STATUS_READY = "Ready";

  /**
   * The constant CUT_ICON.
   */
  public static final ModernIcon CUT_ICON = AssetService.getInstance().loadIcon("cut", 16);

  /**
   * The constant COPY_ICON.
   */
  public static final ModernIcon COPY_ICON = AssetService.getInstance().loadIcon("copy", 16);

  /**
   * The constant PASTE_ICON.
   */
  public static final ModernIcon PASTE_ICON = AssetService.getInstance().loadIcon("paste", 16);

  /**
   * The constant NUMBER_FORMAT.
   */
  public static final NumberFormat NUMBER_FORMAT = new DecimalFormat("0.00");

  /** The custom window decoration. */
  public static boolean CUSTOM_WINDOW_DECORATION = SettingsService.getInstance()
      .getString("modern.ui.window.decoration.style").equals("modern");

  /** The Constant TITLE_BUTTONS_ON_LEFT. */
  public static final boolean TITLE_BUTTONS_ON_LEFT = OpSys.isMac()
      || SettingsService.getInstance().getValue("modern.ui.window.title-bar.buttons.location").equals("left");

  /** The Constant WINDOW_TITLE_STYLE. */
  public static final ModernWindowTitleBarStyle WINDOW_TITLE_STYLE;

  /**
   * Default mouse cursor.
   */
  public static final Cursor DEFAULT_CURSOR = new Cursor(Cursor.DEFAULT_CURSOR);

  /**
   * Default hand cursor for clicking on links etc.
   */
  public static final Cursor HAND_CURSOR = new Cursor(Cursor.HAND_CURSOR);

  public static final String ASSET_ABOUT = "ABOUT";
  public static final String ASSET_LICENSE = "LICENSE";

  public static final String ASSET_THIS_PC = "This PC"; // "THIS PC";

  public static final String ASSET_BROWSE = "Browse..."; // "BROWSE...";

  public static final String ASSET_RECENT_FILES = "Recent Files...";

  public static final String ASSET_TODAY = "Today";

  public static final String ASSET_OLDER = "Older";

  public static final String ASSET_RIBBON_FILE = "File"; // FILE";

  public static final String ASSET_RIBBON_HOME = "Home"; // HOME;

  public static final String ASSET_UPPERCASE = "UPPERCASE";

  public static final String ASSET_LOWERCASE = "lowercase";

  public static final String ASSET_NAME = "Name";

  public static final String ASSET_OUTPUT = "Output";

  public static final String ASSET_LOCATION = "Location";

  public static final String ASSET_OPTIONS = "Options";

  public static final String ASSET_COLOR = "Color";

  public static final String ASSET_GROUP = "Group";

  public static final String ASSET_MENU = "Menu";

  public static final String ACTION_ASSET_CLICKED = "clicked";

  static {
    // Determine if we should mac style buttons or not.
    if (OpSys.isMac()
        || SettingsService.getInstance().getString("modern.ui.window.title-bar.buttons.style").equals("mac")) {
      WINDOW_TITLE_STYLE = ModernWindowTitleBarStyle.MAC;
    } else {
      WINDOW_TITLE_STYLE = ModernWindowTitleBarStyle.WINDOWS;
    }
  }

  /**
   * Instantiates a new ui.
   */
  private UI() {
    // do nothing
  }

  /**
   * Centers a component on the current screen.
   *
   * @param w the w
   * @return true, if successful
   */
  /*
   * public static final void centerWindowToScreen(Component component) {
   * 
   * Dimension d = null; // size of what we're positioning against Point p = null;
   * 
   * 
   * Rectangle screen = getScreenBounds(component);
   * 
   * p = new Point();
   * 
   * double centreX = p.getX() + d.getWidth() / 2; double centreY = p.getY() +
   * d.getHeight() / 2;
   * 
   * component.getSize(d); p.setLocation(centreX - d.getWidth() / 2, centreY -
   * d.getHeight() / 2);
   * 
   * if (p.getX() < 0) { p.setLocation(0, p.getY()); }
   * 
   * if (p.getY() < 0) { p.setLocation(p.getX(), 0); }
   * 
   * component.setLocation(p); }
   */

  public static boolean windowFitsOnScreen(Window w) {
    return w.getGraphicsConfiguration().getBounds().contains(w.getBounds());
  }

  /**
   * Gets the screen bounds.
   *
   * @param c the c
   * @return the screen bounds
   */
  public static Rectangle getScreenBounds(Component c) {
    return c.getGraphicsConfiguration().getBounds();
  }

  /**
   * Gets the graphics configuration.
   *
   * @param c the c
   * @return the graphics configuration
   */
  public static GraphicsConfiguration getGraphicsConfiguration(Component c) {
    return c.getGraphicsConfiguration();
  }

  /**
   * Returns the current device config the window is in. This should be used for
   * multi-monitor environments.
   *
   * @param window the window
   * @return the device config
   */
  public static DeviceConfig findDeviceConfig(Window window) {
    // Prepare default return value:
    DeviceConfig deviceConfig = null;

    // More correct would be to return null when no device or configuration
    // has been found:
    //
    // DeviceConfig deviceConfig = null;
    //
    // See also the comments marked by *** (below).

    Rectangle windowBounds = window.getBounds();
    int lastArea = 0;

    GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();

    GraphicsDevice[] graphicsDevices = graphicsEnvironment.getScreenDevices();

    // Search through all devices…
    for (int i = 0; i < graphicsDevices.length; ++i) {
      GraphicsDevice graphicsDevice = graphicsDevices[i];

      GraphicsConfiguration[] graphicsConfigurations = graphicsDevice.getConfigurations();

      // It is possible that your device will have only one configuration,
      // but you cannot rely on this(!)…
      for (int j = 0; j < graphicsConfigurations.length; ++j) {
        GraphicsConfiguration graphicsConfiguration = graphicsConfigurations[j];

        Rectangle graphicsBounds = graphicsConfiguration.getBounds();

        Rectangle intersection = windowBounds.intersection(graphicsBounds);

        int area = intersection.width * intersection.height;

        if (area != 0) {
          // ***
          // The block of code in this comments is relevant in case you
          // want to return the null value when no device or
          // configuration has been found.
          /*
           * if (null == deviceConfig) { // In this case the better solution would be to
           * declare // the full constructor in the DeviceClass (see below) and // use it
           * here like this:
           * 
           * deviceConfig = new DeviceConfig(i, j, graphicsDevice, graphicsConfiguration);
           * 
           * // (but the current solution is more simple when no // constructor is
           * defined)…
           * 
           * } else
           */

          if (area > lastArea) {
            lastArea = area;

            deviceConfig = new DeviceConfig(i, j, graphicsDevice, graphicsConfiguration);
          }
        }
      }
    }

    return deviceConfig;
  }

  /**
   * Gets the screen insets.
   *
   * @param c the c
   * @return the screen insets
   */
  public static Insets getScreenInsets(Component c) {
    return getScreenInsets(getGraphicsConfiguration(c));
  }

  /**
   * Gets the screen insets.
   *
   * @param gc the gc
   * @return the screen insets
   */
  public static Insets getScreenInsets(GraphicsConfiguration gc) {
    return Toolkit.getDefaultToolkit().getScreenInsets(gc);
  }

  /**
   * Center window to screen.
   *
   * @param w the w
   */
  public static void centerWindowToScreen(Window w) {
    Rectangle screen = getScreenBounds(w);

    w.setLocation(screen.x + (screen.width - w.getWidth()) / 2, screen.y + (screen.height - w.getHeight()) / 2);
  }

  /**
   * UI select a directory.
   *
   * @param parent the parent
   * @param dir    the dir
   * @return the file
   */
  public static final Path selectDirectory(Frame parent, Path dir) {

    JFileChooser fc = new JFileChooser();

    fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    fc.setAcceptAllFileFilterUsed(false);
    // fc.setFileFilter(new DirectoryGuiFileFilter());

    fc.setCurrentDirectory(dir.toFile());

    int returnVal = fc.showDialog(parent, "Directory");

    if (returnVal == JFileChooser.APPROVE_OPTION) {
      return fc.getSelectedFile().toPath();
    } else {
      return null;
    }
  }

  /**
   * Ui select a file with restricted view. This is shared amongst UI elements.
   *
   * @param parent the parent
   * @param view   the view
   * @return the file
   */
  public static final Path selectFile(Component parent, FileSystemView view) {

    JFileChooser fc = new JFileChooser(view);

    fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

    int returnVal = fc.showOpenDialog(parent);

    if (returnVal == JFileChooser.APPROVE_OPTION) {
      return fc.getSelectedFile().toPath();
    } else {
      return null;
    }
  }

  /**
   * Ui select a file. This is shared amongst UI elements.
   *
   * @param parent           the parent
   * @param workingDirectory the working directory
   * @return the file
   */
  public static final Path selectFile(Component parent, Path workingDirectory) {

    JFileChooser fc = new JFileChooser();

    fc.setCurrentDirectory(workingDirectory.toFile());

    int returnVal = fc.showOpenDialog(parent);

    if (returnVal == JFileChooser.CANCEL_OPTION) {
      return null;
    }

    return fc.getSelectedFile().toPath();
  }

  /**
   * Save file.
   *
   * @param parent           the parent
   * @param workingDirectory the working directory
   * @return the file
   */
  public static final Path saveFile(Component parent, Path workingDirectory) {

    JFileChooser fc = new JFileChooser();

    fc.setCurrentDirectory(workingDirectory.toFile());

    int returnVal = fc.showSaveDialog(parent);

    if (returnVal == JFileChooser.APPROVE_OPTION) {
      return fc.getSelectedFile().toPath();
    }

    return null;
  }

  /**
   * Select files.
   *
   * @param parent           the parent
   * @param workingDirectory the working directory
   * @return the file[]
   */
  public static final List<Path> selectFiles(Component parent, Path workingDirectory) {
    JFileChooser fc = new JFileChooser();

    fc.setCurrentDirectory(workingDirectory.toFile());
    fc.setMultiSelectionEnabled(true);

    int returnVal = fc.showOpenDialog(parent);

    if (returnVal == JFileChooser.CANCEL_OPTION) {
      return null;
    }

    return PathUtils.toList(fc.getSelectedFiles());
  }

  /**
   * Sets the size.
   *
   * @param c     the c
   * @param width the width
   * @return the j component
   */
  public static JComponent setSize(JComponent c, int width) {
    return setSize(c, width, c.getPreferredSize().height);
  }

  /**
   * Sets the size.
   *
   * @param c      the c
   * @param width  the width
   * @param height the height
   * @return the j component
   */
  public static JComponent setSize(JComponent c, int width, int height) {
    return setSize(c, new Dimension(width, height));
  }

  /**
   * Try to rigidly size a control.
   *
   * @param c       the c
   * @param max     the max
   * @param padding the padding
   * @return the j component
   */
  public static final JComponent setSize(JComponent c, Dimension max, int padding) {
    return setSize(c, max, BorderFactory.createEmptyBorder(padding, padding, padding, padding));
  }

  /**
   * Sets the size of a component and including the border for BoxLayouts etc.
   *
   * @param c   the component to resize.
   * @param max the max size of the component.
   * @param t   top border.
   * @param l   left border.
   * @param b   bottom border.
   * @param r   right border.
   * @return the j component
   */
  public static final JComponent setSize(JComponent c, Dimension max, int t, int l, int b, int r) {

    return setSize(c, max, BorderFactory.createEmptyBorder(t, l, b, r));
  }

  /**
   * Sets the size.
   *
   * @param c      the c
   * @param max    the max
   * @param border the border
   * @return the j component
   */
  public static final JComponent setSize(JComponent c, Dimension max, Border border) {
    c.setBorder(border);

    return setSize(c, max);
  }

  /**
   * Sets the size.
   *
   * @param c   the c
   * @param max the max
   * @return the j component
   */
  public static final JComponent setSize(JComponent c, IntDim max) {
    return setSize(c, IntDim.toDimension(max));
  }

  /**
   * Sets the size.
   *
   * @param c   the c
   * @param max the max
   * @return the j component
   */
  public static final JComponent setSize(JComponent c, Dimension max) {
    c.setPreferredSize(getMaxSize(c, max));
    c.setMaximumSize(c.getPreferredSize());
    c.setMinimumSize(c.getPreferredSize());
    c.setSize(c.getPreferredSize());

    return c;
  }

  /**
   * Sets the minimum size.
   *
   * @param c      the c
   * @param size   the size
   * @param border the border
   */
  public static void setMinimumSize(JComponent c, Dimension size, Border border) {
    c.setBorder(border);

    c.setMinimumSize(getMaxSize(c, size));
  }

  /**
   * Gets the max size.
   *
   * @param c   the c
   * @param max the max
   * @return the max size
   */
  public static final Dimension getMaxSize(JComponent c, Dimension max) {

    return new Dimension(max.width + c.getInsets().left + c.getInsets().right,
        max.height + c.getInsets().top + c.getInsets().bottom);
  }

  /**
   * Creates the left border.
   *
   * @param padding the padding
   * @return the border
   */
  public static final Border createLeftBorder(int padding) {
    return BorderFactory.createEmptyBorder(0, padding, 0, 0);
  }

  /**
   * Creates the left right border.
   *
   * @param padding the padding
   * @return the border
   */
  public static final Border createLeftRightBorder(int padding) {
    return BorderFactory.createEmptyBorder(0, padding, 0, padding);
  }

  /**
   * Creates the right border.
   *
   * @param padding the padding
   * @return the border
   */
  public static final Border createRightBorder(int padding) {
    return BorderFactory.createEmptyBorder(0, 0, 0, padding);
  }

  /**
   * Creates the top border.
   *
   * @param padding the padding
   * @return the border
   */
  public static final Border createTopBorder(int padding) {
    return BorderFactory.createEmptyBorder(padding, 0, 0, 0);
  }

  /**
   * Creates the bottom border.
   *
   * @param padding the padding
   * @return the border
   */
  public static final Border createBottomBorder(int padding) {
    return BorderFactory.createEmptyBorder(0, 0, padding, 0);
  }

  /**
   * Creates a uniform border for a component.
   *
   * @param padding the padding
   * @return the border
   */
  public static final Border createBorder(int padding) {
    return BorderFactory.createEmptyBorder(padding, padding, padding, padding);
  }

  /**
   * Creates the border.
   *
   * @param top    the top
   * @param left   the left
   * @param bottom the bottom
   * @param right  the right
   * @return the border
   */
  public static Border createBorder(int top, int left, int bottom, int right) {
    return BorderFactory.createEmptyBorder(top, left, bottom, right);
  }

  /**
   * Creates the border.
   *
   * @param page the page
   * @param line the line
   * @return the border
   */
  public static Border createBorder(int page, int line) {
    return createBorder(page, line, page, line);
  }

  /**
   * Creates the top bottom border.
   *
   * @param padding the padding
   * @return the border
   */
  public static final Border createTopBottomBorder(int padding) {
    return BorderFactory.createEmptyBorder(padding, 0, padding, 0);
  }

  /**
   * Creates the top left border.
   *
   * @param padding the padding
   * @return the border
   */
  public static Border createTopLeftBorder(int padding) {
    return BorderFactory.createEmptyBorder(padding, padding, 0, 0);
  }

  /**
   * Creates the bottom left border.
   *
   * @param padding the padding
   * @return the border
   */
  public static Border createBottomLeftBorder(int padding) {
    return BorderFactory.createEmptyBorder(0, padding, padding, 0);
  }

  /**
   * Creates the h gap.
   *
   * @param i the i
   * @return the component
   */
  public static final Component createHGap(int i) {
    return Box.createRigidArea(new Dimension(i, 0));
  }

  /**
   * Creates the v gap.
   *
   * @param i the i
   * @return the component
   */
  public static final Component createVGap(int i) {
    return Box.createRigidArea(new Dimension(0, i));
  }

  /**
   * Change the layout of a panel to vertical box layout.
   *
   * @param panel the panel
   * @return the j panel
   */
  public static JPanel toVertBoxLayout(JPanel panel) {
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

    return panel;
  }

  /**
   * Takes a mouse event and converts to the space relative to a component.
   *
   * @param e the e
   * @param c the c
   * @return the point
   */
  public static Point convertPointToComponent(final MouseEvent e, final Component c) {
    return convertPointToComponent(e.getLocationOnScreen(), c);
  }

  /**
   * Takes a point on screen and converts to the space relative to a component.
   *
   * @param p the p
   * @param c the c
   * @return the point
   */
  public static Point convertPointToComponent(final Point p, final Component c) {
    Point ret = new Point(p);
    SwingUtilities.convertPointFromScreen(ret, c);

    return ret;
  }

  /**
   * Returns true if the mouse event is within the bounds of the component.
   *
   * @param e the e
   * @param c the c
   * @return true, if successful
   */
  public static boolean contains(final MouseEvent e, final Component c) {
    return contains(e.getLocationOnScreen(), c);
  }

  /**
   * Returns true if the point is within the bounds of the component.
   *
   * @param p the p
   * @param c the c
   * @return true, if successful
   */
  public static boolean contains(final Point p, final Component c) {
    Point cp = convertPointToComponent(p, c);

    return c.contains(cp);
  }

  /**
   * Truncates a string to fit within a given space, given a graphics context.
   *
   * @param g2   the g 2
   * @param text the text
   * @param w    the w
   * @return the string
   */
  public static String truncateText(Graphics2D g2, String text, int w) {
    if (text == null) {
      return null;
    }

    String ret = null;

    for (int i = text.length(); i >= 0; --i) {
      ret = TextUtils.truncate(text, i);

      if (g2.getFontMetrics().stringWidth(ret) <= w) {
        break;
      }
    }

    return ret;
  }

  /**
   * Adds a given mouse listener to a component and all of its children.
   *
   * @param component the component
   * @param l         the l
   */
  public static void addMouseListenerAllChildren(JComponent component, MouseListener l) {

    Deque<Component> stack = new ArrayDeque<Component>();

    stack.push(component);

    while (!stack.isEmpty()) {
      JComponent c = (JComponent) stack.pop();

      c.addMouseListener(l);

      for (int i = 0; i < c.getComponentCount(); ++i) {
        stack.push((JComponent) c.getComponent(i));
      }
    }
  }

  /**
   * Sets the min max size.
   *
   * @param c       the c
   * @param minSize the min size
   * @param maxSize the max size
   */
  public static void setMinMaxSize(Component c, Dimension minSize, Dimension maxSize) {
    c.setMinimumSize(minSize);
    c.setMaximumSize(maxSize);
  }

  /**
   * Fill a region with a gradient color.
   *
   * @param g2 the g 2
   * @param w  the w
   * @param h  the h
   * @param c1 the c 1
   * @param c2 the c 2
   */
  public static void drawGradient(Graphics2D g2, int w, int h, Color c1, Color c2) {
    drawGradient(g2, 0, 0, w, h, c1, c2);
  }

  /**
   * Fill a region with a gradient color.
   *
   * @param g2 the g 2
   * @param x  the x
   * @param y  the y
   * @param w  the w
   * @param h  the h
   * @param c1 the c 1
   * @param c2 the c 2
   */
  public static void drawGradient(Graphics2D g2, int x, int y, int w, int h, Color c1, Color c2) {
    GradientPaint gradient = ColorUtils.getVGradient(0, h, c1, c2);

    g2.setPaint(gradient);
    // g2.setColor(ModernMenuItem.HIGHLIGHT_COLOR);

    g2.fillRect(x, y, w, h);
  }

  public static void fillGradient(Graphics2D g2, Color c1, Color c2, int x, int y, int w, int h) {
    GradientPaint gradient = ColorUtils.getVGradient(0, h, c1, c2);

    g2.setPaint(gradient);
    // g2.setColor(ModernMenuItem.HIGHLIGHT_COLOR);

    g2.fillRect(x, y, w, h);
  }

  /**
   * Adjust the width of a component, but keep its height.
   *
   * @param c     the c
   * @param width the width
   */
  public static void setWidth(JComponent c, int width) {
    setSize(c, width, c.getHeight());
  }

}
