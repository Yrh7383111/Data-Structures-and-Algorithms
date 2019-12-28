package Coding_problems;


import Binary_Heap.MaxPQ;
import Binary_Heap.MinPQ;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.NoSuchElementException;



public class MedianHeap
{
    // Variables

    // Store all the numbers greater than the current median in a minheap, i.e median is the minimum, at the root
    private MinPQ<Integer> minheap;
    // Store all the numbers less than the current median in a maxheap, i.e median is the maximum, at the root
    private MaxPQ<Integer> maxheap;


    // Operations
    public MedianHeap()
    {
        minheap = new MinPQ<Integer>();
        maxheap = new MaxPQ<Integer>();
    }

    public boolean isEmpty()
    {
        return (maxheap.size() == 0 && minheap.size() == 0);
    }

    public void insert(int n)
    {
        // Initialize if empty
        if (isEmpty())
            minheap.insert(n);
        else {
            // If n is less than or equal to current median, add to maxheap
            if (Integer.compare(n, findMed()) <= 0)
                maxheap.insert(n);
            // If n is greater than current median, add to min heap
            else
                minheap.insert(n);
        }

        // Balance the heap
        // Absolute difference of sizes is greater than one.
        balanceHeap();
    }

    public void deleteMed()
    {
        if (isEmpty())
            throw new NoSuchElementException("Underflow");
        // Else
        if ((maxheap.size() == minheap.size()) || (maxheap.size() > minheap.size()))
            maxheap.delMax();
        else
            minheap.delMin();

        // Balance the heap
        balanceHeap();
    }

    public int findMed()
    {
        // If total size(no. of elements entered) is even OR maxheap has a larger size
        // then return the root of maxheap
        if ((maxheap.size() == minheap.size()) || (maxheap.size() > minheap.size()))
            return maxheap.max();
        else
            return minheap.min();
    }


    // Helper functions
    // If number of items in one of the heap is greater than other heap by more than 1, remove the root of larger heap and add it to other heap.
    private void balanceHeap()
    {
        // If sizes of heaps differ more than 1,
        if (Math.abs(maxheap.size() - minheap.size()) > 1)
        {
            if (maxheap.size() > minheap.size())
                minheap.insert(maxheap.delMax());
            else
                maxheap.insert(minheap.delMin());
        }
    }



    public static void main(String[] args)
    {
        // Variables
        Integer[] a = new Integer[10];
        for (int i = 0; i < a.length; i++)
            a[i] = StdRandom.uniform(20);
        MedianHeap MH = new MedianHeap();


        // Operations
//        while (!StdIn.isEmpty())
//        {
//            String item = StdIn.readString();
//
//            if (!item.equals("-"))
//                pq.insert(item);
//            else if (!pq.isEmpty())
//                StdOut.print(pq.delMax() + " ");
//        }
//        StdOut.println("(" + pq.size() + " left on pq)");

        Arrays.sort(a);
        for (Integer element : a)
        {
            System.out.print(element + " ");
            MH.insert(element);
        }
        System.out.println();

        System.out.println(MH.findMed());
        MH.deleteMed();
        System.out.println(MH.findMed());
    }
}