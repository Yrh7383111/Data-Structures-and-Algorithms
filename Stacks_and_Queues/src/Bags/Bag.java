// Add items to a collection and iterating (when order doesn't matter)

package Bags;


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;



public class Bag<Item> implements Iterable<Item>
{
    // Bag variables
    private Node<Item> first;                                           // Top of bag
    private int n;                                                      // Size of the bag


    // Operations

    // Constructor
    // Initialize an empty bag
    public Bag()
    {
        first = null;
        n = 0;
    }

    // Node class declaration
    private static class Node<Item>
    {
        private Item item;                                              // Data
        private Node<Item> next;                                        // Pointer
    }

    public boolean isEmpty() {
        return first == null;
    }

    // Size of the bag
    public int size() {
        return n;
    }

    // Add the item to this bag.
    public void add(Item item) 
    {
        Node<Item> oldFirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldFirst;
        n++;
    }

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

        // To check if the pointer reaches the end of linked-list based bag
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
            Item item = current.item;
            current = current.next;
            return item;
        }
    }


    public static void main(String[] args)
    {
        Bag<String> bag = new Bag<String>();

        while (!StdIn.isEmpty())
        {
            String item = StdIn.readString();
            bag.add(item);
        }

        StdOut.println("size of bag = " + bag.size());

        for (String s : bag)
        {
            StdOut.println(s);
        }
    }
}
