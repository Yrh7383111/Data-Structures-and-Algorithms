package Queues;


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;



public class ResizingArrayQueue<Item> implements Iterable<Item>
{
    // Queue variables
    private Item[] s;                                               // Array is between 25% and 100% full.
    private int first;                                              // Index of first element of queue
    private int last;                                               // Index of next available slot
    private int n;                                                  // Size of the queue


    // Operations

    // Constructor
    // Initialize the array with default size: 1
    public ResizingArrayQueue()
    {
        s = (Item[]) new Object[2];
        first = 0;
        last = 0;
        n = 0;
    }

    public boolean isEmpty()
    {
        return n == 0;
    }

    // Size of the queue
    public int size()
    {
        return n;
    }

    // To resize the array
    private void resize(int capacity)
    {
        Item[] copy = (Item[]) new Object[capacity];

        for (int i = 0; i < n; i++)
            copy[i] = s[(first + i) % s.length];                    // There might some empty space in the beginning and at the end

        s = copy;                                                   // Assign the new array to be the array of "ResizingArrayStackOfStrings" class
        first = 0;                                                  // "first" starts from index 0
        last  = n;                                                  // "last" ends at index n
    }

    // Add new item at s[last].
    // If the array is full,
    // create a new array of twice the size, and copy items.
    public void enqueue(Item item)                                  // Inserting first N items takes time proportional to N (~3N)
    {
        if (n == s.length)                                          // Double size of array if necessary and recopy to front of array
            resize(2 * s.length);

        s[last++] = item;                                           // last++ - 1. Index into array   2. Increment n (in order)

        if (last == s.length)                                       // Wrap-around
            last = 0;

        n++;
    }

    // Removes and returns the item on this queue that was least recently added. (Remove item from s[first].)
    // Halve size of the array, when the array is one-quarter full.
    public Item dequeue()
    {
        if (isEmpty())
            throw new NoSuchElementException("Queue underflow");
        // Else
        Item item = s[first];
        s[first] = null;                                            // Allow null items to be inserted.
                                                                    // Avoid "loitering": garbage collector can reclaim memory only if no outstanding references
        n--;
        first++;

        if (first == s.length)                                      // Wrap-around
            first = 0;

        if (n > 0 && n == s.length / 4)
            resize(s.length / 2);

        return item;
    }

    // Return (but does not remove) the item most recently added to this stack.
    public Item peek()
    {
        if (isEmpty())
            throw new NoSuchElementException("Stack underflow");
        // Else
        return s[first];
    }

    public Iterator<Item> iterator()
    {
        return new ArrayIterator();
    }

    // An iterator, doesn't implement remove() since it's optional
    private class ArrayIterator implements Iterator<Item>
    {
        // Start from array index 0
        private int i = 0;


        // To check if i less than the size of the queue
        public boolean hasNext()
        {
            return i < n;                                           // n - size of the queue
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }

        public Item next()
        {
            if (!hasNext())
                throw new NoSuchElementException();
            // Else
            Item item = s[(i + first) % s.length];                  // It's possible that some indexes at the beginning of or at the end of an array are empty
            i++;
            return item;
        }
    }


    public static void main(String[] args)
    {
        ResizingArrayQueue<String> queue = new ResizingArrayQueue<String>();
        while (!StdIn.isEmpty())
        {
            String item = StdIn.readString();
            if (!item.equals("-")) queue.enqueue(item);
            else if (!queue.isEmpty()) StdOut.print(queue.dequeue() + " ");
        }
        StdOut.println("(" + queue.size() + " left on queue)");
    }
}