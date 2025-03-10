package chap1;

public class Insertion extends AbstractSort {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--)
                exch(a, j, j-1);
        }
        assert isSorted(a);
    }

    public static void main(String[] args) {
        Integer[] a = {10, 4, 5, 2, 1, 8, 3, 6};
        Insertion.sort(a);
        Insertion.show(a);
    }
}
