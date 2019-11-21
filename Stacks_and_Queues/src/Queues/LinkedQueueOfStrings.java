// Examine the item least recently added.
// FIFO - First in last out

package Queues;


import java.util.NoSuchElementException;



public class LinkedQueueOfStrings
{
    // Queue variables
    private Node first;                                             // Beginning of queue
    private Node last;                                              // End of queue
    private int n;                                                  // Number of elements on queue


    // Operations

    // Constructor
    // Initialize an empty queue
    public LinkedQueueOfStrings()
    {
        first = null;
        last  = null;
        n = 0;
    }

    // Node class declaration
    private class Node
    {
        // Node variables
        private String item;                                        // Data
        private Node next;                                          // Pointer
    }

    public boolean isEmpty()
    { return first == null; }

    // Size of the queue
    public int size()
    {
        return n;
    }

    // Add the item to this queue.
    public void enqueue(String item)
    {
        Node oldLast = last;                                        // Remember the address of the "last" pointer
        last = new Node();
        last.item = item;                                           // Set up the data
        last.next = null;                                           // Ask the pointer to point to null

        if (isEmpty())
            first = last;
        else
            oldLast.next = last;                                    // Connect the newly added node with the node that was most recently added.

        n++;                                                        // Increase the size
    }

    // Remove and return the item on this queue that was least recently added.
    public String dequeue()
    {
        if (isEmpty())
            throw new NoSuchElementException("Queue underflow");
        // Else
        String item = first.item;                                   // Remember the data of the node which will be removed soon
        first = first.next;                                         // Move the "first" pointer to the next one, and Java garbage collector will remove the first node

        if (isEmpty())
            last = null;

        n--;                                                        // Decrease the size
        return item;
    }

    // Return the item least recently added to this queue.
    public String peek()
    {
        if (isEmpty())
            throw new NoSuchElementException("Queue underflow");
        // Else
        return first.item;
    }
}