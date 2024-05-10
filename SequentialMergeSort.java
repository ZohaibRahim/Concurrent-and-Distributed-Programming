package Project_CPSC222;

import java.util.Arrays;
import java.util.Comparator;

//Implements sequential merge sort algorithm.
public class SequentialMergeSort implements Runnable {
    private final Comparator<Integer> comparator;

    private final int[] temp;
    private final int[] data;

    @Override
    public void run() {
        sort(0, data.length); // Sort the partition
    }

    public SequentialMergeSort(int[] data, int[] temp, Comparator<Integer> comparator) {
        // Check if the data array is null
        if (data == null) {
            throw new IllegalArgumentException("Data array cannot be null");
        }
        // Assertion to ensure temp array size is sufficient
        assert (2 * temp.length >= data.length);
        this.comparator = comparator;
        this.data = data;
        this.temp = temp;
    }

    public SequentialMergeSort(int[] partition) {
        // Check if the partition array is null
        if (partition == null) {
            throw new IllegalArgumentException("Partition array cannot be null");
        }
        this.data = partition;
        this.temp = new int[partition.length];
        this.comparator = Comparator.naturalOrder(); // Default comparator for natural ordering
    }

    public static void sequentialMergeSort(int[] data) {
        sequentialMergeSort(data, Comparator.naturalOrder());
    }

    public static void sequentialMergeSort(int[] data, Comparator<Integer> comparator) {
        final int middle = (data.length + 1) / 2;// Calculate the middle index
        int[] temp = Arrays.copyOfRange(data, 0, middle); // Create a temporary array
        sequentialMergeSort(data, temp, comparator); // Perform sequential merge sort
    }

    public static void sequentialMergeSort(int[] data, int[] temp, Comparator<Integer> comparator) {
        SequentialMergeSort mergeSort = new SequentialMergeSort(data, temp, comparator); // Create an instance of SequentialMergeSort
        mergeSort.sort(0, data.length);// Perform sorting
    }

    public void sort(int begin, int end) {
        // Base case: if the size of the array is less than 2, it is already sorted
        if (end - begin < 2) return;

        final int middle = begin + (end - begin) / 2; // Calculate the middle index
        sort(begin, middle); // Sort the left half
        sort(middle, end); // Sort the right half
        merge(begin, middle, end); // Merge the sorted halves
    }

    public void merge(int begin, int middle, int end) {
        final int len1 = middle - begin; // Length of the left and right sub-arrays
        System.arraycopy(data, begin, temp, 0, len1); // Copy the left sub-array into the temporary array
        int i = 0, j = middle;
        int k = begin;
        // Merge the sorted sub-arrays
        while (i < len1 && j < end) {
            // Compare elements using the comparator
            if (comparator.compare(temp[i], data[j]) <= 0) {
                data[k++] = temp[i++];
            } else {
                data[k++] = data[j++];
            }
        }
        // Copy remaining elements from the left sub-array
        while (i < len1) {
            data[k++] = temp[i++];
        }
    }
}

