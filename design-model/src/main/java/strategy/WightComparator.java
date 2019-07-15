package strategy;

public class WightComparator implements Comparator<Cat> {
    @Override
    public int compare(Cat o1, Cat o2) {
        return o1.getWeight() - o2.getWeight() > 0 ? 1 : -1;
    }
}
