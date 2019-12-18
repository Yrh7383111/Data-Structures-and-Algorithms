package Heap_Sort;


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


/*
Algorithm analysis:
Best case: ~ (NlgN) compares
Worst case: ~ 2 (NlgN) compares
Average case: ~ 2 (NlgN) compares
 */
public class Heap
{
    // Constructor
    private Heap() { }


    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param pq the array to be sorted
     */
    public static void sort(Comparable[] pq)
    {
        // Variable
        int n = pq.length;


        // Operations

        // First phase -
        // Heap construction, where we reorganize the original array into a heap.
        for (int k = n/2; k >= 1; k--)
            sink(pq, k, n);

        // Second phase -
        // Sortdown, where we pull the items out of the heap in decreasing order to build the sorted result.
        while (n > 1)
        {
            exch(pq, 1, n--);
            sink(pq, 1, n);
        }
    }


    /***************************************************************************
     * Helper functions to restore the heap invariant.
     ***************************************************************************/

    private static void sink(Comparable[] pq, int k, int n)
    {
        while (2 * k <= n)
        {
            // Variable
            int j = 2 * k;


            // Operations
            if (j < n && less(pq, j, j+1))
                j++;
            if (!less(pq, k, j))
                break;
            exch(pq, k, j);
            k = j;
        }
    }


    /***************************************************************************
     * Helper functions for comparisons and swaps.
     * Indices are "off-by-one" to support 1-based indexing.
     ***************************************************************************/
    private static boolean less(Comparable[] pq, int i, int j)
    {
        return pq[i-1].compareTo(pq[j-1]) < 0;
    }

    private static void exch(Object[] pq, int i, int j)
    {
        Object swap = pq[i-1];
        pq[i-1] = pq[j-1];
        pq[j-1] = swap;
    }


    // Print array to standard output
    private static void show(Comparable[] a)
    {
        for (Comparable comparable : a)
            StdOut.print(comparable + " ");
        StdOut.println();
    }



    /**
     * Reads in a sequence of strings from standard input; heapsort them;
     * and prints them to standard output in ascending order.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args)
    {
        Integer[] a = new Integer[32];

        for (int i = 0; i < a.length; i++)
        {
            a[i] = StdRandom.uniform(20);
        }
        System.out.println("Original array: ");
        Heap.show(a);

        Heap.sort(a);
        System.out.println("Heap sorted array: ");
        Heap.show(a);
    }
}