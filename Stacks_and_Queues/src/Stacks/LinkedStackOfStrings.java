package Stacks;


import java.util.NoSuchElementException;


// Trade off: (faster push or pop operations)
// 1. Every operation takes constant time in the worst case.
// 2. Uses extra time and space to deal with the links
public class LinkedStackOfStrings
{
    // Stack variables
    private Node first;                                         // Top of the stack
    private int n;                                              // Size of the stack


    // Operations

    // Constructor
    // Initialize an empty stack
    public LinkedStackOfStrings()
    {
        first = null;
        n = 0;
    }

    // Node class declaration
    private class Node
    {
        // Node variables
        String item;                                            // Data
        Node next;                                              // Pointer
    }

    public boolean isEmpty()
    {
        return first == null;
    }

    // Size of the stack
    public int size()
    {
        return n;
    }

    // Add the item to this stack.
    public void push(String item)
    {
        Node oldFirst = first;                                  // Remember the address of the "first" pointer
        first = new Node();
        first.item = item;                                      // Set up the data
        first.next = oldFirst;                                  // Connect the newly added node with the first node previously
        n++;                                                    // Increase the size
    }

    // Remove and return the item most recently added to this stack.
    public String pop()
    {
        if (isEmpty())
            throw new NoSuchElementException("Stack underflow");
        // Else
        String item = first.item;                               // Remember the data of the node which will be removed soon
        first = first.next;                                     // Move the "first" pointer to the next one, and Java garbage collector will remove the first node
        n--;                                                    // Decrease the size
        return item;
    }

    // Return (but does not remove) the item most recently added to this stack.
    public String peek()
    {
        if (isEmpty())
            throw new NoSuchElementException("Stack underflow");
        // Else
        return first.item;
    }
}