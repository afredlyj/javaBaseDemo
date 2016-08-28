package afred.javademo.sort;

/**
 * Created by Afred on 15/7/25.
 */
public class InsertSort {

    private int[] arr;

    public InsertSort(int[] arr) {
        if (null == arr) {
            throw new NullPointerException();
        }

        this.arr = arr;
    }

    public void sort() {
        int[] array = this.arr;
        int length = array.length;
        for (int count = 1; count < length; count++) {
            int index = count - 1;
            int temp = array[count];
            while (index >= 0 && temp < array[index]) {
                array[index + 1] = array[index];
                index--;
            }

            array[index + 1] = temp;
        }

        print(array);
    }

    public void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void main(String[] args) {
        int arr[] = new int[]{3, 8, 9, 0, 11, 34, 54, 23};
        InsertSort sort = new InsertSort(arr);
        sort.sort();

    }


}
