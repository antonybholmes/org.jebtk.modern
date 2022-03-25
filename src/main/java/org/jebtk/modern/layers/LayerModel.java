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
package org.jebtk.modern.layers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * Layer model for layer panels.
 *
 * @author Antony Holmes
 */
public class LayerModel extends LayerEventListeners implements Iterable<String> {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant LAYER_CHANGED.
   */
  public static final String LAYER_CHANGED = "layer_changed";
  // public static final String SELECTION_CLEARED = "selection_cleared";
  // public static final String SELECTION_ADDED = "selection_added";

  /**
   * The layers.
   */
  private List<String> mLayers = new ArrayList<String>();

  /**
   * The index map.
   */
  private Map<String, Integer> mIndexMap = new HashMap<String, Integer>();

  /**
   * The visibles.
   */
  private List<Boolean> mVisibles = new ArrayList<Boolean>();

  /**
   * Adds the layer.
   *
   * @param layer the layer
   */
  public final void addLayer(String layer) {
    mIndexMap.put(layer, mLayers.size());
    mLayers.add(layer);
    mVisibles.add(true);

    fireLayerChanged();
  }

  /**
   * Remove a selected node.
   *
   * @param index the index
   */
  public void removeLayer(int index) {
    mIndexMap.remove(mLayers.get(index));
    mLayers.remove(index);
    mVisibles.remove(index);

    fireLayerChanged();
  }

  /**
   * Sets the visible.
   *
   * @param name    the name
   * @param visible the visible
   */
  public void setVisible(String name, boolean visible) {
    if (mIndexMap.containsKey(name)) {
      setVisible(mIndexMap.get(name), visible);
    }
  }

  /**
   * Sets the visible.
   *
   * @param index   the index
   * @param visible the visible
   */
  public void setVisible(int index, boolean visible) {
    if (index < 0 || index >= mLayers.size()) {
      return;
    }

    System.err.println("layer " + index + " " + visible);

    mVisibles.set(index, visible);

    fireLayerUpdated();
  }

  /**
   * Checks if is visible.
   *
   * @param name the name
   * @return true, if is visible
   */
  public boolean isVisible(String name) {
    if (mIndexMap.containsKey(name)) {
      return false;
    }

    return isVisible(mIndexMap.get(name));
  }

  /**
   * Checks if is visible.
   *
   * @param index the index
   * @return true, if is visible
   */
  public boolean isVisible(int index) {
    if (index < 0 || index >= mLayers.size()) {
      return false;
    }

    return mVisibles.get(index);
  }

  /**
   * Size.
   *
   * @return the int
   */
  public int size() {
    return mLayers.size();
  }

  /**
   * Gets the.
   *
   * @param i the i
   * @return the string
   */
  public String get(int i) {
    return mLayers.get(i);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Iterable#iterator()
   */
  @Override
  public Iterator<String> iterator() {
    return mLayers.iterator();
  }

  /**
   * Adds the layer.
   *
   * @param name    the name
   * @param visible the visible
   */
  public void addLayer(String name, boolean visible) {
    addLayer(name);
    setVisible(name, visible);
  }
}
