import java.util.Arrays;

public class Inversions {

    int counter = 0;

    public Inversions() {

    }

    public int easyinversioncount(int[] a) {
        int count = 0;

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if ((i < j) && (a[i] > a[j])) count += 1;
            }
        }

        return count;
    }

    public int fastinversioncount(int[] a) {
        counter = 0;
        mergeSort(a);
        return counter;
    }

    public int[] mergeSort(int[] a) {
        float n = a.length;
        int floor = (int) Math.floor(n/2);

        int[] b = new int[floor];
        int[] c = new int[a.length-floor];

        if (n > 1) {
            System.arraycopy(a, 0, b, 0, floor);
            System.arraycopy(a, floor, c, 0, a.length-floor);

            b = mergeSort(b);
            c = mergeSort(c);

            a = merge(b, c, a);
        }

        return a;
    }

    public int[] merge(int[] b, int[] c, int[] a) {
        int i = 0, j = 0, k = 0;
        int p = b.length;
        int q = c.length;

        while (i < p && j < q) {
            if (b[i] <= c[j]) {
                a[k] = b[i];
                i += 1;
            } else {
                counter += p-i;
                a[k] = c[j];
                j += 1;
            }
            k += 1;
        }

        if (i == p) {
            System.arraycopy(c, j, a, k, q-j);
        } else {
            System.arraycopy(b, i, a, k, p-i);
        }

        return a;
    }
}
