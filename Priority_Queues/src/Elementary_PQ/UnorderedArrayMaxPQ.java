package Elementary_PQ;



public class UnorderedArrayMaxPQ<Key extends Comparable<Key>>
{
    // Variable
    private Key[] pq;                                                           // Elements
    private int n;                                                              // Number of elements


    // Operations

    // Constructor
    public UnorderedArrayMaxPQ(int capacity)                                    // Set initial size of heap to hold size elements
    {
        pq = (Key[]) new Comparable[capacity];
        n = 0;
    }

    public boolean isEmpty()
    {
        return n == 0;
    }

    public int size()
    {
        return n;
    }

    public void insert(Key x)
    {
        pq[n++] = x;
    }

    public Key delMax()
    {
        // Variable
        int max = 0;


        // Operations
        for (int i = 1; i < n; i++)
        {
            if (less(max, i))
                max = i;
        }
        exch(max, n-1);

        return pq[--n];
    }


    /***************************************************************************
     * Helper functions.
     ***************************************************************************/
    private boolean less(int i, int j)
    {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j)
    {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }



    /***************************************************************************
     * Test routine.
     ***************************************************************************/
    public static void main(String[] args)
    {
        // Variable
        UnorderedArrayMaxPQ<String> pq = new UnorderedArrayMaxPQ<String>(10);


        // Operations
        pq.insert("this");
        pq.insert("is");
        pq.insert("a");
        pq.insert("test");

        while (!pq.isEmpty())
            System.out.print(pq.delMax() + " ");
    }
}