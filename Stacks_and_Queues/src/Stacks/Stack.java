// Examine the item most recently added.
// LIFO - Last in first out

package Stacks;


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;



// Trade off: (faster push or pop operations)
// 1. Every operation takes constant time in the worst case.
// 2. Uses extra time and space to deal with the links
public class Stack<Item> implements Iterable<Item>
{
    // Stack variables
    private Node<Item> first;                                       // Top of the stack
    private int n;                                                  // Size of the stack


    // Operations

    // Constructor
    // Initialize an empty stack
    public Stack()
    {
        first = null;
        n = 0;
    }

    // Node class declaration
    private static class Node<Item>
    {
        // Node variables
        private Item item;                                          // Data
        private Node<Item> next;                                    // Pointer
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
    public void push(Item item)
    {
        Node<Item> oldFirst = first;                                // Remember the address of the "first" pointer
        first = new Node<Item>();
        first.item = item;                                          // Set up the data
        first.next = oldFirst;                                      // Connect the newly added node with the node that was most recently added.
        n++;                                                        // Increase the size
    }

    // Remove and return the item most recently added to this stack.
    public Item pop()
    {
        if (isEmpty())
            throw new NoSuchElementException("Stack underflow");
        // Else
        Item item = first.item;                                     // Remember the data of the node which will be removed soon
        first = first.next;                                         // Move the "first" pointer to the next one, and Java garbage collector will remove the first node
        n--;                                                        // Decrease the size
        return item;
    }

    // Return (but does not remove) the item most recently added to this stack.
    public Item peek()
    {
        if (isEmpty())
            throw new NoSuchElementException("Stack underflow");
        // Else
        return first.item;
    }

    // Return an iterator to this stack that iterates through the items in LIFO order.
    public Iterator<Item> iterator()
    {
        return new ListIterator(first);
    }

    // An iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item>
    {
        // Pointer to iterate through a linked-list based stack
        private Node<Item> current;


        // Constructor
        public ListIterator(Node<Item> first)
        {
            current = first;
        }

        // To check if the pointer reaches the end of linked-list based stack
        public boolean hasNext()
        {
            return current != null;
        }

        // Optional
        public void remove()
        {
            throw new UnsupportedOperationException();
        }

        // In reverse order
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
        Stack<String> stack = new Stack<String>();

        while (!StdIn.isEmpty())
        {
            String item = StdIn.readString();
            if (!item.equals("-"))
                stack.push(item);
            else if (!stack.isEmpty())
                StdOut.print(stack.pop() + " ");
        }
        StdOut.println("(" + stack.size() + " left on stack)");
    }
}