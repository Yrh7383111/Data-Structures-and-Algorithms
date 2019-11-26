package Stacks;


import java.util.Stack;



public class StackMax
{
    // Variables
    private Stack<Double> mainStack;
    private Stack<Double> trackStack;


    // Operations

    // Constructor
    public StackMax()
    {
        mainStack = new Stack<Double>();
        trackStack = new Stack<Double>();
    }

    public void push(double item)
    {
        mainStack.push(item);

        if (mainStack.size() == 0 || trackStack.size() == 0)
        {
            trackStack.push(item);
            return;
        }

        if (item > trackStack.peek())
            trackStack.push(item);
        else
            trackStack.push(trackStack.peek());

    }

    public void pop()
    {
        mainStack.pop();
        trackStack.pop();
    }

    public double getMaximum()
    {
        return trackStack.peek();
    }
}