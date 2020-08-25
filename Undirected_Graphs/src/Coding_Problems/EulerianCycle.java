package Coding_Problems;


import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;



public class EulerianCycle
{
    // Private
    private Stack<Integer> cycle;


    // Edge - examine the same only once
    private static class Edge
    {
        private final int v;
        private final int w;
        private boolean isUsed;

        public Edge(int v, int w)
        {
            this.v = v;
            this.w = w;
            this.isUsed = false;
        }

        public int other(int vertex)
        {
            if (vertex == v)
            {
                return w;
            }
            else {
                return v;
            }
        }
    }


    // Public
    public EulerianCycle(Graph graph)
    {
        // Eulerian Cycle - every vertex
        for (int v = 0; v < graph.V(); v++)
        {
            if (graph.degree(v) % 2 != 0)
            {
                return;
            }
        }
            
            
        // Queue array - store all the edges
        Queue<Edge>[] adj = (Queue<Edge>[]) new Queue[graph.V()];
        for (int v = 0; v < graph.V(); v++)
        {
            adj[v] = new Queue<Edge>();
        }

        // Enqueue all the edges
        for (int v = 0; v < graph.V(); v++)
        {
            // Prevent self-loop
            int selfLoop = 0;

            for (int w : graph.adj(v))
            {
                // Only store the same edge once
                if (v == w)
                {
                    if (selfLoop % 2 == 0)

                    selfLoop++;
                }
                else if (v < w)
                {
                    Edge edge = new Edge(v, w);
                    adj[v].enqueue(edge);
                    adj[w].enqueue(edge);
                }
            }
        }


        // Starting vertex
        int s = 0;
        // Find a random vertex that is not isolated
        for (int v = 0; v < graph.V(); v++)
        {
            if (graph.degree(v) > 0)
            {
                s = v;
            }
        }

        Stack<Integer> stack = new Stack<Integer>();
        stack.push(s);


        // Main greedy algorithm - DFS
        cycle = new Stack<Integer>();

        while (!stack.isEmpty())
        {
            int v = stack.pop();

            while (!adj[v].isEmpty())
            {
                Edge edge = adj[v].dequeue();
                if (!edge.isUsed)
                {
                    edge.isUsed = true;
                    stack.push(v);
                    v = edge.other(v);
                }
            }

            cycle.push(v);
        }
    }

    public boolean hasEulerianCycle()
    {
        return cycle != null;
    }

    public Iterable<Integer> cycle()
    {
        return cycle;
    }



    // Entry point
    public static void main(String[] args)
    {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        EulerianCycle ec = new EulerianCycle(graph);
        for (int v : ec.cycle())
        {
            System.out.println(v + " ");
        }
    }
}