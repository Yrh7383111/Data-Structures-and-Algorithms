package Shell_Sort;


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


/*
Algorithm analysis:
Best case: ~ (NlogN) compares based on 3x+1 increments
Worst case: ~ N(3/2) compares
Average case: ~ N(3/2) compares
 */
public class Shell
{
    // Constructor
    private Shell() { }

    // Rearrange the array in ascending order, using the natural order.
    public static void sort(Comparable[] a)
    {
        int n = a.length;

        // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ...
        int h = 1;
        while (h < n / 3)
            h = 3 * h + 1;

        while (h >= 1)
         {
            // h-sort the array
            for (int i = h; i < n; i++)
            {
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h)
                    exch(a, j, j - h);
            }
            assert isHsorted(a, h);
            h /= 3;
        }
        assert isSorted(a);
    }


    // Helper sorting functions.

    // Is v < w ?
    private static boolean less(Comparable v, Comparable w)
    {
        return v.compareTo(w) < 0;
    }

    // Exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j)
    {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }


    // Check if array is sorted - useful for debugging.

    private static boolean isSorted(Comparable[] a)
    {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1]))
                return false;

        return true;
    }

    // is the array h-sorted?
    private static boolean isHsorted(Comparable[] a, int h)
    {
        for (int i = h; i < a.length; i++)
            if (less(a[i], a[i-h]))
                return false;

        return true;
    }

    // Print array to standard output
    public static void show(Comparable[] a)
    {
        for (Comparable comparable : a)
            StdOut.print(comparable + " ");
        System.out.println();
    }



    public static void main(String[] args)
    {
        // Variable
        Integer[] array = new Integer[20];


        // Operations
        for (int i = 0; i < array.length; i++)
        {
            array[i] = StdRandom.uniform(20);
        }
        System.out.println("Original array: ");
        Shell.show(array);

        Shell.sort(array);
        System.out.println("Shell sorted array: ");
        Shell.show(array);
    }
}