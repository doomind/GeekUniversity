package Lesson_5;

public class Calc extends Thread {
    private float[] arr;
    private boolean calculated;

    Calc(float[] arr) {
        this.arr = arr;
        this.calculated = false;
    }

    @Override
    public void run() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + (float)i / 5) * Math.cos(0.2f + (float)i / 5) * Math.cos(0.4f + (float)i / 2));
        }
        calculated = true;
    }

    float[] getResult() {
        return arr;
    }

    public boolean isCalculated() {
        return calculated;
    }
}
