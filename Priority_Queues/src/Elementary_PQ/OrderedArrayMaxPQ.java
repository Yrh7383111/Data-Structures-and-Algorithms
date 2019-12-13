package Elementary_PQ;



public class OrderedArrayMaxPQ<Key extends Comparable<Key>>
{
    // Variables
    private Key[] pq;                                                               // Elements
    private int n;                                                                  // Number of elements


    // Operations

    // Constructor
    public OrderedArrayMaxPQ(int capacity)                                          // Set initial size of heap to hold size elements
    {
        pq = (Key[]) (new Comparable[capacity]);
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

    public Key delMax()
    {
        return pq[--n];
    }

    public void insert(Key key)
    {
        // Variable
        int i = n-1;


        // Operations
        while (i >= 0 && less(key, pq[i]))
        {
            pq[i+1] = pq[i];
            i--;
        }
        pq[i+1] = key;
        n++;
    }


    /***************************************************************************
     * Helper functions.
     ***************************************************************************/
    private boolean less(Key v, Key w)
    {
        return v.compareTo(w) < 0;
    }



    /***************************************************************************
     * Test routine.
     ***************************************************************************/
    public static void main(String[] args)
    {
        // Variable
        OrderedArrayMaxPQ<String> pq = new OrderedArrayMaxPQ<String>(10);


        // Operations
        pq.insert("this");
        pq.insert("is");
        pq.insert("a");
        pq.insert("test");

        while (!pq.isEmpty())
            System.out.print(pq.delMax() + " ");
    }
}