package com.dadleft4milk.csExercise;

import java.io.*;
import java.util.*;


public class App {
  public static void main(String[] args) {
    final ArrayBuilder builder = new ArrayBuilder();

    /* Specify values and counts */
    for(int i=1; i <= 12; ++i) {
      builder.add(83000, i);
    }
    builder
      .add(5, 20)
      .add(1000, 13)
      .add(500, 14)
      .add(250, 15)
      .add(100, 16)
      .add(50, 17)
      .add(25, 18)
      .add(10, 19);

    /* Build the resulting array */
    final int[] list = builder.build();

    shuffle(list);

    outputResults(list);
  }

  private static void shuffle(final int[] values) {
    /* This is interesting.  It is a fairly standard shuffle algorithm, but
     * preventing repeats is tricky.  Orinally I just incremented `to` until I
     * found a locaiton that wouldn't cause a repeat.  This was both slower,
     * and resulted in a very biased shuffle. Values at the end of the list
     * tended to stay towards the end of the list.
     */
    final Random rand = new Random();
    for(int i = values.length - 1; i > 0; i--) {
      final int from = i;
      int to = rand.nextInt(i + 1);
      while (wouldCauseRepeat(values, from ,to) || wouldCauseRepeat(values, to, from)) {
        to = rand.nextInt(i + 1);
      }
      final int tmp = values[from];
      values[from] = values[to];
      values[to] = tmp;
    }
  }

  private static void outputResults(final int[] arr) {
    final FileWriter output;

    try {
       output = new FileWriter("test.output");
    } catch (IOException e) {
        System.out.printf("Error while opening file for writing\n");
        return;
    }


    try (
      final PrintWriter printer = new PrintWriter(output)
    ) {

      for(int i = 0; i < arr.length; ++i) {
        if(arr[i] == 20) {
          System.out.printf("%d\n", i);
        }
        printer.printf("%d\n", arr[i]);
      }
    }
  }

  private static boolean wouldCauseRepeat(final int[] arr, final int from, final int to) {
    if (to > 0 && arr[to - 1] == arr[from]) return true;
    if (to + 1 != arr.length  && arr[to + 1] == arr[from]) return true;
    return false;
   }
}
