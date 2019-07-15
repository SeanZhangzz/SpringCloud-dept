package com.sean.rabbitmq.demo;

public class VolatileStopResort {
    private static volatile String str = "origin";
    private static volatile int number = 20;
    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            str = "ReaderThread" + number;
            System.out.println(number + ":ReaderThread[1]:set" + str);
        }
    }
    private static class ReaderThread2 extends Thread {
        @Override
        public void run() {
            System.out.println(number + ":ReaderThread[2]:get" + str);
        }
    }
    public static void main(String[] args) {
        for(;number>0;number--){
            new ReaderThread().start();
            new ReaderThread2().start();
        }
    }
}