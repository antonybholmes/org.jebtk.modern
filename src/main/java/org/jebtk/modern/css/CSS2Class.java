package org.jebtk.modern.css;

import org.jebtk.core.NameGetter;


public class CSS2Class extends CSS2Props implements NameGetter {

  private final String mName;
  
  public CSS2Class(String name) {
    mName = name;
  }

  @Override
  public String getName() {
    return mName;
  }
}
