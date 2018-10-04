package com.batiaev.java2.lesson8.sync;

/**
 * Created by Vedeshkin on 10/3/2018.
 * All right reserved.
 */
public class Data implements Runnable {
    private char value;
    private Controller controller;

    public Data(char value, Controller controller) {
        this.value = value;
        this.controller = controller;
    }

    public char getValue() {
        return value;
    }

    @Override
    public void run() {

        synchronized (controller){

            for (int i = 0; i < 5; i++)
            {
                try {
                    while (!controller.isNext(getValue())) {
                        controller.wait();
                    }
                    System.out.print(getValue());
                    controller.setCurrent(getValue());
                    controller.notifyAll();
                }catch (InterruptedException ex)
                {
                    ex.printStackTrace();
                }


            }



    }
}
}
