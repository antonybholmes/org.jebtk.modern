/**
 * Copyright (C) 2016, Antony Holmes
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *  1. Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *  3. Neither the name of copyright holder nor the names of its contributors 
 *     may be used to endorse or promote products derived from this software 
 *     without specific prior written permission. 
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 */
package org.jebtk.modern.theme;

import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.Map;

import org.jebtk.core.collections.DefaultHashMap;
import org.jebtk.core.collections.HashMapCreator;
import org.jebtk.core.collections.IterHashMap;
import org.jebtk.core.collections.IterMap;
import org.jebtk.modern.css.CSSBackgroundUI;
import org.jebtk.modern.css.CSSDrawUI;
import org.jebtk.modern.css.CSSPillContentUI;

/**
 * Provides the shared renderer to components. This is the default way to
 * control component look and feel and to ensure they share renderers as much as
 * possible.
 * 
 * @author Antony Holmes
 *
 */
public final class DrawUIService implements Iterable<String> {

  /**
   * The Class WidgetRendererServiceLoader.
   */
  private static class UIRendererServiceLoader {

    /** The Constant INSTANCE. */
    private static final DrawUIService INSTANCE = new DrawUIService();
  }

  /**
   * Gets the single instance of SettingsService.
   *
   * @return single instance of SettingsService
   */
  public static DrawUIService getInstance() {
    return UIRendererServiceLoader.INSTANCE;
  }

  private final IterMap<String, DrawUI> mRenderMap = new IterHashMap<>();

  private final Map<String, IterMap<String, BufferedImage>> mImageMap = DefaultHashMap
      .create(new HashMapCreator<>());

  private DrawUIService() {
    // Do nothing

    add(new CardUI());

    add(new ContentUI());
    add(new ContentBoxUI());
    add(new ContentOutlineUI());
    // add(new TextBorderUI());
    add(new ButtonDrawUI());
    add(new ButtonFillUI());

    add(new CSSBackgroundUI());
    add(new CSSDrawUI());

    // add(new ButtonSelectedUI());
    add(new CircleOutlineUI());
    add(new CircleFillUI());
    // add(new MenuHighlightUI());
    add(new PillFillUI());
    add(new PillOutlineUI());
    add(new CSSPillContentUI());
    add(new CheckUI());
    add(new CheckedBoxUI());

    add("checkbox", getRenderer("content-box"));

    add(new ColorButtonHighlightUI());
    add(new ColorButtonSelectedUI());
    add(new ColorCircleHighlightUI());

    add(new DialogButtonUI());
    add(new ColorDialogButtonUI());
    add(new ButtonOutlineUI());
    // add(new DUI());

    add(new RadioUI());
    add(new RadioSelectedUI());

    add(new DialogButtonHighlightUI());

    add(new FillUI());
  }

  public void add(DrawUI renderer) {
    add(renderer.getName(), renderer);
  }

  public void add(String name, DrawUI renderer) {
    mRenderMap.put(name, renderer);
  }

  public void add(String group, int id, BufferedImage image) {
    add(group, Integer.toString(id), image);
  }

  public void add(String group, String name, BufferedImage image) {
    mImageMap.get(group).put(name, image);
  }

  /**
   * Return a named UI rendering component.
   * 
   * @param name
   * @return
   */
  public DrawUI getRenderer(String name) {
    return mRenderMap.get(name);
  }

  public BufferedImage getImage(String group, int id) {
    return getImage(group, Integer.toString(id));
  }

  public BufferedImage getImage(String group, String name) {
    return mImageMap.get(group).get(name);
  }

  @Override
  public Iterator<String> iterator() {
    return mRenderMap.keySet().iterator();
  }
}
