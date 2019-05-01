package Lesson_2.Exceptions;

public class Main {

    public static void main(String[] args) {
        String[][] arr1 = new String[1][4];
        String[][] arr2 = {
                {"1","2","3","4"},
                {"5","6","7","8"},
                {"0","nine","8","7"},
                {"3","4","5","6"}
        };
        String[][] arr3 = {
                {"1","2","3","4"},
                {"5","6","7","8"},
                {"0","9","8","7"},
                {"3","4","5","6"}
        };

        try {
            summArr(arr1);
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        }

        try {
            summArr(arr2);
        } catch (MyArrayDataException e){
            e.printStackTrace();
        }

        try {
            int sum = summArr(arr3);
            String msg = "Сумма элементов массива равна " + sum;
            System.out.println(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int summArr(String[][] arr) throws MyArraySizeException, MyArrayDataException {
        int result = 0;

        if (arr.length != 4) {
            throw new MyArraySizeException();
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length != 4) {
                throw new MyArraySizeException();
            }
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    result += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }
        return result;
    }
}
