package Sample_Clients;


import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;



public class FrequencyCounter
{
    // Constructor
    private FrequencyCounter() { }


    public static void main(String[] args)
    {
        // Variables
        int distinct = 0, words = 0;
        int minlen = Integer.parseInt(args[0]);
        ST<String, Integer> st = new ST<String, Integer>();


        // Operations

        // Compute frequency counts
        while (!StdIn.isEmpty())
        {
            String key = StdIn.readString();
            if (key.length() < minlen)
                continue;
            // Else
            words++;
            if (st.contains(key))
                st.put(key, st.get(key) + 1);
            else {
                st.put(key, 1);
                distinct++;
            }
        }

        // Find a key with the highest frequency count
        String max = "";
        st.put(max, 0);
        for (String word : st.keys())
        {
            if (st.get(word) > st.get(max))
                max = word;
        }

        StdOut.println(max + " " + st.get(max));
        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);
    }
}