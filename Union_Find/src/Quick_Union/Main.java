package Quick_Union;


public class Main
{
    public static void main(String[] args)
    {
        QuickUnionUF quickUnion = new QuickUnionUF(10);
        quickUnion.union(4, 3);
        quickUnion.union(3, 8);
        quickUnion.union(6, 5);
        quickUnion.union(9, 4);
        quickUnion.union(2, 1);
        quickUnion.union(5, 0);
        quickUnion.union(7, 2);
        quickUnion.union(6, 1);
        quickUnion.union(7, 3);


//        System.out.println(quickUnion.connected(8, 9));
//        System.out.println(quickUnion.connected(5, 4));
        System.out.println(quickUnion.connected(6, 8));
    }
}
