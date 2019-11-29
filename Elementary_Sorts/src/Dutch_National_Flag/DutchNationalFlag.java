package Dutch_National_Flag;


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;



public class DutchNationalFlag
{
    // Variable
    private String[] array;
    private int n;
    private int counter;


    // Constructor
    public DutchNationalFlag(int capacity)
    {
        this.array = new String[capacity];
        this.n = capacity;
        this.counter = 0;
    }

    public void push(String string)
    {
        array[counter] = string;
        counter++;
    }

    public int size()
    {
        return n;
    }

    public void sort()
    {
        // Variables
        int low = 0;
        int middle = 0;
        int high = size() - 1;


        // Operations
        while (middle <= high)
        {
            switch (array[middle])
            {
                case "Red":
                {
                    swap(array, low, middle);
                    low++;
                    middle++;
                    break;
                }
                case "White":
                {
                    middle++;
                    break;
                }
                case "Blue":
                {
                    swap(array, middle, high);
                    high--;
                    break;
                }
            }
        }
    }

    // Exchange a[i] and a[j]
    public void swap(String[] a, int i, int j)
    {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public String color(int i)
    {
        return array[i];
    }

    public void show()
    {
        for (int i = 0; i < n; i++)
            System.out.print(color(i) + " ");
        System.out.println();
    }


    public static void main(String[] args)
    {
        // Variables
        DutchNationalFlag DFF = new DutchNationalFlag(10);
        String[] array = new String[DFF.size()];
        String[] string = new String[]{"Red", "White", "Blue"};


        // Operations
        for (int i = 0; i < DFF.size(); i++)
        {
            int random = StdRandom.uniform(3);
            DFF.push(string[random]);
        }

        System.out.println("Original array: ");
        DFF.show();

        DFF.sort();
        System.out.println("Sorted array: ");
        DFF.show();
    }
}