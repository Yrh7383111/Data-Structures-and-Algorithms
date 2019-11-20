package Stacks;



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

    public int size()                                           // Size of the stack
    {
        return n;
    }

    public void push(String item)
    {
        Node oldFirst = first;                                  // Remember the address of the "first" pointer
        first = new Node();
        first.item = item;                                      // Set up the data
        first.next = oldFirst;                                  // Connect the newly added node with the first node previously
        n++;                                                    // Increase the size
    }
    public String pop()
    {
        String item = first.item;                               // Remember the data of the node which will be removed soon
        first = first.next;                                     // Move the "first" pointer to the next one, and Java garbage collector will remove the first node
        n--;                                                    // Decrease the size
        return item;
    }
}
