package com.batiaev.java2.lesson7;

import java.io.File;

/**
 * Created by vedeshkin on 25.09.2018.
 */
public class Utils {

    public static boolean isFileExists(String filename) {

        File fileToCheck = new File(filename);

        return fileToCheck.exists() && !fileToCheck.isDirectory();
    }
}
