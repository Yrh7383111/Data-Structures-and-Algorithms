/*
3-SUM in quadratic time. Design an algorithm for the 3-SUM problem that takes time proportional to n^2in the worst case.
You may assume that you can sort the n integers in time proportional to n^2 or better.
*/

package Three_Sum;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class ThreeSum
{
    public List<List<Integer>> threeSum(int[] num)
    {
        // Sort the array in ascending order (up)
        Arrays.sort(num);

        // 2D Linked List the store the result
        List<List<Integer>> result = new LinkedList<>();


        // Operations
        // Only need to loop for (length - 2) times
        for (int i = 0; i < num.length - 2 ; i++)
        {
            // If the value is the same as previous, then go to the next iteration
            if (i == 0 || i > 0 && num[i] != num[i - 1])
            {
                // Variables
                int low = i+1;                                                  // Head pointer
                int high = num.length-1;                                        // Tail pointer
                int sum = 0 - num[i];                                           // Trick

                // Operations
                while (low < high)
                {
                    if (num[low] + num[high] == sum)
                    {
                        // Combine three values as a 1D list, and add it to the 2D list
                        result.add(Arrays.asList(num[i], num[low], num[high]));

                        // Deal with repetitive values
                        // If the value is the same as previous, check next value (after)
                        while (low < high && num[low] == num[low + 1])
                            low++;

                        // If the value is the same as previous, check next value (before)
                        while (low < high && num[high] == num[high-1])
                            high--;

                        low++;
                        high--;
                    }

                    // If the sum is less than sum, increase the lower limit
                    else if (num[low] + num[high] < sum)
                        low++;

                    // If the sum is larger than sum, decrease the upper limit
                    else
                        high--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args)
    {
        int[] array = new int[]{-1, 0, 1, 2, -1, -4};
        ThreeSum object = new ThreeSum();

        List<List<Integer>> result = object.threeSum(array);
        for (List<Integer> row : result) {
            for (Integer col : row)
            {
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }
}