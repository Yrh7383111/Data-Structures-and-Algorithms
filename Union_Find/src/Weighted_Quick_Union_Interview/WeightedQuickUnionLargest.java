package Weighted_Quick_Union_Interview;



// Linear-time algorithm for M union-find ops on N objects
// In practice, WQUPC is linear.

// M union-find operations on a set of N objects -
// Worst-case time: (N + M lg*N)
public class WeightedQuickUnionLargest
{
    // Data Structure
    // Integer array id[] of length N.
    // Interpretation: id[i] is parent of i.
    // Root of i is id[id[id[...id[i]...]]].
    private int[] id;

    private int[] size;                                                     // size[i] = number of sites in subtree rooted at i
    private int[] largestNumber;                                            // To keep track of the largest number within the connected component
    private int count;                                                      // number of components

    // Constructor
    // Set id of each object to itself (N array accesses)
    public WeightedQuickUnionLargest(int N)
    {
        // Validate that N is a valid index
        if (N < 0)
        {
            throw new IllegalArgumentException("Site numbers should be more than 0");
        }
        // Else
        count = N;
        id = new int[N];
        size = new int[N];
        largestNumber = new int[N];

        for (int i = 0; i < N; i++)
        {
            id[i] = i;
            size[i] = 1;
            largestNumber[i] = i;
        }
    }

    // Return the number of components
    public int count()
    {
        return count;
    }

    // Connected?
    // Check if p and q have same root (depth of p and q array accesses)
    public boolean connected(int p, int q)
    {
        // Validate that p and q is a valid index
        if (p < 0 || p >= count || q < 0 || q >= count)
        {
            throw new IllegalArgumentException("Error! Please check the statement: 0 <= p < n and 0 <= q < n");
        }
        // Else
        return root(p) == root(q);
    }

    // Find (too expensive)
    // Chase parent pointers until reach root (depth of p array accesses)
    private int root(int p)
    {
        // Validate that p is a valid index
        if (p < 0 || p >= count)
        {
            throw new IllegalArgumentException("Error! Please check the statement: 0 <= p < n");
        }
        // Else
        while (p != id[p])
        {
            id[p] = id[id[p]];                                          // Path compression -
                                                                        // Simpler one-pass variant (path halving):
                                                                        // Make every other node in path point to its grandparent
            p = id[p];
        }
        return p;
    }

    // Union
    // Change root of p to point to root of q (depth of p and q array accesses) - Only one value changed
    public void union(int p, int q)
    {
        // Validate that p and q is a valid index
        if (p < 0 || p >= count || q < 0 || q >= count)
        {
            throw new IllegalArgumentException("Error! Please check the statement: 0 <= p < n");
        }
        // Else
        int rootP = root(p);
        int rootQ = root(q);

        if (rootP == rootQ) return;

        // Make smaller root point to larger one
        if (size[rootP] < size[rootQ])
        {
            id[rootP] = rootQ;
            size[rootQ] += size[rootP];

            // Size of "rootP" is smaller, but has a larger number inside
            if (largestNumber[rootP] > largestNumber[rootQ])
            {
                largestNumber[rootQ] = largestNumber[rootP];
            }
        }
        else {
            id[rootQ] = rootP;
            size[rootP] += size[rootQ];

            // Size of "rootQ" is smaller, but has a larger number inside
            if (largestNumber[rootQ] > largestNumber[rootP])
            {
                largestNumber[rootP] = largestNumber[rootQ];
            }
        }
    }

    // Find the largest number within the connected component
    public int find(int p)
    {
        // Validate that p is a valid index
        if (p < 0 || p >= count)
        {
            throw new IllegalArgumentException("Error! Please check the statement: 0 <= p < n");
        }
        // Else
        return largestNumber[root(p)];
    }
}