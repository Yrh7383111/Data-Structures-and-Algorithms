package Quick_Find;

public class Main
{
    public static void main(String[] args)
    {
        QuickFindUF quickFind = new QuickFindUF(10);
        quickFind.union(4, 3);
        quickFind.union(3, 8);
        quickFind.union(6, 5);
        quickFind.union(9, 4);
        quickFind.union(2, 1);
        quickFind.union(5, 0);
        quickFind.union(7, 2);
        quickFind.union(6, 1);

//        System.out.println(quickFind.connected(8, 9));
//        System.out.println(quickFind.connected(5, 0));
        System.out.println(quickFind.connected(0, 7));
    }
}
