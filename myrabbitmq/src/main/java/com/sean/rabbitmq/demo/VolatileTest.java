package com.sean.rabbitmq.demo;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileTest {

    public static  AtomicInteger race = new AtomicInteger();
    //public static volatile   int race = 0;

    public static void increase(){
        //race++;
        race.addAndGet(1);

    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[20];
        for(int i = 0; i<20; i++){
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i = 0; i<1000; i++){
                        increase();
                    }
                }
            });
            threads[i].start();
        }

        while(Thread.activeCount() > 2){
            Thread.yield();
        }

//        Thread.sleep(3000);

        System.out.println(race);

    }

}
