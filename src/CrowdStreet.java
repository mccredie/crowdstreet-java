import java.io.*;
import java.util.*;


class CrowdStreet {
  public static void main(String[] args) {
    ArrayBuilder builder = new ArrayBuilder();

    /* Specify values and counts */
    for(int i=1; i <= 12; ++i) {
      builder.add(83000, i);
    }
    builder.add(5, 20);
    builder.add(1000, 13);
    builder.add(500, 14);
    builder.add(250, 15);
    builder.add(100, 16);
    builder.add(50, 17);
    builder.add(25, 18);
    builder.add(10, 19);

    /* Build the resulting array */
    int[] list = builder.build();

    shuffle(list);

    outputResults(list);
  }

  private static void shuffle(int[] values) {
    /* This is interesting.  It is a fairly standard shuffle algorithm, but
     * preventing repeats is tricky.  Orinally I just incremented `to` until I
     * found a locaiton that wouldn't cause a repeat.  This was both slower,
     * and resulted in a very biased shuffle. Values at the end of the list
     * tended to stay towards the end of the list.
     */
    Random rand = new Random();
    for(int i = values.length - 1; i > 0; i--) {
      int to = rand.nextInt(i + 1);
      int from = i;
      while (wouldCauseRepeat(values, from ,to) || wouldCauseRepeat(values, to, from)) {
        to = rand.nextInt(i + 1);
      }
      int tmp = values[from];
      values[from] = values[to];
      values[to] = tmp;
    }
  }

  private static void outputResults(int[] arr) {
    FileWriter output;

    try {
       output = new FileWriter("test.output");
    } catch (IOException e) {
        System.out.printf("Error while opening file for writing\n");
        return;
    }

    PrintWriter printer = new PrintWriter(output);

    for(int i = 0; i < arr.length; ++i) {
      if(arr[i] == 20) {
        System.out.printf("%d\n", i);
      }
      printer.printf("%d\n", arr[i]);
    }
  }

  private static boolean wouldCauseRepeat(int[] arr, int from, int to) {
    if (to > 0 && arr[to - 1] == arr[from]) return true;
    if (to + 1 != arr.length  && arr[to + 1] == arr[from]) return true;
    return false;
   }
}
