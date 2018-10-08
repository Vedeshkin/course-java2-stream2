package com.batiaev.java2.race;

/**
 * Created by Vedeshkin on 10/8/2018.
 * All right reserved.
 */
public abstract class Stage {
    protected int length;
    protected String description;

    public String getDescription() {
        return description;
    }

    public abstract void go(Car c);
}
