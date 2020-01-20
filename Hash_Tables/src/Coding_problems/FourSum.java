package Coding_problems;


import java.util.ArrayList;
import java.util.Arrays;



public class FourSum
{
    public static ArrayList<ArrayList<Integer>> fourSum(int[] nums, int target)
    {
        // Variables
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        int n = nums.length;


        // Operations
        if (n < 4)
        {
            return result;
        }
        // Else
        // Sort the array
        Arrays.sort(nums);

        // Index 0 - (n - 3)
        for (int i = 0; i < n - 3; i++)
        {
            // Target too small, no point in continuing
            if (target <= 0 && nums[i] > 0)
                break;
            // nums[i] has become too large, no point in continuing
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target)
                break;
            // nums[i] is so small, even the largest elements cannot help reach the sum
            if (nums[i] + nums[n - 3] + nums[n - 2] + nums[n - 1] < target)
                continue;
            // Skip duplicates
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            // Index 1 - (n - 2)
            /* Now explore further */
            for (int j = i + 1; j < n - 2; j++)
            {
                /* Some more pruning */
                // nums[j] has become too large, no point in continuing
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target)
                    break;
                // nums[j] is so small, even the largest elements cannot help reach the sum
                if (nums[i] + nums[j] + nums[n - 2] + nums[n - 1] < target)
                    continue;
                // skip duplicates
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue;

                // Two sum problem - Use two pointers to solve (O(n) time)
                /* Explore the solution space */
                int left = j + 1, right = n - 1;
                while (left < right)
                {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target)
                    {
                        result.add(new ArrayList<Integer>(Arrays.asList(nums[i], nums[j], nums[left], nums[right])));

                        // Deal with duplicate values
                        int last_left = nums[left], last_right = nums[right];
                        while (left < right && nums[left] == last_left)
                            left++;
                        while (left < right && nums[right] == last_right)
                            right--;
                    }
                    else if (sum < target)
                    {
                        left++;
                    }
                    else {
                        right--;
                    }
                }
            }
        }
        return result;
    }


    public static void main(String[] args)
    {
        int[] nums = new int[]{1, 0, -1, 0, -2, 2};
        ArrayList<ArrayList<Integer>> list = fourSum(nums, 0);


        for (ArrayList<Integer> list1 : list)
        {
            for (Integer element : list1)
            {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }
}