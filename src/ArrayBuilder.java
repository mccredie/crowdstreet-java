import java.util.*;

class ArrayBuilder {
  /* This class helps to create arrays containing sequences of repeated values. */
  private ArrayList<Entry> entries;
  private int total;

  public ArrayBuilder() {
    total = 0;
    entries = new ArrayList<Entry>();
  }

  /* Add count instences of value */
  public void add(int count, int value) {
    entries.add(new Entry(count, value));
    total += count;
  }

  /* Generate an array based on added entries. */
  int [] build() {
    int[] arr = new int[total];
    int index = 0;
    for(Entry e: entries) {
        insertValues(arr, index, e.getCount(), e.getValue());
        index += e.getCount();

    }
    return arr;
  }

  /* helper to add count instances of value to arr at start index */
  private static void insertValues(int[] arr, int start, int count, int value) {
    for(int i = start; i < start + count; ++i) {
      arr[i] = value;
    }
  }

  /* Class to hold repeated entries */
  private class Entry {
    private int count;
    private int value;

    public Entry(int count, int value) {
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
