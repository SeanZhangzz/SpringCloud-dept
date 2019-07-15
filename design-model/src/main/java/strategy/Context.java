package strategy;

import java.util.List;

public class Context {

    //public static <T extends Comparator<? super T>> void sort(List<T> list, Comparator<T> comparator) {
    public static <T> void sort(List<T> list, Comparator<T> comparator) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (comparator.compare(list.get(i), list.get(j)) > 0) {
                    swap(list, i, j);
                }
            }
        }

    }

    // private static <T extends Comparator<? super T>> void swap(List<T> list ,int index, int location) {
    private static <T> void swap(List<T> list ,int index, int location) {
        T temp = list.get(index);
        list.set(index, list.get(location));
        list.set(location, temp);
    }


}
