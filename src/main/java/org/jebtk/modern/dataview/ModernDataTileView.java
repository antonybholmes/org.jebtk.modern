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
package org.jebtk.modern.dataview;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.TransferHandler;

// TODO: Auto-generated Javadoc
/**
 * Default to a large tile view.
 * 
 * @author Antony Holmes
 *
 */
public class ModernDataTileView extends ModernDataGridView
    implements Transferable, DragSourceListener, DragGestureListener {

  /**
   * The Class DndButton.
   */
  private class DndButton extends JButton implements Transferable {

    /**
     * Instantiates a new dnd button.
     *
     * @param text the text
     */
    public DndButton(String text) {
      super(text);

      t = new TransferHandler() {
        public Transferable createTransferable(JComponent c) {
          return new DndButton(getText());
        }
      };

      setTransferHandler(t);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.datatransfer.Transferable#getTransferData(java.awt.datatransfer.
     * DataFlavor)
     */
    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
      return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.datatransfer.Transferable#getTransferDataFlavors()
     */
    @Override
    public DataFlavor[] getTransferDataFlavors() {
      return new DataFlavor[] { new DataFlavor(DndButton.class, "JButton") };

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.datatransfer.Transferable#isDataFlavorSupported(java.awt.
     * datatransfer.DataFlavor)
     */
    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
      return true;
    }

  }

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /** The m source. */
  private DragSource mSource = null;

  /** The t. */
  private TransferHandler t = null;

  /**
   * Instantiates a new modern data tile view.
   */
  public ModernDataTileView() {
    super();

    setCellRenderer(new ModernDataTileCellRenderer());

    setCellSize(128, 128);

    // The TransferHandler returns a new DnDButton
    t = new TransferHandler() {
      public Transferable createTransferable(JComponent c) {
        return null;
      }
    };

    setTransferHandler(t);

    mSource = new DragSource();
    mSource.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_COPY, this);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.dnd.DragGestureListener#dragGestureRecognized(java.awt.dnd.
   * DragGestureEvent)
   */
  @Override
  public void dragGestureRecognized(DragGestureEvent e) {
    mSource.startDrag(e, DragSource.DefaultMoveDrop, new DndButton("test"), this);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.dnd.DragSourceListener#dragDropEnd(java.awt.dnd.
   * DragSourceDropEvent)
   */
  @Override
  public void dragDropEnd(DragSourceDropEvent dsde) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * java.awt.dnd.DragSourceListener#dragEnter(java.awt.dnd.DragSourceDragEvent)
   */
  @Override
  public void dragEnter(DragSourceDragEvent dsde) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.dnd.DragSourceListener#dragExit(java.awt.dnd.DragSourceEvent)
   */
  @Override
  public void dragExit(DragSourceEvent dse) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * java.awt.dnd.DragSourceListener#dragOver(java.awt.dnd.DragSourceDragEvent)
   */
  @Override
  public void dragOver(DragSourceDragEvent dsde) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.dnd.DragSourceListener#dropActionChanged(java.awt.dnd.
   * DragSourceDragEvent)
   */
  @Override
  public void dropActionChanged(DragSourceDragEvent dsde) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * java.awt.datatransfer.Transferable#getTransferData(java.awt.datatransfer.
   * DataFlavor)
   */
  @Override
  public Object getTransferData(DataFlavor arg0) throws UnsupportedFlavorException, IOException {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.datatransfer.Transferable#getTransferDataFlavors()
   */
  @Override
  public DataFlavor[] getTransferDataFlavors() {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.datatransfer.Transferable#isDataFlavorSupported(java.awt.
   * datatransfer.DataFlavor)
   */
  @Override
  public boolean isDataFlavorSupported(DataFlavor arg0) {
    // TODO Auto-generated method stub
    return false;
  }
}
