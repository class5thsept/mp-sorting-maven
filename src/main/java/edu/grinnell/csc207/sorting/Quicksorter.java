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
    if (values.length <= 1) {
      return;
    } // if
    quickSort(values, 0, values.length);
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

      quickSort(values, lb, pivotIndex);
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
    int pivotIndex = lb + (int) (Math.random() * (ub - lb));
    //Put at the beginning
    ArrayUtils.swap(values, pivotIndex, lb);

    T pivot = values[lb];
    int sm = lb + 1;
    int lg = ub;

    while (sm < lg) {
      if (order.compare(pivot, values[sm]) > 0) {
        sm++;
      } else {
        ArrayUtils.swap(values, sm, lg - 1);
        lg--;
      }
    }
    ArrayUtils.swap(values, lb, sm-1);
    return sm-1;
  } // partition(T[] values, int lb, int ub)
} // class Quicksorter
