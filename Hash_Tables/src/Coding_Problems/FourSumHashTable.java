package Coding_Problems;


import java.util.HashSet;

public class FourSumHashTable
{
    public static boolean fourSum(int[] a)
    {
        // Variables
        int n = a.length;
        assert n >= 4;


        // Operations
        HashSet<Integer> s = new HashSet<Integer>();
        for (int i = 0; i < n; i++)
        {
            for (int j = i + 1; j < n; j++)
            {
                int tmp = a[i] + a[j];

                if (s.contains(tmp))
                {
                    System.out.println("Sum is: " + tmp);
                    System.out.println(i);
                    System.out.println(j);
                    return true;
                }
                else {
                    s.add(tmp);
                }
            }
        }
        return false;
    }



    public static void main(String[] args)
    {
        int[] nums = new int[]{1, 0, -1, 0, -2, 2};


        fourSum(nums);
    }
}
