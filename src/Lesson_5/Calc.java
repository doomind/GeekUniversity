package Lesson_5;

class Calc extends Thread {
    private float[] arr;
    private boolean calculated;
    private boolean debug;

    Calc(float[] arr) {
        this.arr = arr;
        this.calculated = false;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        if (debug) {
            System.out.println(Thread.currentThread().toString() + " started. Part size: " + arr.length + " elements.");
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + (float)i / 5) * Math.cos(0.2f + (float)i / 5) * Math.cos(0.4f + (float)i / 2));
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
