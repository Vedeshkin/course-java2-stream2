package com.batiaev.java2.lesson8.sync;

/**
 * Created by Vedeshkin on 10/3/2018.
 * All right reserved.
 */
public class Controller {

    private static final int START_FROM =  64;
    private char current;

    public Controller() {
        current = (char) START_FROM;
    }

    public boolean isNext(char value) {
        {
            if((int) value == (int)current +1){
                return true;
            }
            if(current == 'C' && value == 'A')
                return true;
        }
        return  false;
    }

    public void setCurrent(char current) {
        this.current = current;
    }
}
