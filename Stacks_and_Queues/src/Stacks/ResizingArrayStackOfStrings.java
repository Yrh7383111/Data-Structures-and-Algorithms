package Stacks;


import java.util.NoSuchElementException;


// Trade off: (less wasted space)
// 1. Every operation takes constant amortized time.
// 2. Less wasted space
public class ResizingArrayStackOfStrings
{
    // Stack variables
    private String[] s;                                              // Array is between 25% and 100% full.
    private int n;                                                   // Size of the stack


    // Operations

    // Constructor
    // Initialize the array with default size: 1
    public ResizingArrayStackOfStrings()
    {
        s = new String[1];
        n = 0;
    }

    public boolean isEmpty()
    {
        return n == 0;
    }

    // Size of the stack
    public int size()
    {
        return n;
    }

    // To resize the array
    private void resize(int capacity)
    {
        String[] copy = new String[capacity];

        for (int i = 0; i < n; i++)                                 // Copy all the data from old array to the new array
            copy[i] = s[i];
        s = copy;                                                   // Assign the new array to be the array of "ResizingArrayStackOfStrings" class
    }

    // Add the item to this stack.
    // If the array is full,
    // create a new array of twice the size, and copy items.
    public void push(String item)                                   // Inserting first N items takes time proportional to N (~3N)
    {
        if (n == s.length)
            resize(2 * s.length);
        s[n++] = item;                                              // n++ - 1. Index into array   2. Increment n (in order)
    }

    // Remove and return the item most recently added to this stack.
    // Halve size of the array, when the array is one-quarter full.
    public String pop()
    {
        if (isEmpty())
            throw new NoSuchElementException("Stack underflow");
        // Else
        String item = s[--n];                                       // --n - 1. Decrement n   2. Index into array (in order)
        s[n] = null;                                                // Allow null items to be inserted.
                                                                    // Avoid "loitering": garbage collector can reclaim memory only if no outstanding references
        if (n > 0 && n == s.length/4)
            resize(s.length/2);
        return item;
    }

    // Return (but does not remove) the item most recently added to this stack.
    public String peek()
    {
        if (isEmpty())
            throw new NoSuchElementException("Stack underflow");
        // Else
        return s[n-1];
    }
}