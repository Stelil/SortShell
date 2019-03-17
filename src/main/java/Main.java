public class Main {
    private static int[] arr = {14, 22, 2313, 24, 5, 1, 1242, 3, 41436, 5462, 1334, 2351, 783, 12, 220, 45,
            234, 4, 523, 435, 2345, 2, 6346, 32, 5, 1, 35, 34, 53, 6, 64, 75, 73, 56, 23, 46, 13, 45, 3146,
            436, 542745, 7, 568, 9, 76, 9879, 678, 357, 798798798, 324, 124, 657, 76924, 56365, 487, 13, 457,
            678, 9125, 45, 8, 9076, 5367, 1631235, 3568, 357, 4387563, 85, 68, 67, 842673, 6, 3457568, 356,
            835, 72, 45, 134, 6, 34, 7835, 68, 578935, 72, 654, 4, 6756, 38};
    static int incrementF, incrementS;

    public static void main(String[] args) {
        System.out.println("Было");
        for (int i = 0; arr.length > i; i++) {
            System.out.print(arr[i] + " ");
        }
        incrementF = arr.length / 2;
        incrementS = incrementF / 2;

        ThreadFirst thread1 = new ThreadFirst();
        thread1.start();
        ThreadSecond thread2 = new ThreadSecond();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("Стало");
        for (int i = 0; arr.length > i; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    private static void insertionSort(int[] arr, int startIndex, int increment) {
        for (int i = startIndex; i < arr.length - 1; i = i + increment) {
            for (int j = Math.min(i + increment, arr.length - 1); j - increment >= 0; j = j - increment) {
                if (arr[j - increment] < arr[j]) {
                    int tmp = arr[j];
                    arr[j] = arr[j - increment];
                    arr[j - increment] = tmp;
                } else {
                    break;
                }
            }
        }
    }

    public static class ThreadFirst extends Thread {
        public void run() {
            System.out.println();
            System.out.println("F1 " + incrementF);
            while (incrementF >= 1) {
                System.out.println("F " + incrementF);
                for (int startIndex = 0; startIndex < incrementF; startIndex++) {
                    insertionSort(arr, startIndex, incrementF);
                }
                incrementF = incrementS / 2;
            }
        }
    }

    public static class ThreadSecond extends Thread {
        public void run() {
            System.out.println("S1 " + incrementS);
            while (incrementS >= 1) {
                for (int startIndex = 0; startIndex < incrementS; startIndex++) {
                    insertionSort(arr, startIndex, incrementS);
                }
                incrementS = incrementF / 2;
            }
        }
    }

}
