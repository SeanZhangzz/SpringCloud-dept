package observer;

import java.util.Observable;

public class SaleObserverable extends Observable {

    public SaleObserverable() {
    }

    public void measurementsChanged() {
        setChanged();
        notifyObservers("iphone8");
    }

    public void startCountDown(Long timeMis) {
        while (System.currentTimeMillis() < timeMis) {
            Thread.yield();
        }
        measurementsChanged();
    }

}
