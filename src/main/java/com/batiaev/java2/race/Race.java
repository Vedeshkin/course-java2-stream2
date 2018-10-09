package com.batiaev.java2.race;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Vedeshkin on 10/8/2018.
 * All right reserved.
 */
public class Race {

    private ArrayList<Stage> stages;
    public ArrayList<Stage> getStages() { return stages; }
    private List<Car> cars =  new ArrayList<>();

    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }
}
