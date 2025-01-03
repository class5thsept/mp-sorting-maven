package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using merge sort.
 *
 * @param <T> The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 */

public class MergeSorter<T> implements Sorter<T> {
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
  public MergeSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // MergeSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using merge sort.
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

    mergeSort(values, 0, values.length - 1);
  } // sort(T[])

  /**
   * Helper method that call merge sort recursively.
   *
   * @param values an array to sort.
   * @param lb lower bound.
   * @param ub upper bound.
   */
  private void mergeSort(T[] values, int lb, int ub) {

    int middle = lb + (ub - lb) / 2;

    if (lb >= ub) {
      return;
    } // if

    mergeSort(values, lb, middle);
    mergeSort(values, middle + 1, ub);

    merge(values, lb, middle, ub);
  } // mergeSort(T[] values, int lb, int ub)

  /*
   * Helper function where comparing and merging happens
   */
  private void merge(T[] values, int lb, int middle, int ub) {
    int lengthOfLeft = middle - lb + 1;
    int lengthOfRight = ub - middle;

    //Although merge sort is fast, it needs extra space
    T[] leftArray = (T[]) new Object[lengthOfLeft];
    T[] rightArray = (T[]) new Object[lengthOfRight];

    // From the beginning until the middle
    for (int i = 0; i < lengthOfLeft; i++) {
      leftArray[i] = values[lb + i];
    } // for

    // From middle until the end
    for (int j = 0; j < lengthOfRight; j++) {
      rightArray[j] = values[middle + 1 + j];
    } // for

    // Now we have two arrays, which represents each side
    // off the original array. The next step is to merge them

    int i = 0; // To keep track of the left array
    int j = 0; // To keep track of the right array

    while (i < lengthOfLeft && j < lengthOfRight) {
      if (order.compare(leftArray[i], rightArray[j]) <= 0) {
        values[lb] = leftArray[i];

        lb++;
        i++;
      } else {
        values[lb] = rightArray[j];

        lb++;
        j++;
      } // if-else
    } // while

    // Just copy the rest of the other array in case
    // we reached the end of one of them
    while (i < lengthOfLeft) {
      values[lb] = leftArray[i];

      lb++;
      i++;
    } // while

    while (j < lengthOfRight) {
      values[lb] = rightArray[j];

      lb++;
      j++;
    } // while
  } // merge (T[] array, int lb, int middle, int ub)
} // class MergeSorter
