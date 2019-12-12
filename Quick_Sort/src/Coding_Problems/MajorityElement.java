package Coding_Problems;



public class MajorityElement
{
    // Moore’s Voting Algorithm - Find possible element
    public static int findElement(int[] a, int size)
    {
        // Variable
        int majorityIndex = 0;
        int counter = 1;


        // Operations
        for (int i = 1; i < size; i++)
        {
            if (a[majorityIndex] == a[i])
                counter++;
            else
                counter--;

            if (counter == 0)
            {
                majorityIndex = i;
                counter = 1;
            }
        }
        return a[majorityIndex];
    }

    // Check if the possible element satisfies the majority requirement
    public static boolean isMajority(int[] a, int size, int element)
    {
        // Variables
        int counter = 0;


        // Operations
        for (int i = 0; i < size; i++)
        {
            if (element == a[i])
                counter++;
        }

        return counter > (size / 2);
    }

    // Summary
    public static void printMajority(int[] a, int size)
    {
        // Variables
        int result = findElement(a, a.length);
        boolean passMajority = isMajority(a, a.length, result);


        // Operations
        if (passMajority)
            System.out.println(result);
        else
            System.out.println("No Majority Element ");
    }


    public static void main(String[] args)
    {
        // Variables
        int[] a = new int[]{3, 3, 4, 2, 4, 4, 2, 4, 4};


        // Operations
        printMajority(a, a.length);
    }
}