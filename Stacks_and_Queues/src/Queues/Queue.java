// Examine the item least recently added.
// FIFO - First in last out

package Queues;


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;



public class Queue<Item> implements Iterable<Item>
{
    // Queue variables
    private Node<Item> first;                                       // Beginning of queue
    private Node<Item> last;                                        // End of queue
    private int n;                                                  // Number of elements on queue


    // Operations

    // Constructor
    // Initialize an empty queue
    public Queue()
    {
        first = null;
        last  = null;
        n = 0;
    }

    // Node class declaration
    private static class Node<Item>
    {
        // Node variables
        private Item item;                                        // Data
        private Node<Item> next;                                  // Pointer
    }

    public boolean isEmpty()
    { return first == null; }

    // Size of the queue
    public int size()
    {
        return n;
    }

    // Add the item to this queue from the last.
    public void enqueue(Item item)
    {
        Node<Item> oldLast = last;                                  // Remember the address of the "last" pointer
        last = new Node<Item>();
        last.item = item;                                           // Set up the data
        last.next = null;                                           // Ask the pointer to point to null

        if (isEmpty())
            first = last;
        else
            oldLast.next = last;                                    // Connect the newly added node with the node that was most recently added.

        n++;                                                        // Increase the size
    }

    // Remove and return the item on this queue that was least recently added.
    public Item dequeue()
    {
        if (isEmpty())
            throw new NoSuchElementException("Queue underflow");
        // Else
        Item item = first.item;                                     // Remember the data of the node which will be removed soon
        first = first.next;                                         // Move the "first" pointer to the next one, and Java garbage collector will remove the first node
        n--;                                                        // Decrease the size

        if (isEmpty())
            last = null;                                            // To avoid loitering

        return item;
    }

    // Return the item least recently added to this queue.
    public Item peek()
    {
        if (isEmpty())
            throw new NoSuchElementException("Queue underflow");
        // Else
        return first.item;
    }

    // Return an iterator that iterates over the items in this queue in FIFO order.
    public Iterator<Item> iterator()
    {
        return new ListIterator(first);
    }

    // An iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item>
    {
        // Pointer to iterate through a linked-list based queue
        private Node<Item> current;


        // Constructor
        public ListIterator(Node<Item> first)
        {
            current = first;
        }

        // To check if the pointer reaches the end of linked-list based queue
        public boolean hasNext()
        {
            return current != null;
        }

        // Optional
        public void remove()
        {
            throw new UnsupportedOperationException();
        }

        // In normal order
        public Item next()
        {
            if (!hasNext())
                throw new NoSuchElementException();
            // Else
            Item item = current.item;                               // Remember the data of the node
            current = current.next;                                 // Move the pointer to the next node
            return item;
        }
    }


    public static void main(String[] args)
    {
        Queue<String> queue = new Queue<String>();

        while (!StdIn.isEmpty())
        {
            String item = StdIn.readString();
            if (!item.equals("-"))
                queue.enqueue(item);
            else if (!queue.isEmpty())
                StdOut.print(queue.dequeue() + " ");
        }
        StdOut.println("(" + queue.size() + " left on queue)");
    }
}