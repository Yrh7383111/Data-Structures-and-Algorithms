package Merge_Sort;


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Comparator;



/*
Algorithm analysis:
Best case: ~ ½ (NlgN) compares
Worst case: ~ (NlgN) compares, 6 (NlgN) array accesses
Average case: ~ (NlgN) compares
 */
public class Merge
{
    // Variable
    private static final int CUTOFF = 7;                                            // Cutoff to insertion sort


    // Operations

    // Constructor
    private Merge(){}

    // Stably merge
    private static void merge(Comparable[] src, Comparable[] dst, int lo, int mid, int hi)
    {
        // Pre-condition: src[lo .. mid] and src[mid+1 .. hi] are sorted subarray
        assert isSorted(src, lo, mid);
        assert isSorted(src, mid+1, hi);

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
        {
            if (i > mid)                                                            // If the subarray starting with index i is exhausted
                dst[k] = src[j++];                                                  // src[j++] - 1. src[j]  2. j++
            else if (j > hi)                                                        // If the subarray starting with index i is exhausted
                dst[k] = src[i++];                                                  // src[i++] - 1. src[i]  2. i++
            else if (less(src[j], src[i]))
                dst[k] = src[j++];
            else
                dst[k] = src[i++];
        }

        // Post-condition: dst[lo .. hi] is sorted subarray
        assert isSorted(dst, lo, hi);
    }

    // Mergesort a[lo..hi]
    private static void sort(Comparable[] src, Comparable[] dst, int lo, int hi)
    {
        // "Base case" in the recursive function
        // Size of the array is small to effectively implement Insertion Sort
        if (hi <= lo + CUTOFF)
        {
            insertionSort(dst, lo, hi);
            return;
        }

        // Divide and conquer
        // Divide the entire array into a small piece with size 7
        int mid = lo + (hi - lo) / 2;
        sort(dst, src, lo, mid);
        sort(dst, src, mid+1, hi);

        // Array which is already in order
        // Still do the recursive calls, but the running time for any sorted subarray is linear
        if (!less(src[mid+1], src[mid]))
        {
            System.arraycopy(src, lo, dst, lo, hi - lo + 1);
            return;
        }

        // Merge two small pieces into one relatively large piece
        merge(src, dst, lo, mid, hi);
    }

    // Rearrange the array in ascending order, using the natural order.
    public static void sort(Comparable[] a)
    {
        Comparable[] aux = a.clone();
        sort(aux, a, 0, a.length-1);
        assert isSorted(a);
    }

    // Sort from a[lo] to a[hi] using insertion sort
    private static void insertionSort(Comparable[] a, int lo, int hi)
    {
        for (int i = lo; i <= hi; i++)
        {
            for (int j = i; j > lo && less(a[j], a[j - 1]); j--)
                exch(a, j, j - 1);
        }
    }


    /*******************************************************************
     *  Version that takes Comparator as argument.
     *******************************************************************/

    // Stably merge
    private static void merge(Object[] src, Object[] dst, int lo, int mid, int hi, Comparator comparator)
    {

        // Pre-condition: src[lo .. mid] and src[mid+1 .. hi] are sorted subarray
        assert isSorted(src, lo, mid, comparator);
        assert isSorted(src, mid+1, hi, comparator);

        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++)
        {
            if (i > mid)
                dst[k] = src[j++];
            else if (j > hi)
                dst[k] = src[i++];
            else if (less(src[j], src[i], comparator))
                dst[k] = src[j++];
            else
                dst[k] = src[i++];
        }

        // Post-condition: dst[lo .. hi] is sorted subarray
        assert isSorted(dst, lo, hi, comparator);
    }

    // Mergesort a[lo..hi]
    private static void sort(Object[] src, Object[] dst, int lo, int hi, Comparator comparator)
    {
        // if (hi <= lo) return;
        if (hi <= lo + CUTOFF)
        {
            insertionSort(dst, lo, hi, comparator);
            return;
        }

        int mid = lo + (hi - lo) / 2;
        sort(dst, src, lo, mid, comparator);
        sort(dst, src, mid+1, hi, comparator);

        // Array which is already in order
        // Still do the recursive calls, but the running time for any sorted subarray is linear
        if (!less(src[mid+1], src[mid], comparator))
        {
            System.arraycopy(src, lo, dst, lo, hi - lo + 1);
            return;
        }

        // Merge two small pieces into one relatively large piece
        merge(src, dst, lo, mid, hi, comparator);
    }

    // Sort from a[lo] to a[hi] using insertion sort
    private static void insertionSort(Object[] a, int lo, int hi, Comparator comparator)
    {
        for (int i = lo; i <= hi; i++)
        {
            for (int j = i; j > lo && less(a[j], a[j - 1], comparator); j--)
                exch(a, j, j - 1);
        }
    }



    /*******************************************************************
     *  Utility methods.
     *******************************************************************/

    // Exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j)
    {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    // Is a[i] < a[j]?
    private static boolean less(Comparable a, Comparable b)
    {
        return a.compareTo(b) < 0;
    }

    // Is a[i] < a[j]?
    private static boolean less(Object a, Object b, Comparator comparator)
    {
        return comparator.compare(a, b) < 0;
    }


    /***************************************************************************
     * Check if array is sorted - useful for debugging.
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

    private static boolean isSorted(Object[] a, Comparator comparator)
    {
        return isSorted(a, 0, a.length - 1, comparator);
    }

    private static boolean isSorted(Object[] a, int lo, int hi, Comparator comparator)
    {
        for (int i = lo + 1; i <= hi; i++)
        {
            if (less(a[i], a[i - 1], comparator))
                return false;
        }

        return true;
    }


    // Print array to standard output
    private static void show(Object[] a)
    {
        for (Object object : a)
            StdOut.print(object + " ");
        System.out.println();
    }



    public static void main(String[] args)
    {
        Integer[] array = new Integer[32];

        for (int i = 0; i < array.length; i++)
        {
            array[i] = StdRandom.uniform(20);
        }
        System.out.println("Original array: ");
        Merge.show(array);

        Merge.sort(array);
        System.out.println("Merge sorted array: ");
        Merge.show(array);
    }
}