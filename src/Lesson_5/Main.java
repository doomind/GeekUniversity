package Lesson_5;

public class Main {
    public static void main(String[] args) {
        final int size = 10000000;
        final int numberOfThreads = 8;
        float[] arr = new float[size];
        long start;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
        float[] arr1 = arr.clone();
        float[] arr2 = arr.clone();

        start = System.currentTimeMillis();
        calcArrOne(arr1);
        long execTime1 = System.currentTimeMillis() - start;
        System.out.println("Serial calculating ended via " + execTime1 + "ms");

        start = System.currentTimeMillis();
        calcArrTwo(arr2, numberOfThreads);
        long execTime2 = System.currentTimeMillis() - start;
        System.out.println("Parallel calculating ended via " + execTime2 + "ms");

    }

    private static float[] calcArrOne(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + (float)i / 5) * Math.cos(0.2f + (float)i / 5) * Math.cos(0.4f + (float)i / 2));
        }

        return arr;
    }

    private static float[] calcArrTwo(float[] arr, int parts) {
        int partSize = arr.length / parts;
        int lastPartSize = arr.length % parts;
        if (lastPartSize > 0) {
            parts++;
        }

        Calc[] calcs = new Calc[parts];
        float[][] partsOfArr = new float[parts][partSize];

        for (int i = 0; i < parts; i++) {
            if (lastPartSize > 0 && i == parts - 1) {
                System.arraycopy(arr, i * partSize, partsOfArr[i], 0, lastPartSize - 1);
                calcs[i] = new Calc(partsOfArr[i]);
                calcs[i].start();
            } else {
                System.arraycopy(arr, i * partSize, partsOfArr[i], 0, partSize - 1);
                calcs[i] = new Calc(partsOfArr[i]);
                calcs[i].start();
            }
        }

        for (int i = 0; i < parts; i++) {
            try {
                calcs[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            partsOfArr[i] = calcs[i].getResult();
            if (lastPartSize > 0 && i == parts - 1) {
                System.arraycopy(partsOfArr[i], 0, arr, i * partSize, lastPartSize - 1);
            } else {
                System.arraycopy(partsOfArr[i], 0, arr, i * partSize, partSize - 1);
            }
        }

        return arr;
    }
}
