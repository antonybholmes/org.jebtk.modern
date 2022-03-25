/**
 * Copyright 2017 Antony Holmes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jebtk.modern.theme;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jebtk.core.collections.IterHashMap;
import org.jebtk.core.collections.IterMap;
import org.jebtk.core.event.ChangeListeners;

/**
 * The Class Animations.
 */
public class DrawUIStates extends ChangeListeners implements Iterable<DrawUI> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  private IterMap<String, DrawUI> mRenderMap = new IterHashMap<String, DrawUI>();

  private List<DrawUI> mRenderers = new ArrayList<DrawUI>();

  public DrawUIStates add(DrawUI draw) {
    return add(draw.getName(), draw);
  }

  public DrawUIStates add(String name, DrawUI draw) {
    mRenderMap.put(name, draw);
    mRenderers.add(draw);

    return this;
  }

  @Override
  public Iterator<DrawUI> iterator() {
    return mRenderers.iterator();
  }

  public DrawUI get(String name) {
    return mRenderMap.get(name);
  }

  public DrawUIStates set(DrawUI draw) {
    return set(draw.getName(), draw);
  }

  public DrawUIStates set(String name, DrawUI draw) {
    clear();

    add(name, draw);

    return this;
  }

  public void clear() {
    mRenderMap.clear();
    mRenderers.clear();
  }
}
