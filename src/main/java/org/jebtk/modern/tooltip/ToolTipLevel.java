package org.jebtk.modern.tooltip;

/**
 * Indicates how the tooltip processor should consider the event. Force implies
 * the processor should try to force the action. The normal level indicates that
 * the processor should try to carry out the task, but can override the choice.
 * For example, popups that do not auto hide, do not respond to hide events with
 * the normal level. Instead the tooltip must be created at a FORCE level.
 * 
 * @author Antony Holmes
 *
 */
public enum ToolTipLevel {
  NORMAL, FORCE
}
