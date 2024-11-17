package edu.grinnell.csc207.sorting;

import java.util.Comparator;
import edu.grinnell.csc207.util.ArrayUtils;

/**
 * Something that sorts using Quicksort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 */

public class Quicksorter<T> implements Sorter<T> {
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
   * @param comparator
   *   The order in which elements in the array should be ordered
   *   after sorting.
   */
  public Quicksorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // Quicksorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using Quicksort.
   *
   * @param values
   *   an array to sort.
   *
   * @post
   *   The array has been sorted according to some order (often
   *   one given to the constructor).
   * @post
   *   For all i, 0 &lt; i &lt; values.length,
   *     order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    if (values.length <= 2) {
      return;
    } // if
    quickSort(values, 0, values.length - 1);
  } // sort(T[])

  /**
   * Helper that recursively calls quick sort with differen pivots.
   *
   * @param values
   * @param lb
   * @param ub
   */
  private void quickSort(T[] values, int lb, int ub) {
    if (lb < ub) {
      int pivotIndex = partition(values, lb, ub);

      quickSort(values, lb, pivotIndex - 1);
      quickSort(values, pivotIndex + 1, ub);
    } // if
  } // quickSort(T[] values, int lb, int ub)

  /**
   * Helper that generates the pivot.
   *
   * @param values
   * @param lb
   * @param ub
   * @return the pivot.
   */
  private int partition(T[] values, int lb, int ub) {
    int randomPivot = lb + (int) (Math.random() * (ub - lb + 1));
    ArrayUtils.swap(values, randomPivot, ub);

    T pivot = values[ub];
    int i = lb - 1;

    for (int j = lb; j < ub; j++) {
      if (order.compare(values[j], pivot) <= 0) {
        i++;
        ArrayUtils.swap(values, i, j);
      } // if
    } // for
    ArrayUtils.swap(values, i + 1, ub);
    return i + 1;
  } // partition(T[] values, int lb, int ub)
} // class Quicksorter
