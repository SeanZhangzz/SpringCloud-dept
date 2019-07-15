package com.sean.rabbitmq.demo;

public class IntegerTest {

    public String name;

    public IntegerTest(){
        System.out.println("IntegerTest init");
    }

    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 128;
        Integer f = 128;
        Long g = 3L;
        Double h = 3d;
        Byte y = 4;
        System.out.println(c == d); //true
        System.out.println(e == f); //false  如果e、f范围在-128到127之内，结果为true
        System.out.println(e == 128); //true,自动拆箱
        System.out.println(c.equals(a + b)); //true，比较值
        System.out.println(g == (a + b)); //true 先自动拆箱成int，计算结果再自动装箱成Long
        System.out.println(h == (a + b)); //true
        System.out.println(g.equals(c)); //false 不是同一类型，返回false
        System.out.println(h + y);


       /* int a = 1;
        boolean b = false;
        char c = 2;
        int d = a+c;
       // int h = b+c;
        char g = 3;
        //int f = a+b;
        c += g;
        long l = 1l;
        d = l + d;*/



    }
}
