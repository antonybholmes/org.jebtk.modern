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
package org.jebtk.modern.animation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jebtk.core.event.ChangeListeners;

/**
 * The Class Animations.
 */
public class AnimationCreator extends ChangeListeners implements Iterable<Class<?>> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The m animations. */
  private List<Class<?>> mAnimations = new ArrayList<Class<?>>();

  /**
   * Add an animation class which can be used to create an animation object for a
   * particular widget.
   *
   * @param animation the animation
   * @return
   */
  public AnimationCreator add(Class<?> animation) {
    update(animation);

    fireChanged();

    return this;
  }

  /**
   * Update.
   *
   * @param animation the animation
   * @return
   */
  public AnimationCreator update(Class<?> animation) {
    mAnimations.add(animation);

    return this;
  }

  /**
   * Sets the.
   *
   * @param animation  the animation
   * @param animations the animations
   * @return
   */
  public AnimationCreator set(Class<?> animation) {
    clear();

    add(animation);

    return this;
  }

  /**
   * Clear.
   * 
   * @return
   */
  private AnimationCreator clear() {
    mAnimations.clear();

    return this;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Iterable#iterator()
   */
  @Override
  public Iterator<Class<?>> iterator() {
    return mAnimations.iterator();
  }

  /**
   * Get a particular animation by index.
   * 
   * @param index
   * @return
   */
  public Class<?> get(int index) {
    return mAnimations.get(index);
  }

}
