import java.util.Random;

public class Main {
    private static int[] arr;
    static int incrementF, incrementS;

    public static void main(String[] args) {
        /*int[] arr = {14, 22, 2313, 24, 5, 1, 1242, 3, 41436, 5462, 1334, 2351, 783, 12, 220, 45,
                234, 4, 523, 435, 2345, 2, 6346, 32, 5, 1, 35, 34, 53, 6, 64, 75, 73, 56, 23, 46, 13, 45, 3146,
                436, 542745, 7, 568, 9, 76, 9879, 678, 357, 798798798, 324, 124, 657, 76924, 56365, 487, 13, 457,
                678, 9125, 45, 8, 9076, 5367, 1631235, 3568, 357, 4387563, 85, 68, 67, 842673, 6, 3457568, 356,
                835, 72, 45, 134, 6, 34, 7835, 68, 578935, 72, 654, 4, 6756, 38};
        */

        int[] arr = new int[8000000];

        sort(arr);
    }

    static void sort(int[] ar) {
        arr = new int[ar.length];
        arr = ar;

        long st = System.currentTimeMillis();
        Random random = new Random();
        System.out.println("Было");
        for (int i = 0; arr.length > i; i++) {
            arr[i] = random.nextInt(1000);
            //System.out.print(arr[i] + " ");
        }

        incrementF = arr.length / 2;
        incrementS = incrementF / 2;

        System.out.println(incrementF);
        System.out.println(incrementS);

        ThreadT thread1 = new ThreadT(incrementF, 1);
        thread1.start();
        ThreadT thread2 = new ThreadT(incrementS, 2);
        thread2.start();

        try {
            thread2.join();
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(System.currentTimeMillis()-st);

       /* System.out.println();
        System.out.println("Стало");
        for (int i = 0; arr.length > i; i++) {
            System.out.print(arr[i] + " ");
        }*/
    }

    private static void insertionSort(int[] arr, int startIndex, int increment) {
        for (int i = startIndex; i < arr.length - 1; i = i + increment) {
            for (int j = Math.min(i + increment, arr.length - 1); j - increment >= 0; j = j - increment) {
                if (arr[j - increment] > arr[j]) {
                    int tmp = arr[j];
                    arr[j] = arr[j - increment];
                    arr[j - increment] = tmp;
                } else {
                    break;
                }
            }
        }
    }

    public static class ThreadT extends Thread {
        int increment;
        int id;

        public ThreadT(int increment, int id) {
            this.increment = increment;
            this.id = id;
        }

        public void run() {
            while (increment >= 1) {
                System.out.println(id + " " + increment);
                for (int startIndex = 0; startIndex < increment; startIndex++) {
                    insertionSort(arr, startIndex, increment);
                }
                increment = increment >> 2;
            }
        }
    }
}