package com.sean.rabbitmq.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RawType {


    public static void method(List<Integer> list){

    }


    public static void main(String[] args) {
//        Map<String,String> map = new HashMap<>();
//        map.put("hello","nihao");
//        map.put("h","a");
//        String a = "c";
//        System.out.println( map.get("hello"));

        List<Integer> list = new ArrayList<>();
        list.add(123);
        //list.add(new IntegerTest());
        System.out.println(list.get(0));

    }
}
