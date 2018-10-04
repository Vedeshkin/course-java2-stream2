package com.batiaev.java2.lesson8.sync;


/**
 * Created by Vedeshkin on 10/3/2018.
 * All right reserved.
 */
public class Homework {
    public static void main(String[] args) {

        Controller controller = new Controller();
        Thread t1 = new Thread(new Data('B',controller));
        Thread t2 = new Thread(new Data('C',controller));
        Thread t3 = new Thread(new Data('A',controller));
        t1.start();
        t2.start();
        t3.start();

    }
}
