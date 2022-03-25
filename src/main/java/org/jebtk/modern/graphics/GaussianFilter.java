package org.jebtk.modern.graphics;

/*
 ** Copyright 2005 Huxtable.com. All rights reserved.
 */

import java.awt.image.BufferedImage;
import java.awt.image.Kernel;

/**
 * A filter which applies Gaussian blur to an image. This is a subclass of
 * ConvolveFilter which simply creates a kernel with a Gaussian distribution for
 * blurring.
 * 
 * @author Jerry Huxtable
 */
public class GaussianFilter extends ConvolveFilter {

  static final long serialVersionUID = 5377089073023183684L;

  protected float radius;
  protected Kernel kernel;

  /**
   * Construct a Gaussian filter
   */
  public GaussianFilter() {
    this(2);
  }

  /**
   * Construct a Gaussian filter
   * 
   * @param radius blur radius in pixels
   */
  public GaussianFilter(float radius) {
    setRadius(radius);
  }

  /**
   * Set the radius of the kernel, and hence the amount of blur. The bigger the
   * radius, the longer this filter will take.
   * 
   * @param radius the radius of the blur in pixels.
   */
  public void setRadius(float radius) {
    this.radius = radius;
    kernel = makeKernel(radius);
  }

  /**
   * Get the radius of the kernel.
   * 
   * @return the radius
   */
  public float getRadius() {
    return radius;
  }

  public BufferedImage filter(BufferedImage src, BufferedImage dst) {
    int width = src.getWidth();
    int height = src.getHeight();

    if (dst == null)
      dst = createCompatibleDestImage(src, null);

    int s = width * height;

    int[] inPixels = new int[s];
    int[] outPixels = new int[s];
    src.getRGB(0, 0, width, height, inPixels, 0, width);

    convolveAndTranspose(kernel, inPixels, outPixels, width, height, alpha, CLAMP_EDGES);
    convolveAndTranspose(kernel, outPixels, inPixels, height, width, alpha, CLAMP_EDGES);

    dst.setRGB(0, 0, width, height, inPixels, 0, width);
    return dst;
  }

  @Override
  public String toString() {
    return "Blur/Gaussian Blur...";
  }

  public static void convolveAndTranspose(Kernel kernel, int[] inPixels, int[] outPixels, int width, int height,
      boolean alpha, int edgeAction) {
    float[] matrix = kernel.getKernelData(null);
    int cols = kernel.getWidth();
    int cols2 = cols / 2;

    for (int y = 0; y < height; y++) {
      int index = y;
      int ioffset = y * width;

      for (int x = 0; x < width; x++) {
        float r = 0, g = 0, b = 0, a = 0;
        int moffset = cols2;

        for (int col = -cols2; col <= cols2; col++) {
          float f = matrix[moffset + col];

          if (f != 0) {
            int ix = x + col;
            if (ix < 0) {
              if (edgeAction == CLAMP_EDGES) {
                ix = 0;
              } else if (edgeAction == WRAP_EDGES) {
                ix = (x + width) % width;
              }
            } else if (ix >= width) {
              if (edgeAction == CLAMP_EDGES) {
                ix = width - 1;
              } else if (edgeAction == WRAP_EDGES) {
                ix = (x + width) % width;
              }
            }

            int rgb = inPixels[ioffset + ix];
            a += f * ((rgb >> 24) & 0xff);
            r += f * ((rgb >> 16) & 0xff);
            g += f * ((rgb >> 8) & 0xff);
            b += f * (rgb & 0xff);
          }
        }

        int ia = alpha ? ImageUtils.clamp((int) (a + 0.5)) : 0xff;
        int ir = ImageUtils.clamp((int) (r + 0.5));
        int ig = ImageUtils.clamp((int) (g + 0.5));
        int ib = ImageUtils.clamp((int) (b + 0.5));
        outPixels[index] = (ia << 24) | (ir << 16) | (ig << 8) | ib;
        index += height;
      }
    }
  }

}