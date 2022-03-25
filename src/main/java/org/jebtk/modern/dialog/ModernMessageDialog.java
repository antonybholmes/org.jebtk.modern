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
package org.jebtk.modern.dialog;

import java.nio.file.Path;
import java.util.List;

import javax.swing.Box;

import org.jebtk.core.collections.CollectionUtils;
import org.jebtk.core.io.PathUtils;
import org.jebtk.core.text.TextUtils;
import org.jebtk.modern.AssetService;
import org.jebtk.modern.BorderService;
import org.jebtk.modern.ModernComponent;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;
import org.jebtk.modern.button.ModernButton;
import org.jebtk.modern.button.ModernClickWidget;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.graphics.icons.HelpVectorIcon;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.graphics.icons.WarningVectorIcon;
import org.jebtk.modern.text.ModernAutoSizeLabel;
import org.jebtk.modern.window.ModernWindow;
import org.jebtk.modern.window.WindowWidgetFocusEvents;

/**
 * Show a message dialog to the user.
 * 
 * @author Antony Holmes
 *
 */
public class ModernMessageDialog extends ModernDialogTaskWindow {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The no button.
   */
  private final ModernButton mNoButton = new ModernDialogButton("No");

  /**
   * The constant DIALOG_SIZE_LARGE.
   */
  // public static final Dimension DIALOG_SIZE_LARGE = new Dimension(480, 150);

  /**
   * The constant DIALOG_SIZE_MEDIUM.
   */
  // public static final Dimension DIALOG_SIZE_MEDIUM = new Dimension(360, 150);

  /**
   * The constant DIALOG_SIZE_SMALL.
   */
  // public static final Dimension DIALOG_SIZE_SMALL = new Dimension(240, 150);

  private static final int ICON_SIZE = 40;

  private static final int FIXED_HEIGHT = 70;

  /** The Constant MIN_HEIGHT. */
  private static final int MIN_HEIGHT = FIXED_HEIGHT;

  /** The Constant MAX_HEIGHT. */
  private static final int MAX_HEIGHT = 400;

  /** The Constant MIN_WIDTH. */
  private static final int MIN_WIDTH = 400;

  /** The Constant FIXED_WIDTH. */
  private static final int FIXED_WIDTH = 100;

  /** The Constant MAX_WIDTH. */
  private static final int MAX_WIDTH = 800;

  /** The m listeners. */
  private final DialogEventListeners mListeners = new DialogEventListeners();

  /**
   * Instantiates a new modern message dialog.
   *
   * @param parent the parent
   * @param title  the title
   * @param icon   the icon
   * @param type   the type
   * @param lines  the lines
   * @param l      the l
   */
  public ModernMessageDialog(ModernWindow parent, String title, ModernIcon icon, MessageDialogType type,
      List<String> lines, DialogEventListener l) {
    super(parent);

    addDialogListener(l);

    setTitle(title);

    ModernComponent content = new ModernComponent(); // (ModernTheme.getInstance().getClass("widget").getInt("padding"));

    // content.setBorder(ModernPanel.BORDER);

    // JPanel content = new DialogPanel(new BorderLayout());
    // JPanel content = new GradientPanel(Color.WHITE, DialogButton.COLOR_1, new
    // BorderLayout());

    ModernDialogImagePanel iconLabel = new ModernDialogImagePanel(icon, ICON_SIZE);
    content.setLeft(iconLabel);

    ModernComponent c = new ModernComponent(new MessageDialogCenterLayout());

    // box.add(Box.createVerticalGlue());

    for (String line : lines) {
      c.add(new ModernAutoSizeLabel(line));
    }

    // box.add(Box.createVerticalGlue());

    // box.setBorder(BorderService.getInstance().createLeftBorder(ModernWidget.TRIPLE_PADDING));
    content.setBody(c);
    content.setBorder(BorderService.getInstance().createLeftRightBorder(ModernWidget.DOUBLE_PADDING));
    // content.setBorder(ModernWidget.DOUBLE_BORDER);

    setContent(content);
    // setFlatCardContent(content);

    setSize(Math.min(Math.max(MIN_WIDTH, c.getPreferredSize().width) + FIXED_WIDTH, MAX_WIDTH),
        Math.min(Math.max(MIN_HEIGHT, c.getPreferredSize().height) + FIXED_HEIGHT, MAX_HEIGHT));

    Box box = new ModernCenterButtonsBox();

    ModernClickWidget focusButton;

    mOkButton.addClickListener(this);
    focusButton = mOkButton;
    box.add(mOkButton);

    if (type == MessageDialogType.INFORMATION_YES_NO || type == MessageDialogType.WARNING_YES_NO_CANCEL
        || type == MessageDialogType.WARNING_YES_NO) {
      mNoButton.addClickListener(this);

      focusButton = mNoButton;

      box.add(UI.createHGap(10));
      box.add(mNoButton);
    }

    if (type == MessageDialogType.INFORMATION_OK_CANCEL || type == MessageDialogType.WARNING_OK_CANCEL
        || type == MessageDialogType.WARNING_YES_NO_CANCEL) {
      mCancelButton.addClickListener(this);
      focusButton = mCancelButton;
      box.add(UI.createHGap(10));
      box.add(mCancelButton);
    }

    if (type == MessageDialogType.INFORMATION_YES_NO || type == MessageDialogType.WARNING_YES_NO_CANCEL
        || type == MessageDialogType.WARNING_YES_NO) {
      mOkButton.setText("Yes");
    }

    // panel.add(Box.createHorizontalGlue());

    // setButtons(box);
    setFooter(box);

    addWindowListener(new WindowWidgetFocusEvents(focusButton));

    UI.centerWindowToScreen(this);
  }

  /**
   * Adds the dialog listener.
   *
   * @param l the l
   */
  public void addDialogListener(DialogEventListener l) {
    if (l != null) {
      mListeners.addDialogListener(l);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  @Override
  public final void clicked(ModernClickEvent e) {
    if (e.getSource().equals(mOkButton)) {
      mStatus = ModernDialogStatus.OK;
    } else {
      mStatus = ModernDialogStatus.CANCEL;
    }

    close();

    mListeners.fireDialogStatusChanged(
        new DialogEvent(this, (e.getSource().equals(mOkButton) ? ModernDialogStatus.OK : ModernDialogStatus.CANCEL)));
  }

  /**
   * Creates the dialog.
   *
   * @param parent the parent
   * @param line   the line
   * @param type   the type
   * @return the modern dialog status
   */
  public static final ModernDialogStatus createDialog(ModernWindow parent, String line, MessageDialogType type) {
    return createDialog(parent, line, type, null);
  }

  /**
   * Creates the dialog.
   *
   * @param parent the parent
   * @param line   the line
   * @param type   the type
   * @param l      the l
   * @return the modern dialog status
   */
  public static final ModernDialogStatus createDialog(ModernWindow parent, String line, MessageDialogType type,
      DialogEventListener l) {
    return createDialog(parent, parent.getAppInfo().getName(), line, type, l);
  }

  public static final ModernDialogStatus createDialog(ModernWindow parent, MessageDialogType type, String line,
      String... lines) {
    return createDialog(parent, type, null, line, lines);
  }

  public static final ModernDialogStatus createDialog(ModernWindow parent, MessageDialogType type,
      DialogEventListener l, String line, String... lines) {
    return createDialog(parent, parent.getAppInfo().getName(), type, l, line, lines);
  }

  /**
   * Creates the dialog.
   *
   * @param parent the parent
   * @param title  the title
   * @param line   the line
   * @param type   the type
   * @return the modern dialog status
   */
  public static final ModernDialogStatus createDialog(ModernWindow parent, String title, String line,
      MessageDialogType type) {
    return createDialog(parent, title, line, type, null);
  }

  /**
   * Creates the dialog.
   *
   * @param parent the parent
   * @param title  the title
   * @param line   the line
   * @param type   the type
   * @param l      the l
   * @return the modern dialog status
   */
  public static final ModernDialogStatus createDialog(ModernWindow parent, String title, String line,
      MessageDialogType type, DialogEventListener l) {
    return createDialog(parent, title, CollectionUtils.asList(line), type, l);
  }

  /**
   * Creates the dialog.
   *
   * @param parent the parent
   * @param title  the title
   * @param lines  the lines
   * @param line
   * @param type   the type
   * @return the modern dialog status
   */
  public static final ModernDialogStatus createDialog(ModernWindow parent, String title, MessageDialogType type,
      String line, String... lines) {
    return createDialog(parent, title, type, null, line, lines);
  }

  /**
   * Creates the dialog.
   *
   * @param parent the parent
   * @param title  the title
   * @param lines  the lines
   * @param type   the type
   * @param line
   * @param l      the l
   * @return the modern dialog status
   */
  public static final ModernDialogStatus createDialog(ModernWindow parent, String title, MessageDialogType type,
      DialogEventListener l, String line, String... lines) {
    return createDialog(parent, title, CollectionUtils.toList(line, lines), type, l);
  }

  /**
   * Creates the dialog.
   *
   * @param parent the parent
   * @param title  the title
   * @param lines  the lines
   * @param type   the type
   * @return the modern dialog status
   */
  public static final ModernDialogStatus createDialog(ModernWindow parent, String title, List<String> lines,
      MessageDialogType type) {
    return createDialog(parent, title, lines, type, null);
  }

  /**
   * Creates the dialog.
   *
   * @param parent the parent
   * @param title  the title
   * @param lines  the lines
   * @param type   the type
   * @param l      the l
   * @return the modern dialog status
   */
  public static final ModernDialogStatus createDialog(ModernWindow parent, String title, List<String> lines,
      MessageDialogType type, DialogEventListener l) {
    ModernMessageDialog dialog;

    ModernIcon icon;

    switch (type) {
    case WARNING:
    case WARNING_OK_CANCEL:
    case WARNING_YES_NO_CANCEL:
      icon = AssetService.getInstance().loadIcon(WarningVectorIcon.class, ICON_SIZE); // Resources.getInstance().loadIcon("warning",
      // Resources.ICON_SIZE_48);
      break;
    default:
      icon = AssetService.getInstance().loadIcon(HelpVectorIcon.class, ICON_SIZE); // Resources.getInstance().loadIcon("information",
      // Resources.ICON_SIZE_48);
      break;
    }

    dialog = new ModernMessageDialog(parent, title, icon, type, lines, l);

    dialog.setVisible(true);

    ModernDialogStatus status = dialog.getStatus();

    dialog.dispose();

    return status;
  }

  /**
   * Creates the file replace dialog.
   *
   * @param parent the parent
   * @param file   the file
   * @return the modern dialog status
   */
  public static final ModernDialogStatus createFileReplaceDialog(ModernWindow parent, Path file) {
    return createFileReplaceDialog(parent, file, null);
  }

  /**
   * Creates the file replace dialog.
   *
   * @param parent the parent
   * @param file   the file
   * @param l      the l
   * @return the modern dialog status
   */
  public static final ModernDialogStatus createFileReplaceDialog(ModernWindow parent, Path file,
      DialogEventListener l) {
    return createDialog(parent, "Confirm Save As", MessageDialogType.WARNING_OK_CANCEL, l,
        "'" + truncate(PathUtils.getName(file)) + "' already exists.", "Do you want to replace it?");
  }

  /**
   * Creates a standardized dialog for restarting an application.
   *
   * @param parent the parent
   * @return the modern dialog status
   */
  public static final ModernDialogStatus createRestartDialog(ModernWindow parent) {
    return createRestartDialog(parent, null);
  }

  /**
   * Creates a standardized dialog for restarting an application.
   *
   * @param parent the parent
   * @param l      the l
   * @return the modern dialog status
   */
  public static final ModernDialogStatus createRestartDialog(ModernWindow parent, DialogEventListener l) {
    return createDialog(parent, "Confirm Restart", MessageDialogType.WARNING_OK_CANCEL, l,
        "Are you sure you want to restart?", "Any unsaved data will be lost.");
  }

  /**
   * Creates the file saved dialog.
   *
   * @param parent the parent
   * @param file   the file
   */
  public static void createFileSavedDialog(ModernWindow parent, Path file) {
    createFileSavedDialog(parent, parent.getAppInfo().getName(), file);
  }

  /**
   * Creates the file saved dialog.
   *
   * @param parent the parent
   * @param title  the title
   * @param file   the file
   */
  public static final void createFileSavedDialog(ModernWindow parent, String title, Path file) {
    createDialog(parent, title, "'" + PathUtils.getName(file) + "' was saved.", MessageDialogType.INFORMATION);
  }

  /**
   * Creates the files saved dialog.
   *
   * @param parent    the parent
   * @param title     the title
   * @param directory the directory
   */
  public static final void createFilesSavedDialog(ModernWindow parent, String title, Path directory) {
    createDialog(parent, title, "Your files were saved in '" + truncate(PathUtils.toString(directory)) + "'.",
        MessageDialogType.INFORMATION);
  }

  /**
   * Creates the file does not exist dialog.
   *
   * @param parent the parent
   * @param file   the file
   */
  public static void createFileDoesNotExistDialog(ModernWindow parent, Path file) {
    createFileDoesNotExistDialog(parent, parent.getAppInfo().getName(), file);
  }

  /**
   * Creates the file does not exist dialog.
   *
   * @param parent the parent
   * @param title  the title
   * @param file   the file
   */
  public static final void createFileDoesNotExistDialog(ModernWindow parent, String title, Path file) {
    createDialog(parent, title, MessageDialogType.WARNING,
        "Path '" + truncate(PathUtils.getName(file)) + "' does not exist.",
        "Please check the location and make sure it is accessible.");
  }

  /**
   * Creates the file open error dialog.
   *
   * @param parent the parent
   * @param title  the title
   * @param file   the file
   */
  public static final void createFileOpenErrorDialog(ModernWindow parent, String title, Path file) {
    String line = "There was an error opening '" + truncate(PathUtils.getName(file)) + "'.";

    createDialog(parent, title, line, MessageDialogType.WARNING);
  }

  /**
   * Creates the integer error dialog.
   *
   * @param parent the parent
   * @param title  the title
   * @param text   the text
   */
  public static final void createIntegerErrorDialog(ModernWindow parent, String title, String text) {
    String line = "'" + text + "' is not a valid number. Please correct this value.";

    createDialog(parent, title, line, MessageDialogType.WARNING);
  }

  /**
   * Creates the file save error dialog.
   *
   * @param parent  the parent
   * @param title   the title
   * @param file    the file
   * @param message the message
   */
  public static final void createFileSaveErrorDialog(ModernWindow parent, String title, Path file, String message) {
    createDialog(parent, title, MessageDialogType.WARNING,
        "There was an error saving '" + truncate(PathUtils.getName(file)) + "'.", message);
  }

  /**
   * Provides a standard warning that the user that the output file is the same as
   * the input file.
   *
   * @param parent the parent
   * @param title  the title
   */
  public static void createInputSameAsOutputDialog(ModernWindow parent, String title) {
    createDialog(parent, title, "The output file name cannot be the same as the input file name.",
        MessageDialogType.WARNING);
  }

  /**
   * Provides a standard warning that the user should specify an input file.
   *
   * @param parent the parent
   * @param title  the title
   */
  public static void createSpecifyInputDialog(ModernWindow parent, String title) {
    createDialog(parent, title, "You must load a file before you can use this feature.", MessageDialogType.WARNING);
  }

  /**
   * Creates the information dialog.
   *
   * @param parent  the parent
   * @param message the message
   * @return the modern dialog status
   */
  public static ModernDialogStatus createInformationDialog(ModernWindow parent, String message) {
    return createDialog(parent, message, MessageDialogType.INFORMATION);
  }

  /**
   * Creates the warning dialog.
   *
   * @param parent the parent
   * @param lines  the lines
   * @return the modern dialog status
   */
  public static ModernDialogStatus createInformationDialog(ModernWindow parent, String line, String... lines) {
    return createDialog(parent, MessageDialogType.INFORMATION, line, lines);
  }

  /**
   * Creates the warning dialog.
   *
   * @param parent  the parent
   * @param message the message
   * @return the modern dialog status
   */
  public static ModernDialogStatus createWarningDialog(ModernWindow parent, String message) {
    return createDialog(parent, message, MessageDialogType.WARNING);
  }

  /**
   * Creates the warning dialog.
   *
   * @param parent the parent
   * @param lines  the lines
   * @return the modern dialog status
   */
  public static ModernDialogStatus createWarningDialog(ModernWindow parent, String line, String... lines) {
    return createDialog(parent, MessageDialogType.WARNING, line, lines);
  }

  /**
   * Truncate.
   *
   * @param text the text
   * @return the string
   */
  public static String truncate(String text) {
    return TextUtils.truncateCenter(text, 40);
  }

  /**
   * Creates the ok cancel warning dialog.
   *
   * @param parent  the parent
   * @param message the message
   * @return the modern dialog status
   */
  public static ModernDialogStatus createOkCancelWarningDialog(ModernWindow parent, String message) {
    return createDialog(parent, message, MessageDialogType.WARNING_OK_CANCEL);
  }

  /**
   * Creates the ok cancel info dialog.
   *
   * @param parent  the parent
   * @param message the message
   * @return the modern dialog status
   */
  public static ModernDialogStatus createOkCancelInfoDialog(ModernWindow parent, String message) {
    return createDialog(parent, message, MessageDialogType.INFORMATION_OK_CANCEL);
  }

  /**
   * Creates the ok cancel warning dialog.
   *
   * @param parent   the parent
   * @param message  the message
   * @param listener the listener
   */
  public static void createOkCancelWarningDialog(ModernWindow parent, String message, DialogEventListener listener) {
    createDialog(parent, message, MessageDialogType.WARNING_OK_CANCEL, listener);
  }
}
