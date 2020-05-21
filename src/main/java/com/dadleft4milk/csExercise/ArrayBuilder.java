package com.dadleft4milk.csExercise;

import java.util.ArrayList;

/**
 * Builer to create arrays containing sequences of repeated values.
 */
public class ArrayBuilder {
  /** Hold the list of entries. */
  private final ArrayList<Entry> entries;

  /** Cumulative total for the size of the resulting array. */
  private int total;

  /**
   * Default constructor creates an empty array builder.
   */
  public ArrayBuilder() {
    total = 0;
    entries = new ArrayList<Entry>();
  }

  /**
   * Add count instances of value.
   *
   * @param count number of instances to add
   * @param value the value to add
   * @return    this
   */
  public ArrayBuilder add(final int count, final int value) {
    entries.add(new Entry(count, value));
    total += count;
    return this;
  }

  /**
   * Generate an array based on added entries.
   *
   * @return    The resulting array.
   */
  public int[] build() {
    final int[] arr = new int[total];
    int index = 0;

    for (Entry e: entries) {
        insertValues(arr, index, e.getCount(), e.getValue());
        index += e.getCount();
    }

    return arr;
  }

  /**
   * Helper to add count instances of value to arr at start index.
   *
   * @param arr Array to add values to.
   * @param start Index where the first value should be added.
   * @param count The number of values to add.
   * @param value The value to add.
   */
  private static void insertValues(
      final int[] arr, final int start, final int count, final int value) {
    for (int i = start; i < start + count; ++i) {
      arr[i] = value;
    }
  }

  /** Hold repeated entry values. */
  private class Entry {
    /** Count of values. */
    private final int theCount;
    /** Value to repeat. */
    private final int theValue;

    /**
     * Default constructor.
     *
     * @param count
     * @param value
     */
    Entry(final int count, final int value) {
      theCount = count;
      theValue = value;
    }

    /**
     * Returns the count.
     *
     * @return count
     */
    public int getCount() {
      return theCount;
    }

    /**
     * Returns the value to repeat.
     *
     * @return value
     */
    public int getValue() {
      return theValue;
    }
  }
}
