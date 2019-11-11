package Quick_Union;


// M union-find operations on a set of N objects -
// Worst-case time: M N
public class QuickUnionUF
{
    // Data Structure
    // Integer array id[] of length N.
    // Interpretation: id[i] is parent of i.
    // Root of i is id[id[id[...id[i]...]]].
    private int[] id;

    // Constructor
    // Set id of each object to itself (N array accesses)
    public QuickUnionUF(int N)
    {
        id = new int[N];
        for (int i = 0; i < N; i++)
        {
            id[i] = i;
        }
    }

    // Connected?
    // Check if p and q have same root (depth of p and q array accesses)
    public boolean connected(int p, int q)
    {
        return find(p) == find(q);
    }

    // Find (too expensive)
    // Chase parent pointers until reach root (depth of i array accesses)
    private int find(int p)
    {
        while (p != id[p])
        {
            p = id[p];
        }
        return p;
    }

    // Union
    // Change root of p to point to root of q (depth of p and q array accesses) - Only one value changed
    public void union(int p, int q)
    {
        int rootP = find(p);
        int rootQ = find(q);
        id[rootP] = rootQ;
    }
}