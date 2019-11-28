package Shuffling;


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;



public class KnuthShuffling
{
    public static void shuffle(Object[] a)
    {
        int N = a.length;

        // Linear time
        for (int i = 0; i < N; i++)
        {
            int r = StdRandom.uniform(i + 1);
            exch(a, i, r);
        }
    }

    // Exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j)
    {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
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
            array[i] = i;
        }
        System.out.println("Original array: ");
        show(array);

        KnuthShuffling.shuffle(array);
        System.out.println("Shuffled array: ");
        show(array);
    }
}
