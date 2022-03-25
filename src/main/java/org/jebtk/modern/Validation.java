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
package org.jebtk.modern;

import java.util.Collection;

import org.jebtk.core.collections.CollectionUtils;
import org.jebtk.modern.dialog.MessageDialogType;
import org.jebtk.modern.dialog.ModernMessageDialog;
import org.jebtk.modern.window.ModernWindow;

/**
 * The class Validation.
 */
public class Validation {

  /**
   * Validate as int.
   *
   * @param name the name
   * @param text the text
   * @return the int
   * @throws ValidationException the validation exception
   */
  public static int validateAsInt(String name, String text) throws ValidationException {
    try {
      return Integer.parseInt(text);
    } catch (NumberFormatException e) {
      throw new ValidationException(name + " is not a valid integer.");
    }
  }

  /**
   * Validate as double.
   *
   * @param name the name
   * @param text the text
   * @return the double
   * @throws ValidationException the validation exception
   */
  public static double validateAsDouble(String name, String text) throws ValidationException {
    try {
      return Double.parseDouble(text);
    } catch (NumberFormatException e) {
      throw new ValidationException(name + " is not a valid number.");
    }
  }

  /**
   * Validates that the text field is a number between min and max inclusive.
   *
   * @param name the name
   * @param text the text
   * @param min  the min
   * @param max  the max
   * @return the double
   * @throws ValidationException the validation exception
   */
  public static double validateAsDouble(String name, String text, double min, double max) throws ValidationException {
    double ret = 0;

    ValidationException ex = new ValidationException(name + " must be a number between " + min + " and " + max + ".");

    try {
      ret = Double.parseDouble(text);
    } catch (NumberFormatException e) {
      throw ex;
    }

    if (ret < min || ret > max) {
      throw ex;
    }

    return ret;
  }

  /**
   * Show validation error.
   *
   * @param parent the parent
   * @param e      the e
   */
  public static void showValidationError(ModernWindow parent, ValidationException e) {
    ModernMessageDialog.createDialog(parent, e.getMessage(), MessageDialogType.WARNING);
  }

  /**
   * Validates that a user made a selection.
   *
   * @param <T>       the generic type
   * @param name      the name
   * @param selection the selection
   * @throws ValidationException the validation exception
   */
  public static <T> void validateSelection(String name, Collection<T> selection) throws ValidationException {
    if (CollectionUtils.isNullOrEmpty(selection)) {
      String message = "You must select some " + name + ".";

      throw new ValidationException(message);
    }
  }

}
