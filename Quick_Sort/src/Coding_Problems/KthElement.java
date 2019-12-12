package Coding_Problems;


import java.util.Arrays;



public class KthElement
{
    public static int findKthElement(int[] a, int sizeA, int[] b, int sizeB, int k)
    {
        if (k < 1 || k > (sizeA + sizeB))
            return -1;
        else if (sizeA > sizeB)
            return findKthElement(b, sizeB, a, sizeA, k);
        else if (sizeA == 0)
            return b[k - 1];
        else if (k == 1)
            return Math.min(a[0], b[0]);
        // Divide and conquer
        else {
            // Variables
            int i = Math.min(sizeA, k / 2);
            int j = Math.min(sizeB, k / 2);


            // Operations
            if (a[i - 1] > b[j - 1])
            {
                int[] temp = Arrays.copyOfRange(b, j, sizeB);
                return findKthElement(a, sizeA, temp, (sizeB - j), (k - j));
            }
            else {
                int[] temp = Arrays.copyOfRange(a, i, sizeA);
                return findKthElement(temp, (sizeA - i), b, sizeB, (k - i));
            }
        }
    }


    public static void main(String[] args)
    {
        int[] a = { 2, 3, 6, 7, 9 };
        int[] b = { 1, 4, 8, 10 };
        int sizeA = a.length;
        int sizeB = b.length;
        int k = 7;

        int result = findKthElement(a, sizeA, b, sizeB, k);
        if (result == -1)
            System.out.println("Invalid query");
        else
            System.out.println(result);
    }
}