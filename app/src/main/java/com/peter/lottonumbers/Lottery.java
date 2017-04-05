package com.peter.lottonumbers;

import java.util.Arrays;
import java.util.Random;

class Lottery {
    // pick from random numbers and return - sorted
    // each number unique
    // howMany - the number of numbers to pick
    // max - the largest number than can be picked
    static int[] pickNumbers(int howMany, int max) throws Exception {
        if (howMany > 0)                // must be positive
        {
            if (max > howMany) {
                int numbers[] = new int[howMany];    // create the array

                Random r = new Random();
                // draw each number randomly
                // no duplicates
                for (int i = 0; i < howMany; i++) {
                    int drawn;

                    // pick a random number between 0 and max
                    boolean finished = false;
                    do {
                        drawn = r.nextInt(max + 1);
                        // finished if not 0 and not already drawn
                        if (drawn != 0) {
                            boolean alreadyDrawn = false;
                            // check if previously drawn
                            for (int j = 0; j < i; j++) {
                                if (numbers[j] == drawn) {
                                    alreadyDrawn = true;
                                    break;
                                }
                            }
                            if (!alreadyDrawn) {
                                numbers[i] = drawn;    // done
                                finished = true;
                            }
                        }
                    } while (!finished);
                }
                Arrays.sort(numbers);
                return numbers;
            } else {
                throw new Exception("Invalid input");
            }
        } else {
            throw new Exception("Invalid input");
        }
    }
}
