package com.dadleft4milk.csExercise;

import java.util.ArrayList;

public class ArrayBuilder {
  /* This class helps to create arrays containing sequences of repeated values. */
  private final ArrayList<Entry> entries;
  private int total;

  public ArrayBuilder() {
    total = 0;
    entries = new ArrayList<Entry>();
  }

  /* Add count instences of value */
  public ArrayBuilder  add(final int count, final int value) {
    entries.add(new Entry(count, value));
    total += count;
    return this;
  }

  /* Generate an array based on added entries. */
  public int [] build() {
    int[] arr = new int[total];
    int index = 0;
    for(Entry e: entries) {
        insertValues(arr, index, e.getCount(), e.getValue());
        index += e.getCount();

    }
    return arr;
  }

  /* helper to add count instances of value to arr at start index */
  private static void insertValues(final int[] arr, final int start, final int count, final int value) {
    for(int i = start; i < start + count; ++i) {
      arr[i] = value;
    }
  }

  /* Class to hold repeated entries */
  private class Entry {
    private final int count;
    private final int value;

    public Entry(final int count, final int value) {
      this.count = count;
      this.value = value;
    }

    int getCount() {
      return count;
    }

    int getValue() {
      return value;
    }
  }
}
