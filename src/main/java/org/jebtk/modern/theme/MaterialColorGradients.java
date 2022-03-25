/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.jebtk.modern.theme;

import org.jebtk.core.model.KeyStore;
import org.jebtk.modern.ColorTheme;

/**
 *
 * @author antony
 */
public class MaterialColorGradients extends KeyStore<ColorTheme, IMaterialColorGradient> {
  public static final IMaterialColorGradient BLUE = new MaterialColorGradientBlue();
  public static final IMaterialColorGradient GRAY = new MaterialColorGradientGray();
  
  public MaterialColorGradients() {
    add(ColorTheme.BLUE, BLUE);
    add(ColorTheme.GRAY, GRAY);
  }
}
