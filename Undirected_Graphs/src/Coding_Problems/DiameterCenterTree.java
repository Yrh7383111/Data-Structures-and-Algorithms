package Coding_Problems;


import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.Graph;

import java.util.ArrayList;



public class DiameterCenterTree
{
    public static void main(String[] args)
    {
        Graph graph = new Graph(7);

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);


//        // Diameter: design a linear-time algorithm to find the longest simple path in the graph
//        BreadthFirstPaths bfs = new BreadthFirstPaths(graph, 0);
//        int maxDisTo = 0;
//        int vertex = 0;
//
//        for (int v = 0; v < graph.V(); v++)
//        {
//            if (bfs.distTo(v) > maxDisTo)
//            {
//                vertex = v;
//                maxDisTo = bfs.distTo(v);
//            }
//        }
//
//        for (int v : bfs.pathTo(vertex))
//            System.out.print(v + " ");


        // Center: design a linear-time algorithm to find a vertex such that
        // its maximum distance from any other vertex is minimized
        BreadthFirstPaths bfs = new BreadthFirstPaths(graph, 0);
        int maxDis = 0;
        int vertex = 0;

        for (int v = 0; v < graph.V(); v++)
        {
            if (bfs.distTo(v) > maxDis)
            {
                vertex = v;
                maxDis = bfs.distTo(v);
            }
        }

        ArrayList<Integer> middle = new ArrayList<Integer>();
        if (maxDis % 2 ==0)
        {
           middle.add(maxDis / 2);
        }
        else {
            middle.add(maxDis / 2);
            middle.add(maxDis / 2 + 1);
        }

        int count = 0;
        ArrayList<Integer> center = new ArrayList<Integer>();
        for (int v : bfs.pathTo(vertex))
        {
            if (middle.size() == 1)
            {
                if (middle.get(0) == count)
                {
                    center.add(v);
                }
            }
            else {
                if (middle.get(0) == count || middle.get(1) == count)
                {
                    center.add(v);
                }
            }
            count++;
        }

        for (int v : center)
        {
            System.out.println(v);
        }
    }
}
