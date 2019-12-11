package Quick_Sort;


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;



/*
Algorithm analysis:
Best case: ~ (N) compares
Worst case: ~ ~ ½ N(2) compares
Average case: ~ 1.39 (NlgN) compares
              39% more compares than mergesort, but faster than mergesort in practice because of less data movement.
 */
public class Quick3way
{
    // Constructor
    private Quick3way() {}

    public static void sort(Comparable[] a)
    {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
        assert isSorted(a);
    }

    // Quicksort the subarray a[lo .. hi] using 3-way partitioning
    private static void sort(Comparable[] a, int lo, int hi)
    {
        if (hi <= lo)
            return;
        // Else

        // Variables
        int lt = lo, gt = hi;
        Comparable v = a[lo];
        int i = lo + 1;


        // Operations
        while (i <= gt)
        {
            int cmp = a[i].compareTo(v);

            if (cmp < 0)
                exch(a, lt++, i++);
            else if (cmp > 0)
                exch(a, i, gt--);
            else
                i++;
        }

        // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi].
        sort(a, lo, lt-1);
        sort(a, gt+1, hi);

        assert isSorted(a, lo, hi);
    }


    /***************************************************************************
     *  Helper sorting functions.
     ***************************************************************************/

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
        String[] a = StdIn.readAllStrings();
        Quick3way.sort(a);
        show(a);
    }
}