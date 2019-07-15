package com.sean.rabbitmq.demo;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MessageStorageByLock {
    private int maxSize;
    private List<String> messages;

    private final ReentrantLock lock;
    private final Condition conditionWrite;//声明两个锁条件
    private final Condition conditionRead;
    public MessageStorageByLock(int maxSize) {
        this.maxSize = maxSize;
        messages = new LinkedList<String>();
        lock = new ReentrantLock(true);//true修改锁的公平性，为true时，使用lifo队列来顺序获得锁
        conditionWrite = lock.newCondition();//调用newCondition()方法，即new ConditionObject();
        conditionRead = lock.newCondition();

    }
    public void set(String message){
        //使用锁实现同步，获取所得操作，当锁被其他线程占用时，当前线程将进入休眠
        lock.lock();
        try{
            while(messages.size() == maxSize){
                System.out.print("the message buffer is full now,start into wait()\n");
                conditionWrite.await();//满足条件时，线程休眠并释放锁。当调用 signalAll()时。线程唤醒并重新获得锁
            }
            Thread.sleep(100);
            messages.add(message);
            System.out.print("add message:"+message+" success\n");
            conditionRead.signalAll();//唤醒因conditionRead.await()休眠的线程
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public String get(){
        String message = null;
        lock.lock();
        try{
            while(messages.size() == 0){
                conditionRead.await();
                System.out.print("the message buffer is empty now,start into wait()\n");
            }
            Thread.sleep(100);
            message = ((LinkedList<String>)messages).poll();
            System.out.print("get message:"+message+" success\n");
            conditionWrite.signalAll();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return message;
    }
}