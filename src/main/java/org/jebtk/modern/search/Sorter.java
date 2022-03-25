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
package org.jebtk.modern.search;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jebtk.core.NameGetter;
import org.jebtk.core.collections.ArrayListCreator;
import org.jebtk.core.collections.CollectionUtils;
import org.jebtk.core.collections.DefaultHashMap;
import org.jebtk.core.text.NaturalComparator;
import org.jebtk.core.tree.TreeNode;
import org.jebtk.core.tree.TreeRootNode;
import org.jebtk.modern.tree.ModernTree;

/**
 * The experiment tree can be sorted in multiple ways. Given a list of
 * experiments and a tree, generate a custom sorted tree of experiments.
 *
 * @author Antony Holmes
 * @param <T> the generic type
 */
public abstract class Sorter<T extends NameGetter> implements Comparable<Sorter<T>>, NameGetter {

  public static final Comparator<String> STRING_NAT_SORTER = new NaturalComparator<String>();

  /**
   * Gets the type.
   *
   * @return the type
   */
  public abstract String getType();

  /**
   * Arrange.
   *
   * @param items       the items
   * @param tree        the tree
   * @param ascending   the ascending
   * @param filterModel the filter model
   */
  public abstract void arrange(Collection<T> items, ModernTree<T> tree, boolean ascending, FilterModel filterModel);

  /**
   * Filter.
   *
   * @param items       the items
   * @param filterModel the filter model
   */
  public void filter(Collection<T> items, FilterModel filterModel) {
    filterModel.clear();
  }

  /**
   * Adds the filter names.
   *
   * @param names       the names
   * @param filterModel the filter model
   */
  public static void addFilterNames(Collection<String> names, FilterModel filterModel) {
    filterModel.setSelected(names, true);
  }

  /**
   * Add the names to the filter using natural sorting.
   * 
   * @param names
   * @param filterModel
   */
  public static void addSortedFilterNames(Collection<String> names, FilterModel filterModel) {
    addFilterNames(CollectionUtils.sort(names, STRING_NAT_SORTER), filterModel);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  public int compareTo(Sorter<T> s) {
    return getName().compareTo(s.getName());
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @SuppressWarnings("unchecked")
  public boolean equals(Object o) {
    if (o instanceof Sorter) {
      return false;
    }

    return compareTo((Sorter<T>) o) == 0;
  }

  /**
   * Sort a set of ChipSeqSamples by name.
   *
   * @param <X>       the generic type
   * @param items     the items
   * @param ascending the ascending
   * @return the list
   */
  public static <X extends NameGetter> List<X> sortByName(Iterable<X> items, boolean ascending) {
    Map<String, List<X>> itemMap = DefaultHashMap.create(new ArrayListCreator<X>());

    for (X item : items) {
      String name = item.getName();

      itemMap.get(name).add(item);
    }

    List<String> names = CollectionUtils.sort(itemMap.keySet(), STRING_NAT_SORTER);

    if (!ascending) {
      Collections.reverse(names);
    }

    List<X> sortedItems = new ArrayList<X>(names.size());

    for (String name : names) {
      for (X item : itemMap.get(name)) {
        sortedItems.add(item);
      }
    }

    return sortedItems;
  }

  /**
   * Arrange.
   *
   * @param <X>       the generic type
   * @param map       the map
   * @param ascending the ascending
   * @param tree      the tree
   */
  protected <X extends Comparable<? super X>> void arrange(Map<X, ? extends Iterable<T>> map, boolean ascending,
      ModernTree<T> tree) {
    List<X> sortedNames = CollectionUtils.sortKeys(map, new NaturalComparator<>(), ascending);

    tree.clear();

    TreeRootNode<T> root = new TreeRootNode<>();

    for (X name : sortedNames) {
      TreeNode<T> node = new TreeNode<>(name.toString());

      List<T> sortedValues = sortByName(map.get(name), ascending);

      for (T sample : sortedValues) {
        node.addChild(new TreeNode<>(sample.getName(), sample));
      }

      root.addChild(node);
    }

    tree.setRoot(root);

    // tree.getSelectionModel().setSelection(1);
  }

  /**
   * Create a tree from a date map. Dates will sorted in order and formatted
   * according the format provided.
   *
   * @param <X>       the generic type
   * @param map       the map
   * @param format    the format
   * @param ascending the ascending
   * @param tree      the tree
   */
  protected <X extends Comparable<? super X>> void arrange(Map<Date, ? extends Iterable<T>> map, DateFormat format,
      boolean ascending, ModernTree<T> tree) {
    List<Date> sortedDates = CollectionUtils.sortKeys(map, ascending);

    tree.clear();

    TreeRootNode<T> root = new TreeRootNode<T>();

    for (Date date : sortedDates) {
      TreeNode<T> node = new TreeNode<T>(format.format(date));

      List<T> sortedValues = sortByName(map.get(date), ascending);

      for (T sample : sortedValues) {
        node.addChild(new TreeNode<T>(sample.getName(), sample));
      }

      root.addChild(node);
    }

    tree.setRoot(root);
  }
}
