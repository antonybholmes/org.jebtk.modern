package org.jebtk.modern.panel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;

import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;
import org.jebtk.modern.css.CSSWidget;

// TODO: Auto-generated Javadoc
/**
 * Create a panel with a pill shaped border
 *
 * @author Antony Holmes
 *
 */
public class ModernPillBorderPanel extends CSSWidget {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new modern line border panel.
   */
  public ModernPillBorderPanel() {
    init();
  }

  /**
   * Instantiates a new modern line border panel.
   *
   * @param layout the layout
   */
  public ModernPillBorderPanel(LayoutManager layout) {
    super(layout);

    init();
  }

  /**
   * Instantiates a new modern line border panel.
   *
   * @param component the component
   */
  public ModernPillBorderPanel(Component component) {
    add(component, BorderLayout.CENTER);

    init();
  }

  /**
   * Instantiates a new modern line border panel.
   *
   * @param component the component
   * @param dimension the dimension
   */
  public ModernPillBorderPanel(Component component, Dimension dimension) {
    this(component);

    UI.setSize(this, dimension);

    init();
  }

  /**
   * Inits the.
   */
  private void init() {
    setBorder(ModernWidget.TWO_PIXEL_BORDER); // BorderService.getInstance().createBorder(2));

    addStyleClass("content", "content-outline", "pill");

    getAnimations().add("css-hover");
  }
}
