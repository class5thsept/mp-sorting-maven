package edu.grinnell.csc207.sorting;

import java.util.Comparator;
import edu.grinnell.csc207.util.ArrayUtils;

/**
 * Something that sorts using insertion sort.
 *
 * @param <T> The types of values that are sorted.
 *
 * @author Leonardo Alves Nunes
 */

public class NunesLeonardoSort<T> implements Sorter<T> {
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
  public NunesLeonardoSort(Comparator<? super T> comparator) {
    this.order = comparator;
  } // InsertionSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sorts using Merge sort for large inputs and Selection sort for small ones.
   *
   * @param values an array to sort.
   *
   * @post The array has been sorted according to some order (often one given to the constructor).
   * @post For all i, 0 &lt; i &lt; values.length, order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    //The benefit of this is that as Merge and Selection sort have similar time
    //for small inputs, we can change to selection sort to avoid creating too many
    //arrays for merge sort, saving space.

    //After running some experiments comparing Merge and NunesLeonardoSort I
    //observed that my sort is not faster by a significant amount.
    if (values.length <= 1) {
      return;
    } // if

    mergeSort(values, 0, values.length - 1);
  } // sort(T[])

  /*
   * Helper method that call merge sort recursively.
   */
  private void mergeSort(T[] array, int lb, int ub) {
    int middle = lb + (ub - lb) / 2;

    if (lb >= ub) {
      return;
    } // if

    //I considered 10 to be a "small input".
    if (array.length < 10) {
      int smallIndex;
      if (array.length <= 1) {
        return;
      } // if
      for (int current = 0; current < array.length; current++) {
        smallIndex = current;
        // Finding the smallest value among the unprocessed array
        for (int current2 = current; current2 < array.length; current2++) {
          if (order.compare(array[smallIndex], array[current2]) > 0) {
            smallIndex = current2;
          } // if
        } // for
        ArrayUtils.swap(array, current, smallIndex);
      } // for
    } else {
      mergeSort(array, lb, middle);
      mergeSort(array, middle + 1, ub);
      merge(array, lb, middle, ub);
    } // if-else
  } // mergeSort(T[] array, int lb, ub)

  /*
   * Helper function where comparing and merging happens
   */
  private void merge(T[] array, int lb, int middle, int ub) {
    int lengthOfLeft = middle - lb + 1;
    int lengthOfRight = ub - middle;

    T[] leftArray = (T[]) new Object[lengthOfLeft];
    T[] rightArray = (T[]) new Object[lengthOfRight];

    // From the beginning until the middle
    for (int i = 0; i < lengthOfLeft; i++) {
      leftArray[i] = array[lb + i];
    } // for

    // From middle until the end
    for (int j = 0; j < lengthOfRight; j++) {
      rightArray[j] = array[middle + 1 + j];
    } // for

    // Now we have two arrays, which represents each side
    // off the original array. The next step is to merge them

    int i = 0; // To keep track of the left array
    int j = 0; // To keep track of the right array

    while (i < lengthOfLeft && j < lengthOfRight) {
      if (order.compare(leftArray[i], rightArray[j]) <= 0) {
        array[lb] = leftArray[i];

        lb++;
        i++;
      } else {
        array[lb] = rightArray[j];

        lb++;
        j++;
      } // if-else
    } // while

    // Just copy the rest of the other array in case
    // we reached the end of the other
    while (i < lengthOfLeft) {
      array[lb] = leftArray[i];

      lb++;
      i++;
    } // while

    while (j < lengthOfRight) {
      array[lb] = rightArray[j];

      lb++;
      j++;
    } // while
  } // merge (T[] array, int lb, int middle, int ub)
} // class InsertionSorter
