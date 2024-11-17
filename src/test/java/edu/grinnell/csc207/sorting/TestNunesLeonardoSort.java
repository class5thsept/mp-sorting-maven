package edu.grinnell.csc207.sorting;

import org.junit.jupiter.api.BeforeAll;

/**
 * Tests of our NunesLeonardoSort.
 */
public class TestNunesLeonardoSort extends TestSorter {
  /**
   * Set up the sorters.
   */
  @BeforeAll
  static void setup() {
    stringSorter = new NunesLeonardoSort<String>((x,y) -> x.compareTo(y));
    intSorter = new NunesLeonardoSort<Integer>((x,y) -> x.compareTo(y));
  } // setup()

} // class TestNunesLeonardoSort