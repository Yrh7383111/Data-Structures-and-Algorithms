package Merge_Sort;


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;



public class MergeBU
{
    // Constructor
    private MergeBU(){}

    // Stably merge
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi)
    {
        // Copy to aux[]
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];

        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++)
        {
            if (i > mid)                                                    // This copying is unneccessary
                a[k] = aux[j++];
            else if (j > hi)
                a[k] = aux[i++];
            else if (less(aux[j], aux[i]))
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }
    }

    // Mergesort a[lo..hi]
    public static void sort(Comparable[] a)
    {
        int n = a.length;
        Comparable[] aux = new Comparable[n];

        for (int len = 1; len < n; len *= 2)                                // lgN
        {
            for (int lo = 0; lo < n-len; lo += len + len)                   // N (Linear time)
            {
                int mid  = lo + len - 1;
                int hi = Math.min(lo + len + len - 1, n - 1);
                merge(a, aux, lo, mid, hi);
            }
        }
        assert isSorted(a);
    }


    /***********************************************************************
     *  Helper sorting functions.
     ***************************************************************************/

    // Is v < w ?
    private static boolean less(Comparable v, Comparable w)
    {
        return v.compareTo(w) < 0;
    }


    /***************************************************************************
     *  Check if array is sorted - useful for debugging.
     ***************************************************************************/

    private static boolean isSorted(Comparable[] a)
    {
        for (int i = 1; i < a.length; i++)
        {
            if (less(a[i], a[i - 1]))
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
        MergeBU.show(array);

        MergeBU.sort(array);
        System.out.println("Merge sorted array: ");
        MergeBU.show(array);
    }
}