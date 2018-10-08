package com.batiaev.java2.race;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Vedeshkin on 10/8/2018.
 * All right reserved.
 */
public class Race {

    private ArrayList<Stage> stages;
    public ArrayList<Stage> getStages() { return stages; }
    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }
}
