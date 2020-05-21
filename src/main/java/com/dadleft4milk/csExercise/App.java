package com.dadleft4milk.csExercise;

import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;

import java.util.Random;

import java.lang.SuppressWarnings;


/**
 * Application that produces a scrambled collection of values.
 */
public final class App {

  private App() { }

  /**
   * Main entry point for the applicatino.
   *
   * @param args Command line arguments
   */

  @SuppressWarnings("stylechecker:magicnumber")
  public static void main(final String[] args) {
    final ArrayBuilder builder = new ArrayBuilder();

    /* Specify values and counts */
    for (int i = 1; i <= 12; ++i) {
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

  /**
   * This is a fairly standard shuffle algorithm, but preventing repeats is
   * tricky.  Orinally I just incremented `to` until I found a location that
   * wouldn't cause a repeat.  This was both slower, and resulted in a very
   * biased shuffle. Values at the end of the list tended to stay towards the
   * end of the list.
   *
   * @param values Array of values to shuffle in place.
   */
  private static void shuffle(final int[] values) {
    final Random rand = new Random();
    for (int i = values.length - 1; i > 0; i--) {
      final int from = i;
      int to = rand.nextInt(i + 1);
      while (
          wouldCauseRepeat(values, from, to)
          || wouldCauseRepeat(values, to, from)) {
        to = rand.nextInt(i + 1);
      }
      final int tmp = values[from];
      values[from] = values[to];
      values[to] = tmp;
    }
  }

  /**
   * Write values from arr to <em>test.output</em>. Also prints to stdout a
   * list of entries containing <em>20</em>.
   *
   * @param arr Array of values to output.
   */
  private static void outputResults(final int[] arr) {
    final FileWriter output;

    try {
       output = new FileWriter("test.output");
    } catch (IOException e) {
        System.out.printf("Error while opening file for writing\n");
        return;
    }

    try (
      PrintWriter printer = new PrintWriter(output)
    ) {

      for (int i = 0; i < arr.length; ++i) {
        if (arr[i] == 20) {
          System.out.printf("%d\n", i + 1);
        }
        printer.printf("%d\n", arr[i]);
      }
    }
  }

  /**
   * Checks whether swapping the value at <em>from</em> with the value at
   * <em>to</em> would cause a repeated value in the list.  Note that it only
   * checks for repeats around the <em>to</em> location.
   *
   * @param arr Array of values where swap will occur.
   * @param from Location to swap from.
   * @param to location to swap to.
   * @return True if the swap would cause a repeat around the <em>to</em>
   * location.
   */
  private static boolean wouldCauseRepeat(
      final int[] arr, final int from, final int to) {
    return (to > 0 && arr[to - 1] == arr[from])
      || (to + 1 != arr.length  && arr[to + 1] == arr[from]);
   }
}
