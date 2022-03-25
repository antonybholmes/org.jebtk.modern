/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.jebtk.modern.css;

/**
 *
 * @author antony
 */
public enum CSSPropName {
  BACKGROUND_COLOR,
  BORDER_COLOR,
  BORDER_RADIUS,
  BORDER_WIDTH,
  BORDER_BOTTOM_COLOR,
  WIDTH;
  
  public static CSSPropName parse(String name) {
    switch (name) {
      case "background-color":
        return BACKGROUND_COLOR;
      case "border-color":
        return BORDER_COLOR;
      case "border-radius":
        return BORDER_RADIUS;
      case "border-width":
        return BORDER_WIDTH;
      case "border-bottom-color":
        return BORDER_BOTTOM_COLOR;
      default:
        return WIDTH;
    }
  }
}
