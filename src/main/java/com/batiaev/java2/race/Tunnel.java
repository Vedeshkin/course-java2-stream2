package com.batiaev.java2.race;

import java.util.concurrent.Semaphore;

/**
 * Created by Vedeshkin on 10/8/2018.
 * All right reserved.
 */
public class Tunnel extends Stage {
    private Semaphore limit;
    public Tunnel(int limit) {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        this.limit = new Semaphore(limit);
    }
    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                limit.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
                limit.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
