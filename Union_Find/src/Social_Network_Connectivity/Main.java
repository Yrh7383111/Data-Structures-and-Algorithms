package Social_Network_Connectivity;


import Weighted_Quick_Union_PC.WeightedQuickUnionUF;

public class Main
{
    public static void main(String[] args)
    {
        WeightedQuickUnion weightedQuickUnion = new WeightedQuickUnion(6);
        weightedQuickUnion.union(1,5, "2019-08-14 18:00:00");
        weightedQuickUnion.union(2,4, "2019-08-14 18:00:01");
        weightedQuickUnion.union(1,3, "2019-08-14 18:00:02");
        weightedQuickUnion.union(5,2, "2019-08-14 18:00:03");
        weightedQuickUnion.union(0,3,"2019-08-14 18:00:04");
        weightedQuickUnion.union(2,1,"2019-08-14 18:00:05");
        weightedQuickUnion.printId();
        System.out.println();
        weightedQuickUnion.printSize();
    }
}


