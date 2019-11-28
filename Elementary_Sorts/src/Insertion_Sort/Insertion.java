package Insertion_Sort;


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Comparator;



/*
Invariants:
1. Entries to the left of ↑ (including ↑) are in ascending order.
2. Entries to the right of ↑ have not yet been seen.

Algorithm analysis:
Best case: If the array is in ascending order, insertion sort makes N– 1 compares and 0 exchanges.
Worst case: If the array is in descending order (and no duplicates), insertion sort makes ~ ½ N(2) compares and ~ ½ N(2) exchanges.
Average case: To sort a randomly-ordered array with distinct keys, insertion sort uses ~ ¼ N(2) compares and ~ ¼ N(2) exchanges on average.
Special case:  For partially-sorted arrays, insertion sort runs in linear time.
               (An array is partially sorted if the number of inversions is ≤ cN.)
               (An inversion is a pair of keys that are out of order.)
 */
public class Insertion
{
    // Constructor
    private Insertion(){}

    // Rearrange the array in ascending order, using the natural order.
    public static void sort(Comparable[] a)
    {
        int n = a.length;
        for (int i = 1; i < n; i++)
        {
            // Transitivity of total order
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--)
                exch(a, j, j-1);

            assert isSorted(a, 0, i);
        }
        assert isSorted(a);
    }

    // Rearrange the array in ascending order, using a comparator.
    public static void sort(Object[] a, Comparator comparator)
    {
        int n = a.length;
        for (int i = 1; i < n; i++)
        {
            for (int j = i; j > 0 && less(a[j], a[j-1], comparator); j--)
                exch(a, j, j-1);

            assert isSorted(a, 0, i, comparator);
        }
        assert isSorted(a, comparator);
    }

    // Return a permutation that gives the elements in a[] in ascending order
    // Do not change the original array a[]
    public static int[] indexSort(Comparable[] a)
    {
        // Variable
        int n = a.length;
        int[] index = new int[n];

        // Operations
        for (int i = 0; i < n; i++)
            index[i] = i;

        for (int i = 1; i < n; i++)
        {
            for (int j = i; j > 0 && less(a[index[j]], a[index[j - 1]]); j--)
                exch(index, j, j - 1);
        }

        return index;
    }



    // Helper sorting functions

    // Is v < w ?
    private static boolean less(Comparable v, Comparable w)
    {
        return v.compareTo(w) < 0;
    }

    // Is v < w ?
    private static boolean less(Object v, Object w, Comparator comparator)
    {
        return comparator.compare(v, w) < 0;
    }

    // Exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j)
    {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    // Exchange a[i] and a[j]  (for indirect sort)
    private static void exch(int[] a, int i, int j)
    {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }


    // Check if array is sorted

    private static boolean isSorted(Comparable[] a)
    {
        return isSorted(a, 0, a.length);
    }

    // Is the array a[lo..hi] sorted
    private static boolean isSorted(Comparable[] a, int lo, int hi)
    {
        for (int i = lo + 1; i < hi; i++)
            if (less(a[i], a[i-1]))
                return false;

        return true;
    }

    private static boolean isSorted(Object[] a, Comparator comparator)
    {
        return isSorted(a, 0, a.length, comparator);
    }

    // Is the array a[lo..hi) sorted
    private static boolean isSorted(Object[] a, int lo, int hi, Comparator comparator)
    {
        for (int i = lo + 1; i < hi; i++)
        {
            if (less(a[i], a[i - 1], comparator))
                return false;
        }

        return true;
    }


    // print array to standard output
    private static void show(Comparable[] a)
    {
        for (Comparable comparable : a)
            StdOut.print(comparable + " ");
        System.out.println();
    }

    private static void show(int[] a)
    {
        for (int element : a)
            StdOut.print(element + " ");
        System.out.println();
    }



    public static void main(String[] args)
    {
        // Variable
        Integer[] array = new Integer[20];
        int[] indexArray = new int[20];


        // Operations
        for (int i = 0; i < array.length; i++)
        {
            array[i] = StdRandom.uniform(20);
        }
        System.out.println("Original array: ");
        show(array);

        indexArray = indexSort(array);
        System.out.println("Index array: ");
        show(indexArray);

        Insertion.sort(array);
        System.out.println("Sorted array: ");
        show(array);
    }
}