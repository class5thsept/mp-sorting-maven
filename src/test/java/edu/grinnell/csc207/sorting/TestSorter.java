package edu.grinnell.csc207.sorting;

import edu.grinnell.csc207.util.ArrayUtils;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

/**
 * Tests of Sorter objects. Please do not use this class directly.
 * Rather, you should subclass it and initialize stringSorter and
 * intSorter in a static @BeforeAll method.
 *
 * @author Your Name
 * @uathor Samuel A. Rebelsky
 */
public class TestSorter {

  // +---------+-----------------------------------------------------
  // | Globals |
  // +---------+

  /**
   * The sorter we use to sort arrays of strings.
   */
  static Sorter<String> stringSorter = null;

  /**
   * The sorter we use to sort arrays of integers.
   */
  static Sorter<Integer> intSorter = null;

  // +-----------+---------------------------------------------------
  // | Utilities |
  // +-----------+

  /**
   * Given a sorted array and a permutation of the array, sort the
   * permutation and assert that it equals the original.
   *
   * @param <T>
   *   The type of values in the array.
   * @param sorted
   *   The sorted array.
   * @param perm
   *   The permuted sorted array.
   * @param sorter
   *   The thing to use to sort.
   */
  public <T> void assertSorts(T[] sorted, T[] perm, Sorter<? super T> sorter) {
    T[] tmp = perm.clone();
    sorter.sort(perm);
    assertArrayEquals(sorted, perm,
      () -> String.format("sort(%s) yields %s rather than %s",
          Arrays.toString(tmp), 
          Arrays.toString(perm), 
          Arrays.toString(sorted)));
  } // assertSorts

  // +-------+-------------------------------------------------------
  // | Tests |
  // +-------+

  /**
   * A fake test. I've forgotten why I've included this here. Probably
   * just to make sure that some test succeeds.
   */
  @Test
  public void fakeTest() {
    assertTrue(true);
  } // fakeTest()

  /**
   * Ensure that an array that is already in order gets sorted correctly.
   */
  @Test
  public void orderedStringTest() {
    if (null == stringSorter) {
      return;
    } // if
    String[] original = { "alpha", "bravo", "charlie", "delta", "foxtrot" };
    String[] expected = original.clone();
    assertSorts(expected, original, stringSorter);
  } // orderedStringTest

  /**
   * Ensure that an array that is ordered backwards gets sorted correctly.
   */
  @Test
  public void reverseOrderedStringTest() {
    if (null == stringSorter) {
      return;
    } // if
    String[] original = { "foxtrot", "delta", "charlie", "bravo", "alpha" };
    String[] expected = { "alpha", "bravo", "charlie", "delta", "foxtrot" };
    assertSorts(expected, original, stringSorter);
  } // orderedStringTest

  /**
   * Ensure that a randomly permuted version of a moderate-sized
   * array sorts correctly.
   */
  @Test 
  public void permutedIntegersTest() { 
    int SIZE = 100; 
    if (null == intSorter) { 
      return; 
    } // if
    Integer[] original = new Integer[SIZE];
    for (int i = 0; i < SIZE; i++) {
      original[i] = i;
    } // for
    Integer[] expected = original.clone();
    ArrayUtils.permute(original);
    assertSorts(expected, original, intSorter);
  } // permutedIntegers

  /**
   * Ensure that an empty array remains as the same empty array.
   */
  @Test
  public void emptyArrayTest(){
    if (null == stringSorter) {
      return;
    } // if
    String[] original = {};
    String[] expected = original.clone();
    assertSorts(expected, original, stringSorter);
  }

  /**
   * Ensure that an array with one element returns the same array.
   */
  @Test
  public void singleElementArrayTest(){
    if (null == stringSorter) {
      return;
    } // if
    String[] original = {"Saturday"};
    String[] expected = original.clone();
    ArrayUtils.permute(original);
    assertSorts(expected, original, stringSorter);
  }

  /**
   * Ensure that it will return the same array if all elements are equal.
   */
  @Test
  public void sameElementsArrayTest(){
    if (null == stringSorter) { 
      return; 
    } // if
    String[] original = new String[1000];
    for (int i = 0; i < 1000; i++) {
      original[i] = "Saturday";
    } // for
    String[] expected = original.clone();
    ArrayUtils.permute(original);
    assertSorts(expected, original, stringSorter);
  }
  
  /**
   * Ensure that an array that constains its elements to be sorted as the
   * first and last elements get sorted correctly.
   */
  @Test
  public void elementsAtExtremesTest(){
    if (null == intSorter) {
      return;
    } // if
    Integer[] original = { 1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1 };
    Integer[] expected = { -1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1 };
    assertSorts(expected, original, intSorter);
  }

  /**
   * Ensure string sorting is case sensitive
   */
  @Test
  public void caseSensitiveTest(){
    if (null == stringSorter) {
      return;
    } // if
    String[] original = {"saturday", "Saturday", "SATURDAY", "sAtUrDaY"};
    String[] expected = {"SATURDAY", "Saturday", "sAtUrDaY", "saturday" };
    assertSorts(expected, original, stringSorter);
  }
} // class TestSorter
