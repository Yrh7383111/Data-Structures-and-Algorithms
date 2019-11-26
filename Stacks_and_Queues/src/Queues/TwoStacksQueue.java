package Queues;


import java.util.Stack;

import java.util.NoSuchElementException;



public class TwoStacksQueue<Item>
{
    // Queue variables
    private Stack<Item> stackEnqueue;
    private Stack<Item> stackDequeue;


    // Operations

    // Constructor
    public TwoStacksQueue()
    {
        stackEnqueue = new Stack<Item>();
        stackDequeue = new Stack<Item>();
    }

    // Size of the queue
    public int size()
    {
        return (stackEnqueue.size() + stackDequeue.size());
    }

    // Add the item to "stackEnqueue"
    public void enqueue(Item item)
    {
        if (item == null)
            throw new IllegalArgumentException();
        // Else
        stackEnqueue.push(item);
    }

    // Remove the item from "stackDequeue"
    public Item dequeue()
    {
        if (size() == 0)
            throw new NoSuchElementException();
        // Else
        if (stackDequeue.size() == 0)
        {
            while (stackEnqueue.size() != 0)
                stackDequeue.push(stackEnqueue.pop());
        }
        return stackDequeue.pop();
    }


    public static void main(String[] args)
    {
        // Variable
        TwoStacksQueue<Integer> twoStacksQueue = new TwoStacksQueue<Integer>();

        for (int i = 0; i < 10; i++)
        {
            twoStacksQueue.enqueue(i);
        }
        for (int i = 0; i < 10; i++)
        {
            System.out.print(twoStacksQueue.dequeue() + " ");
        }
    }
}