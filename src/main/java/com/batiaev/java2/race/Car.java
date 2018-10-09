package com.batiaev.java2.race;

import sun.applet.Main;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Vedeshkin on 10/8/2018.
 * All right reserved.
 */
public class Car  implements Runnable{
    private static int CARS_COUNT;
    private static final CountDownLatch READY_TO_GO;

    public static CountDownLatch getReadyToGo() {
        return READY_TO_GO;
    }

    static {
        CARS_COUNT = 0;
        READY_TO_GO = new CountDownLatch(MainClass.CARS_COUNT +1 );
    }
    private Race race;
    private int speed;
    private String name;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        this.name = "Участник #" + ++CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
         READY_TO_GO.countDown();

        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        if (MainClass.FINISH.getCount() == MainClass.CARS_COUNT) {
            System.out.printf("%s  - выиграл гонку \n", name);
        }
            MainClass.FINISH.countDown();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
