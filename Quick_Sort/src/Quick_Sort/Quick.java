package Quick_Sort;


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;



public class Quick
{

    // Constructor
    private Quick() { }


    public static void sort(Comparable[] a)
    {
        // Randomly shuffle the array
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
        assert isSorted(a);
    }

    // Quicksort the subarray from a[lo] to a[hi]
    private static void sort(Comparable[] a, int lo, int hi)
    {
        if (hi <= lo)
            return;
        // Else
        int j = partition(a, lo, hi);
        // Divide and conquer
        sort(a, lo, j-1);
        sort(a, j+1, hi);

        assert isSorted(a, lo, hi);
    }

    // Partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
    // and return the index j.
    private static int partition(Comparable[] a, int lo, int hi)
    {
        // Variable
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];


        // Operations
        while (true)
        {
            // Find item on lo to swap
            while (less(a[++i], v))
                if (i == hi)
                    break;

            // Find item on hi to swap
            while (less(v, a[--j]))
                // Redundant since a[lo] acts as sentinel
                if (j == lo)
                    break;

            // Check if pointers cross
            if (i >= j)
                break;
            // Else
            exch(a, i, j);
        }

        // Put partitioning item v at a[j]
        exch(a, lo, j);

        // Now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }

    // Given an array of n items, find the kth smallest item
    public static Comparable select(Comparable[] a, int k)
    {
        if (k < 0 || k >= a.length)
            throw new IllegalArgumentException("index is not between 0 and " + a.length + ": " + k);
        // Else

        StdRandom.shuffle(a);

        int lo = 0, hi = a.length - 1;
        while (hi > lo)
        {
            int i = partition(a, lo, hi);

            if (i > k)
                hi = i - 1;
            else if (i < k)
                lo = i + 1;
            else
                return a[i];
        }
        return a[lo];
    }


    /***************************************************************************
     *  Helper sorting functions.
     ***************************************************************************/

    // Is v < w ?
    private static boolean less(Comparable v, Comparable w)
    {
        if (v == w)
            // Optimization when reference equals
            return false;

        return v.compareTo(w) < 0;
    }

    // Exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j)
    {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }


    /***************************************************************************
     *  Check if array is sorted - useful for debugging.
     ***************************************************************************/

    private static boolean isSorted(Comparable[] a)
    {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi)
    {
        for (int i = lo + 1; i <= hi; i++)
        {
            if (less(a[i], a[i - 1]))
                return false;
        }
        return true;
    }


    // Print array to standard output
    private static void show(Comparable[] a)
    {
        for (Comparable comparable : a)
            StdOut.print(comparable + " ");
        StdOut.println();
    }



    public static void main(String[] args)
    {
        Integer[] a = new Integer[32];

        for (int i = 0; i < a.length; i++)
        {
            a[i] = StdRandom.uniform(20);
        }
        System.out.println("Original array: ");
        Quick.show(a);

        Quick.sort(a);
        System.out.println("Quick sorted array: ");
        Quick.show(a);
        assert isSorted(a);

        // Shuffle
        StdRandom.shuffle(a);

        // Display results again using select
        System.out.println("Quick-select sorted array: ");
        for (int i = 0; i < a.length; i++)
        {
            Integer ith = (Integer) Quick.select(a, i);
            StdOut.print(ith + " ");
        }
    }
}
