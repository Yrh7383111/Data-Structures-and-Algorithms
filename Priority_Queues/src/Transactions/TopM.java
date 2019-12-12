package Transactions;


import edu.princeton.cs.algs4.*;



public class TopM
{

    // Constructor
    private TopM() { }

    /**
     *  Reads a sequence of transactions from standard input; takes a
     *  command-line integer m; prints to standard output the m largest
     *  transactions in descending order.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args)
    {
        // Variables
        int m = Integer.parseInt(args[0]);
        MinPQ<Transaction> pq = new MinPQ<Transaction>(m+1);


        // Operations
        while (StdIn.hasNextLine())
        {
            // Create an entry from the next line and put on the PQ.
            String line = StdIn.readLine();
            Transaction transaction = new Transaction(line);
            pq.insert(transaction);

            // Remove minimum if m+1 entries on the PQ
            if (pq.size() > m)
                pq.delMin();
        }


        // Print entries on PQ in reverse order
        Stack<Transaction> stack = new Stack<Transaction>();

        for (Transaction transaction : pq)
            stack.push(transaction);
        for (Transaction transaction : stack)
            StdOut.println(transaction);
    }
}