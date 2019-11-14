package Weighted_Quick_Union_Interview;


public class Main
{
    public static void main(String[] args)
    {
        // To find the largest number within the connected component
//        WeightedQuickUnionLargest weightedQuickUnion = new WeightedQuickUnionLargest(10);
//        weightedQuickUnion.union(4, 3);
//        weightedQuickUnion.union(3, 8);
//        weightedQuickUnion.union(6, 5);
//        weightedQuickUnion.union(9, 4);
//        weightedQuickUnion.union(2, 1);
//        weightedQuickUnion.union(5, 0);
//        weightedQuickUnion.union(7, 2);
//        weightedQuickUnion.union(6, 1);
//
//        System.out.println(weightedQuickUnion.find(3));



        // To accomplish Successor with Delete
        SuccessorWithDelete test = new SuccessorWithDelete(10);
        test.remove(2);
        System.out.println(test.successor(2) == 3);
        test.remove(3);
        System.out.println(test.successor(2) == 4);
        System.out.println(test.successor(8) == 8);
        test.remove(8);
        System.out.println(test.successor(8) == 9);
        test.remove(9);
        System.out.println(test.successor(8) == -1);
    }
}
