package com.sean.rabbitmq.demo;

import java.io.InputStream;

interface ParentTest {

    static IntegerTest f1 = null;

    static IntegerTest test = new IntegerTest();
}

public class Test implements ParentTest {

    /**
     * @param args
     */
    static int x = 1;
    static String s = "123";
    static Object o = test;

    static {
        if (s.equals("1234"))
            test.equals("1");
        if (x == 1) {
            x = 2;
        }
        System.out.println("Test static ...");
    }

    {
        System.out.println("Test <init>");
        /*if (s.equals("345"))
            s = "678";
        if (x == 2)
            x = 3;
        ++x;*/
    }

    public Test() {
        /*System.out.println(x);
        System.out.println(s);*/
    }

    public void hello(Test t){

    }
    public void hello(ParentTest t){

    }


    public static void main(String[] args) throws Exception {
        ClassLoader myloader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".")+1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        System.out.println("is null...");
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (Exception e) {
                    System.out.println("io exception");
                    throw new ClassNotFoundException(name);
                }

            }
        };


        Object obj = myloader.loadClass("com.sean.rabbitmq.demo.VolatileTest").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof com.sean.rabbitmq.demo.VolatileTest);
        ClassLoader.getSystemClassLoader();
    }

}
