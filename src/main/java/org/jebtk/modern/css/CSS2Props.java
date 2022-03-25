package org.jebtk.modern.css;

import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;
import org.jebtk.core.CSSColor;
import org.jebtk.core.event.ChangeListeners;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.theme.ColorGradient;

public class CSS2Props extends ChangeListeners {

  private final CSSNumProp ZERO = new CSSNumProp(CSSPXNum.ZERO);
  private final CSSFontProp DEFAULT_FONT = new CSSFontProp(ModernWidget.FONT);

  private final Map<CSSPropName, CSSNumProp> mNumMap = new HashMap<>();
  private final Map<CSSPropName, CSSBaseColorProp> mColorMap = new HashMap<>();
  private final Map<CSSPropName, CSSFontProp> mFontMap = new HashMap<>();
  private final Map<CSSPropName, CSSColorGradientProp> mColorGradientMap = new HashMap<>();

  public CSS2Props setColor(CSSPropName name, CSSBaseColorProp c) {
    mColorMap.put(name, c);
    fireChanged();
    return this;
  }

  public CSSColor getColor(String name) {
    return getColor(name, CSSProp.FROM);
  }

  public CSSColor getColor(String name, int frame) {
    return getColor(CSSPropName.parse(name)).getKeyFrame(frame);
  }

  public CSSBaseColorProp getColor(CSSPropName name) {
    return mColorMap.getOrDefault(name, CSSColorProp.TRANS);
  }

  public ColorGradient getColorGradient(String name) {
    return ColorGradient(name, CSSProp.FROM);
  }

  public ColorGradient ColorGradient(String name, int frame) {
    return getColorGradient(CSSPropName.parse(name)).getKeyFrame(frame);
  }

  public CSSColorGradientProp getColorGradient(CSSPropName name) {
    return mColorGradientMap.getOrDefault(name, CSSColorGradientProp.BLACK_WHITE);
  }

  public CSSNumProp getNum(CSSPropName name) {
    return mNumMap.getOrDefault(name, ZERO);
  }

  public CSSFontProp getFont(CSSPropName name) {
    return mFontMap.getOrDefault(name, DEFAULT_FONT);
  }

  public CSS2Props set(CSSPropName name, int v) {
    return set(name, new CSSPXNum(v));
  }

  public CSS2Props set(CSSPropName name, CSSNum v) {
    return set(name, new CSSNumProp(v));
  }

  public CSS2Props set(CSSPropName name, CSSNumProp c) {
    mNumMap.put(name, c);
    fireChanged();
    return this;
  }

  public CSS2Props set(CSSPropName name, Color p) {
    return set(name, new CSSColor(p));
  }

  public CSS2Props set(CSSPropName name, CSSColor p) {
    return set(name, new CSSColorProp(p));
  }

  public CSS2Props set(CSSPropName name, CSSBaseColorProp c) {
    mColorMap.put(name, c);
    fireChanged();
    return this;
  }

  public CSS2Props set(CSSPropName name, Font p) {
    return set(name, new CSSFontProp(p));
  }

  public CSS2Props set(CSSPropName name, CSSFontProp c) {
    mFontMap.put(name, c);
    fireChanged();
    return this;
  }

  public CSS2Props update(CSS2Props props) {
    mNumMap.putAll(props.mNumMap);
    mColorMap.putAll(props.mColorMap);
    mFontMap.putAll(props.mFontMap);
    mColorGradientMap.putAll(props.mColorGradientMap);
    fireChanged();
    return this;
  }

  boolean hasColor(CSSPropName name) {
    return mColorMap.containsKey(name);
  }
  
  @Override
  public String toString() {
    return mColorMap.toString();
  }
}
