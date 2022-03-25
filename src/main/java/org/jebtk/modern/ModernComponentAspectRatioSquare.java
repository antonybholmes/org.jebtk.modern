package org.jebtk.modern;

import java.awt.Dimension;

public class ModernComponentAspectRatioSquare extends ModernComponentAspectRatio {
  @Override
  public Dimension getPreferredSize(Dimension preferred, Dimension min, Dimension max) {
    return squareSize(preferred);
  }

  @Override
  public Dimension getMinimumSize(Dimension preferred, Dimension min, Dimension max) {
    return getPreferredSize(preferred, min, max);
  }

  @Override
  public Dimension getMaximumSize(Dimension preferred, Dimension min, Dimension max) {
    return getPreferredSize(preferred, min, max);
  }

  public static final Dimension squareSize(Dimension s) {
    int w = Math.max(s.width, s.height);

    return new Dimension(w, w);
  }
}
