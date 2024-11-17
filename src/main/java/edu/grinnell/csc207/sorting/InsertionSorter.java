package edu.grinnell.csc207.sorting;

import java.util.Comparator;
import edu.grinnell.csc207.util.ArrayUtils;

/**
 * Something that sorts using insertion sort.
 *
 * @param <T> The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 */

public class InsertionSorter<T> implements Sorter<T> {
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
  public InsertionSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // InsertionSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using insertion sort.
   *
   * @param values an array to sort.
   *
   * @post The array has been sorted according to some order (often one given to the constructor).
   * @post For all i, 0 &lt; i &lt; values.length, order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    // If there is one or no element, there is nothing to change
    // This statement will appear on every sorting from now on.
    if (values.length <= 1) {
      return;
    } // if
    for (int current = 1; current < values.length; current++) {
      int i = current;
      while (true) {
        if (i == 0) {
          //Stops to avoid comparing with a -1 index
          break;
        } // if
        if (order.compare(values[i], values[i - 1]) >= 0) {
          break;
        } else {
          ArrayUtils.swap(values, i, i - 1);
          i--;
        } // if
      } // while
    } // for
  } // sort(T[])
} // class InsertionSorter
