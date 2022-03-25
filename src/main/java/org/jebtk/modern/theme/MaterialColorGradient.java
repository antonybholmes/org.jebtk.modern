/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.jebtk.modern.theme;

import java.util.ArrayList;
import java.util.List;
import org.jebtk.core.CSSColor;

/**
 *
 * @author antony
 */
public class MaterialColorGradient implements IMaterialColorGradient {
  protected List<CSSColor> mColors = new ArrayList<>();

  @Override
  public CSSColor getColor(int index) {
    return mColors.get(index);
  }
}
