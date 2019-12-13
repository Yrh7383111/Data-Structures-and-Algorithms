package Binary_Heap;


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;



public class MaxPQ<Key> implements Iterable<Key>
{
    // Variable
    private Key[] pq;                                                                       // Store items at indices 1 to n
    private int n;                                                                          // Number of items on priority queue
    private Comparator<Key> comparator;                                                     // Optional comparator


    // Operations

    // Five different constructors (two for non-comparator based, two for comparators based, one for array based)
    /**
     * Initializes an empty priority queue with the given initial capacity.
     *
     * @param  initCapacity the initial capacity of this priority queue
     */
    public MaxPQ(int initCapacity)
    {
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
    }

    /**
     * Initializes an empty priority queue.
     */
    public MaxPQ()
    {
        this(1);
    }

    /**
     * Initializes an empty priority queue with the given initial capacity,
     * using the given comparator.
     *
     * @param  initCapacity the initial capacity of this priority queue
     * @param  comparator the order in which to compare the keys
     */
    public MaxPQ(int initCapacity, Comparator<Key> comparator)
    {
        this.comparator = comparator;
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
    }

    /**
     * Initializes an empty priority queue using the given comparator.
     *
     * @param  comparator the order in which to compare the keys
     */
    public MaxPQ(Comparator<Key> comparator) {
        this(1, comparator);
    }

    /**
     * Initializes a priority queue from the array of keys.
     * Takes time proportional to the number of keys, using sink-based heap construction.
     *
     * @param  keys the array of keys
     */
    public MaxPQ(Key[] keys)
    {
        // Variable
        n = keys.length;
        pq = (Key[]) new Object[keys.length + 1];


        // Operations
        for (int i = 0; i < n; i++)
            // Leave pq[0] empty
            pq[i + 1] = keys[i];
        for (int k = n / 2; k >= 1; k--)
            sink(k);

        assert isMaxHeap();
    }


    /**
     * Returns true if this priority queue is empty.
     *
     * @return {@code true} if this priority queue is empty;
     *         {@code false} otherwise
     */
    public boolean isEmpty()
    {
        return n == 0;
    }

    /**
     * Returns the number of keys on this priority queue.
     *
     * @return the number of keys on this priority queue
     */
    public int size()
    {
        return n;
    }

    /**
     * Returns a largest key on this priority queue.
     *
     * @return a largest key on this priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    public Key max()
    {
        if (isEmpty())
            throw new NoSuchElementException("Priority queue underflow");
        // Else
        return pq[1];
    }

    // Helper function to double the size of the heap array
    private void resize(int capacity)
    {
        assert capacity > n;

        Key[] temp = (Key[]) new Object[capacity];

        for (int i = 1; i <= n; i++)
            temp[i] = pq[i];

        pq = temp;
    }

    /**
     * Adds a new key to this priority queue.
     *
     * @param  x the new key to add to this priority queue
     */

    // At most (1 + lgn) compares
    public void insert(Key x)
    {
        // Double size of array if necessary
        if (n == pq.length - 1)
            resize(2 * pq.length);

        // Add x, and percolate it up to maintain heap invariant
        pq[++n] = x;
        swim(n);

        assert isMaxHeap();
    }

    /**
     * Removes and returns a largest key on this priority queue.
     *
     * @return a largest key on this priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */

    // At most (2 lgn) compares
    public Key delMax()
    {
        if (isEmpty())
            throw new NoSuchElementException("Priority queue underflow");
        // Else
        Key max = pq[1];
        exch(1, n--);                                                                       // Exchange root with node at end
        sink(1);                                                                            // Sink the new root down
        pq[n+1] = null;                                                                         // To avoid loitering and help with garbage collection

        if ((n > 0) && (n == (pq.length - 1) / 4))
            resize(pq.length / 2);

        assert isMaxHeap();
        return max;
    }


    /***************************************************************************
     * Helper functions to restore the heap invariant.
     ***************************************************************************/

    private void swim(int k)
    {
        while (k > 1 && less(k/2, k))
        {
            exch(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k)
    {
        while (2 * k <= n)
        {
            // Variable
            int j = 2 * k;


            // Operations
            if (j < n && less(j, j+1))
                j++;
            if (!less(k, j))
                break;
            exch(k, j);
            k = j;
        }
    }


    /***************************************************************************
     * Helper functions for compares and swaps.
     ***************************************************************************/

    private boolean less(int i, int j)
    {
        if (comparator == null)
        {
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
        } else {
            return comparator.compare(pq[i], pq[j]) < 0;
        }
    }

    private void exch(int i, int j)
    {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    // Is pq[1..n] a max heap?
    private boolean isMaxHeap()
    {
        for (int i = 1; i <= n; i++)
        {
            if (pq[i] == null)
                return false;
        }
        for (int i = n + 1; i < pq.length; i++)
        {
            if (pq[i] != null)
                return false;
        }

        // Leave pq[0] empty
        if (pq[0] != null)
            return false;

        return isMaxHeapOrdered(1);
    }

    // Is subtree of pq[1..n] rooted at k a max heap?
    private boolean isMaxHeapOrdered(int k)
    {
        if (k > n)
            return true;

        int left = 2 * k;
        int right = 2 * k + 1;

        if (left <= n && less(k, left))
            return false;
        if (right <= n && less(k, right))
            return false;

        return isMaxHeapOrdered(left) && isMaxHeapOrdered(right);
    }


    /***************************************************************************
     * Iterator.
     ***************************************************************************/

    /**
     * Returns an iterator that iterates over the keys on this priority queue
     * in descending order.
     * The iterator doesn't implement {@code remove()} since it's optional.
     *
     * @return an iterator that iterates over the keys in descending order
     */
    public Iterator<Key> iterator()
    {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Key>
    {
        // Create a new pq
        private MaxPQ<Key> copy;

        // Add all items to copy of heap
        // Takes linear time since already in heap order so no keys move
        public HeapIterator()
        {
            if (comparator == null)
                copy = new MaxPQ<Key>(size());
            else
                copy = new MaxPQ<Key>(size(), comparator);

            for (int i = 1; i <= n; i++)
                copy.insert(pq[i]);
        }

        public boolean hasNext()
        {
            return !copy.isEmpty();
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }

        public Key next()
        {
            if (!hasNext())
                throw new NoSuchElementException();
            // Else
            return copy.delMax();
        }
    }



    /**
     * Unit tests the {@code MaxPQ} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args)
    {
        // Variables
        Integer[] a = new Integer[32];
        for (int i = 0; i < a.length; i++)
        {
            a[i] = StdRandom.uniform(20);
        }
        MaxPQ<Integer> pq = new MaxPQ<Integer>(a);


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
            System.out.print(element + " ");
        System.out.println();

        Iterator itr = pq.iterator();
        while (itr.hasNext())
        {
            //  moving cursor to next element
            int i = (Integer)itr.next();

            // getting even elements one by one
            System.out.print(i + " ");
        }
    }
}