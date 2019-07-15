package com.sean.rabbitmq.demo;

public class VolatileTest2 {

    //public static  AtomicInteger race = new AtomicInteger();
    public static  int race = 5;

    public static void increase(){
        race += 5;
        //race.addAndGet(1);
    }

    public static void main(String[] args) throws InterruptedException {
        /*Thread[] threads = new Thread[20];
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
        }*/
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int a  = race ;

                System.out.println(race);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(race);
            }
        });
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                increase();

            }
        });

        thread2.start();

        while(Thread.activeCount() > 2){
            Thread.yield();
        }

        thread2 = null;

        try{
            thread2.start();
        }catch (IndexOutOfBoundsException e){
            System.out.println("#####NullPointerException catch");
            e.printStackTrace();
        }

//        Thread.sleep(3000);

       // System.out.println(race);

    }

}
