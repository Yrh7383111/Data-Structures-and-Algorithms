package Quick_Find;


// Quadratic algorithm
// It takes N*2(square) array accesses to process a sequence of N union commands on N objects.
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

    // Find + Connected?
    // Check if p and q have the same id (2 array accesses)
    public boolean connected(int p, int q)
    {
        return id[p] == id[q];
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
