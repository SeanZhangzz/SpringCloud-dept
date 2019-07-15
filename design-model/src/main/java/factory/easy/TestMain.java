package factory.easy;

import factory.IUserOperation;
import factory.User;

public class TestMain {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        //EasyFactory factory = new EasyFactory();
        //ReflectFactory factory = new ReflectFactory();
        IUserOperation userOperation = ReflectFactory.createUser();
        userOperation.insert(new User());

        byte[] numbers = new byte[10];
        //System.out.println(numbers[5]);
        int a = 1;
        int b=5;

        System.out.println(++a==2 ^ ++b==5);
        System.out.println(b);

    }
}
