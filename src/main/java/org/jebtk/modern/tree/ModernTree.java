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
package org.jebtk.modern.tree;

import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import org.jebtk.core.Indexed;
import org.jebtk.core.Mathematics;
import org.jebtk.core.collections.CollectionUtils;
import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.path.Path;
import org.jebtk.core.tree.TreeNode;
import org.jebtk.core.tree.TreeNodeEventListeners;
import org.jebtk.core.tree.TreeNodeEventProducer;
import org.jebtk.core.tree.TreePath;
import org.jebtk.core.tree.TreeRootNode;
import org.jebtk.modern.SelectionPolicy;
import org.jebtk.modern.SelectionRangeModel;
import org.jebtk.modern.UI;
import org.jebtk.modern.event.HighlightEvent;
import org.jebtk.modern.event.HighlightEventProducer;
import org.jebtk.modern.event.HighlightListener;
import org.jebtk.modern.event.HighlightListeners;
import org.jebtk.modern.event.ModernSelectionListener;
import org.jebtk.modern.event.ModernSelectionListeners;
import org.jebtk.modern.graphics.DrawingContext;
import org.jebtk.modern.graphics.ImageUtils;
import org.jebtk.core.tree.ITreeNodeEventListener;

/**
 * Display objects in a graphical tree.
 *
 * @author Antony Holmes
 * @param <T> the generic type
 */
public class ModernTree<T> extends Tree<T>
    implements TreeNodeEventProducer, ITreeNodeEventListener, HighlightEventProducer {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * If the user is within this number of pixels from the edge of the node, assume
   * they want to insert between rather than making the node a child of another.
   */
  private static final int INSERT_ZONE_SIZE = 5;

  /** The Constant CTRL_MASK. */
  private static final int CTRL_MASK = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

  /**
   * The member listeners.
   */
  private TreeNodeEventListeners mListeners = new TreeNodeEventListeners();

  /**
   * The member node renderer.
   */
  protected ModernTreeNodeRenderer mNodeRenderer = new TreeNodeFileCountRenderer();

  /**
   * The member node insert renderer.
   */
  private ModernTreeNodeInsertRenderer mNodeInsertRenderer = new ModernTreeNodeInsertLineRenderer();

  /**
   * The member root.
   */
  protected TreeRootNode<T> mRoot = null;

  /**
   * The member flat node list.
   */
  // Keeps track of the currently visible nodes
  protected List<TreeNode<T>> mFlatNodeList = null; // >new
  // ArrayList<TreeNode<T>>();

  /**
   * The member selection model.
   */
  protected SelectionRangeModel mSelectionModel = new SelectionRangeModel();

  /**
   * The member highlight node.
   */
  protected TreeNode<T> mHighlightNode = null;

  /**
   * The member drag to node.
   */
  private TreeNode<T> mDragToNode = null;

  /**
   * The member target node.
   */
  private TreeNode<T> mTargetNode = null;

  /**
   * The member parent drag to node.
   */
  private TreeNode<T> mParentDragToNode = null;

  /**
   * The member selection policy.
   */
  private SelectionPolicy mSelectionPolicy = SelectionPolicy.MULTIPLE;

  // private boolean multiSelect = false;

  // private boolean multiRangeSelect = false;

  /**
   * The member node index map.
   */
  protected Map<TreeNode<T>, Integer> mNodeIndexMap = new HashMap<TreeNode<T>, Integer>();

  /**
   * The member node depth map.
   */
  protected Map<TreeNode<T>, Integer> mNodeDepthMap = new HashMap<TreeNode<T>, Integer>();

  /**
   * The member tree event listeners.
   */
  private TreeEventListeners mTreeEventListeners = new TreeEventListeners();

  /**
   * The member selection listeners.
   */
  private ModernSelectionListeners mSelectionListeners = new ModernSelectionListeners();

  protected HighlightListeners mHighlightListeners = new HighlightListeners();

  /** The m allow multi select. */
  private boolean mAllowMultiSelect = true;

  /**
   * The member highlight node index.
   */
  private int mHighlightNodeIndex = -1;

  /**
   * The member drag to.
   */
  protected TreeNodeMove mDragTo;

  /**
   * The member drag enabled.
   */
  private boolean mDragEnabled = false;

  /**
   * The class SelectionEvents.
   */
  private class SelectionEvents implements ModernSelectionListener {

    @Override
    public void selectionAdded(ChangeEvent e) {
      repaint();

      mSelectionListeners.fireSelectionAdded(new ChangeEvent(this, e.getMessage()));
    }

    @Override
    public void selectionRemoved(ChangeEvent e) {
      repaint();

      mSelectionListeners.fireSelectionRemoved(new ChangeEvent(this, e.getMessage()));
    }
  }

  /**
   * The class MouseEvents.
   */
  private class MouseEvents extends MouseAdapter {
    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseClicked(MouseEvent e) {
      if (e.isPopupTrigger()) {
        return;
      }

      if (e.getClickCount() > 1) {
        mTreeEventListeners.fireTreeNodeDoubleClicked(new ModernTreeEvent(this, NODE_DOUBLE_CLICKED));
      }

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mouseExited(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseExited(MouseEvent e) {
      reset();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
     */
    @Override
    public void mousePressed(MouseEvent e) {
      if (e.isPopupTrigger() || e.getClickCount() > 1) {
        return;
      }

      TreeNodeMove move = getSelectedIndexFromY(e.getY());

      if (move == null || move.index == mFlatNodeList.size()) {
        // clear selection if we click outside the nodes i.e in an
        // empty space
        mSelectionModel.clear();

        return;
      }

      boolean multiSelect = mAllowMultiSelect && (e.getModifiers() & CTRL_MASK) == CTRL_MASK;

      boolean multiRangeSelect = mAllowMultiSelect
          && (e.getModifiers() & ActionEvent.SHIFT_MASK) == ActionEvent.SHIFT_MASK;

      adjustSelectedNodes(move.index, multiSelect, multiRangeSelect);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseReleased(MouseEvent e) {
      setCursor(UI.DEFAULT_CURSOR);

      if (e.isPopupTrigger() || e.getClickCount() > 1) {
        return;
      }

      // System.err.println("what to " + (mDragToNode != null) + " " +
      // mSelectionModel.size() + " " + (mDragTo != null));

      if (mDragToNode != null && mSelectionModel.size() > 0 && mDragTo != null) {

        mTargetNode = mDragToNode;

        if (mDragTo.index == mFlatNodeList.size()) {
          // Add at end of tree
          List<TreeNode<T>> selectedNodes = getSelectedNodes();

          for (TreeNode<T> node : selectedNodes) {
            node.getParent().removeChild(node);
          }

          for (TreeNode<T> node : selectedNodes) {
            mRoot.addChild(node);
          }
        } else if (mDragTo.insertBetween) {
          // Add the list in reverse order since we are inserting at an index
          // that will cause the index of each element to increase by one,
          // therefore elements must be added in reverse so that the last
          // element is shifted the most, so the inserts appear in order
          for (TreeNode<T> node : CollectionUtils.reverse(getSelectedNodes())) {
            if (!mParentDragToNode.equals(node) && !mTargetNode.equals(node)) {

              // Remove node from parent
              node.getParent().removeChild(node);

              // add not to target
              mParentDragToNode.addChildBefore(mTargetNode, node);
            }
          }
        } else {
          for (TreeNode<T> node : getSelectedNodes()) {
            // A node cannot be added to itself nor to its children
            if (!mTargetNode.equals(node) && !mTargetNode.getParent().equals(node)) {
              // Remove node from parent
              node.getParent().removeChild(node);

              // add not to target
              mTargetNode.addChild(node);
            }
          }
        }

        reset();

        mTreeEventListeners.fireTreeNodeDragged(new ModernTreeEvent(this, NODE_DRAGGED));
      }

      // Trigger click events

      int si = mSelectionModel.first();

      if (si >= 0) {
        TreeNode<T> selectedNode = mFlatNodeList.get(si);

        boolean inCollapeRegion = inCollapseRegion(e.getX(), selectedNode);

        if (selectedNode.isSelectable() && selectedNode.isParent() && selectedNode.isExpandable() && inCollapeRegion) {

          // Toggle between expanded and collapsed state
          selectedNode.setExpanded(!selectedNode.isExpanded());

          // organize();

          // Since organizing clears the selection, set the selection
          // to what we were just on
          mSelectionModel.setSelection(si);
        }

        if (inCollapeRegion) {
          mTreeEventListeners.fireTreeNodeClicked(new ModernTreeEvent(this, NODE_COLLAPSE_REGION_CLICKED));
        } else {
          mTreeEventListeners.fireTreeNodeClicked(new ModernTreeEvent(this, NODE_CLICKED));
        }
      }

    }
  }

  /**
   * The class MouseMoveEvents.
   */
  private class MouseMoveEvents implements MouseMotionListener {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.
     * MouseEvent)
     */
    @Override
    public void mouseDragged(MouseEvent e) {
      if (!mDragEnabled || e.getClickCount() > 1) {
        return;
      }

      TreeNodeMove move = getSelectedIndexFromY(e.getY());

      if (move == null) {
        return;
      }

      if (move.index == mFlatNodeList.size()) {
        mParentDragToNode = mRoot;
        mDragToNode = mRoot;
      } else {
        mParentDragToNode = mFlatNodeList.get(move.index).getParent();
        mDragToNode = mFlatNodeList.get(move.index);
      }

      // System.err.println("move me " + mParentDragToNode.getName() + " " +
      // mDragToNode.getName());

      mDragTo = move;

      setCursor(UI.HAND_CURSOR);

      repaint();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseMoved(MouseEvent e) {
      TreeNodeMove move = getSelectedIndexFromY(e.getY());

      if (move == null || move.index == mFlatNodeList.size() || move.index == mHighlightNodeIndex) {
        return;
      }

      mHighlightNode = mFlatNodeList.get(move.index);
      mHighlightNodeIndex = move.index;

      // System.err.println("sdfsd " + mHighlightNode + " " +
      // mHighlightNodeIndex);

      fireHighlighted();

      // repaint();
    }
  }

  /**
   * The Class KeyEvents.
   */
  private class KeyEvents implements KeyListener {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
     */
    @Override
    public void keyPressed(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_UP) {
        if (e.isControlDown()) {
          selectPrevious();
        } else {
          movePrevious();
        }
      } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
        if (e.isControlDown()) {
          selectNext();
        } else {
          moveNext();
        }
      } else {

      }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
     */
    @Override
    public void keyReleased(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
        mTreeEventListeners.fireTreeNodeClicked(new ModernTreeEvent(this, NODE_CLICKED));
      }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
     */
    @Override
    public void keyTyped(KeyEvent e) {
      // TODO Auto-generated method stub

    }

  }

  /**
   * Instantiates a new modern tree.
   */
  public ModernTree() {
    setup();
  }

  /**
   * Instantiates a new modern tree.
   *
   * @param renderer the renderer
   */
  public ModernTree(ModernTreeNodeRenderer renderer) {
    setup();

    setNodeRenderer(renderer);
  }

  /**
   * Setup.
   */
  private void setup() {
    addMouseListener(new MouseEvents());

    addMouseMotionListener(new MouseMoveEvents());

    // addKeyListener(new KeyEvents());

    getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(
        KeyStroke.getKeyStroke(KeyEvent.VK_A, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()), "ctrl_a_pressed");
    getActionMap().put("ctrl_a_pressed", new AbstractAction() {
      private static final long serialVersionUID = 1L;

      @Override
      public void actionPerformed(ActionEvent e) {
        selectAll();
      }
    });

    getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(
        KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()),
        "tree_ctrl_down_pressed");
    getActionMap().put("tree_ctrl_down_pressed", new AbstractAction() {
      private static final long serialVersionUID = 1L;

      @Override
      public void actionPerformed(ActionEvent e) {
        selectNext();
      }
    });

    getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(
        KeyStroke.getKeyStroke(KeyEvent.VK_UP, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()),
        "tree_ctrl_up_pressed");
    getActionMap().put("tree_ctrl_up_pressed", new AbstractAction() {
      private static final long serialVersionUID = 1L;

      @Override
      public void actionPerformed(ActionEvent e) {
        selectPrevious();
      }
    });

    getInputMap(WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "tree_down_pressed");
    getActionMap().put("tree_down_pressed", new AbstractAction() {
      private static final long serialVersionUID = 1L;

      @Override
      public void actionPerformed(ActionEvent e) {
        moveNext();
      }
    });

    getInputMap(WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "tree_up_pressed");
    getActionMap().put("tree_up_pressed", new AbstractAction() {
      private static final long serialVersionUID = 1L;

      @Override
      public void actionPerformed(ActionEvent e) {
        movePrevious();
      }
    });

    /*
     * getInputMap(WHEN_FOCUSED).put(KeyStroke.getKeyStroke("released UP"),
     * "up_released"); getActionMap().put("up_released", new AbstractAction() {
     * private static final long serialVersionUID = 1L;
     * 
     * @Override public void actionPerformed(ActionEvent e) {
     * mTreeEventListeners.fireTreeNodeClicked(new ModernTreeEvent(this,
     * NODE_CLICKED)); } });
     * 
     * getInputMap(WHEN_FOCUSED).put(KeyStroke.getKeyStroke("released DOWN"),
     * "down_released"); getActionMap().put("down_released", new AbstractAction() {
     * private static final long serialVersionUID = 1L;
     * 
     * @Override public void actionPerformed(ActionEvent e) {
     * mTreeEventListeners.fireTreeNodeClicked(new ModernTreeEvent(this,
     * NODE_CLICKED)); } });
     */

    mSelectionModel.addSelectionListener(new SelectionEvents());

    // Set a default root
    setRoot(new TreeRootNode<T>());

    setAnimations("tree");
  }

  /**
   * Control whether tree allows multiple node selection.
   *
   * @param allow the new allow multi select
   */
  public void setAllowMultiSelect(boolean allow) {
    mAllowMultiSelect = allow;
  }

  /**
   * Sets the drag enabled.
   *
   * @param dragEnabled the new drag enabled
   */
  public void setDragEnabled(boolean dragEnabled) {
    mDragEnabled = dragEnabled;
  }

  /**
   * Set how the user can select nodes.
   *
   * @param selectionPolicy the new node selection policy
   */
  public final void setNodeSelectionPolicy(SelectionPolicy selectionPolicy) {
    mSelectionPolicy = selectionPolicy;
  }

  /**
   * Set the renderer for displaying nodes.
   *
   * @param nodeRenderer the new node renderer
   */
  public void setNodeRenderer(ModernTreeNodeRenderer nodeRenderer) {
    mNodeRenderer = nodeRenderer;

    // repaint();
    // fireCanvasChanged();

    // Since the renderer has changed, it may also affect the size
    // of the tree
    organize();
  }

  /**
   * Return the node renderer.
   * 
   * @return The node renderer.
   */
  public ModernTreeNodeRenderer getNodeRenderer() {
    return mNodeRenderer;
  }

  /**
   * Return the node renderer customized for a given node.
   * 
   * @param i The index of a node in the flattened tree hierarchy.
   * @return The node renderer.
   */
  public ModernTreeNodeRenderer getNodeRenderer(int i) {
    return mNodeRenderer.getRenderer(this, mFlatNodeList.get(i), false, false, false, false, 0, i);
  }

  /**
   * Returns the cumulative height of the tree up to a given node. Nodes are
   * allowed to have different heights, so it is only always appropriate to simply
   * mulitply the row by a fixed height.
   * 
   * @param row A row in the flattened tree.
   * @return The distance to the start of this node.
   */
  public int getCumOffset(int row) {
    int ret = 0;

    for (int i = 0; i < row; ++i) {
      ret += getNodeRenderer(i).getHeight();
    }

    return ret;
  }

  /**
   * Set the root of the tree.
   *
   * @return the root
   */
  /*
   * public void setRoot(FlatTreeNode root) { this.root = root;
   * 
   * organiseTree(); }
   */

  public TreeRootNode<T> getRoot() {
    return mRoot;
  }

  /**
   * Sets the root.
   *
   * @param node the new root
   */
  public void setRoot(TreeNode<T> node) {
    TreeRootNode<T> root = new TreeRootNode<>();

    root.addChild(node);

    setRoot(root);
  }

  /**
   * Sets the root.
   *
   * @param root the new root
   */
  public void setRoot(TreeRootNode<T> root) {
    mRoot = root;

    root.addTreeNodeListener(this);

    // organize();

    // Force the tree to recreate itself
    root.fireTreeNodeChanged();
  }

  /**
   * Creates the UI for the tree.
   */
  protected void organize() {

    /*
     * mFlatNodeList.clear();
     * 
     * int depth = 0;
     * 
     * for (TreeNode<T> child : mRoot) { depth += flatten(child, 0); }
     * 
     * 
     * 
     * setCanvasSize(getWidth(), depth);
     */

    flatten();

    mSelectionModel.clear();
    reset();

    // System.err.println("organize " + depth);
  }

  /**
   * Flatten.
   */
  protected void flatten() {
    mFlatNodeList = new ArrayList<TreeNode<T>>(1000);

    Deque<Indexed<Integer, TreeNode<T>>> stack = new ArrayDeque<Indexed<Integer, TreeNode<T>>>();

    for (TreeNode<T> child : mRoot.reversed()) {
      stack.push(Indexed.createInt(0, child));
    }

    int depth = 0;
    int height = 0;
    TreeNode<T> node;

    while (!stack.isEmpty()) {
      Indexed<Integer, TreeNode<T>> iv = stack.pop();

      depth = iv.getIndex();
      node = iv.getValue();

      if (node.isVisible()) {
        mNodeIndexMap.put(node, mFlatNodeList.size());

        mNodeDepthMap.put(node, depth);

        mFlatNodeList.add(node);

        ModernTreeNodeRenderer renderer = mNodeRenderer.getRenderer(this, node, false, false, false, false, depth, -1);

        height += renderer.getHeight();

        if (node.isExpanded()) {
          for (TreeNode<T> child : node.reversed()) {
            stack.push(Indexed.createInt(depth + 1, child));
          }
        }
      }
    }

    setCanvasSize(getWidth(), height);
  }

  /**
   * Creates a list of the visible cells in the tree for drawing purposes and
   * correctly estimating the tree height.
   *
   * @return the int
   */
  /*
   * protected int flatten(TreeNode<T> node, int depth) { if (!node.isVisible()) {
   * return 0; }
   * 
   * mNodeIndexMap.put(node, mFlatNodeList.size());
   * 
   * mNodeDepthMap.put(node, depth);
   * 
   * mFlatNodeList.add(node);
   * 
   * ModernTreeNodeRenderer<T> renderer = mNodeRenderer.getRenderer(this, node,
   * false, false, false, false, depth, -1);
   * 
   * int height = renderer.getHeight();
   * 
   * if (!node.isExpanded()) { return height; }
   * 
   * // Add the height of the children
   * 
   * for (TreeNode<T> child : node) { height += flatten(child, depth + 1); }
   * 
   * return height; }
   */

  /**
   * Returns the visible tree nodes in a flattened state.
   *
   * @return the flattened tree
   */
  public List<TreeNode<T>> getFlattenedTree() {
    return Collections.unmodifiableList(mFlatNodeList);
  }

  /*
   * protected void calculateViewRectangle() { getViewRect()angle.x =
   * getViewRect().getX(); getViewRect()angle.y = getViewRect().getY();
   * 
   * getViewRect()angle.width = getWidth(); getViewRect()angle.height =
   * getHeight(); }
   */

  /*
   * @Override public void setViewRectangle(int x, int y) {
   * super.setViewRectangle(x, y);
   * 
   * calculateViewRectangle(); }
   */

  /**
   * Returns the first selected node, if one exists, otherwise null.
   *
   * @return the selected node
   */
  public TreeNode<T> getSelectedNode() {
    if (mSelectionModel.size() == 0) {
      return null;
    }

    return mFlatNodeList.get(mSelectionModel.first());
  }

  /**
   * Gets the selected node.
   *
   * @param i the i
   * @return the selected node
   */
  public TreeNode<T> getSelectedNode(int i) {
    return mFlatNodeList.get(i);
  }

  /**
   * This stores the node that was dragged somewhere.
   *
   * @return the target node
   */
  // public final TreeNode<T> getDraggedNode() {
  // return mDraggedNode;
  // }

  /**
   * Returns the parent where the dragged node should be added.
   *
   * @return
   */
  public final TreeNode<T> getTargetNode() {
    return mTargetNode;
  }

  /*
   * @Override public void drawWidgetBackground(Graphics2D g2) { fill(g2,
   * Color.RED); }
   */

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.graphics.ModernCanvas#drawCanvasForeground(java.awt.
   * Graphics2D)
   */
  /*
   * public void drawBackgroundAA(Graphics2D g2) { if (mSelectionModel == null ||
   * mNodeIndexMap == null || mNodeDepthMap == null || mNodeRenderer == null) {
   * return; }
   * 
   * ModernTreeNodeRenderer<T> renderer;
   * 
   * Graphics2D g2Temp = ImageUtils.clone(g2);
   * 
   * // account for insets etc //g2Temp.translate(mInternalRect.getX(),
   * mInternalRect.getY());
   * 
   * int y = 0; int h = 0;
   * 
   * int y1 = getVisibleRect().y; int maxY = y1 + mInternalRect.getH();
   * 
   * int c = 0;
   * 
   * try { for (TreeNode<T> node : mFlatNodeList) { // Speed up so we don't plot
   * more nodes than can // be seen on screen if (y > maxY) { break; }
   * 
   * boolean isDragToNode = mDragTo != null && mDragTo.index == c &&
   * !mDragTo.insertBetween;
   * 
   * renderer = mNodeRenderer.getRenderer(this, node, node.equals(mHighlightNode)
   * || isDragToNode, mSelectionModel.contains(mNodeIndexMap.get(node)),
   * isFocusOwner(), isDragToNode, mNodeDepthMap.get(node), c);
   * 
   * h = renderer.getHeight();
   * 
   * // Skip nodes until we encounter one in the // view space if (y >= y1 || y +
   * h >= y1) { renderer.print(g2Temp); }
   * 
   * g2Temp.translate(0, h);
   * 
   * y += h;
   * 
   * ++c; } } finally { g2Temp.dispose(); } }
   */

  @Override
  public void rasterCanvas(Graphics2D g2, DrawingContext context) {
    if (mDragEnabled && mDragTo != null && mDragTo.insertBetween) {

      g2 = ImageUtils.clone(g2);

      try {
        g2.translate(0, mDragTo.y);

        // System.err.println("d " + mDragTo.d + " " + mDragTo.index + " " +
        // mDragTo.y);

        mNodeInsertRenderer.getRenderer(this, mDragTo.d, mDragTo.index).print(g2);
      } finally {
        g2.dispose();
      }
    }
  }

  /**
   * Returns a list of the currently selected nodes.
   *
   * @return the selected nodes
   */
  public List<TreeNode<T>> getSelectedNodes() {
    List<TreeNode<T>> nodes = new ArrayList<TreeNode<T>>(100);

    for (int i : mSelectionModel) {
      nodes.add(mFlatNodeList.get(i));
    }

    return nodes;
  }

  /**
   * Returns all the non-null values of the selected nodes.
   *
   * @return the selected values
   */
  public List<T> getSelectedValues() {
    List<TreeNode<T>> nodes = getSelectedNodes();

    List<T> ret = new ArrayList<T>();

    for (TreeNode<T> node : nodes) {
      T v = node.getValue();

      if (v != null) {
        ret.add(v);
      }
    }

    return ret;
  }

  /**
   * Gets the selected index.
   *
   * @return the selected index
   */
  public int getSelectedIndex() {
    // Selected index must be within current bounds. If it is expired,
    // pick the closest node, with the assumption that the error is
    // caused by a node deletion.
    return Mathematics.bound(mSelectionModel.getCurrent(), 0, mFlatNodeList.size() - 1);
  }

  /**
   * Returns a list of the currently selected nodes.
   *
   * @return the selected indexes
   */
  public Iterable<Integer> getSelectedIndexes() {
    return mSelectionModel;
  }

  /**
   * Determine whether the mouse is in the region that can cause a node to
   * collapse.
   *
   * @param x            the x
   * @param selectedNode the selected node
   * @return true, if successful
   */
  private boolean inCollapseRegion(int x, TreeNode<T> selectedNode) {
    ModernTreeNodeRenderer r = mNodeRenderer.getRenderer(this, selectedNode, false, false, false, false,
        getDepth(selectedNode), -1);

    int x1 = r.getCumulativeXDepthOffset();
    int x2 = x1 + r.getCollapseRegionWidth();

    return x >= x1 && x < x2;
  }

  /**
   * Adjust selected nodes.
   *
   * @param index            the index
   * @param multiSelect      the multi select
   * @param multiRangeSelect the multi range select
   */
  private void adjustSelectedNodes(int index, boolean multiSelect, boolean multiRangeSelect) {
    if (index == -1) {
      return;
    }

    // if (!multiSelect && !multiRangeSelect && mSelectionModel.contains(index))
    // {
    // return;
    // }

    if ((!multiSelect && !multiRangeSelect) || mSelectionPolicy != SelectionPolicy.MULTIPLE) {
      mSelectionModel.removeAll();
    }

    if (multiSelect) {
      if (mFlatNodeList.get(index).isSelectable()) {
        if (mSelectionModel.contains(index)) {
          // If we are multi selecting and pick something we have
          // already selected, then remove it.

          mSelectionModel.remove(index);
        } else {
          mSelectionModel.add(index);
        }
      }
    } else if (multiRangeSelect && mSelectionModel.size() > 0) {
      // if there is a range between the first and the last
      // selected cells and the user has selected multi range,
      // determine the first and last row of the selection
      // and add all of the intermediate rows to the selection.

      // System.err.println("tree multi range");

      int minRow = Math.min(mSelectionModel.first(), index);

      int maxRow = Math.max(mSelectionModel.first(), index);

      List<Integer> indices = new ArrayList<Integer>();

      // since we are adding intermediates, we do not
      // add the current first and last in duplicate,
      // hence the index begins one past the min and
      // ends one before the max
      for (int i = minRow; i <= maxRow; ++i) {
        if (!mFlatNodeList.get(i).isSelectable()) {
          continue;
        }

        indices.add(i);
      }

      mSelectionModel.setSelection(indices);
    } else {
      if (mFlatNodeList.get(index).isSelectable()) {
        mSelectionModel.add(index);
      }
    }

    repaint();
  }

  /**
   * Returns the drag movement requested by the user. This will either take the
   * form of dragging a node to be a child of another or inserting one node
   * between a pair of other nodes.
   *
   * @param y the y
   * @return the selected index from y
   */
  public TreeNodeMove getSelectedIndexFromY(int y) {

    // Correct y for view window
    // y -= getViewRect().getY();

    ModernTreeNodeRenderer renderer;

    // Corrected for offset display window
    double height = getInsets().top; // - getViewRect().getY();

    if (y < height) {
      return null;
    }

    // We must return the full distance from the start of the tree to
    // the current y. This is because the painting corrects for the
    // display window being offset so it expects all coordinate to be
    // in the normal space.
    int offset = getInsets().top;
    int h;

    for (int i = 0; i < mFlatNodeList.size(); ++i) {
      int d = mFlatNodeList.get(i).getDepth();

      // get the renderer although here depth is
      // not important
      renderer = mNodeRenderer.getRenderer(this, mFlatNodeList.get(i), false, false, false, false, d, i);

      h = renderer.getHeight();

      if (y >= height && y < height + h) {
        boolean insert = y - height < INSERT_ZONE_SIZE;

        if (insert) {
          --d;
        }

        return new TreeNodeMove(i, d, offset, h, insert);
      }

      height += h;
      offset += h;
    }

    return new TreeNodeMove(mFlatNodeList.size(), 0, offset, -1, true);
  }

  /**
   * Search for a node by name and trigger it being highlighted if it is found.
   *
   * @param name the name
   * @return the found node or null if not found.
   */
  public TreeNode<T> getNodeByName(String name) {
    int index = getNodeIndexByName(name);

    if (index == -1) {
      return null;
    }

    // adjustSelectedNodes(index);

    return mFlatNodeList.get(index);
  }

  /**
   * Returns the index of a node matching to a name.
   *
   * @param name the name
   * @return the node index by name
   */
  public int getNodeIndexByName(String name) {
    String s = name.toLowerCase();

    for (int i = 0; i < mFlatNodeList.size(); ++i) {
      if (mFlatNodeList.get(i).getName().toLowerCase().contains(s)) {
        return i;
      }
    }

    return -1;
  }

  /**
   * Gets the depth.
   *
   * @param node the node
   * @return the depth
   */
  protected int getDepth(TreeNode<T> node) {
    Integer depth = mNodeDepthMap.get(node);

    if (depth == null) {
      return -1;
    }

    return depth;
  }

  /**
   * Select a node by the order it appears in the tree sequentially.
   *
   * @param index the index
   */
  public void selectNode(int index) {
    if (index < 0 || index > mFlatNodeList.size() - 1) {
      return;
    }

    adjustSelectedNodes(index, false, false);
  }

  /**
   * Select all nodes.
   */
  private void selectAll() {
    mSelectionModel.clear();

    List<Integer> indices = new ArrayList<Integer>();

    // since we are adding intermediates, we do not
    // add the current first and last in duplicate,
    // hence the index begins one past the min and
    // ends one before the max
    for (int i = 0; i < mFlatNodeList.size(); ++i) {
      if (!mFlatNodeList.get(i).isSelectable()) {
        continue;
      }

      indices.add(i);
    }

    mSelectionModel.setSelection(indices);
  }

  /**
   * Select the next node in the tree, possibly expanding the nodes selected.
   */
  private void selectNext() {
    int current = mSelectionModel.last();

    if (current == mFlatNodeList.size() - 1) {
      return;
    }

    for (int i = current + 1; i < mFlatNodeList.size(); ++i) {
      if (mFlatNodeList.get(i).isSelectable()) {
        mSelectionModel.add(i);

        break;
      }
    }
  }

  /**
   * Select the next node in the tree. All other nodes will be unselected.
   */
  private void moveNext() {
    int current = mSelectionModel.last();

    if (current < mFlatNodeList.size() - 1) {
      for (int i = current + 1; i < mFlatNodeList.size(); ++i) {
        if (mFlatNodeList.get(i).isSelectable()) {
          mSelectionModel.setSelection(i);

          break;
        }
      }
    }
  }

  /**
   * Select previous.
   */
  private void selectPrevious() {
    int current = mSelectionModel.last();

    if (mFlatNodeList.get(current).isSelectable()) {
      mSelectionModel.remove(current);
    }
  }

  /**
   * From the currently select node, move to the previous one above it.
   */
  private void movePrevious() {
    int current = mSelectionModel.last();

    if (current > 0) {
      for (int i = current - 1; i >= 0; --i) {
        if (mFlatNodeList.get(i).isSelectable()) {
          mSelectionModel.setSelection(i);

          break;
        }
      }
    }
  }

  /**
   * Removes all nodes from the tree.
   */
  public void clear() {
    mRoot.clear();
  }

  /**
   * Functionally equivalent to calling getRoot().addChild().
   *
   * @param node the node
   */
  public void addChild(TreeNode<T> node) {
    mRoot.addChild(node);
  }

  /**
   * Search the tree for a node.
   *
   * @param search the search
   * @return the tree node
   */
  public TreeNode<T> findFirst(String search) {
    return mRoot.findFirst(search);
  }

  /**
   * Search the tree for a node.
   *
   * @param search the search
   * @return the tree node
   */
  public TreeNode<T> matchFirst(String search) {
    return mRoot.matchFirst(search);
  }

  /**
   * Functionally equivalent to calling getRoot().getChild().
   *
   * @param index the index
   * @return the child
   */
  public TreeNode<T> getChild(int index) {
    return mRoot.getChild(index);
  }

  /**
   * Remove nodes by their indices in the flattened tree.
   *
   * @param indices the indices
   */
  public void removeNodes(Collection<Integer> indices) {
    for (int i : CollectionUtils.reverse(CollectionUtils.sort(indices))) {
      TreeNode<T> node = mFlatNodeList.get(i);

      removeNode(node);
    }
  }

  /**
   * Removes the node.
   *
   * @param node the node
   */
  public void removeNode(TreeNode<T> node) {
    node.getParent().removeChild(node);
  }

  /**
   * Functionally equivalent to calling getRoot().getChildCount().
   *
   * @return the child count
   */
  public int getChildCount() {
    return mRoot.getChildCount();
  }

  /**
   * Adds the selection listener.
   *
   * @param l the l
   */
  public void addSelectionListener(ModernSelectionListener l) {
    mSelectionListeners.addSelectionListener(l);
  }

  /**
   * Removes the selection listener.
   *
   * @param l the l
   */
  public void removeSelectionListener(ModernSelectionListener l) {
    mSelectionListeners.removeSelectionListener(l);
  }

  /**
   * Adds the tree listener.
   *
   * @param l the l
   */
  public void addTreeListener(TreeEventListener l) {
    mTreeEventListeners.addTreeListener(l);
  }

  /**
   * Removes the tree listener.
   *
   * @param l the l
   */
  public void removeTreeListener(TreeEventListener l) {
    mTreeEventListeners.removeTreeListener(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Iterable#iterator()
   */
  public Iterator<TreeNode<T>> iterator() {
    return mRoot.iterator();
  }

  /**
   * Arrange.
   *
   * @param <T>   the generic type
   * @param tree  the tree
   * @param root  the root
   * @param nodes the nodes
   */
  public static <T extends Comparable<? super T>> void arrange(ModernTree<T> tree, TreeRootNode<T> root,
      List<TreeNode<T>> nodes) {

    tree.clear();

    for (TreeNode<T> child : nodes) {
      root.addChild(child);
    }

    tree.setRoot(root);

    if (tree.getChildCount() > 0) {
      tree.selectNode(1);
    }
  }

  /**
   * Arrange.
   *
   * @param <T>   the generic type
   * @param tree  the tree
   * @param root  the root
   * @param nodes the nodes
   */
  public static <T extends Comparable<? super T>> void arrange(ModernTree<T> tree, TreeNode<T> root,
      List<TreeNode<T>> nodes) {

    tree.clear();

    for (TreeNode<T> child : nodes) {
      root.addChild(child);
    }

    TreeRootNode<T> root2 = new TreeRootNode<>();

    root2.addChild(root);

    tree.setRoot(root2);

    tree.organize();

    if (tree.getChildCount() > 0) {
      tree.selectNode(1);
    }
  }

  /**
   * Uses a sort model to organise a tree.
   *
   * @param <T>       the generic type
   * @param tree      the tree
   * @param nodes     the nodes
   * @param sorter    the sorter
   * @param ascending the ascending
   */
  public static <T extends Comparable<? super T>> void arrange(ModernTree<T> tree, List<TreeNode<T>> nodes,
      ModernTreeNodeSorter<T> sorter, boolean ascending) {

    List<TreeNode<T>> sortedNodes = sorter.sort(nodes, ascending);

    tree.clear();

    TreeRootNode<T> root = new TreeRootNode<>();

    for (TreeNode<T> child : sortedNodes) {
      root.addChild(child);
    }

    tree.setRoot(root);

    tree.organize();

    if (tree.getChildCount() > 0) {
      tree.selectNode(1);
    }
  }

  /**
   * Sort nodes by name.
   *
   * @param <T>       the generic type
   * @param nodes     the nodes
   * @param ascending the ascending
   * @return the list
   */
  public static <T extends Comparable<? super T>> List<TreeNode<T>> sortNodesByName(List<TreeNode<T>> nodes,
      boolean ascending) {

    List<String> names = new ArrayList<String>();

    Map<String, TreeNode<T>> nodeMap = new HashMap<String, TreeNode<T>>();

    for (TreeNode<T> node : nodes) {
      String name = node.getName();

      names.add(name);
      nodeMap.put(name, node);
    }

    Collections.sort(names);

    if (!ascending) {
      Collections.reverse(names);
    }

    List<TreeNode<T>> sortedNodes = new ArrayList<TreeNode<T>>();

    for (String name : names) {
      sortedNodes.add(nodeMap.get(name));
    }

    return sortedNodes;
  }

  /**
   * Gets the selection model.
   *
   * @return the selection model
   */
  public SelectionRangeModel getSelectionModel() {
    return mSelectionModel;
  }

  /**
   * Equivalent to calling tree.getRoot().getPath(treePath).
   *
   * @param treePath the tree path
   * @return the path
   */
  public Path convertToPath(TreePath treePath) {
    return getRoot().convertToPath(treePath);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.tree.TreeNodeEventProducer#addTreeNodeListener(org.abh.lib.
   * tree. TreeNodeEventListener)
   */
  @Override
  public void addTreeNodeListener(ITreeNodeEventListener l) {
    mListeners.addTreeNodeListener(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.tree.TreeNodeEventProducer#removeTreeNodeListener(org.abh.lib.
   * tree.TreeNodeEventListener)
   */
  @Override
  public void removeTreeNodeListener(ITreeNodeEventListener l) {
    mListeners.removeTreeNodeListener(l);
  }

  /**
   * Fire tree node changed.
   */
  public void fireTreeNodeChanged() {
    fireTreeNodeChanged(new ChangeEvent(this));
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.tree.TreeNodeEventProducer#fireTreeNodeChanged(org.abh.lib.
   * event. ChangeEvent)
   */
  @Override
  public void fireTreeNodeChanged(ChangeEvent e) {
    mListeners.fireTreeNodeChanged(e);
  }

  /**
   * Fire tree node updated.
   */
  public void fireTreeNodeUpdated() {
    fireCanvasRedraw();

    fireTreeNodeUpdated(new ChangeEvent(this));
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.tree.TreeNodeEventProducer#fireTreeNodeUpdated(org.abh.lib.
   * event. ChangeEvent)
   */
  @Override
  public void fireTreeNodeUpdated(ChangeEvent e) {
    mListeners.fireTreeNodeUpdated(e);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.tree.TreeNodeEventListener#nodeChanged(org.abh.lib.event.
   * ChangeEvent)
   */
  @Override
  public void nodeChanged(ChangeEvent e) {
    organize();

    fireTreeNodeChanged();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.tree.TreeNodeEventListener#nodeUpdated(org.abh.lib.event.
   * ChangeEvent)
   */
  @Override
  public void nodeUpdated(ChangeEvent e) {
    fireTreeNodeUpdated();
  }

  /**
   * Reset highlighted nodes etc.
   */
  private void reset() {
    mHighlightNode = null;
    mDragToNode = null;
    mHighlightNodeIndex = -1;
    mDragTo = null;

    fireHighlighted();
  }

  /**
   * Equivalent to calling {@code getRoot().getChildByPath()}.
   *
   * @param path the path
   * @return the child by path
   */
  public TreeNode<T> getChildByPath(String path) {
    return getRoot().getChildByPath(path);
  }

  /**
   * Equivalent to calling {@code getRoot().setChildrenAreExpanded()}.
   *
   * @param isExpanded the is expanded
   * @param recursive  the recursive
   */
  public void setChildrenAreExpanded(boolean isExpanded, boolean recursive) {
    getRoot().setChildrenAreExpanded(isExpanded, recursive);
  }

  @Override
  public void addHighlightListener(HighlightListener l) {
    mHighlightListeners.addHighlightListener(l);
  }

  @Override
  public void removeHighlightListener(HighlightListener l) {
    mHighlightListeners.removeHighlightListener(l);
  }

  public void fireHighlighted() {
    fireHighlighted(new HighlightEvent(this, mHighlightNodeIndex));
  }

  @Override
  public void fireHighlighted(HighlightEvent e) {
    mHighlightListeners.fireHighlighted(e);
  }
}
