package com.batiaev.java2.HW6.Test.com.batiaev.java2.HW6.Test;

import com.batiaev.java2.HW6.src.ArrayUtil;
import org.junit.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;


/**
 * Created by vedeshkin on 08.10.2018.
 */

@RunWith(Parameterized.class)
public class ArrayUtilTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[]{1, 2, 4, 4, 3, 4, 1, 7}, new int[]{1, 7}},
                {new int[10], new int[]{}},
                {new int[]{1, 2, 4, 4, 2, 5, 6, 7, 8}, new int[]{2, 5, 6, 7, 8}},
                {new int[]{1, 2, 4}, new int[]{}},
        });
    }

    private int[] data;
    private int[] result;

    public ArrayUtilTest(int[] data, int[] result) {
        this.data = data;
        this.result = result;
    }

    @Test
    public void splitFromFour() {
        Assert.assertArrayEquals(String.format("Unexpected result for array %s ", Arrays.toString(data)), result, ArrayUtil.splitFromFour(data));

    }

}