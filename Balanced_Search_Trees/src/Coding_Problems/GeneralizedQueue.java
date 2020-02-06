package Coding_Problems;


import edu.princeton.cs.algs4.RedBlackBST;



public class GeneralizedQueue<Item>
{
    // Variables
    private int n;
    private RedBlackBST<Integer, Item> GQueue;


    // Functions
    public GeneralizedQueue()
    {
        this.n = 0;
        this.GQueue = new RedBlackBST<Integer, Item>();
    }

    public void addBack(Item item)
    {
        GQueue.put(n++, item);
    }

    public void removeFront()
    {
        GQueue.deleteMin();
    }

    public Item element(int index)
    {
        return GQueue.get(GQueue.rank(index));
    }

    public void removeElement(int index)
    {
        GQueue.delete(GQueue.rank(index));
    }


    public static void main(String[] args)
    {

    }
}