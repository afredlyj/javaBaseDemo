package afred.demo.sort;

/**
 * Created by Afred on 15/7/25.
 */
public class QuickSort {

    private int[] arr;

    public QuickSort(int arr[]) {
        this.arr = arr;
    }

    private int partition(int arr[], int left, int right) {
        int pivot = arr[left];
        while (left < right) {
            while (left < right && arr[right] >= pivot) {
                right--;
            }

            if (left < right) {
                arr[left++] = arr[right];
            }

            while (left < right && arr[left] <= pivot) {
                left++;
            }

            if (left < right) {
                arr[right--] = arr[left];
            }
        }

        arr[left] = pivot;
        return left;
    }

    public void sort(int[] arr, int left, int right) {
        if (left < right) {
            int pivotpos = partition(arr, left, right);
            sort(arr, left, pivotpos - 1);
            sort(arr, pivotpos + 1, right);
        }

    }

    public void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void main(String[] args) {
        int arr[] = new int[]{3, 8, 9, 0, 11, 34, 54, 23};
        QuickSort sort = new QuickSort(arr);
        sort.sort(arr, 0, arr.length  - 1);
        sort.print(arr);

    }
}
