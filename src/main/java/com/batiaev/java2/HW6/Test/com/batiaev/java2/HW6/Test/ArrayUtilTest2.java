package com.batiaev.java2.HW6.Test.com.batiaev.java2.HW6.Test;

import com.batiaev.java2.HW6.src.ArrayUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Vedeshkin on 10/9/2018.
 * All right reserved.
 */
public class ArrayUtilTest2 {
    int [] data = new int[] {1,31,3,543,5,76,76,45,3,44,2,42,42,34,6,5,6,6,78,8,4};
    @Test
    public void checkArray1() {


        Assert.assertTrue(ArrayUtil.checkArray(data, 3, 4) == true);
    }
    @Test
    public void checkArray2()
    {
        Assert.assertTrue(ArrayUtil.checkArray(data, 1031231, 4) == false);
    }
    @Test
    public void checkArray3(){

        Assert.assertTrue(ArrayUtil.checkArray(new int [10],3,4) == false);

    }

}
