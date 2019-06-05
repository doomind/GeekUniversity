package Advanced.SpiralArray;

public class Main {
    public static void main(String[] args) {
        spiralArray(5,5);
    }

    private static void spiralArray (int x, int y) {
        int[][] arr = new int[y][x];
        int topEdge = 0;
        int bottomEdge = arr.length - 1;
        int rightEdge = arr[y - 1].length - 1;
        int leftEdge = 0;
        int num = 1;
        int maxNum = (x * y) + 1;

        while (num < maxNum) {
            for (int i = leftEdge; i <= rightEdge && num < maxNum; i++, num++) {
                arr[topEdge][i] = num;
            }
            topEdge++;
            for (int i = topEdge; i <= bottomEdge && num < maxNum; i++, num++) {
                arr[i][rightEdge] = num;
            }
            rightEdge--;
            for (int i = rightEdge; i >= leftEdge && num < maxNum; i--, num++) {
                arr[bottomEdge][i] = num;
            }
            bottomEdge--;
            for (int i = bottomEdge; i >= topEdge && num < maxNum; i--, num++) {
                arr[i][leftEdge] = num;
            }
            leftEdge++;
        }
        printArray(arr);
    }

    public static void printArray (int[][] arr) {
        int maxNum = maxOfArray(arr);
        int maxLen = Integer.toString(maxNum).length();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                String out = Integer.toString(arr[i][j]);
                while (out.length() < maxLen) {
                    out = (" " + out);
                }
                System.out.print(out + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int maxOfArray(int[][] arr) {
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                result = arr[i][j] > result ? arr[i][j] : result;
            }
        }
        return result;
    }

}