package Project_CPSC222;

import java.util.Comparator;
import java.util.concurrent.*;

public class ParallelStepByStepRepresentation {
    private static final int sequentialThreshold = 2; // Threshold for sequential merge sort
    private final Comparator<Integer> comparator;
    private final ForkJoinPool forkJoinPool; // Using ForkJoinPool for parallel execution

    public ParallelStepByStepRepresentation(Comparator<Integer> comparator) {
        this.comparator = comparator;
        int parallelism = Runtime.getRuntime().availableProcessors();
        forkJoinPool = new ForkJoinPool(parallelism);
    }

    public void sort(int[] arr) {
        int[] result = new int[arr.length];
        System.out.println("Original Array: " + printArray(arr));
        long startTime = System.currentTimeMillis();
        parallelMergeSort task = new parallelMergeSort(arr, result, 0, arr.length - 1, startTime);
        forkJoinPool.invoke(task);
        System.out.println("Sorted Array: " + printArray(arr));
    }

    private class parallelMergeSort extends RecursiveAction {
        private final int[] arr;
        private final int[] result;
        private final int low;
        private final int high;
        private final long startTime;

        parallelMergeSort(int[] arr, int[] result, int low, int high, long startTime) {
            this.arr = arr;
            this.result = result;
            this.low = low;
            this.high = high;
            this.startTime = startTime;
        }

        @Override
        protected void compute() {
            if (high - low < sequentialThreshold) {
                sequentialMergeSort(arr, result, low, high,startTime);
            } else {
                int mid = low + (high - low) / 2;
                parallelMergeSort leftTask = new parallelMergeSort(arr, result, low, mid, startTime);
                parallelMergeSort rightTask = new parallelMergeSort(arr, result, mid + 1, high, startTime);
                invokeAll(leftTask, rightTask);
                merge(arr, result, low, mid, high, startTime);
            }
        }
    }

    private void sequentialMergeSort(int[] arr, int[] result, int low, int high, long startTime) {
        if (low < high) {
            int mid = low + (high - low) / 2;
            sequentialMergeSort(arr, result, low, mid, startTime);
            sequentialMergeSort(arr, result, mid + 1, high, startTime);
            merge(arr, result, low, mid, high, startTime);
        }
    }

    private void merge(int[] arr, int[] result, int low, int mid, int high, long startTime) {
        int left = low;
        int right = mid + 1;
        int index = low;

        while (left <= mid && right <= high) {
            if (comparator.compare(arr[left], arr[right]) <= 0) {
                result[index++] = arr[left++];
            } else {
                result[index++] = arr[right++];
            }
        }

        while (left <= mid) {
            result[index++] = arr[left++];
        }

        while (right <= high) {
            result[index++] = arr[right++];
        }

        for (index = low; index <= high; index++) {
            arr[index] = result[index];
        }

        System.out.println("Time = " + (System.currentTimeMillis() - startTime) + " ms, Thread " + (Thread.currentThread().getId() % 10 + 1) + ": Merged sub-arrays [" + low + ", " + mid + "] and [" + (mid + 1) + ", " + high + "]");
        System.out.println("Time = " + (System.currentTimeMillis() - startTime) + " ms, Thread " + (Thread.currentThread().getId() % 10 + 1) + ": Merged sub-arrays [" + low + ", " + high + "]: " + printArray(arr, low, high));
    }

    private String printArray(int[] arr, int low, int high) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = low; i <= high; i++) {
            sb.append(arr[i]);
            if (i < high) {
                sb.append(" ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private String printArray(int[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(" ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] arr = {7, 6, 9, 8, 1, 2, 4, 3, 5};
        ParallelStepByStepRepresentation sorter = new ParallelStepByStepRepresentation(Comparator.naturalOrder());
        sorter.sort(arr);
    }
}
