package Queues;


import java.util.NoSuchElementException;



public class ResizingArrayQueueOfStrings
{
    // Queue variables
    private String[] s;                                             // Array is between 25% and 100% full.
    private int first;                                              // Index of first element of queue
    private int last;                                               // Index of next available slot
    private int n;                                                  // Size of the stack


    // Operations

    // Constructor
    // Initialize the array with default size: 1
    public ResizingArrayQueueOfStrings()
    {
        s = new String[2];
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
        String[] copy = new String[capacity];

        for (int i = 0; i < n; i++)
            copy[i] = s[(first + i) % s.length];                    // There might some empty space in the beginning and at the end

        s = copy;                                                   // Assign the new array to be the array of "ResizingArrayStackOfStrings" class
        first = 0;                                                  // "first" starts from index 0
        last  = n;                                                  // "last" ends at index n
    }

    // Add new item at s[last].
    // If the array is full,
    // create a new array of twice the size, and copy items.
    public void enqueue(String item)                                // Inserting first N items takes time proportional to N (~3N)
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
    public String pop()
    {
        if (isEmpty())
            throw new NoSuchElementException("Stack underflow");
        // Else
        String item = s[--n];                                       // --n - 1. Decrement n   2. Index into array (in order)
        s[n] = null;                                                // Allow null items to be inserted.
                                                                    // Avoid "loitering": garbage collector can reclaim memory only if no outstanding references
        if (n > 0 && n == s.length / 4)
            resize(s.length / 2);
        
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