import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr1 = generateRandomArray();
        int[] arr2 = Arrays.copyOf(arr1, arr1.length);
        int[] arr3 = Arrays.copyOf(arr1, arr1.length);
        checkSortTime(arr1, arr2, arr3);
    }

    private static int[] generateRandomArray() {
        java.util.Random random = new java.util.Random();
        int[] arr = new int[100_000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100_000) + 100_000;
        }
        return arr;
    }

    private static void swapElements(int[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    private static void sortBubble(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    private static void sortSelection(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    private static void sortInsertion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }

    }

    private static void checkSortTime(int[] arr1, int[] arr2, int[] arr3) {
        long start1 = System.currentTimeMillis();
        sortBubble(arr1);
        System.out.println("Пузырьковая сортировка ");
        System.out.println(System.currentTimeMillis() - start1);
        long start2 = System.currentTimeMillis();
        sortSelection(arr2);
        System.out.println("Сортировка выбором");
        System.out.println(System.currentTimeMillis() - start2);
        long start3 = System.currentTimeMillis();
        sortInsertion(arr3);
        System.out.println("Сортировка вставкой");
        System.out.println(System.currentTimeMillis() - start3);
    }
}
