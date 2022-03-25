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
package org.jebtk.modern.io;

import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import org.jebtk.core.collections.CollectionUtils;
import org.jebtk.core.io.PathUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * Functions for creating and saving files.
 * 
 * @author Antony Holmes
 *
 */
public class FileDialog {

  /**
   * The constant LOG.
   */
  private static final Logger LOG = LoggerFactory.getLogger(FileDialog.class);

  /** The Constant EMPTY_FILE. */
  public static final Path EMPTY_FILE = PathUtils.getPath("");

  /**
   * Save file.
   *
   * @param parent  the parent
   * @param pwd     the pwd
   * @param filter
   * @param filters the filters
   * @return the path
   */
  public static Path saveFile(Frame parent, Path pwd, GuiFileExtFilter filter, GuiFileExtFilter... filters) {
    return saveFile(parent, pwd, EMPTY_FILE, 0, filter, filters);
  }

  /**
   * Save file.
   *
   * @param parent        the parent
   * @param pwd           the working directory
   * @param defaultFilter the default filter
   * @param filter
   * @param filters       the filters
   * @return the file
   */
  public static Path saveFile(Frame parent, Path pwd, int defaultFilter, GuiFileExtFilter filter,
      GuiFileExtFilter... filters) {
    return saveFile(parent, pwd, defaultFilter, CollectionUtils.toList(filter, filters));
  }

  /**
   * Save file.
   *
   * @param parent        the parent
   * @param pwd           the pwd
   * @param defaultFilter the default filter
   * @param filters       the filters
   * @return the path
   */
  public static Path saveFile(Frame parent, Path pwd, int defaultFilter, List<GuiFileExtFilter> filters) {
    return saveFile(parent, pwd, EMPTY_FILE, defaultFilter, filters);
  }

  /**
   * Save file.
   *
   * @param parent        the parent
   * @param pwd           the pwd
   * @param suggestedFile the suggested file
   * @param filter
   * @param filters       the filters
   * @return the path
   */
  public static Path saveFile(Frame parent, Path pwd, Path suggestedFile, GuiFileExtFilter filter,
      GuiFileExtFilter... filters) {
    return saveFile(parent, pwd, suggestedFile, CollectionUtils.toList(filter, filters));
  }

  /**
   * Save file.
   *
   * @param parent        the parent
   * @param pwd           the pwd
   * @param suggestedFile the suggested file
   * @param filters       the filters
   * @return the path
   */
  public static Path saveFile(Frame parent, Path pwd, Path suggestedFile, List<GuiFileExtFilter> filters) {
    return saveFile(parent, pwd, suggestedFile, 0, filters);
  }

  /**
   * Save file.
   *
   * @param parent        the parent
   * @param pwd           the working directory
   * @param suggestedFile the suggested file
   * @param defaultFilter the default filter
   * @param filter
   * @param filters       the filters
   * @return the file
   */
  public static Path saveFile(Frame parent, Path pwd, Path suggestedFile, int defaultFilter, GuiFileExtFilter filter,
      GuiFileExtFilter... filters) {
    return saveFile(parent, pwd, suggestedFile, defaultFilter, CollectionUtils.toList(filter, filters));
  }

  /**
   * Save file.
   *
   * @param parent        the parent
   * @param pwd           the pwd
   * @param suggestedFile the suggested file
   * @param defaultFilter the default filter
   * @param filters       the filters
   * @return the path
   */
  public static Path saveFile(Frame parent, Path pwd, Path suggestedFile, int defaultFilter,
      List<GuiFileExtFilter> filters) {

    JFileChooser fc = new JFileChooser();

    fc.setCurrentDirectory(pwd.toFile());
    fc.setAcceptAllFileFilterUsed(false);

    for (FileFilter filter : filters) {
      fc.addChoosableFileFilter(filter);
    }

    System.err.println("default " + defaultFilter);

    fc.setFileFilter(filters.get(defaultFilter));

    fc.setSelectedFile(new File(PathUtils.getNameNoExt(suggestedFile)));

    int returnVal = fc.showSaveDialog(parent);

    if (returnVal == JFileChooser.CANCEL_OPTION) {
      return null;
    }

    Path file = fc.getSelectedFile().toPath();

    return GuiFileExtFilter.addExtension(file, (GuiFileExtFilter) fc.getFileFilter());
  }

  /**
   * Allow users to save Text files.
   *
   * @param parent the parent
   * @param pwd    the working directory
   * @return the file
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public static Path saveTextFile(Frame parent, Path pwd) throws IOException {
    return saveFile(parent, pwd, 0, new TxtGuiFileFilter());
  }

  /**
   * Save zip file.
   *
   * @param parent the parent
   * @param pwd    the working directory
   * @return the file
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public static Path saveZipFile(Frame parent, Path pwd) throws IOException {
    return saveFile(parent, pwd, 0, new ZipGuiFileFilter());
  }

  /**
   * Open file.
   *
   * @param parent  the parent
   * @param pwd     the pwd
   * @param filters the filters
   * @return the path
   */
  public static Path openFile(Frame parent, Path pwd, GuiFileExtFilter filter, GuiFileExtFilter... filters) {
    return openFile(parent, pwd, 0, filter, filters);
  }

  /**
   * Open file.
   *
   * @param parent        the parent
   * @param pwd           the working directory
   * @param defaultFilter the default filter
   * @param filters       the filters
   * @return the file
   */
  public static Path openFile(Frame parent, Path pwd, int defaultFilter, GuiFileExtFilter filter,
      GuiFileExtFilter... filters) {
    return openFile(parent, pwd, 0, CollectionUtils.toList(filter, filters));
  }

  /**
   * Open file.
   *
   * @param parent  the parent
   * @param pwd     the pwd
   * @param filters the filters
   * @return the path
   */
  public static Path openFile(Frame parent, Path pwd, List<GuiFileExtFilter> filters) {
    return openFile(parent, pwd, 0, filters);
  }

  /**
   * Open file.
   *
   * @param parent        the parent
   * @param pwd           the pwd
   * @param defaultFilter the default filter
   * @param filters       the filters
   * @return the path
   */
  public static Path openFile(Frame parent, Path pwd, int defaultFilter, List<GuiFileExtFilter> filters) {
    JFileChooser fc = new JFileChooser();

    LOG.info("Set working directory to {}", pwd);

    fc.setCurrentDirectory(pwd.toFile());
    fc.setAcceptAllFileFilterUsed(false);

    for (FileFilter filter : filters) {
      fc.addChoosableFileFilter(filter);
    }

    fc.setFileFilter(filters.get(defaultFilter));

    int returnVal = fc.showOpenDialog(parent);

    if (returnVal == JFileChooser.APPROVE_OPTION) {
      return fc.getSelectedFile().toPath();
    } else {
      return null;
    }
  }

  /**
   * Open files.
   *
   * @param parent        the parent
   * @param pwd           the working directory
   * @param defaultFilter the default filter
   * @param filters       the filters
   * @return the list
   */
  public static List<Path> openFiles(Frame parent, Path pwd, int defaultFilter, GuiFileExtFilter... filters) {
    return openFiles(parent, pwd, 0, filters);
  }

  /**
   * Open files.
   *
   * @param parent  the parent
   * @param pwd     the pwd
   * @param filters the filters
   * @return the list
   */
  public static List<Path> openFiles(Frame parent, Path pwd, GuiFileExtFilter filter, GuiFileExtFilter... filters) {
    return openFiles(parent, pwd, 0, true, filter, filters);
  }

  public static List<Path> openFiles(Frame parent, Path pwd, List<GuiFileExtFilter> filters) {
    return openFiles(parent, pwd, 0, true, filters);
  }

  /**
   * Open files.
   *
   * @param parent  the parent
   * @param pwd     the pwd
   * @param filters the filters
   * @return the list
   */
  public static List<Path> openFiles(Frame parent, Path pwd, boolean multiSelect, List<GuiFileExtFilter> filters) {
    return openFiles(parent, pwd, 0, multiSelect, filters);
  }

  /**
   * Open files.
   *
   * @param parent      the parent
   * @param pwd         the pwd
   * @param multiSelect the multi select
   * @param filters     the filters
   * @return the list
   */
  public static List<Path> openFiles(Frame parent, Path pwd, boolean multiSelect, GuiFileExtFilter filter,
      GuiFileExtFilter... filters) {
    return openFiles(parent, pwd, 0, multiSelect, filter, filters);
  }

  /**
   * Open files.
   *
   * @param parent        the parent
   * @param pwd           the pwd
   * @param defaultFilter the default filter
   * @param multiSelect   the multi select
   * @param filters       the filters
   * @return the list
   */
  public static List<Path> openFiles(Frame parent, Path pwd, int defaultFilter, boolean multiSelect,
      GuiFileExtFilter filter, GuiFileExtFilter... filters) {
    return openFiles(parent, pwd, defaultFilter, multiSelect, CollectionUtils.toList(filter, filters));
  }

  /**
   * Open files.
   *
   * @param parent        the parent
   * @param pwd           the pwd
   * @param defaultFilter the default filter
   * @param multiSelect   the multi select
   * @param filters       the filters
   * @return the list
   */
  public static List<Path> openFiles(Frame parent, Path pwd, int defaultFilter, boolean multiSelect,
      List<GuiFileExtFilter> filters) {
    JFileChooser fc = new JFileChooser();

    LOG.info("Set working directory to {}", pwd.toAbsolutePath());

    fc.setCurrentDirectory(pwd.toFile());
    fc.setAcceptAllFileFilterUsed(false);

    for (FileFilter filter : filters) {
      fc.addChoosableFileFilter(filter);
    }

    fc.setMultiSelectionEnabled(multiSelect);
    fc.setFileFilter(filters.get(defaultFilter));

    int returnVal = fc.showOpenDialog(parent);

    if (returnVal == JFileChooser.APPROVE_OPTION) {
      if (multiSelect) {
        return PathUtils.toList(fc.getSelectedFiles());
      } else {
        // getSelectedFiles() does not work if multi-selection is off
        // so we need to make a list from the selected file
        return CollectionUtils.asList(fc.getSelectedFile().toPath());
      }
    } else {
      return Collections.emptyList();
    }
  }

  /**
   * Open text file.
   *
   * @param parent the parent
   * @param pwd    the working directory
   * @return the file
   */
  public static Path openTextFile(Frame parent, Path pwd) {
    return openFile(parent, pwd, new TxtGuiFileFilter());
  }

  /**
   * Open text files.
   *
   * @param parent the parent
   * @param pwd    the pwd
   * @return the list
   */
  public static List<Path> openTextFiles(Frame parent, Path pwd) {
    return openFiles(parent, pwd, new TxtGuiFileFilter());
  }

  /**
   * Open text file.
   *
   * @param parent the parent
   * @param pwd    the working directory
   * @return the file
   */
  public static Path openTabCsvFile(Frame parent, Path pwd) {
    return openFile(parent, pwd, new TxtTsvCsvGuiFileFilter(), new TxtGuiFileFilter(), new TsvGuiFileFilter(),
        new CsvGuiFileFilter());
  }

  /**
   * Open tab file.
   *
   * @param parent the parent
   * @param pwd    the pwd
   * @return the path
   */
  public static Path openTabFile(Frame parent, Path pwd) {
    return openFile(parent, pwd, new TxtTsvGuiFileFilter());
  }

  /**
   * Save tab file.
   *
   * @param parent the parent
   * @param pwd    the pwd
   * @return the path
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public static Path saveTabFile(Frame parent, Path pwd) throws IOException {
    return saveFile(parent, pwd, new TxtGuiFileFilter());
  }

  /**
   * Open dir.
   *
   * @param parent the parent
   * @param pwd    the pwd
   * @return the path
   */
  public static Path openDir(Frame parent, Path pwd) {
    JFileChooser fc = new JFileChooser();

    fc.setCurrentDirectory(pwd.toFile());
    fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

    int returnVal = fc.showOpenDialog(parent);

    if (returnVal == JFileChooser.APPROVE_OPTION) {
      return fc.getSelectedFile().toPath();
    } else {
      return null;
    }
  }

  /**
   * Open dirs.
   *
   * @param parent the parent
   * @param pwd    the pwd
   * @return the list
   */
  public static List<Path> openDirs(Frame parent, Path pwd) {
    JFileChooser fc = new JFileChooser();

    fc.setCurrentDirectory(pwd.toFile());
    fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    fc.setMultiSelectionEnabled(true);

    int returnVal = fc.showOpenDialog(parent);

    if (returnVal == JFileChooser.APPROVE_OPTION) {
      return PathUtils.toList(fc.getSelectedFiles());
    } else {
      return Collections.emptyList();
    }
  }

  //
  // Functional style addressing of dialogs
  //

  /**
   * The Class PathsSelection.
   */
  public static abstract class PathsSelection extends PathSelection {

    /**
     * Instantiates a new paths selection.
     *
     * @param frame the frame
     */
    public PathsSelection(Frame frame) {
      super(frame);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.abh.common.ui.io.FileDialog.PathSelection#getFile(java.nio.file.Path)
     */
    @Override
    public Path getFile(Path pwd) {
      List<Path> paths = getFiles(pwd);

      if (paths.size() > 0) {
        return paths.get(0);
      } else {
        return null;
      }
    }

    /**
     * Returns a list of selected files. The current present working directory will
     * be used as the starting directory.
     *
     * @return the files
     */
    public List<Path> getFiles() {
      return getFiles(RecentFilesService.getInstance().getPwd());
    }

    /**
     * Returns a list of selected files. The default directory is specified with
     * {@code pwd}.
     *
     * @param pwd the pwd
     * @return the files
     */
    public abstract List<Path> getFiles(Path pwd);
  }

  /**
   * The Class PathSelection.
   */
  public static abstract class PathSelection {

    /** The m frame. */
    protected Frame mFrame;

    /**
     * Instantiates a new path selection.
     *
     * @param frame the frame
     */
    public PathSelection(Frame frame) {
      mFrame = frame;
    }

    /**
     * Returns a selected file using the present working directory as the default
     * starting directory to locate a file.
     *
     * @return the file
     */
    public Path getFile() {
      return getFile(RecentFilesService.getInstance().getPwd());
    }

    /**
     * Gets the file.
     *
     * @param pwd the pwd
     * @return the file
     */
    public abstract Path getFile(Path pwd);
  }

  //
  // Open dialogs
  //

  /**
   * The Class OpenDirSelection.
   */
  public static class OpenDirSelection extends PathsSelection {

    /**
     * Instantiates a new open dir selection.
     *
     * @param frame the frame
     */
    public OpenDirSelection(Frame frame) {
      super(frame);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.abh.common.ui.io.FileDialog.PathsSelection#getFiles(java.nio.file.
     * Path)
     */
    @Override
    public List<Path> getFiles(Path pwd) {
      return openDirs(mFrame, pwd);
    }
  }

  /**
   * The Class OpenTextFileSelection.
   */
  public static class OpenTextFileSelection extends PathsSelection {

    /**
     * Instantiates a new open text file selection.
     *
     * @param frame the frame
     */
    public OpenTextFileSelection(Frame frame) {
      super(frame);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.abh.common.ui.io.FileDialog.PathsSelection#getFiles(java.nio.file.
     * Path)
     */
    @Override
    public List<Path> getFiles(Path pwd) {
      return openTextFiles(mFrame, pwd);
    }
  }

  /**
   * The Class OpenFilesSelection.
   */
  public static class OpenFilesSelection extends PathsSelection {

    /** The m filters. */
    private List<GuiFileExtFilter> mFilters;

    /** The m multi select. */
    private boolean mMultiSelect = true;

    /**
     * Instantiates a new open files selection.
     *
     * @param frame   the frame
     * @param filters the filters
     */
    public OpenFilesSelection(Frame frame, GuiFileExtFilter filter, GuiFileExtFilter... filters) {
      super(frame);

      mFilters = CollectionUtils.toList(filter, filters);
    }

    public OpenFilesSelection(Frame frame, List<GuiFileExtFilter> filters) {
      super(frame);

      mFilters = filters;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.abh.common.ui.io.FileDialog.PathsSelection#getFiles(java.nio.file.
     * Path)
     */
    @Override
    public List<Path> getFiles(Path pwd) {
      return openFiles(mFrame, pwd, mMultiSelect, mFilters);
    }

    /**
     * Sets whether the user can select multiple files or not.
     *
     * @param multiSelect the multi select
     * @return the paths selection
     */
    public PathsSelection multiSelect(boolean multiSelect) {
      mMultiSelect = multiSelect;

      return this;
    }
  }

  /**
   * The Class OpenDialog.
   */
  public static class OpenDialog {

    /** The m dirs selection. */
    private PathsSelection mDirsSelection;

    /** The m text file selection. */
    private PathsSelection mTextFileSelection;

    /** The m frame. */
    private Frame mFrame;

    /**
     * Instantiates a new open dialog.
     *
     * @param frame the frame
     */
    private OpenDialog(Frame frame) {
      mFrame = frame;

      mDirsSelection = new OpenDirSelection(frame);
      mTextFileSelection = new OpenTextFileSelection(frame);

    }

    /**
     * Dirs.
     *
     * @return the paths selection
     */
    public PathsSelection dirs() {
      return mDirsSelection;
    }

    /**
     * Txt.
     *
     * @return the paths selection
     */
    public PathsSelection txt() {
      return mTextFileSelection;
    }

    /**
     * All.
     *
     * @return the paths selection
     */
    public PathsSelection all() {
      return new OpenFilesSelection(mFrame, new AllGuiFilesFilter());
    }

    /**
     * Settings.
     *
     * @return the paths selection
     */
    public PathsSelection settings() {
      return filter(new AllSettingsGuiFileFilter(), new JsonGuiFileFilter(), new XmlGuiFileFilter());
    }

    /**
     * Filter.
     *
     * @param filters the filters
     * @return the open files selection
     */
    public OpenFilesSelection filter(GuiFileExtFilter filter, GuiFileExtFilter... filters) {
      return new OpenFilesSelection(mFrame, filter, filters);
    }

    public OpenFilesSelection filter(List<GuiFileExtFilter> filters) {
      return new OpenFilesSelection(mFrame, filters);
    }
  }

  /**
   * Open.
   *
   * @param frame the frame
   * @return the open dialog
   */
  public static OpenDialog open(Frame frame) {
    return new OpenDialog(frame);
  }

  //
  // Save Dialogs
  //

  /**
   * The Class SaveFileSelection.
   */
  public static class SaveFileSelection extends PathSelection {

    /** The m filters. */
    protected List<GuiFileExtFilter> mFilters;

    /** The m suggested file. */
    private Path mSuggestedFile;

    /** The m default filter. */
    private int mDefaultFilter = 0;

    /**
     * Instantiates a new save file selection.
     *
     * @param frame         the frame
     * @param suggestedFile the suggested file
     * @param filters       the filters
     */
    public SaveFileSelection(Frame frame, Path suggestedFile, List<GuiFileExtFilter> filters) {
      this(frame, suggestedFile, 0, filters);
    }

    /**
     * Instantiates a new save file selection.
     *
     * @param frame         the frame
     * @param suggestedFile the suggested file
     * @param defaultFilter the default filter
     * @param filters       the filters
     */
    public SaveFileSelection(Frame frame, Path suggestedFile, int defaultFilter, List<GuiFileExtFilter> filters) {
      super(frame);

      mSuggestedFile = suggestedFile;
      mDefaultFilter = defaultFilter;
      mFilters = filters;
    }

    /**
     * Set the default filter by name. Searches filter descriptions for name and
     * matches on the first.
     *
     * @param name the name
     * @return the save file selection
     */
    public SaveFileSelection setDefaultFilter(String name) {
      String ln = name.toLowerCase();

      int index = 0;

      for (int i = 0; i < mFilters.size(); ++i) {
        if (mFilters.get(i).getDescription().toLowerCase().contains(ln)) {
          index = i;

          break;
        }
      }

      return setDefaultFilter(index);
    }

    /**
     * Sets the default filter.
     *
     * @param index the index
     * @return the save file selection
     */
    public SaveFileSelection setDefaultFilter(int index) {
      return new SaveFileSelection(mFrame, mSuggestedFile, index, mFilters);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.abh.common.ui.io.FileDialog.PathSelection#getFile(java.nio.file.Path)
     */
    @Override
    public Path getFile(Path pwd) {
      return saveFile(mFrame, pwd, mSuggestedFile, mDefaultFilter, mFilters);
    }

    /**
     * Suggested.
     *
     * @param suggestedFile the suggested file
     * @return the save file selection
     */
    public SaveFileSelection suggested(String suggestedFile) {
      return suggested(PathUtils.getPath(suggestedFile));
    }

    /**
     * Suggested.
     *
     * @param suggestedFile the suggested file
     * @return the save file selection
     */
    public SaveFileSelection suggested(Path suggestedFile) {
      return new SaveFileSelection(mFrame, suggestedFile, mDefaultFilter, mFilters);
    }
  }

  /**
   * The Class SaveDialog.
   */
  public static class SaveDialog {

    /** The m frame. */
    private Frame mFrame;

    /**
     * Instantiates a new save dialog.
     *
     * @param frame the frame
     */
    private SaveDialog(Frame frame) {
      mFrame = frame;
    }

    /**
     * Filter.
     *
     * @param filters the filters
     * @return the save file selection
     */
    public SaveFileSelection filter(GuiFileExtFilter filter, GuiFileExtFilter... filters) {
      return filter(CollectionUtils.toList(filter, filters));
    }

    /**
     * Filter.
     *
     * @param filters the filters
     * @return the save file selection
     */
    public SaveFileSelection filter(List<GuiFileExtFilter> filters) {
      return new SaveFileSelection(mFrame, EMPTY_FILE, filters);
    }
  }

  /**
   * Save.
   *
   * @param frame the frame
   * @return the save dialog
   */
  public static SaveDialog save(Frame frame) {
    return new SaveDialog(frame);
  }
}
