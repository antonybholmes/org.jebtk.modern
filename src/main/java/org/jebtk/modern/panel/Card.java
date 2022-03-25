package org.jebtk.modern.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Transparency;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.border.Border;

import org.jebtk.core.ColorUtils;
import org.jebtk.core.geom.IntDim;
import org.jebtk.modern.BorderService;
import org.jebtk.modern.ModernComponent;
import org.jebtk.modern.graphics.GaussianFilter;
import org.jebtk.modern.graphics.ImageUtils;

public class Card extends ModernComponent {

  private static final long serialVersionUID = 1L;

  public static final int ROUNDING = 6;

  private static final int V_SPACE = ROUNDING * 2;

  public static final int SHADOW_SIZE = 8;

  private static final int SHADOW_SIZE_2X = SHADOW_SIZE * 2;

  public static final int HALF_SHADOW_SIZE = SHADOW_SIZE / 2;

  public static final double ALPHA = 0.85;

  private static final Insets OFFSETS = new Insets(HALF_SHADOW_SIZE, HALF_SHADOW_SIZE, HALF_SHADOW_SIZE,
      HALF_SHADOW_SIZE);

  private static final IntDim SHADOW_PADDING = IntDim.create(OFFSETS.left + OFFSETS.right,
      OFFSETS.top + OFFSETS.bottom);

  private static final int IMAGE_SIZE = 200;

  private static final int SHADOW_WIDTH = IMAGE_SIZE / 2;

  private static final int CORNER_OFFSET = IMAGE_SIZE - SHADOW_SIZE;

  /**
   * Allow for space at the top so that items appear just below the rounded
   * corners rather than running into them.
   */
  private static final Border CARD_BORDER = BorderService.getInstance().createBorder(HALF_SHADOW_SIZE, HALF_SHADOW_SIZE,
      HALF_SHADOW_SIZE, HALF_SHADOW_SIZE);

  public static final Color COLOR = ColorUtils.getTransparentColor(Color.BLACK, Card.ALPHA);

  /**
   * Pick a point in the image that is not a corner so that we can clone a
   * straight edge section of the shadow
   */
  private static final int SHADOW_OFFSET = 50;

  private static BufferedImage C1;

  private static BufferedImage C2;

  private static BufferedImage C3;

  private static BufferedImage C4;

  private static BufferedImage TB;

  private static BufferedImage RB;

  private static BufferedImage BB;

  private static BufferedImage LB;

  // private BufferedImage mShadow;

  /*
   * private class ComponentEvents extends ComponentAdapter {
   * 
   * @Override public void componentResized(ComponentEvent e) { mShadow = null;
   * //mBackground = null; } }
   */

  static {
    //
    // Create images to make shadow
    //

    BufferedImage shadow = shadow(IMAGE_SIZE, IMAGE_SIZE);

    Graphics g;

    C1 = ImageUtils.createImage(SHADOW_SIZE);
    g = C1.getGraphics();
    g.drawImage(shadow, 0, 0, null);
    g.dispose();

    C2 = ImageUtils.createImage(SHADOW_SIZE);
    g = C2.getGraphics();
    g.drawImage(shadow, -CORNER_OFFSET, 0, null);
    g.dispose();

    C3 = ImageUtils.createImage(SHADOW_SIZE);
    g = C3.getGraphics();
    g.drawImage(shadow, -CORNER_OFFSET, -CORNER_OFFSET, null);
    g.dispose();

    C4 = ImageUtils.createImage(SHADOW_SIZE);
    g = C4.getGraphics();
    g.drawImage(shadow, 0, -CORNER_OFFSET, null);
    g.dispose();

    TB = ImageUtils.createImage(SHADOW_WIDTH, SHADOW_SIZE);
    g = TB.getGraphics();
    g.drawImage(shadow, -SHADOW_OFFSET, 0, null);
    g.dispose();

    RB = ImageUtils.createImage(SHADOW_SIZE, SHADOW_WIDTH);
    g = RB.getGraphics();
    g.drawImage(shadow, -CORNER_OFFSET, -SHADOW_OFFSET, null);
    g.dispose();

    BB = ImageUtils.createImage(SHADOW_WIDTH, SHADOW_SIZE);
    g = BB.getGraphics();
    g.drawImage(shadow, -SHADOW_OFFSET, -CORNER_OFFSET, null);
    g.dispose();

    LB = ImageUtils.createImage(SHADOW_SIZE, SHADOW_WIDTH);
    g = LB.getGraphics();
    g.drawImage(shadow, 0, -SHADOW_OFFSET, null);
    g.dispose();
  }

  public Card() {
    // addComponentListener(new ComponentEvents());

    setBorder(CARD_BORDER);
  }

  public Card(Component c) {
    this();

    setBody(c);
  }

  @Override
  public void setBody(Component c) {
    super.setBody(c);

    Dimension s = c.getPreferredSize();

    setPreferredSize(new Dimension(s.width + SHADOW_SIZE, s.height + SHADOW_SIZE + V_SPACE));
  }

  @Override
  public void drawBackground(Graphics2D g2) {
    int width = getWidth() - 1;
    int height = getHeight() - 1;

    /*
     * if (mShadow == null) { // Cache shadow as expensive operation to create
     * mShadow = shadow(width, height);
     * 
     * // Add the shadow background(mShadow); }
     * 
     * g2.drawImage(mShadow, 0, 0, this);
     */

    int w2 = width - SHADOW_SIZE_2X;
    int h2 = height - SHADOW_SIZE_2X;

    // Draw the borders
    g2.drawImage(TB, SHADOW_SIZE, 0, w2, SHADOW_SIZE, null);
    g2.drawImage(RB, width - SHADOW_SIZE, SHADOW_SIZE, SHADOW_SIZE, h2, null);
    g2.drawImage(BB, SHADOW_SIZE, height - SHADOW_SIZE, w2, SHADOW_SIZE, null);
    g2.drawImage(LB, 0, SHADOW_SIZE, SHADOW_SIZE, h2, null);

    // Draw the corners
    g2.drawImage(C1, 0, 0, null);
    g2.drawImage(C2, width - SHADOW_SIZE, 0, null);
    g2.drawImage(C3, width - SHADOW_SIZE, height - SHADOW_SIZE, null);
    g2.drawImage(C4, 0, height - SHADOW_SIZE, null);

    // Draw the content box

    Graphics2D g2Temp = ImageUtils.createAATextGraphics(g2);

    try {
      g2Temp.setColor(Color.WHITE);

      g2Temp.fillRoundRect(OFFSETS.left, OFFSETS.top, width - SHADOW_PADDING.w, height - SHADOW_PADDING.h, ROUNDING,
          ROUNDING);
    } finally {
      g2Temp.dispose();
    }
  }

  /*
   * public static GraphicsConfiguration getGraphicsConfiguration() { return
   * GraphicsEnvironment.getLocalGraphicsEnvironment()
   * .getDefaultScreenDevice().getDefaultConfiguration();
   * 
   * }
   */

  public static BufferedImage createCompatibleImage(int width, int height) {
    return createCompatibleImage(width, height, Transparency.TRANSLUCENT);
  }

  public static BufferedImage createCompatibleImage(int width, int height, int transparency) {

    return ImageUtils.createImage(width, height);

    /*
     * BufferedImage image = GraphicsEnvironment .getLocalGraphicsEnvironment()
     * .getDefaultScreenDevice() .getDefaultConfiguration()
     * .createCompatibleImage(width, height, transparency); image.coerceData(true);
     * return image;
     */

  }

  public static void background(BufferedImage img) {
    int width = img.getWidth();
    int height = img.getHeight();

    Rectangle bounds = new Rectangle();
    bounds.x = OFFSETS.left;
    bounds.y = OFFSETS.top;
    bounds.width = width - SHADOW_PADDING.w;
    bounds.height = height - SHADOW_PADDING.h;

    RoundRectangle2D shape = new RoundRectangle2D.Double(bounds.x, bounds.y, bounds.width, bounds.height, ROUNDING,
        ROUNDING);

    Graphics2D g2 = img.createGraphics();

    try {
      ImageUtils.setQualityHints(g2);

      // g2.setColor(Color.RED);
      // g2.draw(shape);
      g2.setColor(Color.WHITE);
      g2.fill(shape);
    } finally {
      g2.dispose();
    }
  }

  public static BufferedImage shadow(int width, int height) {
    BufferedImage img = createCompatibleImage(width, height);

    width -= OFFSETS.right + HALF_SHADOW_SIZE;
    height -= OFFSETS.bottom + HALF_SHADOW_SIZE;

    RoundRectangle2D shape = new RoundRectangle2D.Double(HALF_SHADOW_SIZE, HALF_SHADOW_SIZE, width, height, ROUNDING,
        ROUNDING);

    Graphics2D g2 = img.createGraphics();

    try {
      ImageUtils.setQualityHints(g2);
      g2.setColor(COLOR);
      // tg2.translate(-bounds.x, -bounds.y);
      g2.fill(shape);
    } finally {
      g2.dispose();
    }

    /*
     * //int border = size * 2;
     * 
     * int imgWidth = imgSource.getWidth(); // + border; int imgHeight =
     * imgSource.getHeight(); // + border;
     * 
     * BufferedImage imgMask = createCompatibleImage(imgWidth, imgHeight);
     * Graphics2D g2 = imgMask.createGraphics();
     * 
     * try { ImageUtils.setQualityHints(g2);
     * 
     * g2.drawImage(imgSource, 0, 0, null); } finally { g2.dispose(); }
     */

    return blur(img, SHADOW_SIZE); // imgMask; //blur(imgMask, size);
  }

  /**
   * Apply Gaussian blur to image to simulate diffuse shadow border.
   * 
   * @param img
   * @param size
   * @return
   */
  public static BufferedImage blur(BufferedImage img, int size) {
    /*
     * GaussianFilter filter = new GaussianFilter(size);
     * 
     * int imgWidth = imgSource.getWidth(); int imgHeight = imgSource.getHeight();
     * 
     * BufferedImage imgBlur = createCompatibleImage(imgWidth, imgHeight);
     * Graphics2D g2d = imgBlur.createGraphics();
     * 
     * try { ImageUtils.setQualityHints(g2d);
     * 
     * g2d.drawImage(imgSource, 0, 0, null);
     * g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN,
     * (float)alpha)); g2d.setColor(color);
     * 
     * g2d.fillRect(0, 0, imgSource.getWidth(), imgSource.getHeight()); } finally {
     * g2d.dispose(); }
     */

    return new GaussianFilter(size).filter(img, null);
  }
}