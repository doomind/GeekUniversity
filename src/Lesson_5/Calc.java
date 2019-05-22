package Lesson_5;

class Calc extends Thread {
    private float[] arr;
    private int partNumber;
    private boolean calculated;
    private boolean debug;

    Calc(float[] arr) {
        this.arr = arr;
        this.partNumber = 0;
        this.calculated = false;
        this.debug = false;
    }

    Calc(float[] arr, int partNumber) {
        this.arr = arr;
        this.partNumber = partNumber;
        this.calculated = false;
        this.debug = false;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        if (debug) {
            System.out.println(Thread.currentThread().toString() + " started. Part size: " + arr.length + " elements.");
        }
        for (int i = 0; i < arr.length; i++) {
            int iCorrected = i + arr.length * partNumber;
            arr[i] = (float)(arr[i] * Math.sin(0.2f + (float)iCorrected / 5) * Math.cos(0.2f + (float)iCorrected / 5) * Math.cos(0.4f + (float)iCorrected / 2));
        }
        calculated = true;
        if (debug) {
            System.out.println(Thread.currentThread().toString() + " ended via " + (System.currentTimeMillis() - start) + "ms");
        }
    }

    float[] getResult() {
        return arr;
    }

    public boolean isCalculated() {
        return calculated;
    }

    public void debugEnable(boolean debug) {
        this.debug = debug;
    }
}
