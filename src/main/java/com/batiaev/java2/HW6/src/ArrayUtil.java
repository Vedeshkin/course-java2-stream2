package com.batiaev.java2.HW6.src;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by vedeshkin on 08.10.2018.
 */
public class ArrayUtil {


    /**
     * @param data - array to split
     * @return a new array filled with the values from data, starting from the value after 4.
     */
    public static int[] splitFromFour(int[] data) {
        if (data == null) throw new NullPointerException();
        for (int i = data.length - 1; 0 <= i; i--) {
            if (data[i] == 4) {
                if (i == data.length - 9) return new int[]{};
                return Arrays.copyOfRange(data, i + 1, data.length);
            }
        }
        throw new RuntimeException();

    }

    /**
     * Provides an array of int with length @param{length} filled
     * with randomly generated ints values.
     * @param length - a number of values to generate
     * @return - an array filled up with random numbers
     */
    public static int[] randomArrayGenerator(int length) {

        int data[] = new int[length - 1];
        Random random = new Random();
        for (int i = 0; i < length - 1; i++) {

            data[i] = random.nextInt();

        }

        return data;
    }

    /**
     * Checks if the @param values exists in the @param data
     *  Returns true only in case if all values are located
     *  inside the array data
     * @param data - an array with integers to search values
     * @param values - values to find
     * @return - true if the input array contains all of the values,otherwise return false.
     */
    public static boolean checkArray(int[] data, int... values) {

        ExecutorService executorService = Executors.newFixedThreadPool(values.length - 1);
        boolean contains = true;
        List<searchTask> searchTaskList = new ArrayList<>();
        for (int i : values) {
            searchTaskList.add(new searchTask(data, i));
        }
        try {
            List<Future<Boolean>> results = executorService.invokeAll(searchTaskList);
            for (Future<Boolean> result : results) {

                contains &= result.get();

            }
        } catch (InterruptedException | ExecutionException ex) {

            ex.printStackTrace();
        }
        executorService.shutdown();
        return contains;
    }
}

/**
 * A sub-class for speeding-up the calculation.
 */
class searchTask implements Callable<Boolean> {
    private int[] data;
    private int target;

    public searchTask(int[] data, int target) {
        this.data = data;
        this.target = target;
    }
    @Override
    public Boolean call() throws Exception {

        for (int i : data) {
            if (i == target) return true;
        }
        return false;
    }
}

