package observer;

import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;

public class TestMain {

    public static void main(String[] args) {

        SaleObserverable observable = new SaleObserverable();

        Observer observerA = new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                System.out.println("observerA begin sale:" + arg);
            }
        };
        Observer observerB = new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                System.out.println("observerB begin sale:" + arg);
            }
        };

        observable.addObserver(observerA);
        observable.addObserver(observerB);


        Calendar calendar = Calendar.getInstance();
        calendar.set(2019,6,0,17,25,0);
        observable.startCountDown(calendar.getTimeInMillis());

    }
}
