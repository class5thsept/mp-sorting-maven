package edu.grinnell.csc207.sorting;

import java.util.Comparator;
import edu.grinnell.csc207.util.ArrayUtils;

/**
 * Something that sorts using selection sort.
 *
 * @param <T> The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 */

public class SelectionSorter<T> implements Sorter<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The way in which elements are ordered.
   */
  Comparator<? super T> order;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a sorter using a particular comparator.
   *
   * @param comparator The order in which elements in the array should be ordered after sorting.
   */
  public SelectionSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // SelectionSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using selection sort.
   *
   * @param values an array to sort.
   *
   * @post The array has been sorted according to some order (often one given to the constructor).
   * @post For all i, 0 &lt; i &lt; values.length, order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    if (values.length <= 1) {
      return;
    } // if

    for (int current = 0; current < values.length; current++) {
      // Finding the smallest value among the unprocessed array
      ArrayUtils.swap(values, current, indexOfSmallest(values, current));
    } // for
  } // sort(T[])

  private int indexOfSmallest(T[] values, int indexOfCurrent){
    int smallIndex = indexOfCurrent;
    for (int current2 = indexOfCurrent; current2 < values.length; current2++) {
      if (order.compare(values[smallIndex], values[current2]) > 0) {
        smallIndex = current2;
      } // if
    } // for
    return smallIndex;
  }
} // class SelectionSorter
