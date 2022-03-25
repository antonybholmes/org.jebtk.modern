package org.jebtk.modern;

import java.awt.Dimension;

public class ModernComponentAspectRatio {
  public Dimension getPreferredSize(Dimension preferred, Dimension min, Dimension max) {
    return preferred;
  }

  public Dimension getMinimumSize(Dimension preferred, Dimension min, Dimension max) {
    return min;
  }

  public Dimension getMaximumSize(Dimension preferred, Dimension min, Dimension max) {
    return max;
  }
}
