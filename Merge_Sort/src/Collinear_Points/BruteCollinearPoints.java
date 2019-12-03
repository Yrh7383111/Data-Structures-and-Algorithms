package Collinear_Points;


import java.util.ArrayList;



public class BruteCollinearPoints
{
    // Variable
    private LineSegment[] lineSegments;


    // Find all line segments containing 4 points
    public BruteCollinearPoints(Point[] points)
    {
        // Corner cases
        if (points == null || checkPoints(points) || checkDuplicates(points))
            throw new  IllegalArgumentException();
        // Else
        ArrayList<LineSegment> arrayList = new ArrayList<LineSegment>();


        for (int p = 0; p < points.length - 3; p++)
        {
            for (int q = p + 1; q < points.length - 2; q++)
            {
                for (int r = q + 1; r < points.length - 1; r++)
                {
                    for (int s = r + 1; s < points.length; s++)
                    {
                        arrayList.add(new LineSegment(points[p], points[s]));
                    }
                }
            }
        }

        lineSegments = arrayList.toArray(new LineSegment[numberOfSegments()]);
    }

    // Number of line segments
    public int numberOfSegments()
    {
        return lineSegments.length;
    }

    // Line segments
    public LineSegment[] segments()
    {
        return lineSegments;
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