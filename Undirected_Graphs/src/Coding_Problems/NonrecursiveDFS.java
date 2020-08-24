package Coding_Problems;


import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;



public class NonrecursiveDFS
{
    // Private
    private final int s;
    private boolean[] marked;
    private int[] edgeTo;


    // Public
    public NonrecursiveDFS(Graph graph, int s)
    {
        this.s = s;
        marked = new boolean[graph.V()];
        edgeTo = new int[graph.V()];


        Iterator<Integer>[] adj = (Iterator<Integer>[]) new Iterator[graph.V()];
        for (int v = 0; v < graph.V(); v++)
        {
            adj[v] = graph.adj(v).iterator();
        }

        // Run dfs
        marked[s] = true;

        Stack<Integer> stack = new Stack<Integer>();
        stack.push(s);

        while (!stack.isEmpty())
        {
            int v = stack.peek();
            if (adj[v].hasNext())
            {
                int w = adj[v].next();
                if (!marked[w])
                {
                    marked[w] = true;
                    edgeTo[w] = v;
                    stack.push(w);
                }
            }
            else {
                stack.pop();
            }
        }
    }

    public boolean hasPathTo(int v)
    {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v)
    {
        if (!hasPathTo(v))
            return null;
        // Else
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x])
        {
            path.push(x);
        }
        path.push(s);

        return path;
    }



    // Main entry
    public static void main(String[] args)
    {
        Graph graph = new Graph(13);

        graph.addEdge(0, 5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(3, 4);
        graph.addEdge(4, 6);
        graph.addEdge(0, 6);
        graph.addEdge(4, 5);
        graph.addEdge(3, 5);

        NonrecursiveDFS dfs = new NonrecursiveDFS(graph, 0);
        for (int v = 0; v < graph.V(); v++)
        {
            if (dfs.hasPathTo(v))
            {
                StdOut.printf("%d to %d:  ", 0, v);
                for (int x : dfs.pathTo(v))
                {
                    if (x == 0)
                        StdOut.print(x);
                    else {
                        StdOut.print("-" + x);
                    }
                }
                StdOut.println();
            }
            else {
                StdOut.printf("%d to %d:  not connected\n", 0, v);
            }
        }
    }
}
