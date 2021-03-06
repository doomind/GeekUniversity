package Advanced.Lesson_5;

public class Main {
    public static void main(String[] args) {
        final int size = 10000000;
        final int numberOfThreads = 20;
        float[] arr = new float[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
        float[] arr1 = arr.clone();
        float[] arr2 = arr.clone();

        arr1 = calcArrOne(arr1);
        System.out.println("------------------------------------");
        arr2 = calcArrTwo(arr2, numberOfThreads);

        boolean arraysEquals = true;
        for (int i = 0; i < size; i++) {
            if (arr1[i] != arr2[i]) {
                arraysEquals = false;
                System.out.println("Different at " + i + " : arr1[" + i + "]=" + arr1[i] + ", arr2[" + i + "]=" + arr2[i] + ".");
                break;
            }
        }
        String msg = arraysEquals ? "Arrays are equals" : "Arrays are not equals";
        System.out.println("------------------------------------");
        System.out.println(msg);
    }

    private static float[] calcArrOne(float[] arr) {
        System.out.println("Serial calculating started.");
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + (float)i / 5) * Math.cos(0.2f + (float)i / 5) * Math.cos(0.4f + (float)i / 2));
        }

        long execTime = System.currentTimeMillis() - startTime;
        System.out.println("Serial calculating ended via " + execTime + "ms");

        return arr;
    }

    private static float[] calcArrTwo(float[] arr, int parts) {
        System.out.println("Parallel calculating started.");
        long startTime = System.currentTimeMillis();
        int partSize = arr.length / parts;
        int lastPartSize = arr.length % parts;
        if (lastPartSize > 0) {
            parts++;
        }

        Calc[] calcs = new Calc[parts];
        float[][] partsOfArr = new float[parts][partSize];

        long startCutting = System.currentTimeMillis();
        for (int i = 0; i < parts; i++) {
            if (lastPartSize > 0 && i == parts - 1) {
                System.arraycopy(arr, i * partSize, partsOfArr[i], 0, lastPartSize);
                calcs[i] = new Calc(partsOfArr[i], i);
                calcs[i].start();
            } else {
                System.arraycopy(arr, i * partSize, partsOfArr[i], 0, partSize);
                calcs[i] = new Calc(partsOfArr[i], i);
                calcs[i].start();
            }
        }
        long cuttingTime = System.currentTimeMillis() - startCutting;
        System.out.println("Cutting ended via " + cuttingTime + "ms");

        long startCalc = System.currentTimeMillis();
        for (int i = 0; i < parts; i++) {
            try {
                calcs[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long calcTime = System.currentTimeMillis() - startCalc;
        System.out.println("Calculating ended via " + calcTime + "ms");

        long startGluing = System.currentTimeMillis();
        for (int i = 0; i < calcs.length; i++) {
            partsOfArr[i] = calcs[i].getResult();
            if (lastPartSize > 0 && i == parts - 1) {
                System.arraycopy(partsOfArr[i], 0, arr, i * partSize, lastPartSize);
            } else {
                System.arraycopy(partsOfArr[i], 0, arr, i * partSize, partSize);
            }
        }
        long gluingTime = System.currentTimeMillis() - startGluing;
        System.out.println("Gluing ended via " + gluingTime + "ms");

        long execTime = System.currentTimeMillis() - startTime;
        System.out.println("Parallel calculating ended via " + execTime + "ms");

        return arr;
    }
}
