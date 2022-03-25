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
package org.jebtk.modern.tree;

import org.jebtk.core.event.EventProducer;

/**
 * The basis for model controls in a model view controller setup.
 * 
 * @author Antony Holmes
 *
 */
public class TreeEventListeners extends EventProducer<TreeEventListener> implements TreeEventProducer {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.tree.TreeEventProducer#addTreeListener(org.abh.lib. ui.
   * modern.tree.TreeEventListener)
   */
  @Override
  public void addTreeListener(TreeEventListener l) {
    mListeners.add(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.tree.TreeEventProducer#removeTreeListener(org.abh.
   * lib. ui.modern.tree.TreeEventListener)
   */
  @Override
  public void removeTreeListener(TreeEventListener l) {
    mListeners.remove(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.tree.TreeEventProducer#fireTreeNodeDragged(org.abh.
   * lib. ui.modern.tree.ModernTreeEvent)
   */
  @Override
  public void fireTreeNodeDragged(ModernTreeEvent e) {
    for (TreeEventListener l : mListeners) {
      l.treeNodeDragged(e);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.tree.TreeEventProducer#fireTreeNodeClicked(org.abh.
   * lib. ui.modern.tree.ModernTreeEvent)
   */
  @Override
  public void fireTreeNodeClicked(ModernTreeEvent e) {
    for (TreeEventListener l : mListeners) {
      l.treeNodeClicked(e);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.tree.TreeEventProducer#fireTreeNodeDoubleClicked(org.
   * abh.lib.ui.modern.tree.ModernTreeEvent)
   */
  @Override
  public void fireTreeNodeDoubleClicked(ModernTreeEvent e) {
    for (TreeEventListener l : mListeners) {
      l.treeNodeDoubleClicked(e);
    }
  }
}