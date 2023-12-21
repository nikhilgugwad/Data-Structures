public class MinElementArray {
    public static void main(String[] args) {
        int arr[] = { 5, 8, 17, 2, 93 };
        System.out.println(minArray(arr));
    }

    static int minArray(int arr[]) {
        int min = arr[0];
        for (int i=0;i<arr.length;i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;

    }
}

