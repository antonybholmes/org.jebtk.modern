package org.jebtk.modern.button;

import org.jebtk.modern.dialog.ModernDialogButton;
import org.jebtk.modern.graphics.icons.ModernIcon;

public class ModernCircleButton extends ModernDialogButton {

  private static final long serialVersionUID = 1L;

  public ModernCircleButton(ModernIcon icon) {
    super(icon);

    // getDrawStates().set(DrawUIService.getInstance().getRenderer("css-circle"));
    // setAnimations("circle-fill");
    setButtonStyle(ButtonStyle.CIRCLE);
  }

  public ModernCircleButton(ModernIcon icon, String toolTipTitle, String toolTipText) {
    this(icon);

    setToolTip(toolTipTitle, toolTipText);
  }
}
