package afred.javademo.sort;

/**
 * Created by Afred on 15/7/25.
 */
public class BubbleSort {

    private int[] arr;

    public BubbleSort(int arr[]) {
        if (null == arr) {
            throw new NullPointerException();
        }

        this.arr = arr;
    }

    public void sort() {
        int length = arr.length;
        for (int index = 0; index < length - 1; index++) {
            for (int count = 0; count < length - 1 - index; count++) {
                if (arr[count] > arr[count + 1]) {
                    int temp = arr[count];
                    arr[count] = arr[count + 1];
                    arr[count + 1] = temp;
                }
            }
        }

        print(arr);
    }

    public void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void main(String[] args) {
        int arr[] = new int[]{3, 8, 9, 0, 11, 34, 54, 23};
        BubbleSort sort = new BubbleSort(arr);
        sort.sort();
    }

}
