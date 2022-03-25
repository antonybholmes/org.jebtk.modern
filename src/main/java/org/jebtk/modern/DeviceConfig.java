/**
 * Copyright 2017 Antony Holmes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jebtk.modern;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;

/**
 * The Class DeviceConfig.
 */
public class DeviceConfig {

  /** The device index. */
  public int deviceIndex = 0;

  /** The config index. */
  public int configIndex = 0;

  /** The device. */
  public GraphicsDevice device = null;

  /** The config. */
  public GraphicsConfiguration config = null;

  /**
   * Instantiates a new device config.
   *
   * @param i                     the i
   * @param j                     the j
   * @param graphicsDevice        the graphics device
   * @param graphicsConfiguration the graphics configuration
   */
  public DeviceConfig(int i, int j, GraphicsDevice graphicsDevice, GraphicsConfiguration graphicsConfiguration) {
    deviceIndex = i;
    configIndex = j;
    device = graphicsDevice;
    config = graphicsConfiguration;
  }
}