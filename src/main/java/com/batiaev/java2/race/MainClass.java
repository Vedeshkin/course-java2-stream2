package com.batiaev.java2.race;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * Created by Vedeshkin on 10/8/2018.
 * All right reserved.
 */
public class MainClass {
    public static final int CARS_COUNT = 4;
    public static final CountDownLatch READY_TO_GO = new CountDownLatch(CARS_COUNT +1 );
    public static final Semaphore TUNNEL_BLOCK = new Semaphore(CARS_COUNT/2,false);
    public static final CountDownLatch FINISH = new CountDownLatch(CARS_COUNT);
    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        while (READY_TO_GO.getCount() > 1){
            try {
                Thread.sleep(100);
            }catch (InterruptedException ie){
                ie.printStackTrace();
            }
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        READY_TO_GO.countDown();
        try {
            FINISH.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }

}
