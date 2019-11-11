package Quick_Find;


// M union-find operations on a set of N objects -
// Worst-case time: M N
public class QuickFindUF
{
    // Data Structure
    // Integer array id[] of length N.
    // Interpretation: p and q are connected iff they have the same id.
    private int[] id;

    // Constructor
    // Set id of each object to itself (N array accesses)
    public QuickFindUF(int N)
    {
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
    }

    // Connected?
    // Check if p and q have the same id (2 array accesses)
    public boolean connected(int p, int q)
    {
        return id[p] == id[q];
    }

    // Find
    // Return the id of p (1 array access)
    public int find(int p)
    {
        return id[p];
    }

    // Union (too expensive)
    // Change all entries with id[p] to id[q] (at most 2N + 2 array accesses) - Problem: many values can be changed
    public void union(int p, int q)
    {
        int pid = id[p];
        int qid = id[q];

        for (int i = 0; i < id.length; i++)
        {
            if (id[i] == pid)
            {
                id[i] = qid;
            }
        }
    }
}
