package strategy;

import java.util.ArrayList;
import java.util.List;

public class TestMain {

    public static void main(String[] args) {

        List<Cat> list = new ArrayList<>();
        list.add(new Cat("dasiy",9));
        list.add(new Cat("sean", 14));
        list.add(new Cat("mod",5));

        //Context.sort(list, new WightComparator());
        Context.sort(list, new WightComparator());

        for(Cat c : list){
            System.out.println(c);

        }



    }
}
