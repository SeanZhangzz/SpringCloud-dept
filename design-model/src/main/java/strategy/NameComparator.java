package strategy;

public class NameComparator implements Comparator<Cat> {

    @Override
    public int compare(Cat o1,Cat o2) {
        int nameLengthForO1 = o1.getName().length();
        int nameLengthForO2 = o2.getName().length();

        return nameLengthForO1 - nameLengthForO2 > 0 ? 1 : -1;
    }
}
