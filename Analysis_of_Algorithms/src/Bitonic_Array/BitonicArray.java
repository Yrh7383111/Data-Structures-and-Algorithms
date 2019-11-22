package Bitonic_Array;



public class BitonicArray
{
    //
    private static int ascendingBinarySearch(int[] array, int low, int high, int value)
    {
        if (array.length == 0)
            throw new IllegalArgumentException("Array size cannot be zero");
        // Else
        while (low <= high)
        {
            // Be careful with the data type
            int middle = low + (high - low) / 2;

            if (array[middle] == value)
                return middle;                                          // Return the index of the value in the array
            else if (array[middle] < value)
                low = middle + 1;
            else
                high = middle - 1;
        }
        // If not found in the array
        return -1;
    }

    //
    private static int descendingBinarySearch(int[] array, int low, int high, int value)
    {
        if (array.length == 0)
            throw new IllegalArgumentException("Array size cannot be zero");
        // Else
        while (low <= high)
        {
            // Be careful with the data type
            int middle = low + (high - low) / 2;

            if (array[middle] == value)
                return middle;                                          // Return the index of the value in the array
            else if (array[middle] < value)
                high = middle - 1;
            else
                low = middle + 1;
        }
        // If not found in the array
        return -1;
    }

    //
    public static int findBitonicPoint(int[] array, int arrayLength, int firstIndex, int lastIndex)
    {
        if (array.length == 0)
            throw new IllegalArgumentException("Array size cannot be zero");
        // Else
        while (firstIndex <= lastIndex)
        {
            int middle = (firstIndex + lastIndex) / 2;

            if (array[middle] > array[middle - 1] && array[middle] > array[middle + 1])
                return middle;
            else {
                if (array[middle] > array[middle - 1] && array[middle] < array[middle + 1])
                    firstIndex =  middle;
                else
                    lastIndex = middle;
            }
        }
        // Assume the input follows the bitonic rules
        return -1;
    }

    //
    public static int searchBitonic(int[] array, int arrayLength, int bitonicPointIndex, int value)
    {
        if (value > array[bitonicPointIndex])
            return -1;
        else if (value == array[bitonicPointIndex])
            return bitonicPointIndex;
        else {
            int indexOne = ascendingBinarySearch(array, 0, bitonicPointIndex - 1, value);
            if (indexOne != -1)
                return indexOne;
            else
                return descendingBinarySearch(array, bitonicPointIndex + 1, arrayLength - 1, value);
        }
    }


    public static void main(String args[])
    {
        // Variables
        int[] array = {-8, 1, 2, 3, 4, 5, -2, -3};
        int value = -2;
        int arraySize = array.length;
        int firstIndex = 0;
        int lastIndex = arraySize - 1;

        // Operations
        int bitonicPoint = findBitonicPoint(array, arraySize, firstIndex, lastIndex);
        int result = searchBitonic(array, arraySize, bitonicPoint, value);

        if (result == -1)
            System.out.println("Element not found");
        else
            System.out.println("Element found at index " + result);
    }
}