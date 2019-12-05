package Collinear_Points;


import java.util.Arrays;



public class FastCollinearPoints
{
    // Variable
    private LineSegment[] lineSegments;


    // Operations

    // Finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points)
    {
        // Corner case
        if (points == null)
            throw new  IllegalArgumentException();


        // Variables
        Point[] sortedPoints = points.clone();
        Arrays.sort(sortedPoints);


        // Corner cases
        if (checkPoints(sortedPoints) || checkDuplicates(sortedPoints))
            throw new  IllegalArgumentException();
        // Else


    }

    // Number of line segments
    public int numberOfSegments()
    {
        return lineSegments.length;
    }

    // Line segments
    public LineSegment[] segments()
    {
        return lineSegments.clone();
    }

    private boolean checkPoints(Point[] points)
    {
        for (Point point : points)
        {
            if (point == null)
                return true;
        }
        return false;
    }

    private boolean checkDuplicates(Point[] points)
    {
        for (int i = 0; i < points.length; i++)
        {
            for (int j = i + 1; j < points.length; j++)
            {
                if (points[i].compareTo(points[j]) == 0)
                    return true;
            }
        }
        return false;
    }
}