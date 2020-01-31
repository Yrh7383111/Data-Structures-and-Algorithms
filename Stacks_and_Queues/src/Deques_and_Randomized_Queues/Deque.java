package Deques_and_Randomized_Queues;


import java.util.Iterator;
import java.util.NoSuchElementException;



// Deque implementation must support each deque operation (including construction) in constant worst-case time.
public class Deque<Item> implements Iterable<Item>
{
    // Dequeue variables
    private Node<Item> first;                                       // Beginning of queue
    private Node<Item> last;                                        // End of queue
    private int n;                                                  // Number of elements on deque


    // construct an empty deque
    public Deque()
    {
        this.first = null;
        this.last = null;
        this.n = 0;
    }

    // Doubly linked-list
    private static class Node<Item>
    {
        // Node variables
        private Item item;                                          // Data
        private Node<Item> next;                                    // Pointer to the next node
        private Node<Item> previous;                                // Pointer to the previous node

        // Node constructor
        public Node(Item item)
        {
            this.item = item;
            this.next = null;
            this.previous = null;
        }
    }

    // is the deque empty?
    public boolean isEmpty()
    {
        return this.n == 0;
    }

    // return the number of items on the deque
    public int size()
    {
        return this.n;
    }

    // add the item to the front
    public void addFirst(Item item)
    {
        if (item == null)
            throw new IllegalArgumentException();
        // Else
        if (isEmpty())
        {
            this.first = new Node<Item>(item);
            this.last = first;
        }
        else {
            Node<Item> node = new Node<Item>(item);
            node.next = this.first;
            this.first.previous = node;
            this.first = node;
        }

        this.n++;
    }

    // add the item to the back
    public void addLast(Item item)
    {
        if (item == null)
            throw new IllegalArgumentException();
        // Else
        if (isEmpty())
        {
            this.last = new Node<Item>(item);
            this.first = this.last;
        }
        else {
            Node<Item> node = new Node<Item>(item);
            node.previous = this.last;
            this.last.next = node;
            this.last = node;
        }

        this.n++;
    }

    // remove and return the item from the front
    public Item removeFirst()
    {
        if (isEmpty())
            throw new NoSuchElementException();
        // Else
        Item item = this.first.item;
        this.first = this.first.next;

        if (this.first == null)
            this.last = null;                                            // To avoid loitering
        else {
            this.first.previous = null;
        }

        this.n--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast()
    {
        if (isEmpty())
            throw new NoSuchElementException();
        // Else
        Item item = this.last.item;
        this.last = this.last.previous;

        if (this.last == null)
            this.first = null;                                            // To avoid loitering
        else {
            this.last.next = null;
        }

        this.n--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator()
    {
        return new ListIterator(this.first);
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
            return this.current != null;
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
            Item item = this.current.item;                               // Remember the data of the node
            this.current = this.current.next;                                 // Move the pointer to the next node
            return item;
        }
    }


    // unit testing (required)
    public static void main(String[] args)
    {
        Deque<Integer> deque = new Deque<Integer>();


        for (int i = 0; i < 10; i++)
        {
            deque.addFirst(i);
            deque.addLast(i * 2);
        }
//        for (Integer element : deque)
//        {
//            System.out.print(element + " ");
//        }
//        Node current = deque.first;
//        do {
//            System.out.print(current.item + " ");
//            current = current.next;
//        }
//        while (current != null);

        while (!deque.isEmpty())
        {
            System.out.print(deque.removeFirst() + " ");
        }
    }
}