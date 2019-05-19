package Lesson_5;

public class Main {
    public static void main(String[] args) {
        final int size = 10000000;
        final int numberOfThreads = 4;
        float[] arr = new float[size];
        long start;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }


        start = System.currentTimeMillis();
        float[] arr1 = calcArrOne(arr);
        long execTime1 = System.currentTimeMillis() - start;
        System.out.println("execTime1 = " + execTime1 + "ms");

        start = System.currentTimeMillis();
        float[] arr2 = calcArrTwo(arr, numberOfThreads);
        long execTime2 = System.currentTimeMillis() - start;
        System.out.println("execTime2 = " + execTime2 + "ms");

    }

    private static float[] calcArrOne(float[] arr) {
        float[] result = new float[arr.length];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + (float)i / 5) * Math.cos(0.2f + (float)i / 5) * Math.cos(0.4f + (float)i / 2));
        }

        return result;
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
//                float[] partOfArr = new float[lastPartSize];
                System.arraycopy(arr, i * partSize, partsOfArr[i], 0, lastPartSize - 1);
                System.out.println("Current part size: " + lastPartSize);
                calcs[i] = new Calc(partsOfArr[i]);
                calcs[i].start();
            } else {
//                float[] partOfArr = new float[partSize];
                System.arraycopy(arr, i * partSize, partsOfArr[i], 0, partSize - 1);
                System.out.println("Current part size: " + partSize);
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
