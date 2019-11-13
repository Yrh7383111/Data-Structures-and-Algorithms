package Social_Network_Connectivity;


import java.util.Arrays;



// Linear-time algorithm for M union-find ops on N objects
// In practice, WQUPC is linear.

// M union-find operations on a set of N objects -
// Worst-case time: (N + M lgN)
public class WeightedQuickUnion
{
    // Data Structure
    // Integer array id[] of length N.
    // Interpretation: id[i] is parent of i.
    // Root of i is id[id[id[...id[i]...]]].
    private int[] id;

    private int[] size;                                                     // size[i] = number of sites in subtree rooted at i
    private int count;                                                      // number of components

    // Constructor
    // Set id of each object to itself (N array accesses)
    public WeightedQuickUnion(int N)
    {
        count = N;
        id = new int[N];
        size = new int[N];

        for (int i = 0; i < N; i++)
        {
            id[i] = i;
            size[i] = 1;
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
        return find(p) == find(q);
    }

    // Validate that p is a valid index
    private void validate(int p)
    {
        int n = id.length;
        if (p < 0 || p >= n)
        {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));
        }
    }

    // Find (too expensive)
    // Chase parent pointers until reach root (depth of p array accesses)
    private int find(int p)
    {
        validate(p);

        while (p != id[p])
        {
            id[p] = id[id[p]];                              // Path compression -
            // Simpler one-pass variant (path halving):
            // Make every other node in path point to its grandparent
            p = id[p];
        }
        return p;
    }

    // Union
    // Change root of p to point to root of q (depth of p and q array accesses) - Only one value changed
    public void union(int p, int q, String timestamp)
    {
        int rootP = find(p);
        int rootQ = find(q);

        if (rootP == rootQ) return;

        // Make smaller root point to larger one
        if (size[rootP] < size[rootQ])
        {
            id[rootP] = rootQ;
            size[rootQ] += size[rootP];
            if (size[rootQ] == count)
            {
                System.out.println("All Members are connected at Timestamp"+ timestamp);
            }
        }
        else {
            id[rootQ] = rootP;
            size[rootP] += size[rootQ];
            if (size[rootP] == count)
            {
                System.out.println("All Members are connected at Timestamp"+ timestamp);
            }
        }
    }

    public void printId()
    {
        for (int i = 0; i < id.length; i++)
        {
            System.out.print(id[i]);
        }
    }

    public void printSize()
    {
        for (int i = 0; i < size.length; i++)
        {
            System.out.print(size[i]);
        }
    }
}
