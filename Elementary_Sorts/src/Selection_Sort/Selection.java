package Selection_Sort;


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Comparator;


/*
Invariants:
1. Entries the left of ↑ (including ↑) fixed and in ascending order.
2. No entry to right of ↑ is smaller than any entry to the left of ↑.

Algorithm analysis:
(Best, Worst and Average cases): Selection sort uses (N– 1) + (N– 2) + ... + 1 + 0 (~ ½ N(2)) compares, and N exchanges.

Pros and Cons:
Pro: Data movement is minimal. Linear number of exchanges.
Con: Running time insensitive to input. Quadratic time, even if input is sorted.
 */

public class Selection
{
    // Constructor
    private Selection(){}

    // Rearrange the array in ascending order, using the natural order.
    public static void sort(Comparable[] a)
    {
        int n = a.length;

        for (int i = 0; i < n; i++)
        {
            int min = i;
            for (int j = i+1; j < n; j++)
            {
                if (less(a[j], a[min]))
                    min = j;
            }
            exch(a, i, min);
            assert isSorted(a, 0, i);
        }
        assert isSorted(a);
    }

    // Rearrange the array in ascending order, using a comparator.
    public static void sort(Object[] a, Comparator comparator)
    {
        int n = a.length;
        for (int i = 0; i < n; i++)
        {
            int min = i;
            for (int j = i+1; j < n; j++)
            {
                if (less(comparator, a[j], a[min]))
                    min = j;
            }
            exch(a, i, min);
            assert isSorted(a, comparator, 0, i);
        }
        assert isSorted(a, comparator);
    }


    // Helper sorting functions.

    // Is v < w ?
    private static boolean less(Comparable v, Comparable w)
    {
        return v.compareTo(w) < 0;
    }

    // is v < w ?
    private static boolean less(Comparator comparator, Object v, Object w)
    {
        return comparator.compare(v, w) < 0;
    }

    private static void exch(Object[] a, int i, int j)
    {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }


    // Check if array is sorted - useful for debugging.

    // Is the array a[] sorted?
    private static boolean isSorted(Comparable[] a)
    {
        return isSorted(a, 0, a.length - 1);
    }

    // Is the array sorted from a[lo] to a[hi]?
    private static boolean isSorted(Comparable[] a, int lo, int hi)
    {
        for (int i = lo + 1; i <= hi; i++)
        {
            if (less(a[i], a[i - 1]))
                return false;
        }

        return true;
    }

    // Is the array a[] sorted?
    private static boolean isSorted(Object[] a, Comparator comparator)
    {
        return isSorted(a, comparator, 0, a.length - 1);
    }

    // Is the array sorted from a[lo] to a[hi]?
    private static boolean isSorted(Object[] a, Comparator comparator, int lo, int hi)
    {
        for (int i = lo + 1; i <= hi; i++)
            if (less(comparator, a[i], a[i-1]))
                return false;

        return true;
    }


    // Print array to standard output
    private static void show(Comparable[] a)
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
        show(array);

        Selection.sort(array);
        show(array);
    }
}