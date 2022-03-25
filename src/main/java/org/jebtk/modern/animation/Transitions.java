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

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import org.jebtk.core.Props;
import org.jebtk.modern.ModernWidget;

/**
 * Allows for fade in/out animation on an element.
 *
 * @author Antony Holmes
 */
public abstract class Transitions extends Transition {

  private TransitionTimer mTimer = null;

  private List<Transition> mTransitions = new ArrayList<Transition>();

  protected ModernWidget mWidget;

  public Transitions(ModernWidget widget) {
    this(widget, new EasingTransitionTimer(5));
  }

  public Transitions(ModernWidget widget, TransitionTimer timer) {
    mWidget = widget;
    mTimer = timer;
  }

  public void addTransition(Transition transition) {
    mTransitions.add(transition);
  }

  public TransitionTimer getTimer() {
    return mTimer;
  }

  @Override
  public void draw(ModernWidget c, Graphics2D g2, Props props) {
    // TODO Auto-generated method stub

  }

  @Override
  public void updateState(double t, boolean forward) {
    for (Transition tran : mTransitions) {
      tran.updateState(t, forward);
    }
  }
}
