package Coding_problems;



public class TaxicabNumber
{
    public static void printTaxicab2(int N)
    {
        // Variables
        int i = 1;
        int count = 0;


        // Operations
        while (count < N)
        {
            int int_count = 0;

            // Try all possible pairs (j, k) whose
            // cube sums can be i.
            for (int j = 1; j <= Math.pow(i, 1.0/3); j++)
            {
                for (int k = j + 1; k <= Math.pow(i, 1.0 / 3); k++)
                {
                    if (j * j * j + k * k * k == i)
                        int_count++;
                }
            }

            if (int_count == 2)
            {
                count++;
                System.out.println(count + " " + i);
            }

            i++;
        }
    }


    public static void main(String[] args)
    {
        int N = 5;
        printTaxicab2(N);
    }
}