package Weighted_Quick_Union_Interview;



public class SuccessorWithDelete
{
    // Data Structure
    private boolean[] data;                                                     // data[i] == false if removed
    private WeightedQuickUnionLargest weightedQuickUnion;                       // To find the largest unremoved element
    private int N;

    // Constructor
    public SuccessorWithDelete(int N)
    {
        this.N = N;                                                             // this = object (SuccessorWithDelete)
        data = new boolean[N];

        weightedQuickUnion = new WeightedQuickUnionLargest(N);

        for (int i = 0; i < N; ++i)
            data[i] = true;
    }

    // Remove an element
    public void remove(int x)
    {
        data[x] = false;

        if (x > 0 && !data[x-1])                                                // To link nodes which have already been removed
                                                                                // keep track of the largest number with connected components
        {
            weightedQuickUnion.union(x, x - 1);
        }
        if (x < N - 1 && !data[x+1])                                            // To link nodes which have already been removed -
                                                                                // keep track of the largest number with connected components
        {
            weightedQuickUnion.union(x, x + 1);
        }
    }

    // Find the successor in an array
    public int successor(int x)
    {
        if (data[x])                                                            // If data[x] is not removed yet, then return
        {
            return x;
        }
        else {
            int result = weightedQuickUnion.find(x) + 1;                        // If data[x] has already been removed, then set the successor to the next number

            if (result >= N)                                                    // If the result even goes beyond than the largest number in the array, return error
            {
               System.out.println("No successor can be found");
               return -1;
            }
            else {
                return result;
            }
        }
    }
}